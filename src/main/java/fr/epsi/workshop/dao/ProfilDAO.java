package fr.epsi.workshop.dao;


import fr.epsi.workshop.models.Profil;
import fr.epsi.workshop.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public class ProfilDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${get.utilisateur}")
    private String requestUtilisateurById;

    @Value("${get.all.utilisateurs}")
    private String requestGetAllUtilisateurs;

    @Value("${update.profil}")
    private String requestUpdateProfil;

    @Value("${exist.pseudo}")
    private String requestExistPseudo;

    @Value("${delete.utilisateur}")
    private String requestUtilisateurToDelete;

    @Value("${insert.profil}")
    private String requestInsertProfil;

    public Profil createProfil(Profil profil) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(requestInsertProfil,
                new MapSqlParameterSource()
                        .addValue("niveauEtude", profil.getNiveauEtude())
                        .addValue("description", profil.getDescription())
                        .addValue("competences", profil.getCompetences())
                        .addValue("profil_id", profil.getUtilisateur_id()),
                keyHolder
        );
        profil.setId(keyHolder.getKey().longValue());
        return profil;
    }

    public Profil getProfilByUtilisateurId(Long id) {
        return jdbcTemplate.queryForObject(requestUtilisateurById, new MapSqlParameterSource().addValue("id", id),
                (rs, rownum) -> Profil.builder()
                        .id(rs.getLong("PROFIL_ID"))
                        .niveauEtude(rs.getString("PROFIL_NIVEAUETUDE"))
                        .description(rs.getString("PROFIL_DESCRIPTION")).build());
    }

    public Profil updateProfile(Long id, Profil profil) {
        jdbcTemplate.update(requestUpdateProfil,  new MapSqlParameterSource()
                .addValue("niveauEtude", profil.getNiveauEtude())
                .addValue("description", profil.getDescription())
                .addValue("competences", profil.getCompetences())
                .addValue("profil_id", profil.getUtilisateur_id())
                .addValue("id", id);
        return profil;

    }
}

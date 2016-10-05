package fr.epsi.workshop.dao;

import fr.epsi.workshop.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@PropertySource("classpath:db/sql/utilisateurs.properties")
public class UtilisateurDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${get.utilisateur}")
    private String requestUtilisateurById;

    @Value("${get.all.utilisateurs}")
    private String requestGetAllUtilisateurs;

    @Value("${update.utilisateur}")
    private String requestUpdateUtilisateur;

    @Value("${exist.pseudo}")
    private String requestExistPseudo;

    @Value("${delete.utilisateur}")
    private String requestUtilisateurToDelete;

    @Value("${insert.utilisateur}")
    private String requestInsertUtilisateur;

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(requestInsertUtilisateur,
                new MapSqlParameterSource()
                    .addValue("nom", utilisateur.getNom())
                    .addValue("prenom", utilisateur.getPrenom())
                    .addValue("pseudo", utilisateur.getPseudo())
                    .addValue("adresse", utilisateur.getAdresse())
                    .addValue("codePostal", utilisateur.getCodePostal())
                    .addValue("ville", utilisateur.getVille())
                    .addValue("telephone", utilisateur.getTelephone())
                    .addValue("email", utilisateur.getEmail()),
                keyHolder
        );
        utilisateur.setId(keyHolder.getKey().longValue());
        return utilisateur;
    }

    public boolean existPseudo(Utilisateur utilisateur) {
        Integer count = jdbcTemplate.queryForObject(requestExistPseudo, new MapSqlParameterSource()
                        .addValue("pseudo", utilisateur.getPseudo()),
                Integer.class);
        return count.intValue() > 0 ? true : false;
    }

    public Utilisateur getUtilisateurById(Long id) {
        return jdbcTemplate.queryForObject(requestUtilisateurById, new MapSqlParameterSource().addValue("id", id),
                (rs, rownum) -> Utilisateur.builder()
                        .id(rs.getLong("UTILISATEUR_ID"))
                        .nom(rs.getString("UTILISATEUR_NOM"))
                        .pseudo(rs.getString("UTILISATEUR_PSEUDO"))
                        .prenom(rs.getString("UTILISATEUR_PRENOM"))
                        .adresse(rs.getString("UTILISATEUR_ADRESSE"))
                        .codePostal(rs.getInt("UTILISATEUR_CODEPOSTAL"))
                        .ville(rs.getString("UTILISATEUR_VILLE"))
                        .email(rs.getString("UTILISATEUR_EMAIL"))
                        .telephone(rs.getInt("UTILISATEUR_TELEPHONE")).build());
    }

    public List<Utilisateur> getAllUtilisateurs(){
        return jdbcTemplate.query(requestGetAllUtilisateurs,
                (rs, rownum) -> Utilisateur.builder()
                        .id(rs.getLong("UTILISATEUR_ID"))
                        .pseudo(rs.getString("UTILISATEUR_PSEUDO"))
                        .codePostal(rs.getInt("UTILISATEUR_CODEPOSTAL"))
                        .ville(rs.getString("UTILISATEUR_VILLE"))
                        .email(rs.getString("UTILISATEUR_EMAIL")).build());
    }

    public Utilisateur updateUtilisateur(Long id,Utilisateur utilisateur) {
        jdbcTemplate.update(requestUpdateUtilisateur,  new MapSqlParameterSource()
                .addValue("nom", utilisateur.getNom())
                .addValue("prenom", utilisateur.getPrenom())
                .addValue("adresse", utilisateur.getAdresse())
                .addValue("codePostal", utilisateur.getCodePostal())
                .addValue("ville", utilisateur.getVille())
                .addValue("telephone", utilisateur.getTelephone())
                .addValue("email", utilisateur.getEmail())
                .addValue("id", id));

        return utilisateur;
    }

    public void deleteChannel(Long id)  {
        jdbcTemplate.update(requestUtilisateurToDelete, new MapSqlParameterSource().addValue("id", id));
    }

}

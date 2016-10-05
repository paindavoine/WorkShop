package fr.epsi.workshop.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.List;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profil {


    private @Id @GeneratedValue Long id;
    private String niveauEtude;
    private String description;
    private List<Competence> competences;
    private List<Avis> avis;
    private int utilisateur_id;
}

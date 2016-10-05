package fr.epsi.workshop.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Utilisateur {

    private @Id @GeneratedValue Long id;
    private String nom;
    private String prenom;
    private String pseudo;
    private String adresse;
    private int codePostal;
    private String ville;
    private int telephone;
    private String email;
    private Profil profil;
}

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
public class Avis {

    private @Id
    @GeneratedValue Long id;
    private int note;
    private String commentaire;
    private Long profil_id;
}

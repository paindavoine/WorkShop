package fr.epsi.workshop.models;

import lombok.*;

import javax.persistence.Entity;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Competence {

    private Long id;
    private String titre;
}

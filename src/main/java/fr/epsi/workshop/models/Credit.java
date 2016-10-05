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
public class Credit {

    private @Id @GeneratedValue Long id;
    private int compteur;
    private Long user_id;
}

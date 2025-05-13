package com.ndiaye.commande.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "commande")
@EqualsAndHashCode(exclude = "commande")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le produit est requis")
    private Long productId;

    @Min(value = 1, message = "La quantité doit être au minimum de 1")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;
}

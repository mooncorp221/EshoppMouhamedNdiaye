package com.ndiaye.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    //@ManyToOne(optional = false)
    //@JoinColumn(nullable = false)
    @ManyToOne
    private Category category;
}

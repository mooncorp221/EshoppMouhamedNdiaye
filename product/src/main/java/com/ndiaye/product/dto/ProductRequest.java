package com.ndiaye.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Le nom du produit est obligatoire")
    private String name;

    @NotNull(message = "Le prix est obligatoire")
    private Double price;

    @NotNull(message = "L'identifiant de la catégorie est obligatoire")
    private Long categoryId;
}

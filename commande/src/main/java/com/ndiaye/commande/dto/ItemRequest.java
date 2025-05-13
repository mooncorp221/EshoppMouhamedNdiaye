package com.ndiaye.commande.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemRequest {

    @NotNull(message = "L'identifiant du produit est requis")
    private Long productId;

    @NotNull(message = "La quantité est requise")
    private Integer quantity;
}

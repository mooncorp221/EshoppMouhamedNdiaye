package com.ndiaye.commande.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CommandeRequest {

    @NotNull(message = "La date est obligatoire")
    private LocalDate date;

    @NotNull(message = "L'utilisateur est requis")
    private Long userId;

    @NotNull(message = "Les items sont requis")
    private List<ItemRequest> items;
}

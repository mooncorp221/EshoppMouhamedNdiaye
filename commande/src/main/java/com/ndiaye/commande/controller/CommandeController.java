package com.ndiaye.commande.controller;

import com.ndiaye.commande.dto.CommandeRequest;
import com.ndiaye.commande.dto.ItemRequest;
import com.ndiaye.commande.entity.Commande;
import com.ndiaye.commande.entity.Item;
import com.ndiaye.commande.service.CommandeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/commandes")
@CrossOrigin(origins = "*")
public class CommandeController {

    private final CommandeService service;

    public CommandeController(CommandeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Commande> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> one(@PathVariable Long id) {
        Optional<Commande> commande = service.findById(id);
        return commande.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Commande> save(@RequestBody @Valid CommandeRequest request) {
        Commande commande = new Commande();
        commande.setDate(request.getDate());
        commande.setUserId(request.getUserId());

        List<Item> items = mapToItems(request.getItems(), commande);
        commande.setItems(items);

        Commande saved = service.save(commande);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> update(@PathVariable Long id, @Valid @RequestBody CommandeRequest request) {
        return service.findById(id).map(existing -> {
            existing.setDate(request.getDate());
            existing.setUserId(request.getUserId());

            List<Item> updatedItems = mapToItems(request.getItems(), existing);

            existing.getItems().clear(); // Orphan removal support
            existing.getItems().addAll(updatedItems);

            Commande updated = service.save(existing);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.findById(id).map(c -> {
            service.delete(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }


    // ✅ Méthode utilitaire pour mapper les ItemRequest en Item
    private List<Item> mapToItems(List<ItemRequest> itemRequests, Commande commande) {
        return itemRequests.stream().map(itemDto -> {
            Item item = new Item();
            item.setProductId(itemDto.getProductId());
            item.setQuantity(itemDto.getQuantity());
            item.setCommande(commande);
            return item;
        }).collect(Collectors.toList());
    }
}

package com.ndiaye.commande.repository;

import com.ndiaye.commande.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

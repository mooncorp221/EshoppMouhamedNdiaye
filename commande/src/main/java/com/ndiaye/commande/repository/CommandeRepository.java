package com.ndiaye.commande.repository;

import com.ndiaye.commande.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}

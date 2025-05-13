package com.ndiaye.commande.service;

import com.ndiaye.commande.entity.Commande;
import com.ndiaye.commande.repository.CommandeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    private final CommandeRepository repo;

    public CommandeService(CommandeRepository repo) {
        this.repo = repo;
    }

    public List<Commande> findAll() {
        return repo.findAll();
    }

    public Commande save(Commande commande) {
        return repo.save(commande);
    }

    public Optional<Commande> findById(Long id) {
        return repo.findById(id);
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }
}

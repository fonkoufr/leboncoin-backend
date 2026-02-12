package com.monapp.api.controller;

import com.monapp.api.model.Annonce;
import com.monapp.api.repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/annonces")
@CrossOrigin(origins = "http://localhost:5173")
public class AnnonceController {

    @Autowired 
    private AnnonceRepository repo;

    @GetMapping
    public List<Annonce> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Annonce> create(@RequestBody Annonce nouvelleAnnonce) {
        nouvelleAnnonce.setId(UUID.randomUUID().toString());
        nouvelleAnnonce.setDatePublication(LocalDate.now().toString());
        Annonce saved = repo.save(nouvelleAnnonce);
        return ResponseEntity.ok(saved);
    }
}
package com.monapp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monapp.api.model.Annonce;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce, String> {

    // 🔍 Spring créé automatiquement les requêtes SQL pour ces fonctions :
    
    // Trouver par catégorie
    List<Annonce> findByCategorie(String categorie);

    // Trouver par ville
    List<Annonce> findByVille(String ville);

    // Trouver seulement les urgentes (urgent = true)
    List<Annonce> findByUrgentTrue();

    // Recherche par mot clé dans le titre (Ignorer majuscule/minuscule)
    // SQL généré : WHERE LOWER(TITRE) LIKE %texte%
    List<Annonce> findByTitreContainingIgnoreCase(String texte);
}
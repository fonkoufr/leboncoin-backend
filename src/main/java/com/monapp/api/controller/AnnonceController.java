package com.monapp.api.controller;

import com.monapp.api.model.Annonce;
import com.monapp.api.repository.AnnonceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/annonces")
@Tag(name = "Annonces", description = "Gestion complète des petites annonces - création, consultation et suppression")
public class AnnonceController {

    @Autowired 
    private AnnonceRepository repo;

    @GetMapping
    @Operation(summary = "Récupérer toutes les annonces")
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès",
            content = @Content(schema = @Schema(implementation = Annonce.class)))
    public List<Annonce> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une annonce par ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Annonce trouvée",
                content = @Content(schema = @Schema(implementation = Annonce.class))),
        @ApiResponse(responseCode = "404", description = "Annonce introuvable")
    })
    public ResponseEntity<Annonce> getById(@PathVariable String id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "Créer une nouvelle annonce",
        description = "Crée une nouvelle petite annonce avec les données fournies. L'ID et la date de publication sont générés automatiquement"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Annonce créée avec succès",
            content = @Content(schema = @Schema(implementation = Annonce.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données d'annonce invalides"
        )
    })
    public ResponseEntity<?> create(
        @Valid @RequestBody
        @Parameter(description = "Objet Annonce contenant titre, description, prix, etc.")
        Annonce nouvelleAnnonce,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body("Erreurs de validation: " + errors);
        }
        
        nouvelleAnnonce.setId(UUID.randomUUID().toString());
        nouvelleAnnonce.setDatePublication(LocalDate.now().toString());
        Annonce saved = repo.save(nouvelleAnnonce);
        return ResponseEntity.ok(saved);
    }
}
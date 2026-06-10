package com.monapp.api.controller;

import com.monapp.api.model.Utilisateur;
import com.monapp.api.service.UtilisateurService;
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

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utilisateurs")
@Tag(name = "Utilisateurs", description = "Gestion de l'authentification des utilisateurs - inscription et connexion")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/register")
    @Operation(
        summary = "Inscrire un nouvel utilisateur",
        description = "Crée un nouveau compte utilisateur avec validation des données. L'email doit être unique et le mot de passe sera sécurisé"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Inscription réussie, utilisateur créé",
            content = @Content(schema = @Schema(implementation = Utilisateur.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Erreur lors de l'inscription - données invalides ou email déjà existant"
        )
    })
    public ResponseEntity<?> register(
        @Valid @RequestBody
        @Parameter(description = "Objet Utilisateur contenant email, mot de passe et nom")
        Utilisateur utilisateur,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body("Erreurs de validation: " + errors);
        }
        
        try {
            Utilisateur nouveau = utilisateurService.inscription(utilisateur);
            return ResponseEntity.ok(nouveau);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(
        summary = "Authentifier un utilisateur",
        description = "Valide les identifiants (email et mot de passe) et retourne les données de l'utilisateur en cas de succès"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Authentification réussie, données utilisateur retournées",
            content = @Content(schema = @Schema(implementation = Utilisateur.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Authentification échouée - email ou mot de passe incorrect"
        )
    })
    public ResponseEntity<?> login(
        @Valid @RequestBody
        @Parameter(description = "Objet contenant email et mot de passe pour la connexion")
        Utilisateur loginData,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body("Erreurs de validation: " + errors);
        }
        
        Optional<Utilisateur> user = utilisateurService.connexion(loginData.getEmail(), loginData.getMotDePasse());
        
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(401).body("Email ou mot de passe incorrect");
        }
    }
}
package com.monapp.api.service;

import com.monapp.api.model.Utilisateur;
import com.monapp.api.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Inscription
    public Utilisateur inscription(Utilisateur utilisateur) throws Exception {
        if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
            throw new Exception("Cet email est déjà utilisé !");
        }
        // On crypte le mot de passe avant de sauvegarder
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        return utilisateurRepository.save(utilisateur);
    }

    // Connexion
    public Optional<Utilisateur> connexion(String email, String motDePasseBrut) {
        Optional<Utilisateur> userOpt = utilisateurRepository.findByEmail(email);
        
        if (userOpt.isPresent()) {
            Utilisateur user = userOpt.get();
            // On vérifie si le mot de passe correspond au hash en base
            if (passwordEncoder.matches(motDePasseBrut, user.getMotDePasse())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
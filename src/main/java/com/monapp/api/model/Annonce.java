package com.monapp.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "ANNONCES")
public class Annonce {

    @Id
    private String id;
    
    @NotBlank(message = "Le titre ne peut pas être vide")
    @Size(min = 3, max = 200, message = "Le titre doit avoir entre 3 et 200 caractères")
    private String titre;
    
    @NotBlank(message = "La description ne peut pas être vide")
    @Size(min = 10, max = 2000, message = "La description doit avoir entre 10 et 2000 caractères")
    private String description;
    
    @NotNull(message = "Le prix ne peut pas être null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être supérieur à 0")
    @DecimalMax(value = "999999.99", message = "Le prix ne doit pas dépasser 999999.99")
    private Double prix; 
    
    @NotBlank(message = "La ville ne peut pas être vide")
    @Size(min = 2, max = 100, message = "La ville doit avoir entre 2 et 100 caractères")
    private String ville;
    
    @NotBlank(message = "La catégorie ne peut pas être vide")
    @Size(min = 2, max = 100, message = "La catégorie doit avoir entre 2 et 100 caractères")
    private String categorie;
    
    @Lob
    @Column(name = "image_url")
    private String imageUrl;
    
    @Pattern(regexp = "^[0-9+\\-. ()]{10,}$", message = "Le téléphone doit avoir un format valide")
    private String telephone;
    
    @Email(message = "L'email doit être valide")
    private String email;
    
    private Boolean urgent;
    
    @Column(name = "date_publication")
    private String datePublication;

    public Annonce() {}

    // --- GETTERS ET SETTERS ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean isUrgent() { return urgent; }
    public void setUrgent(Boolean urgent) { this.urgent = urgent; }

    public String getDatePublication() { return datePublication; }
    public void setDatePublication(String datePublication) { this.datePublication = datePublication; }
}
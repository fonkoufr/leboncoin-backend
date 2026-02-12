package com.monapp.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ANNONCES")
public class Annonce {

    @Id
    private String id;
    
    private String titre;
    private String description;
    private Double prix; 
    private String ville;
    private String categorie;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    private String telephone;
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
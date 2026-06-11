package com.monapp.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MESSAGES")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EXPEDITEUR_ID", nullable = false)
    private Long expediteurId;

    @Column(name = "DESTINATAIRE_ID", nullable = false)
    private Long destinataireId;

    @Column(name = "ANNONCE_ID")
    private String annonceId;

    @Column(name = "CONTENU", length = 1000, nullable = false)
    private String contenu;

    @Column(name = "DATE_ENVOI")
    private LocalDateTime dateEnvoi;

    @Column(name = "LU")
    private Boolean lu = false;

    public Message() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getExpediteurId() { return expediteurId; }
    public void setExpediteurId(Long expediteurId) { this.expediteurId = expediteurId; }

    public Long getDestinataireid() { return destinataireId; }
    public void setDestinataireid(Long destinataireId) { this.destinataireId = destinataireId; }

    public String getAnnonceId() { return annonceId; }
    public void setAnnonceId(String annonceId) { this.annonceId = annonceId; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public LocalDateTime getDateEnvoi() { return dateEnvoi; }
    public void setDateEnvoi(LocalDateTime dateEnvoi) { this.dateEnvoi = dateEnvoi; }

    public Boolean getLu() { return lu; }
    public void setLu(Boolean lu) { this.lu = lu; }
}

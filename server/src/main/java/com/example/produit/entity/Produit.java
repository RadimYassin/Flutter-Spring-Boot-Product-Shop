package com.example.produit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity representing a Produit (Product)
 */
@Entity
@Table(name = "produits")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 3, max = 100, message = "Le nom doit contenir entre 3 et 100 caractères")
    @Column(nullable = false, length = 100)
    private String nom;

    @Size(max = 255, message = "La description ne peut pas dépasser 255 caractères")
    @Column(length = 255)
    private String description;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    @Column(nullable = false)
    private Double prix;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 0, message = "La quantité doit être positive ou zéro")
    @Column(nullable = false)
    private Integer quantite;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDate dateCreation;

    /**
     * Automatically set the creation date before persisting
     */
    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDate.now();
    }
}

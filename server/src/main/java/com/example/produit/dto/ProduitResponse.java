package com.example.produit.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for Produit responses
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for Produit")
public class ProduitResponse {

    @Schema(description = "ID unique du produit", example = "1")
    private Long id;

    @Schema(description = "Nom du produit", example = "Ordinateur portable Dell")
    private String nom;

    @Schema(description = "Description du produit", example = "Ordinateur portable haute performance avec processeur i7")
    private String description;

    @Schema(description = "Prix du produit", example = "1299.99")
    private Double prix;

    @Schema(description = "Quantité en stock", example = "50")
    private Integer quantite;

    @Schema(description = "Date de création", example = "2025-12-12")
    private LocalDate dateCreation;
}

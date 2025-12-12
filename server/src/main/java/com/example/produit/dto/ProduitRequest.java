package com.example.produit.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for creating or updating a Produit
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for creating or updating a Produit")
public class ProduitRequest {

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 3, max = 100, message = "Le nom doit contenir entre 3 et 100 caractères")
    @Schema(description = "Nom du produit", example = "Ordinateur portable Dell", required = true)
    private String nom;

    @Size(max = 255, message = "La description ne peut pas dépasser 255 caractères")
    @Schema(description = "Description du produit", example = "Ordinateur portable haute performance avec processeur i7")
    private String description;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    @Schema(description = "Prix du produit", example = "1299.99", required = true)
    private Double prix;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 0, message = "La quantité doit être positive ou zéro")
    @Schema(description = "Quantité en stock", example = "50", required = true)
    private Integer quantite;
}

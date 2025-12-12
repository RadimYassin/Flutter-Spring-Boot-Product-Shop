package com.example.produit.controller;

import com.example.produit.dto.ProduitRequest;
import com.example.produit.dto.ProduitResponse;
import com.example.produit.service.ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Produit CRUD operations
 */
@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
@Tag(name = "Produit", description = "API de gestion des produits")
public class ProduitController {

    private final ProduitService produitService;

    @PostMapping
    @Operation(summary = "Créer un nouveau produit", description = "Crée un nouveau produit dans la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produit créé avec succès", content = @Content(schema = @Schema(implementation = ProduitResponse.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public ResponseEntity<ProduitResponse> create(
            @Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Données du produit à créer", required = true, content = @Content(schema = @Schema(implementation = ProduitRequest.class))) ProduitRequest request) {

        ProduitResponse response = produitService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un produit", description = "Met à jour un produit existant par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produit mis à jour avec succès", content = @Content(schema = @Schema(implementation = ProduitResponse.class))),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public ResponseEntity<ProduitResponse> update(
            @Parameter(description = "ID du produit", required = true) @PathVariable Long id,

            @Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Nouvelles données du produit", required = true, content = @Content(schema = @Schema(implementation = ProduitRequest.class))) ProduitRequest request) {

        ProduitResponse response = produitService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un produit par ID", description = "Récupère un produit par son identifiant unique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produit trouvé", content = @Content(schema = @Schema(implementation = ProduitResponse.class))),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    })
    public ResponseEntity<ProduitResponse> getById(
            @Parameter(description = "ID du produit", required = true) @PathVariable Long id) {

        ProduitResponse response = produitService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Lister tous les produits", description = "Récupère la liste de tous les produits")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des produits récupérée avec succès", content = @Content(schema = @Schema(implementation = ProduitResponse.class)))
    })
    public ResponseEntity<List<ProduitResponse>> getAll() {
        List<ProduitResponse> responses = produitService.getAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/paginated")
    @Operation(summary = "Lister les produits avec pagination", description = "Récupère une page de produits")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page de produits récupérée avec succès")
    })
    public ResponseEntity<Page<ProduitResponse>> getAllPaginated(
            @Parameter(description = "Numéro de page (0-indexé)", example = "0") @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Taille de la page", example = "10") @RequestParam(defaultValue = "10") int size) {

        Page<ProduitResponse> responses = produitService.getAllPaginated(page, size);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    @Operation(summary = "Rechercher des produits par nom", description = "Recherche des produits dont le nom contient la chaîne spécifiée (insensible à la casse)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Résultats de recherche récupérés avec succès", content = @Content(schema = @Schema(implementation = ProduitResponse.class)))
    })
    public ResponseEntity<List<ProduitResponse>> searchByNom(
            @Parameter(description = "Nom à rechercher", required = true, example = "ordinateur") @RequestParam String nom) {

        List<ProduitResponse> responses = produitService.searchByNom(nom);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un produit", description = "Supprime un produit par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produit supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID du produit", required = true) @PathVariable Long id) {

        produitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

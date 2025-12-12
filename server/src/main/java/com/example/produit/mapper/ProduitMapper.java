package com.example.produit.mapper;

import com.example.produit.dto.ProduitRequest;
import com.example.produit.dto.ProduitResponse;
import com.example.produit.entity.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * MapStruct mapper for converting between Produit entity and DTOs
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProduitMapper {

    /**
     * Convert ProduitRequest to Produit entity
     */
    Produit toEntity(ProduitRequest request);

    /**
     * Convert Produit entity to ProduitResponse
     */
    ProduitResponse toResponse(Produit produit);

    /**
     * Convert list of Produit entities to list of ProduitResponse
     */
    List<ProduitResponse> toResponseList(List<Produit> produits);

    /**
     * Update existing Produit entity with data from ProduitRequest
     */
    void updateEntityFromRequest(ProduitRequest request, @MappingTarget Produit produit);
}

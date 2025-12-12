package com.example.produit.mapper;

import com.example.produit.dto.ProduitRequest;
import com.example.produit.dto.ProduitResponse;
import com.example.produit.entity.Produit;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-12T16:18:35+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ProduitMapperImpl implements ProduitMapper {

    @Override
    public Produit toEntity(ProduitRequest request) {
        if ( request == null ) {
            return null;
        }

        Produit.ProduitBuilder produit = Produit.builder();

        produit.nom( request.getNom() );
        produit.description( request.getDescription() );
        produit.prix( request.getPrix() );
        produit.quantite( request.getQuantite() );

        return produit.build();
    }

    @Override
    public ProduitResponse toResponse(Produit produit) {
        if ( produit == null ) {
            return null;
        }

        ProduitResponse produitResponse = new ProduitResponse();

        produitResponse.setId( produit.getId() );
        produitResponse.setNom( produit.getNom() );
        produitResponse.setDescription( produit.getDescription() );
        produitResponse.setPrix( produit.getPrix() );
        produitResponse.setQuantite( produit.getQuantite() );
        produitResponse.setDateCreation( produit.getDateCreation() );

        return produitResponse;
    }

    @Override
    public List<ProduitResponse> toResponseList(List<Produit> produits) {
        if ( produits == null ) {
            return null;
        }

        List<ProduitResponse> list = new ArrayList<ProduitResponse>( produits.size() );
        for ( Produit produit : produits ) {
            list.add( toResponse( produit ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromRequest(ProduitRequest request, Produit produit) {
        if ( request == null ) {
            return;
        }

        if ( request.getNom() != null ) {
            produit.setNom( request.getNom() );
        }
        if ( request.getDescription() != null ) {
            produit.setDescription( request.getDescription() );
        }
        if ( request.getPrix() != null ) {
            produit.setPrix( request.getPrix() );
        }
        if ( request.getQuantite() != null ) {
            produit.setQuantite( request.getQuantite() );
        }
    }
}

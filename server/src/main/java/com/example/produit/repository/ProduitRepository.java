package com.example.produit.repository;

import com.example.produit.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Produit entity
 */
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    /**
     * Find products by name containing the given string (case-insensitive)
     * 
     * @param nom the name to search for
     * @return list of matching products
     */
    List<Produit> findByNomContainingIgnoreCase(String nom);
}

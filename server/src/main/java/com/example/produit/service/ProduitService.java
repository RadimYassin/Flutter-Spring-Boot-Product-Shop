package com.example.produit.service;

import com.example.produit.dto.ProduitRequest;
import com.example.produit.dto.ProduitResponse;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Service interface for Produit operations
 */
public interface ProduitService {

    /**
     * Create a new product
     * 
     * @param request the product data
     * @return the created product
     */
    ProduitResponse create(ProduitRequest request);

    /**
     * Update an existing product
     * 
     * @param id      the product ID
     * @param request the updated product data
     * @return the updated product
     */
    ProduitResponse update(Long id, ProduitRequest request);

    /**
     * Get a product by ID
     * 
     * @param id the product ID
     * @return the product
     */
    ProduitResponse getById(Long id);

    /**
     * Get all products
     * 
     * @return list of all products
     */
    List<ProduitResponse> getAll();

    /**
     * Get all products with pagination
     * 
     * @param page the page number (0-indexed)
     * @param size the page size
     * @return page of products
     */
    Page<ProduitResponse> getAllPaginated(int page, int size);

    /**
     * Search products by name
     * 
     * @param nom the name to search for
     * @return list of matching products
     */
    List<ProduitResponse> searchByNom(String nom);

    /**
     * Delete a product by ID
     * 
     * @param id the product ID
     */
    void delete(Long id);
}

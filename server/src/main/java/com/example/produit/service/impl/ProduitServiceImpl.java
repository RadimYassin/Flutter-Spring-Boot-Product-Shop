package com.example.produit.service.impl;

import com.example.produit.dto.ProduitRequest;
import com.example.produit.dto.ProduitResponse;
import com.example.produit.entity.Produit;
import com.example.produit.exception.ResourceNotFoundException;
import com.example.produit.mapper.ProduitMapper;
import com.example.produit.repository.ProduitRepository;
import com.example.produit.service.ProduitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of ProduitService
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private final ProduitMapper produitMapper;

    @Override
    public ProduitResponse create(ProduitRequest request) {
        log.info("Creating new product with name: {}", request.getNom());

        Produit produit = produitMapper.toEntity(request);
        Produit savedProduit = produitRepository.save(produit);

        log.info("Product created successfully with ID: {}", savedProduit.getId());
        return produitMapper.toResponse(savedProduit);
    }

    @Override
    public ProduitResponse update(Long id, ProduitRequest request) {
        log.info("Updating product with ID: {}", id);

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", "id", id));

        produitMapper.updateEntityFromRequest(request, produit);
        Produit updatedProduit = produitRepository.save(produit);

        log.info("Product updated successfully with ID: {}", id);
        return produitMapper.toResponse(updatedProduit);
    }

    @Override
    @Transactional(readOnly = true)
    public ProduitResponse getById(Long id) {
        log.info("Fetching product with ID: {}", id);

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", "id", id));

        return produitMapper.toResponse(produit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduitResponse> getAll() {
        log.info("Fetching all products");

        List<Produit> produits = produitRepository.findAll(Sort.by(Sort.Direction.DESC, "dateCreation"));

        log.info("Found {} products", produits.size());
        return produitMapper.toResponseList(produits);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProduitResponse> getAllPaginated(int page, int size) {
        log.info("Fetching products - page: {}, size: {}", page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreation"));
        Page<Produit> produitPage = produitRepository.findAll(pageable);

        log.info("Found {} products on page {}", produitPage.getNumberOfElements(), page);
        return produitPage.map(produitMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduitResponse> searchByNom(String nom) {
        log.info("Searching products by name containing: {}", nom);

        List<Produit> produits = produitRepository.findByNomContainingIgnoreCase(nom);

        log.info("Found {} products matching '{}'", produits.size(), nom);
        return produitMapper.toResponseList(produits);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting product with ID: {}", id);

        if (!produitRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produit", "id", id);
        }

        produitRepository.deleteById(id);
        log.info("Product deleted successfully with ID: {}", id);
    }
}

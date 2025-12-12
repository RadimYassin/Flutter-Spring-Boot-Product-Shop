package com.example.produit.config;

import com.example.produit.entity.Produit;
import com.example.produit.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Initializes the database with sample product data
 * Runs automatically on application startup
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final ProduitRepository produitRepository;

    @Override
    public void run(String... args) {
        if (produitRepository.count() == 0) {
            log.info("Database is empty. Initializing with sample data...");

            List<Produit> sampleProducts = Arrays.asList(
                    Produit.builder()
                            .nom("Ordinateur Portable Dell XPS 15")
                            .description(
                                    "Ordinateur portable haute performance avec écran 15.6 pouces, processeur Intel i7, 16GB RAM, SSD 512GB")
                            .prix(1299.99)
                            .quantite(25)
                            .dateCreation(LocalDate.now().minusDays(30))
                            .build(),

                    Produit.builder()
                            .nom("iPhone 15 Pro Max")
                            .description(
                                    "Smartphone Apple dernière génération, 256GB, écran Super Retina XDR, puce A17 Pro")
                            .prix(1399.00)
                            .quantite(50)
                            .dateCreation(LocalDate.now().minusDays(25))
                            .build(),

                    Produit.builder()
                            .nom("Samsung Galaxy Tab S9")
                            .description("Tablette Android premium, écran AMOLED 11 pouces, 128GB, S Pen inclus")
                            .prix(649.99)
                            .quantite(35)
                            .dateCreation(LocalDate.now().minusDays(20))
                            .build(),

                    Produit.builder()
                            .nom("Sony WH-1000XM5")
                            .description(
                                    "Casque audio sans fil avec réduction de bruit active, autonomie 30h, qualité audio exceptionnelle")
                            .prix(399.99)
                            .quantite(100)
                            .dateCreation(LocalDate.now().minusDays(15))
                            .build(),

                    Produit.builder()
                            .nom("Apple Watch Series 9")
                            .description(
                                    "Montre connectée Apple, GPS + Cellular, écran Always-On Retina, suivi santé avancé")
                            .prix(499.00)
                            .quantite(60)
                            .dateCreation(LocalDate.now().minusDays(12))
                            .build(),

                    Produit.builder()
                            .nom("MacBook Pro 14 M3")
                            .description(
                                    "Ordinateur portable Apple avec puce M3, 16GB RAM unifiée, SSD 1TB, écran Liquid Retina XDR")
                            .prix(2299.00)
                            .quantite(15)
                            .dateCreation(LocalDate.now().minusDays(10))
                            .build(),

                    Produit.builder()
                            .nom("Logitech MX Master 3S")
                            .description(
                                    "Souris sans fil ergonomique pour professionnels, précision 8000 DPI, batterie longue durée")
                            .prix(99.99)
                            .quantite(150)
                            .dateCreation(LocalDate.now().minusDays(8))
                            .build(),

                    Produit.builder()
                            .nom("Samsung Monitor 27 4K")
                            .description(
                                    "Écran professionnel 27 pouces, résolution 4K UHD, HDR10, taux de rafraîchissement 60Hz")
                            .prix(449.99)
                            .quantite(40)
                            .dateCreation(LocalDate.now().minusDays(5))
                            .build(),

                    Produit.builder()
                            .nom("Nintendo Switch OLED")
                            .description(
                                    "Console de jeu portable avec écran OLED 7 pouces, 64GB stockage interne, Joy-Con inclus")
                            .prix(349.99)
                            .quantite(75)
                            .dateCreation(LocalDate.now().minusDays(3))
                            .build(),

                    Produit.builder()
                            .nom("Kindle Paperwhite 2024")
                            .description(
                                    "Liseuse électronique Amazon, écran 6.8 pouces antireflet, étanche IPX8, stockage 16GB")
                            .prix(159.99)
                            .quantite(120)
                            .dateCreation(LocalDate.now().minusDays(1))
                            .build());

            produitRepository.saveAll(sampleProducts);
            log.info("Successfully initialized database with {} products", sampleProducts.size());
        } else {
            log.info("Database already contains {} products. Skipping initialization.", produitRepository.count());
        }
    }
}

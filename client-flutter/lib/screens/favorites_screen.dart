import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/product_provider.dart';
import '../providers/favorites_provider.dart';
import '../widgets/product_card.dart';
import '../widgets/custom_app_bar.dart';

class FavoritesScreen extends StatelessWidget {
  const FavoritesScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(
        title: 'Mes Favoris',
        actions: [],
      ),
      body: Consumer2<FavoritesProvider, ProductProvider>(
        builder: (context, favoritesProvider, productProvider, child) {
          if (!favoritesProvider.isInitialized) {
            return const Center(
              child: CircularProgressIndicator(),
            );
          }

          final favoriteIds = favoritesProvider.favoriteIds;

          if (favoriteIds.isEmpty) {
            return Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Icon(
                    Icons.favorite_border,
                    size: 120,
                    color: Colors.grey[300],
                  ),
                  const SizedBox(height: 24),
                  Text(
                    'Aucun favori',
                    style: Theme.of(context).textTheme.headlineSmall,
                  ),
                  const SizedBox(height: 8),
                  Text(
                    'Ajoutez vos produits préférés ici',
                    style: TextStyle(color: Colors.grey[600]),
                  ),
                  const SizedBox(height: 32),
                  ElevatedButton.icon(
                    onPressed: () {
                      Navigator.of(context).pushNamed('/home');
                    },
                    icon: const Icon(Icons.shopping_bag),
                    label: const Text('Découvrir les produits'),
                  ),
                ],
              ),
            );
          }

          // Get actual product objects for favorites
          final favoriteProducts = productProvider.products
              .where((product) => favoriteIds.contains(product.id))
              .toList();

          if (favoriteProducts.isEmpty && productProvider.products.isNotEmpty) {
            return Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Icon(
                    Icons.refresh,
                    size: 64,
                    color: Colors.grey[400],
                  ),
                  const SizedBox(height: 16),
                  const Text('Chargement des favoris...'),
                  const SizedBox(height: 16),
                  ElevatedButton(
                    onPressed: () {
                      productProvider.loadProducts();
                    },
                    child: const Text('Actualiser'),
                  ),
                ],
              ),
            );
          }

          return Column(
            children: [
              // Favorites count and clear button
              Padding(
                padding: const EdgeInsets.all(16),
                child: Row(
                  children: [
                    Text(
                      '${favoriteProducts.length} favori${favoriteProducts.length > 1 ? 's' : ''}',
                      style: const TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const Spacer(),
                    if (favoriteIds.isNotEmpty)
                      TextButton.icon(
                        onPressed: () {
                          showDialog(
                            context: context,
                            builder: (context) => AlertDialog(
                              title: const Text('Effacer les favoris'),
                              content: const Text(
                                'Êtes-vous sûr de vouloir supprimer tous vos favoris?',
                              ),
                              actions: [
                                TextButton(
                                  onPressed: () => Navigator.of(context).pop(),
                                  child: const Text('Annuler'),
                                ),
                                TextButton(
                                  onPressed: () {
                                    favoritesProvider.clearAllFavorites();
                                    Navigator.of(context).pop();
                                    ScaffoldMessenger.of(context).showSnackBar(
                                      const SnackBar(
                                        content: Text(
                                            'Tous les favoris ont été supprimés'),
                                      ),
                                    );
                                  },
                                  child: const Text(
                                    'Effacer',
                                    style: TextStyle(color: Colors.red),
                                  ),
                                ),
                              ],
                            ),
                          );
                        },
                        icon: const Icon(Icons.delete_outline, color: Colors.red),
                        label: const Text(
                          'Tout effacer',
                          style: TextStyle(color: Colors.red),
                        ),
                      ),
                  ],
                ),
              ),
              // Favorites grid
              Expanded(
                child: favoriteProducts.isEmpty
                    ? const Center(
                        child: CircularProgressIndicator(),
                      )
                    : GridView.builder(
                        padding: const EdgeInsets.symmetric(horizontal: 16),
                        gridDelegate:
                            const SliverGridDelegateWithFixedCrossAxisCount(
                          crossAxisCount: 2,
                          childAspectRatio: 0.7,
                          crossAxisSpacing: 16,
                          mainAxisSpacing: 16,
                        ),
                        itemCount: favoriteProducts.length,
                        itemBuilder: (context, index) {
                          return ProductCard(product: favoriteProducts[index]);
                        },
                      ),
              ),
            ],
          );
        },
      ),
    );
  }
}

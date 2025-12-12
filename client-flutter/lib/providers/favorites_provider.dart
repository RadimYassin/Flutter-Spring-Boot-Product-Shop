import 'package:flutter/foundation.dart';
import '../services/storage_service.dart';

class FavoritesProvider with ChangeNotifier {
  final StorageService _storageService = StorageService();
  List<int> _favoriteIds = [];
  bool _isInitialized = false;

  List<int> get favoriteIds => [..._favoriteIds];
  bool get isInitialized => _isInitialized;

  Future<void> loadFavorites() async {
    try {
      _favoriteIds = await _storageService.loadFavorites();
      _isInitialized = true;
      notifyListeners();
    } catch (e) {
      debugPrint('Error loading favorites: $e');
      _favoriteIds = [];
      _isInitialized = true;
      notifyListeners();
    }
  }

  Future<void> toggleFavorite(int productId) async {
    if (_favoriteIds.contains(productId)) {
      _favoriteIds.remove(productId);
    } else {
      _favoriteIds.add(productId);
    }
    
    await _storageService.saveFavorites(_favoriteIds);
    notifyListeners();
  }

  bool isFavorite(int productId) {
    return _favoriteIds.contains(productId);
  }

  Future<void> clearAllFavorites() async {
    _favoriteIds.clear();
    await _storageService.clearFavorites();
    notifyListeners();
  }

  int get favoriteCount => _favoriteIds.length;
}

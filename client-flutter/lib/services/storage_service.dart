import 'dart:convert';
import 'package:shared_preferences/shared_preferences.dart';

class StorageService {
  static final StorageService _instance = StorageService._internal();
  factory StorageService() => _instance;
  StorageService._internal();

  static const String _favoritesKey = 'favorites';

  Future<void> saveFavorites(List<int> favoriteIds) async {
    try {
      final prefs = await SharedPreferences.getInstance();
      final String encoded = json.encode(favoriteIds);
      await prefs.setString(_favoritesKey, encoded);
    } catch (e) {
      throw Exception('Error saving favorites: $e');
    }
  }

  Future<List<int>> loadFavorites() async {
    try {
      final prefs = await SharedPreferences.getInstance();
      final String? encoded = prefs.getString(_favoritesKey);
      
      if (encoded == null) {
        return [];
      }
      
      final List<dynamic> decoded = json.decode(encoded);
      return decoded.map((id) => id as int).toList();
    } catch (e) {
      return [];
    }
  }

  Future<void> clearFavorites() async {
    try {
      final prefs = await SharedPreferences.getInstance();
      await prefs.remove(_favoritesKey);
    } catch (e) {
      throw Exception('Error clearing favorites: $e');
    }
  }
}

class Product {
  final int id;
  final String nom;
  final String description;
  final double prix;
  final int quantite;
  final String dateCreation;

  Product({
    required this.id,
    required this.nom,
    required this.description,
    required this.prix,
    required this.quantite,
    required this.dateCreation,
  });

  factory Product.fromJson(Map<String, dynamic> json) {
    return Product(
      id: json['id'] as int,
      nom: json['nom'] as String,
      description: json['description'] as String,
      prix: (json['prix'] as num).toDouble(),
      quantite: json['quantite'] as int,
      dateCreation: json['dateCreation'] as String,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'nom': nom,
      'description': description,
      'prix': prix,
      'quantite': quantite,
      'dateCreation': dateCreation,
    };
  }

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is Product && runtimeType == other.runtimeType && id == other.id;

  @override
  int get hashCode => id.hashCode;
}

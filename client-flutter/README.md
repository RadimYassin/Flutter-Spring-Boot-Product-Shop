# Flutter Product Shop Application

A complete mobile shopping application built with Flutter and Spring Boot backend integration.

## Features

- ✅ Product listing with search functionality
- ✅ Product detail view with add to cart
- ✅ Shopping cart management (add, remove, update quantity)
- ✅ Persistent favorites using SharedPreferences
- ✅ Clean MVC + Provider architecture
- ✅ Material Design 3 UI
- ✅ Bottom navigation
- ✅ Pull-to-refresh
- ✅ Error handling

## Tech Stack

### Frontend (Flutter)
- **Flutter SDK**: >=3.0.0
- **State Management**: Provider
- **HTTP Client**: http package
- **Local Storage**: shared_preferences
- **Image Caching**: cached_network_image
- **UI Components**: Material Design 3

### Backend (Spring Boot)
- Spring Boot REST API
- H2 Database
- JPA/Hibernate
- Running on `localhost:8080`

## Project Structure

```
lib/
├── main.dart                  # App entry point
├── models/
│   ├── product.dart          # Product model
│   └── cart_item.dart        # Cart item model
├── services/
│   ├── api_service.dart      # API communication
│   └── storage_service.dart  # Local persistence
├── providers/
│   ├── product_provider.dart # Product state
│   ├── cart_provider.dart    # Cart state
│   └── favorites_provider.dart # Favorites state
├── screens/
│   ├── home_screen.dart      # Product listing
│   ├── product_detail_screen.dart # Product details
│   ├── cart_screen.dart      # Shopping cart
│   └── favorites_screen.dart # Favorites
└── widgets/
    ├── product_card.dart     # Product card widget
    └── custom_app_bar.dart   # Custom app bar
```

## Getting Started

### Prerequisites

1. Flutter SDK (3.0.0 or higher)
2. Android Studio or VS Code with Flutter
3. Spring Boot backend running on `localhost:8080`

### Installation

1. **Clone or navigate to the project:**
   ```bash
   cd c:\Users\ROG\Desktop\client-flutter
   ```

2. **Install dependencies:**
   ```bash
   flutter pub get
   ```

3. **Ensure your Spring Boot backend is running:**
   - The app expects the API at `http://localhost:8080/api/produits`
   - For Android emulator, it uses `http://10.0.2.2:8080/api/produits`

4. **Run the app:**
   ```bash
   # On Android emulator
   flutter run

   # On specific device
   flutter run -d <device_id>

   # List all devices
   flutter devices
   ```

## API Configuration

The app is configured to connect to your Spring Boot backend:

- **Base URL (Android Emulator)**: `http://10.0.2.2:8080/api/produits`
- **Base URL (iOS/Web)**: Change to `http://localhost:8080/api/produits` in `lib/services/api_service.dart`

### API Endpoints Used

- `GET /api/produits` - Fetch all products
- `GET /api/produits/{id}` - Fetch single product
- `GET /api/produits/search?nom={query}` - Search products

## Features Overview

### Home Screen
- Grid view of all products
- Search bar for filtering products
- Pull-to-refresh
- Product count display
- Bottom navigation (Home, Cart, Favorites)

### Product Detail Screen
- Large product image (gradient placeholder)
- Product name, price, and stock status
- Description
- Quantity selector
- Add to cart button
- Favorite toggle

### Cart Screen
- List of cart items with images
- Quantity controls (+/-)
- Remove item functionality
- Real-time total calculation
- Clear cart option
- Checkout button (placeholder)

### Favorites Screen
- Grid of favorite products
- Persistent across app restarts
- Clear all favorites option
- Empty state messaging

## Architecture

### State Management (Provider)

1. **ProductProvider**: Manages product data and loading states
2. **CartProvider**: Handles cart operations and calculations
3. **FavoritesProvider**: Manages favorites with local persistence

### Data Flow

```
User Action → Provider → Service Layer → API/Storage → Provider → UI Update
```

## Building for Production

```bash
# Android APK
flutter build apk --release

# Android App Bundle
flutter build appbundle --release

# iOS
flutter build ios --release
```

## Troubleshooting

### Cannot connect to backend

1. Verify Spring Boot is running on port 8080
2. Check the baseUrl in `lib/services/api_service.dart`
3. For Android emulator, ensure you're using `10.0.2.2` instead of `localhost`

### Dependencies issues

```bash
flutter clean
flutter pub get
```

### Analysis issues

```bash
flutter analyze
```

## License

This project is created for educational purposes.

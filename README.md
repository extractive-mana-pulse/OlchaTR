### 🛍️ Product Catalog Mini-App

Implementation of the Android Developer Test Task for Olcha Market.
The application displays products from a public API, allows viewing product details, and supports saving products to favorites with local persistence.
Although the task required only Android, the project was implemented using Kotlin Multiplatform, allowing the same business logic to run on Android and iOS.

### 📹 Demo Videos

<details>
  <summary>Android app</summary>

https://github.com/user-attachments/assets/efa26581-1d93-45c7-b4cf-e60afc89d311
  
</details>

<details>
  <summary>iOS app</summary>
  
https://github.com/user-attachments/assets/8ec91702-da8e-41b2-97c8-b6c46e0bce06

</details>

### 📱 Features
- Product List
- Fetch products from API
- Display image, title, price, and rating
- Pull-to-refresh support
- Proper loading and error states
- Product Detail
- Navigate from product list
Show full product information:
Image
Title
Description
Price
Category
Rating
Add to Favorites button
Favorites
View saved favorite products
Remove items from favorites
Favorites persist across app restarts

### 🧱 Tech Stack

Language: Kotlin
Architecture: MVVM + Clean Architecture
Networking: Ktor Client
Local Storage: Room (Kotlin Multiplatform support)
Concurrency: Coroutines + Flow
Dependency Injection: Koin
UI: Jetpack Compose (Android & iOS)
Image Loading: Coil
Navigation: Compose navigation

### 🏗️ Architecture

The project follows Clean Architecture principles to keep the codebase maintainable and scalable.

### 🌐 API

Products are fetched from:
**https://fakestoreapi.com/products**

### 🧠 Architecture Decisions

Kotlin Multiplatform

Even though the task required only Android, Kotlin Multiplatform was used so the same business logic, networking, and database layer can run on both Android and iOS.

- Clean Architecture:
    Separating presentation, domain, and data layers makes the project easier to maintain and test.
- Room (KMP):
    Room's multiplatform support allows using the same database layer for both platforms.

- Coroutines + Flow:
    Used for asynchronous operations and reactive UI updates.

### ▶️ Build Instructions

- Android:
    Clone the repository

- git clone <repository-url>

- Open the project in Android Studio

- Run the Android app

- composeApp → Run
    iOS (Optional)

- Open the iosApp folder in Xcode
    Build and run on simulator or device

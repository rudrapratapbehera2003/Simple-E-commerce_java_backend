E-Commerce Backend API
This is the backend API for the e-commerce project built with Spring Boot. It provides functionality for managing products, including adding, updating, deleting, and searching products. It also supports image uploads for products and serves product images.

Features
CRUD Operations for Products: Add, update, get, and delete products.
Search Products: Search for products using a keyword.
Image Upload and Retrieval: Upload images for products and retrieve product images by ID.
Cross-Origin Resource Sharing (CORS): Configured to allow requests from http://localhost:3000 for local development.
Endpoints
1. Get All Products
   Endpoint: GET /api/products
   Description: Fetches a list of all products.
   Response: List of products in JSON format.
2. Get Product by ID
   Endpoint: GET /api/products/{id}
   Description: Fetches product details by product ID.
   Path Variable: id (Product ID)
   Response: Product details in JSON format if found, or 404 if not found.
3. Add Product
   Endpoint: POST /api/products
   Description: Adds a new product along with its image.
   Request Body:
   ProductData object with details like name, description, price, and quantity.
   imageFile (MultipartFile) for the product image.
   Response: 201 if product is successfully added or 500 if an error occurs.
4. Get Product Image by ID
   Endpoint: GET /api/products/{productId}/image
   Description: Fetches the image of a product by its ID.
   Path Variable: productId (Product ID)
   Response: The image file in byte array format, with the correct content type.
5. Update Product
   Endpoint: PUT /api/products/{id}
   Description: Updates the details and image of an existing product.
   Path Variable: id (Product ID)
   Request Body:
   ProductData object with updated details.
   imageFile (MultipartFile) for the product image.
   Response: 200 if product is successfully updated, or 400 if an error occurs.
6. Delete Product
   Endpoint: DELETE /api/products/{id}
   Description: Deletes a product by ID.
   Path Variable: id (Product ID)
   Response: 200 if product is deleted, 404 if product is not found.
7. Search Products
   Endpoint: GET /api/products/search
   Description: Search for products based on a keyword (e.g., product name, description).
   Request Parameter: keyword (Search term)
   Response: List of products that match the search keyword.
   Tech Stack
   Backend Framework: Spring Boot
   Database: MySQL (configured in application.properties)
   Persistence: Spring Data JPA
   Image Handling: MultipartFile for image upload
   Security: Spring Security (if needed in future)
   Development Tools: Spring Boot DevTools, Lombok
   Setup Instructions
8. Clone the repository
   git clone https://github.com/rudrapratapbehera2003/Simple-E-commerce_java_backend.git
   About
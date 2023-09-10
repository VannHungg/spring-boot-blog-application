# spring-boot-blog-application
Sure, I can generate a more complete readme file for your project. Here is a possible version:

# Spring Boot Blog Application

This is a simple blog application built with Spring Boot, Thymeleaf, Bootstrap, MySQL and JPA. It allows users to create, edit, delete and view blog posts with different categories and tags. It also supports user authentication and authorization with Spring Security and JWT.

## Features

- CRUD operations for blog posts
- Pagination and sorting for blog posts
- Search blog posts by title, category or tag
- User registration and login
- User roles and permissions
- JSON Web Token (JWT) authentication
- RESTful API for blog posts
- Exception handling and validation
- Unit testing with JUnit and Mockito

## Installation

To run this application, you need to have Java 11, Maven and MySQL installed on your machine. You also need to create a database named `blog` in MySQL and configure the username and password in the `application.properties` file.

To clone this repository, run the following command:

```bash
git clone https://github.com/VannHungg/spring-boot-blog-application.git
```

To build the application, run the following command in the project directory:

```bash
mvn clean install
```

To run the application, run the following command in the project directory:

```bash
mvn spring-boot:run
```

The application will be accessible at http://localhost:8080.

## Usage

To use the application, you need to register an account first. You can choose either `ROLE_USER` or `ROLE_ADMIN` as your role. The default role is `ROLE_USER`.

After registering, you can log in with your username and password. You will receive a JWT token that will be stored in your browser's local storage.

As a user, you can create, edit, delete and view your own blog posts. You can also view other users' blog posts, but you cannot edit or delete them. You can also search blog posts by title, category or tag.

As an admin, you can do everything that a user can do, plus you can edit or delete any blog posts created by any user.

To access the RESTful API for blog posts, you need to send a GET request to http://localhost:8080/api/posts with the `Authorization` header set to `Bearer <your JWT token>`. You will receive a JSON response with a list of blog posts.

You can also send a GET request to http://localhost:8080/api/posts/{id} with the `Authorization` header set to `Bearer <your JWT token>` to get a single blog post by its id.

You can also send a POST request to http://localhost:8080/api/posts with the `Authorization` header set to `Bearer <your JWT token>` and a JSON body with the following fields to create a new blog post:

- title (required)
- content (required)
- category (optional)
- tags (optional)

You can also send a PUT request to http://localhost:8080/api/posts/{id} with the `Authorization` header set to `Bearer <your JWT token>` and a JSON body with the same fields as above to update an existing blog post by its id.

You can also send a DELETE request to http://localhost:8080/api/posts/{id} with the `Authorization` header set to `Bearer <your JWT token>` to delete an existing blog post by its id.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

# Project: Spring Social Media Blog API

## Overview
This project serves as the backend for a hypothetical social media app, managing user accounts and messages. Built with the Spring framework, it leverages Spring Boot, Spring MVC, and Spring Data for automatic configuration and data persistence.

## Features Implemented
- User Registration: Create a new account via POST localhost:8080/register. Validates username, password, and avoids duplicates.

- User Login: Verify login via POST localhost:8080/login with JSON representation of an account.

- Message Creation: Submit a new post via POST localhost:8080/messages. Validates message text and user existence.

- Retrieve All Messages: Get all messages via GET localhost:8080/messages.

- Retrieve Message by ID: Get a specific message by ID via GET localhost:8080/messages/{message_id}.

- Delete Message: Delete a message by ID via DELETE localhost:8080/messages/{message_id}.

- Update Message: Update a message text via PATCH localhost:8080/messages. Validates message ID and text length.

- Retrieve User's Messages: Get all messages posted by a user via GET localhost:8080/accounts/{account_id}/messages.

## Technologies Used
- Spring Boot
- Spring MVC
- Spring Data
- Java

## How to Set Up/Get Started
- Ensure you have Java and Maven installed.
- Clone the repository.
- Start the application using Maven or your preferred IDE.
- The application will run on localhost:8080.

## Usage
- Register a new account using POST localhost:8080/register.
- Log in with your account via POST localhost:8080/login.
- Create, retrieve, update, or delete messages as needed.
- Retrieve all messages or messages by a specific user.

## Contributors
Caitlyn Nguyen

## License
Revature

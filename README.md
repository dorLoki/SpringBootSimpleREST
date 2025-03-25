# Spring Boot Simple REST

This project is a simple REST service built with Spring Boot, developed as part of a university project for an Alexa Skill.

## Overview

The application allows authorized users to set a custom message, which can then be retrieved via a REST API. It includes a basic backend authentication mechanism to secure message updates.

## Features

- **Custom Message Management:** Authenticated users can set a custom message.
- **Public Access:** The custom message is accessible through a public REST API endpoint.

## Endpoints

### `/login`

- **Description:** Provides a simple login mechanism.
- **Default Credentials:**
  - **Username:** `admin`
  - **Password:** `test123`

*Note: It is recommended to update these default credentials in the `InitDB.java` file.*

### `/admin/api`

- **Description:** Allows authenticated users to update the custom message.

### `/message`

- **Description:** Public endpoint to retrieve the current custom message.

## Technology Stack

- **Framework:** Spring Boot

## Setup and Usage

1. **Clone the Repository:**  
   Obtain the source code from the repository.

2. **Configuration:**  
   Configure the necessary environment settings and update the default credentials in `InitDB.java` if needed.

3. **Build the Project:**  
   Use Gradle to build the project.

4. **Run the Application:**  
   Start the Spring Boot application.

5. **Access the Endpoints:**  
   - Use `/login` for authentication.
   - Update the custom message via `/admin/api`.
   - Retrieve the message using `/message`.

## Additional Note

For enhanced security, it is highly recommended to configure SSL for the application. For detailed instructions, please refer to the [SSL Configuration Guide](https://docs.spring.io/spring-boot/reference/features/ssl.html).

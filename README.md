# oauth
# OAuth Java Spring Boot Example

This is a simple example application demonstrating how to implement OAuth authentication using Java and the Spring Boot framework. The application showcases the OAuth 2.0 authorization code grant type.

## Prerequisites

Before running this application, make sure you have the following installed:

- Java Development Kit (JDK) 11 or later
- Apache Maven
- An IDE of your choice (e.g., IntelliJ IDEA, Eclipse)

## Getting Started

1. Clone this repository to your local machine or download the source code as a ZIP file.

2. Open the project in your IDE.

3. Configure the application:

   - Open the `src/main/resources/application.properties` file.
   - Update the following properties according to your OAuth provider's configuration:
     - `spring.security.oauth2.client.registration.<registration-id>.client-id`: The client ID provided by your OAuth provider.
     - `spring.security.oauth2.client.registration.<registration-id>.client-secret`: The client secret provided by your OAuth provider.
     - `spring.security.oauth2.client.registration.<registration-id>.redirect-uri`: The redirect URI configured in your OAuth provider.
     - `spring.security.oauth2.client.provider.<provider-id>.authorization-uri`: The authorization URI provided by your OAuth provider.
     - `spring.security.oauth2.client.provider.<provider-id>.token-uri`: The token URI provided by your OAuth provider.
     - `spring.security.oauth2.client.provider.<provider-id>.user-info-uri`: The user info URI provided by your OAuth provider.

4. Build the project using Maven:

   ```shell
   mvn clean install


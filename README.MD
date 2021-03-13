# horse-managment

A simple full stack application implementing Spring Boot and Angular.

This was created to implement a basic Spring Boot Backend and a basic Angular Frontend.

The styling is a mix of Bootstrp and custom CSS.

## Installation

### Backend

In order to start the backend server you need JDK 11 or higher.

The server in the folder backend contains a Maven wrapper, so you don't have to install Maven on your local machine. In order to start the server navigate into the backend folder and start Spring Boot with:

```
./mvnw spring-boot:run
```

### Frontend

The frontend needs to install all Angular dependencies first. In order to do this navigate to the folder frontend/wendys-friends and type:

```
npm run install
```

In order to start the application use the command:

```
npm run start
```

## Usage

The app gives the user to save horse owners and horses. Every horse needs to have a owner. Owners can only be deleted when they don't have any horses any more.

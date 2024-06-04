# Parking System

A JavaFX-based parking system application for managing vehicle parking, including motorcycles and cars. This application supports user authentication, vehicle entry and exit logging, and calculates parking fees based on the type of vehicle and duration of parking.

## Features

- User authentication (login and registration)
- Vehicle entry and exit logging
- Calculates parking fees based on the type of vehicle and parking duration
- Modern and intuitive user interface

## Requirements

- Java 17 or later
- Gradle
- SQLite

## Getting Started

### Clone the Repository

```sh
git clone https://github.com/NojsNojin/parking-system.git
cd parking-system
```

### Build and Run the Application

1. Navigate to the project directory:

```sh
cd parking-system
```

2. Build and run the application using Gradle:

```sh
.\gradlew.bat run
```

### Project Structure

```plaintext
parking-system
├── build.gradle
├── gradlew.bat
├── gradlew
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── parking
│       │               ├── MainApp.java
│       │               ├── controllers
│       │               │   ├── LoginController.java
│       │               │   ├── RegisterController.java
│       │               │   ├── ParkingController.java
│       │               └── models
│       │                   ├── Database.java
│       │                   ├── User.java
│       │                   ├── Vehicle.java
│       └── resources
│           ├── fxml
│           │   ├── login.fxml
│           │   ├── register.fxml
│           │   ├── parking.fxml
│           └── css
│               └── styles.css
```

### Database Schema

The application uses SQLite as the database. The following tables are created:

#### Users Table
- `username` (TEXT, PRIMARY KEY): The username of the user.
- `password` (TEXT): The password of the user.

#### Vehicles Table
- `number` (TEXT, PRIMARY KEY): The vehicle number.
- `entryTime` (INTEGER): The timestamp when the vehicle entered.
- `isMotorcycle` (INTEGER): Indicates if the vehicle is a motorcycle (1) or not (0).

### How It Works

1. **User Authentication**:
   - Users can register by providing a username and password.
   - Registered users can log in using their credentials.
   - Upon successful login, users are redirected to the parking management screen.

2. **Parking Management**:
   - Users can log vehicle entry and exit.
   - The application calculates parking fees based on the type of vehicle (motorcycle or car) and the duration of parking.
   - Parking fees for motorcycles:
     - First hour: Rp2,000
     - Subsequent hours: Rp1,000 per hour
     - Overnight penalty: Rp20,000
   - Parking fees for cars:
     - First hour: Rp5,000
     - Subsequent hours: Rp4,000 per hour
     - Overnight penalty: Rp50,000

### License

This project is licensed under the MIT License.

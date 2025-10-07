# Travel Agency Application

A full-stack travel agency application with React frontend and Java Spring Boot backend.

![Travel Agency React App](assets/Travel%20Agency%20React%20App.gif)

## Project Structure

```
travel-agency-react-app/
├── frontend/                 # React application
│   ├── src/
│   │   ├── components/       # React components
│   │   ├── pages/           # Page components
│   │   ├── services/        # API service layer
│   │   └── types/           # TypeScript type definitions
│   ├── public/              # Static assets
│   ├── package.json
│   └── .env                 # Frontend environment variables
├── backend/                 # Java Spring Boot API
│   ├── src/main/java/com/travelagency/
│   │   ├── model/           # JPA entities
│   │   ├── repository/      # Data repositories
│   │   ├── controller/      # REST controllers
│   │   ├── config/          # Configuration classes
│   │   └── TravelAgencyApplication.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml              # Maven dependencies
└── README.md
```

## Database Schema

The application uses a PostgreSQL database (Neon) with the following tables:

- **travel_user**: User accounts and profiles
- **travel_destination**: Available travel destinations
- **travel_booking**: User bookings for destinations
- **travel_review**: User reviews for destinations

## Setup Instructions

### Prerequisites

- Node.js 16+ and npm
- Java 17+
- Maven 3.6+
- PostgreSQL database (Neon)

### Backend Setup

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Install dependencies and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. The backend API will be available at `http://localhost:8080`

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm start
   ```

4. The frontend will be available at `http://localhost:3000`

## API Endpoints

### Health Check
- `GET /api/health` - Check API status

### Users
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Destinations
- `GET /api/destinations` - Get all destinations
- `GET /api/destinations/{id}` - Get destination by ID
- `POST /api/destinations` - Create new destination
- `PUT /api/destinations/{id}` - Update destination
- `DELETE /api/destinations/{id}` - Delete destination

### Bookings
- `GET /api/bookings` - Get all bookings
- `GET /api/bookings/{id}` - Get booking by ID
- `GET /api/bookings/user/{userId}` - Get bookings by user
- `POST /api/bookings` - Create new booking
- `PUT /api/bookings/{id}` - Update booking
- `DELETE /api/bookings/{id}` - Delete booking

### Reviews
- `GET /api/reviews` - Get all reviews
- `GET /api/reviews/{id}` - Get review by ID
- `GET /api/reviews/destination/{destinationId}` - Get reviews by destination
- `POST /api/reviews` - Create new review
- `PUT /api/reviews/{id}` - Update review
- `DELETE /api/reviews/{id}` - Delete review

## Environment Variables

### Frontend (.env)
```
REACT_APP_API_URL=http://localhost:8080/api
```

### Backend (application.properties)
```
spring.datasource.url=jdbc:postgresql://[neon-host]/neondb
spring.datasource.username=[username]
spring.datasource.password=[password]
```

## Changes Made

1. **Project Restructuring**: 
   - Created `frontend/` and `backend/` directories
   - Moved React app files to `frontend/`
   - Created Java Spring Boot project in `backend/`

2. **Frontend Updates**:
   - Updated API service to call local backend instead of GibsonAI
   - Changed API endpoints to use RESTful conventions
   - Added environment configuration for backend URL

3. **Backend Implementation**:
   - Created JPA entities matching the database schema
   - Implemented Spring Data repositories
   - Created REST controllers with full CRUD operations
   - Added proper CORS configuration
   - Connected to Neon PostgreSQL database

4. **Database Integration**:
   - Configured Spring Boot to connect to Neon database
   - Used PostgreSQL-compatible syntax and data types
   - Implemented proper foreign key relationships
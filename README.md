# LeadManager

**LeadManager** is application that allows users to manage leads and activities. 
This project includes a backend built with Spring Boot (using an H2 database for demo purposes) and a frontend built with React, Vite, and Tailwind CSS.
It’s designed as a demo to showcase key features like user authentication, lead and activity management, and integration with external services.

## Features

- **User Authentication:** Login and registration with JWT-based security.
- **Lead Management:** Create, update, view, and delete leads.
- **Activity Management:** Associate activities (calls, emails, meetings) with leads.
- **Responsive UI:** Built with React, Vite, and styled using Tailwind CSS.
- **Demo Database:** Uses an H2 in-memory database for quick testing.
- **Deployment:** Containerized with Docker and deployed on Render.

## Technologies

- **Backend:** Java, Spring Boot, H2 Database, Maven, Docker
- **Frontend:** React, Vite, Tailwind CSS, Axios, React Router
- **Deployment:** Render (Docker and Static Site deployments)

## Getting Started

### Prerequisites

- Java 17
- Maven
- Node.js and npm


### Backend Setup

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/janis-karklins88/lead-management-backend.git
2. Navigate to Project Directory
   ```bash
   cd lead-management-backend
3. Buld the Application
   ```bach
   ./mvnw clean package -DskipTests
4. Run the application
   ```bash
   java -jar target/Salesforelikeapp-0.0.1-SNAPSHOT.jar
5. Health Check
   
   Access the health endpoint at http://localhost:8080/api/health to verify the backend is running.

### Frontend Setup

1. Clone the Repository
   ```bash
   git clone https://github.com/janis-karklins88/LeadManagerFront.git
2. Navigate to the Project Directory
   ```bash
   cd LeadManagerFront
3. Install dependencies
   ```bash
   npm install
4. Start the development Server
   ```bash
   npm run dev
5. Access the App
   Open http://localhost:5173 in your browser.
   
6. Configure API URL:
   
   Change API_URL to http://localhost:8080/api in the relevant files (e.g., api.js, LoginPage.jsx, RegisterPage.jsx).



### Deployment

#### Backend:
- Deployed on Render as a Docker service using the provided Dockerfile.

#### Frontend:
- Deployed on Render as a static site.

### License
This project is licensed under the MIT License.

### Contact
For questions or support, please contact janis.karklins88@gmail.com

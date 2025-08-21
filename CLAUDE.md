# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Development Commands

### Building and Running
```bash
# Build the project
./gradlew build

# Run the application (development)
./gradlew bootRun

# Run with specific profile
./gradlew bootRun --args='--spring.profiles.active=local'

# Build executable jar
./gradlew bootJar
```

### Testing
```bash
# Run all tests
./gradlew test

# Run test classes
./gradlew testClasses

# View test reports at: build/reports/tests/test/index.html
```

### Code Quality
```bash
# Run ktlint check
./gradlew ktlintCheck

# Auto-format code with ktlint
./gradlew ktlintFormat

# Run all verification tasks (tests + linting)
./gradlew check
```

### Database
```bash
# Flyway migrations are run automatically on startup when flyway.enabled=true
# Migration files are in: src/main/resources/db/migration/local/ (for local profile)
```

## Architecture Overview

### Application Structure
This is a **layered + hexagonal architecture** Spring Boot application with the following flow:
```
Controller → Facade → Service → Repository → Entity
```

Key architectural patterns:
- **Facade Pattern**: Business logic orchestration layer between controllers and services
- **CQRS-like Separation**: Separate Command/Query services (e.g., `ArtistCommandService`, `ArtistQueryService`)
- **Port/Adapter**: Partial implementation with `AuthPort` for external integrations

### Core Domain Entities
- **Artist**: VTuber/Artist profiles with agency relationships
- **Member**: User accounts with Kakao OAuth integration
- **Agency**: Organizations that manage artists
- **Fandom**: Fan communities associated with artists
- **Channel**: Social media/platform channels for artists

### Security & Authentication
- **JWT-based authentication** with access/refresh token pattern
- **Kakao OAuth 2.0** for user registration/login
- **Jasypt encryption** for sensitive configuration (database credentials, etc.)
- **HTTP-Only cookies** for refresh token storage
- Security configuration allows public access to `/api/auth/**` and `/api/home`

### Database
- **PostgreSQL** with **Flyway** for schema migrations
- **JPA + QueryDSL** for data access
- Profile-specific migration paths: `db/migration/local/`, `db/migration/dev/`
- Custom repositories with `*CustomRepository` interfaces for complex queries

### Configuration Profiles
- **local**: Development environment with local PostgreSQL
- **dev**: Development environment with external database
- Encrypted database credentials using Jasypt (look for `ENC(...)` values)

## Key Implementation Notes

### Adding New Entities
1. Create entity class extending `BaseTimeEntity` in `domain/entity/`
2. Create corresponding DTOs in `domain/dto/request/` and `domain/dto/response/`
3. Add repository interface in `repository/[entity]/`
4. Implement Command/Query services in `service/[entity]/`
5. Create facade in `facade/`
6. Add controller in `controller/`

### Authentication Flow
- Login: `/api/auth/login` → Kakao OAuth → JWT tokens
- Refresh: `/api/auth/refresh` → New access token from refresh cookie
- Logout: `/api/auth/logout` → Clear refresh token cookie

### Testing
- Uses **JUnit 5** with **Kotest** assertions and **MockK** for mocking
- Test framework configured but no test directory structure exists yet

### Database Changes
- Add new migration files in `src/main/resources/db/migration/local/`
- Follow naming convention: `V[timestamp]__[description].sql`
- Migrations run automatically on application startup
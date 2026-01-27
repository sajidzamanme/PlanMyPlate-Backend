# PlanMyPlate - Quick Start Guide

## 5-Minute Setup

### Step 1: Prerequisites Check
```bash
# Check Java version
java -version
# Expected: Java 21 or higher

# Check MySQL
mysql --version
# Expected: MySQL 10.4 or higher
```

### Step 2: Database Setup
```bash
# Login to MySQL
mysql -u root -p

# Create database
CREATE DATABASE plan_my_plate;

# Exit MySQL
exit
```

### Step 3: Import Schema
```bash
# Navigate to project
cd /home/sajidzaman/Study/dbms/PlanMyPlate

# Import the schema
mysql -u root -p plan_my_plate < /path/to/plan_my_plate.sql
```

### Step 4: Update Configuration
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:8050/plan_my_plate
spring.datasource.username=root
spring.datasource.password=your_password
```

### Step 5: Build & Run
```bash
# Build
./gradlew clean build

# Run
./gradlew bootRun
```

**API Available at:** `http://localhost:8081/api`

---

## Quick Test

### 1. Sign Up
```bash
curl -X POST http://localhost:8081/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test User",
    "email": "test@example.com",
    "password": "Test@123"
  }'
```

**Copy the token from response**

### 2. Test Authenticated Endpoint
```bash
curl -X GET http://localhost:8081/api/users/me \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

### 3. Create a Recipe
```bash
curl -X POST http://localhost:8081/api/recipes \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Pasta",
    "description": "Delicious pasta",
    "calories": 400
  }'
```

### 4. Get All Recipes
```bash
curl -X GET http://localhost:8081/api/recipes
```

---

## Common Commands

### Start Application
```bash
cd /home/sajidzaman/Study/dbms/PlanMyPlate
./gradlew bootRun
```

### Run Tests
```bash
./gradlew test
```

### Build JAR
```bash
./gradlew build
java -jar build/libs/PlanMyPlate-0.0.1-SNAPSHOT.jar
```

### View Logs
```bash
# Real-time logs
./gradlew bootRun --info

# Check logs in application
tail -f build/logs/spring.log
```

### Clean Build
```bash
./gradlew clean build
```

---

## API Endpoints Cheat Sheet

| Feature | Method | Endpoint |
|---------|--------|----------|
| Signup | POST | `/auth/signup` |
| Login | POST | `/auth/signin` |
| Reset Password | POST | `/auth/reset-password` |
| Get Recipes | GET | `/recipes` |
| Create Recipe | POST | `/recipes` |
| Search Recipes | GET | `/recipes/search?name=` |
| Get Meal Plans | GET | `/meal-plans/user/{id}` |
| Create Meal Plan | POST | `/meal-plans/user/{id}` |
| Get Groceries | GET | `/grocery-lists/user/{id}` |
| Create Grocery | POST | `/grocery-lists/user/{id}` |
| Get Inventory | GET | `/inventory/user/{id}` |
| Create Inventory | POST | `/inventory/user/{id}` |
| Get Ingredients | GET | `/ingredients` |
| Create Ingredient | POST | `/ingredients` |

---

## Troubleshooting

### Port 8081 Already in Use
```bash
# Change port in application.properties
server.port=8082
```

### MySQL Connection Failed
```bash
# Start MySQL
sudo systemctl start mysql

# Check connection
mysql -u root -p
```

### Database Not Found
```bash
# Create database
mysql -u root -p < plan_my_plate.sql
```

### Build Fails
```bash
# Clean and rebuild
./gradlew clean build --refresh-dependencies
```

---

## IDE Setup (VS Code)

1. Install Extension Pack for Java
2. Open folder in VS Code
3. Wait for Gradle to sync
4. Run application from Command Palette: "Java: Start Debugging"

---

## IDE Setup (IntelliJ IDEA)

1. Open project
2. Configure SDK: File > Project Structure > SDK
3. Run > Edit Configurations > Add Spring Boot configuration
4. Click Run button (Ctrl+R)

---

## API Testing Tools

### Option 1: Postman
1. Download Postman
2. Import collection from documentation
3. Set authorization token
4. Test endpoints

### Option 2: cURL
Use commands provided in this guide

### Option 3: Thunder Client (VS Code)
Install extension and create requests

---

## Next Steps

1. Read `README.md` for detailed setup
2. Review `API_DOCUMENTATION.md` for all endpoints
3. Follow `TESTING_GUIDE.md` for comprehensive testing
4. Check `IMPLEMENTATION_SUMMARY.md` for technical details

---

## Key Features to Try

- ✅ Sign up and log in
- ✅ Create recipes
- ✅ Create meal plans
- ✅ Create grocery lists
- ✅ Create inventory and add items
- ✅ Search and filter recipes
- ✅ Set user preferences

---

## Support Resources

- **Documentation:** See `README.md` and `API_DOCUMENTATION.md`
- **Testing Guide:** See `TESTING_GUIDE.md`
- **Implementation Details:** See `IMPLEMENTATION_SUMMARY.md`
- **Code Comments:** Check source code

---

## Environment Variables (Optional)

```bash
# Create .env file (if needed)
DATABASE_URL=jdbc:mysql://localhost:8050/plan_my_plate
DATABASE_USER=root
DATABASE_PASSWORD=your_password
JWT_SECRET=your_jwt_secret
JWT_EXPIRATION=86400000
```

---

## Production Deployment

### Prerequisites
- Server with Java 21
- MySQL 10.4+
- 2GB RAM minimum
- 10GB storage

### Steps
```bash
# Build
./gradlew build

# Deploy JAR
java -jar build/libs/PlanMyPlate-0.0.1-SNAPSHOT.jar \
  --spring.datasource.url=jdbc:mysql://prod-db:3306/plan_my_plate \
  --spring.datasource.username=prod_user \
  --spring.datasource.password=strong_password
```

---

## Performance Tips

- Enable query caching in MySQL
- Use connection pooling (HikariCP is default)
- Implement Redis caching for recipes
- Add indexes to frequently queried columns
- Monitor database performance

---

## Security Checklist

- [ ] Change default JWT secret
- [ ] Use strong MySQL passwords
- [ ] Enable HTTPS in production
- [ ] Configure firewall rules
- [ ] Enable SQL injection protection
- [ ] Regular security updates
- [ ] Backup database regularly
- [ ] Monitor logs for suspicious activity

---

**Happy Coding! 🚀**

For detailed documentation, see the other markdown files in this directory.

---

**Version:** 1.0.0
**Last Updated:** January 28, 2026

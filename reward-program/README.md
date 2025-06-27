# Reward Calculator Program

This is a Spring Boot REST API that calculates reward points for customers based on their transaction amounts per month & total.

## Reward Rules
- $1 - $50 -> 0 points  
- $51 - $100 -> 1 point per $1 over $50  
- Over $100 -> 2 points per $1 over $100 + 50 points for the previous tier
---
## Project Structure
---
src/
- main/
- java/com/retail/reward/
- controller/RewardController.java
- model/Customer.java
- model/Transaction.java
- service/RewardService.java
- test/
- java/com/retail/reward/
- controller/RewardControllerTest.java
- service/RewardServiceTest.java

## Sample Response

GET/api/getRewardsData

{
    "CID01": {
        "name": "Virendra",
        "totalPoints": 365,
        "monthlyPoints": {
            "JANUARY": 90,
            "FEBRUARY": 25,
            "MARCH": 250
        }
    }
}

## How to Run the App
```bash
# Step 1: Clean and build
mvn clean install
# Step 2: Run the app
mvn spring-boot:run

# How to Run tests
mvn test

# Customer Reward Point Project

## Tech stack:
 Java 17, Spring Boot, Spring JPA, h2, Junit, Mockito

## Execute the Application
### 1. Git Clone the project
### 2. mvn clean install
### 3. Run the application using Spring Boot under:
```src/main/java/com/example/rewardpointsoa/RewardPointsOaApplication.java```

## Test application
### 1. Use the POST endpoint for saving some customer data
Test Request Body:
```
[
{
   "customerId": 1,
   "purchaseAmount": 120,
   "transactionDate": "2023-01-15"
 },
  {
   "customerId": 1,
   "purchaseAmount": 100,
   "transactionDate": "2023-02-15"
 },
  {
   "customerId": 1,
   "purchaseAmount": 50,
   "transactionDate": "2023-01-15"
 },
  {
   "customerId": 2,
   "purchaseAmount": 120,
   "transactionDate": "2023-02-15"
 }
]
```
Expected Output:
```
[
    {
        "id": 1,
        "customerId": 1,
        "purchaseAmount": 120.0,
        "rewardPoints": 90,
        "transactionDate": "2023-01-15"
    },
    {
        "id": 2,
        "customerId": 1,
        "purchaseAmount": 100.0,
        "rewardPoints": 50,
        "transactionDate": "2023-02-15"
    },
    {
        "id": 3,
        "customerId": 1,
        "purchaseAmount": 50.0,
        "rewardPoints": 0,
        "transactionDate": "2023-01-15"
    },
    {
        "id": 4,
        "customerId": 2,
        "purchaseAmount": 120.0,
        "rewardPoints": 90,
        "transactionDate": "2023-02-15"
    }
]
```
### 2. Test GET call for getting each Customer's each month points by using endpoint:
```
http://localhost:8080/api/transactions/customer/1/monthly-reward-points
```
Expected output:
```
{
    "FEBRUARY": 50,
    "JANUARY": 90
}
```
and
```
http://localhost:8080/api/transactions/customer/2/monthly-reward-points
```
Expected output:
```
{
    "FEBRUARY": 90
}
```

### 3. Test GET call for getting each customer's total points by the endpoint
```
http://localhost:8080/api/transactions/customer/1/total-reward-points
```
Expected Output:
```
140
```
and
```
http://localhost:8080/api/transactions/customer/2/total-reward-points
```
Expected Output:
```
90
```

**SHOPPING CART SERVICE APPLICATION**
**Introduction**
This project is a backend service for a shopping cart application. The service provides functionalities to manage shopping carts, products, orders, and customers. This README file outlines the main APIs available for the Shopping Cart entity and briefly describes the APIs for Product, Order, and Customer entities.

**Entities**

Shopping Cart
**Shopping Cart Entity**: Manages the items added to the shopping cart by the customer.
**Product Entity**: Manages the products available for purchase.
**Order Entity**: Manages the orders placed by customers.
**Customer Entity**: Manages the customers using the shopping cart service

I have Created following API's for Items in the Cart

**1) Add to Cart**
**2) Get the Items in the Cart**
**3) Delete the Item From Cart**
**4) Update the Item Quantity in the Cart**


**4) GET - http://localhost:8080/api/getAllProducts** 

**Test Response** 


[
    {
        "id": 1,
        "name": "Powder",
        "availableQuantity": 4,
        "price": 900.0
    },
    {
        "id": 2,
        "name": "mobile phone",
        "availableQuantity": 45,
        "price": 7999.0
    },
    {
        "id": 3,
        "name": "Bed Sheets",
        "availableQuantity": 3,
        "price": 299.0
    }
]


 **5) POST - http://localhost:8080/api/placeOrder**
 
 Request Payload   
 {
     "orderDescription": "Test Order Description",
     
     "cartItems": [
         {
             "productId": 1,
             "quantity": 2
         },
         {
             "productId": 3,
             "quantity": 7
         }
     ],
     "customerEmail": "krishna@gmail.com",
     "customerName": "Krishna Chaitanya"
 }
 
 **Response** 
 
 
  {
      "amount": 10893.0,
      "invoiceNumber": 987,
      "date": "08-24-2024 23:16:40",
      "orderId": 1,
      "orderDescription": "This order is for my friend's Birthday"
  }
  
  **3 GET - http://localhost:8080/api/getOrder/3**
  
  Response
  
  {
      "id": 3,
      "orderDescription": "This order is for my friend's Engagement",
      
      "customer": {
          "id": 2,
          "name": "krishna Chaitanya",
          "email": "krishna@gmail.com"
      },
      "cartItems": [
          {
              "id": 4,
              "productId": 1,
              "productName": "powder",
              "quantity": 1,
              "amount": 900.0
          }
      ]
  }
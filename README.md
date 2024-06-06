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

**End point : http://localhost:8080/api/add-itemstocart**

Sample Input Format for JSON:
[
    {
      "name": "Trash bag",
      "price": 10.99,
      "quantity": 2
    },
    {
      "name": "Sofa",
      "price": 25.50,
      "quantity": 1
    },
    {
      "name": "Television",
      "price": 155.75,
      "quantity": 3
    }
]

**2) Get the Items in the Cart**(This Api is for viewing all the Cart Items)

**End Point :http://localhost:8080/api/viewcart**

Expected Output Format:

[
    {
        "id": 1,
        "name": "Product 1",
        "price": 10.99,
        "quantity": 2
    },
    {
        "id": 2,
        "name": "Product 2",
        "price": 25.5,
        "quantity": 1
    },
    {
        "id": 3,
        "name": "Product 3",
        "price": 15.75,
        "quantity": 3
    },
    {
        "id": 4,
        "name": "Trash bag",
        "price": 10.99,
        "quantity": 2
    },
    {
        "id": 5,
        "name": "Sofa",
        "price": 25.5,
        "quantity": 1
    },
    {
        "id": 6,
        "name": "Television",
        "price": 155.75,
        "quantity": 3
    }
]

**3) Delete the Item From Cart**

**End Point: http://localhost:8080/api/delete/1** Item 1 should be deleted from the cart

Sample Input:
{
     "id": 1,
    "quantity": 6
}

Sample Output:
Item has been deleted

**4) Update the Item Quantity in the Cart**

**End Point: http://localhost:8080/api/update/2** It Should update the item no 2 in the cart

Sample Input:
{
     "id": 2,
    "quantity": 66
}

Expected output:
It Should be able to update the quantity of 66 for the item no 2 in the cart

**Apart from CartItem service i have also create a relation between order and customer and cart items and the Apis are as follows**

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

 **Sample Input**

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
  
  **6) GET - http://localhost:8080/api/getOrder/4**(this is the relation between Order,Customer and cart Item)
  
  Response
  
  {
      "id": 4,
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

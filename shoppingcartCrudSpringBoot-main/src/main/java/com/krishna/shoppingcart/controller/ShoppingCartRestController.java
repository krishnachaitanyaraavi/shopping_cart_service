package com.krishna.shoppingcart.controller;


import com.krishna.shoppingcart.dto.OrderDTO;
import com.krishna.shoppingcart.dto.ResponseOrderDTO;
import com.krishna.shoppingcart.entity.CartItem;
import com.krishna.shoppingcart.entity.Customer;
import com.krishna.shoppingcart.entity.Order;
import com.krishna.shoppingcart.entity.Product;
import com.krishna.shoppingcart.service.CartService;
import com.krishna.shoppingcart.service.CustomerService;
import com.krishna.shoppingcart.service.OrderService;
import com.krishna.shoppingcart.service.ProductService;
import com.krishna.shoppingcart.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class ShoppingCartRestController {

    private OrderService orderService;
    private ProductService productService;
    private CustomerService customerService;
    @Autowired
    private CartService cartService;


    public ShoppingCartRestController(OrderService orderService, ProductService productService, CustomerService customerService) {
        this.orderService = orderService;
        this.productService = productService;
        this.customerService = customerService;
    }

    private Logger logger = LoggerFactory.getLogger(ShoppingCartRestController.class);

    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> productList = productService.getAllProducts();

        return ResponseEntity.ok(productList);
    }

    @GetMapping(value = "/getOrder/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable int orderId) {

        Order order = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(order);
    }


    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseOrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        logger.info("Request Payload " + orderDTO.toString());
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
        float amount = orderService.getCartAmount(orderDTO.getCartItems());

        Customer customer = new Customer(orderDTO.getCustomerName(), orderDTO.getCustomerEmail());
        Integer customerIdFromDb = customerService.isCustomerPresent(customer);
        if (customerIdFromDb != null) {
            customer.setId(customerIdFromDb);
            logger.info("Customer already present in db with id : " + customerIdFromDb);
        }else{
            customer = customerService.saveCustomer(customer);
            logger.info("Customer saved.. with id : " + customer.getId());
        }
        Order order = new Order(orderDTO.getOrderDescription(), customer, orderDTO.getCartItems());
        order = orderService.saveOrder(order);
        logger.info("Order processed successfully..");

        responseOrderDTO.setAmount(amount);
        responseOrderDTO.setDate(DateUtil.getCurrentDateTime());
        responseOrderDTO.setInvoiceNumber(new Random().nextInt(1000));
        responseOrderDTO.setOrderId(order.getId());
        responseOrderDTO.setOrderDescription(orderDTO.getOrderDescription());

        logger.info("test push..");

        return ResponseEntity.ok(responseOrderDTO);
    }


    @PostMapping("/addtocart")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody CartItem cartItemDTO) {
        logger.info("Request Payload: " + cartItemDTO.toString());
        CartItem addedItem = cartService.addItemToCart(cartItemDTO);
        logger.info("Item added to cart: " + addedItem.toString());
        return ResponseEntity.ok(addedItem);
    }

    @GetMapping("/viewcart")
    public ResponseEntity<List<CartItem>> viewCartItems() {
        List<CartItem> cartItems = cartService.viewCartItems();
        logger.info("Cart items retrieved: " + cartItems.toString());
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItemFromCart(@PathVariable Long id) {
        logger.info("Deleting item with id: " + id);
        cartService.deleteItemFromCart(id);
        logger.info("Item deleted with id: " + id);
        return ResponseEntity.ok("Item has been deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CartItem> updateItemQuantity(@PathVariable Long id, @RequestBody CartItem cartItemDTO) {
        CartItem updatedItem = null;
        logger.info("Updating item quantity for id: " + id + " to " + cartItemDTO.getQuantity());
        if(Objects.nonNull(cartItemDTO)){
            if(Objects.nonNull(cartItemDTO.getQuantity())){
                updatedItem = cartService.updateItemQuantity(id, cartItemDTO.getQuantity());
            }
        }
        logger.info("Item updated: " + updatedItem.toString());
        return ResponseEntity.ok(updatedItem);
    }

    @PostMapping("/addproduct")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PostMapping("/addMultiple")
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> products) {
        List<Product> addedProducts = productService.addProducts(products);
        return ResponseEntity.ok(addedProducts);
    }
}

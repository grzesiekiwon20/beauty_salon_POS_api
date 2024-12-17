package com.beautysalon.cart;


import com.beautysalon.cart.dto.CartResponse;
import com.beautysalon.cartitem.CartItem;
import com.beautysalon.cartitem.CartItemRepository;
import com.beautysalon.cartitem.CartItemService;
import com.beautysalon.cartitem.dto.CartItemResponse;
import com.beautysalon.customer.Customer;
import com.beautysalon.customer.CustomerRepository;
import com.beautysalon.customer.CustomerService;
import com.beautysalon.exception.CartAlreadyExistsException;
import com.beautysalon.product.InventoryStatus;
import com.beautysalon.product.Product;
import com.beautysalon.product.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemService cartItemService;
    private final CartMapper cartMapper;

    public CartService(CartRepository cartRepository, CustomerRepository customerRepository, CustomerService customerService, ProductRepository productRepository, CartItemRepository cartItemRepository, CartItemService cartItemService, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartItemService = cartItemService;
        this.cartMapper = cartMapper;
    }

    @Transactional
    public Long createCartForCustomer(Authentication authentication) {
        Customer customer = getOrCreateCustomer(authentication);
        if (cartRepository.findByCustomerId(customer.getId()) == null) {
            Cart cart = new Cart();
            cart.setCustomer(customer);
            cart.setCartItems(new HashSet<>());
        }
        return cartRepository.findByCustomerId(customer.getId()).getId();
    }

    public CartResponse findCartResponse(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NoSuchElementException("No cart found with id: " + cartId));
        return cartMapper.mapCart(cart);
    }

    @Transactional
    public CartResponse addProductToCart(Long productId, Integer quantity, Authentication authentication) {
        Customer customer = getOrCreateCustomer(authentication);

        Cart cart = cartRepository.findByCustomerId(customer.getId());


        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("No product found with id: " + productId));

        if (Objects.equals(product.getInventoryStatus(), InventoryStatus.OutOfStock)) {
            throw new RuntimeException("Product is out of stock!");
        }
        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("You can't add more products to cart than there is available!");
        }
        Long cartItemId = cartItemService.createCartItem(cart.getId(), product.getId(), quantity);

        CartItem cartItem = cartItemRepository
                .findById(cartItemId)
                .orElseThrow(
                        () -> new NoSuchElementException("No cart item found")
                );

        Set<CartItem> cartProductList = cart.getCartItems();
        cartProductList.add(cartItem);
        cart.setCartItems(cartProductList);
        cart.setTotalPrice(getTotalPrice(cartProductList));

        Long cartId = cartRepository.save(cart).getId();

        return cartRepository
                .findById(cartId)
                .map(cartMapper::mapCart)
                .orElseThrow(
                        () -> new NullPointerException("No cart found with id: " + cartId)
                );

    }

    private Customer getOrCreateCustomer(Authentication authentication) {
        Customer customer = customerRepository.findCustomerByUserKeycloakId(authentication.getName());
        if (customer == null) {
            Long userId = customerService.saveUserIntoRepository(authentication.getName());
            customer = customerRepository.findCustomerByUserKeycloakId(authentication.getName());
        }
        return customer;
    }

    private Double getTotalPrice(Set<CartItem> cartItems) {
        double total = 0.00;
        for (CartItem cartItem : cartItems) {
            double discount = cartItem.getProduct().getDiscount() / 100;
            total += cartItem.getSubTotal() - (cartItem.getSubTotal() * discount);
        }
        return total;
    }

}

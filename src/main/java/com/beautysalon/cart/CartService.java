package com.beautysalon.cart;

import com.beautysalon.product.Product;
import com.beautysalon.product.ProductRepository;
import com.beautysalon.user.UserServiceImpl;
import com.beautysalon.user.dto.UserEntityResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserServiceImpl userService;
//    private final CartMapper cartMapper;


    public CartResponse getCart(Authentication authentication, HttpSession session) {
        Cart cart = new Cart();

        if (authentication == null) {
            if (!cartRepository.existsById(session.getId())) {
                cart.setCartId(session.getId());
                cart.setCartItems(new HashSet<>());
                cart.setTotal(0.00);
            } else {
                Cart existingSessionCart = cartRepository.findById(session.getId()).orElseThrow(() -> new EntityNotFoundException("No cart found with id " + session.getId()));
                return mapCartResponse(existingSessionCart);
            }
        } else {
            UserEntityResponse loggedInUser = userService.getLoggedInUserDetails(authentication);
            if (!cartRepository.existsById(loggedInUser.userId())) {
                cart.setCartId(loggedInUser.userId());
                cart.setTotal(0.00);
                cart.setCartItems(new HashSet<>());
            } else {
                Cart existingAuthenticatedCart = cartRepository.findById(loggedInUser.userId()).orElseThrow(() -> new EntityNotFoundException("No cart found with id " + loggedInUser.userId()));
                return mapCartResponse(existingAuthenticatedCart);
            }
        }
        Cart cartCreated = cartRepository.save(cart);
        return mapCartResponse(cartCreated);
    }

    public CartResponse findCart(String cartId) {
        final Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("No cart found with id: " + cartId));
        return this.mapCartResponse(cart);
    }
//
//    @Transactional
//    public CartResponse addProductToCart(Long productId, Integer quantity, Authentication authentication) {
//        Customer customer = getOrCreateCustomer(authentication);
//
//        Cart cart = cartRepository.findByCustomerId(customer.getId());
//
//
//        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("No product found with id: " + productId));
//
//        if (Objects.equals(product.getInventoryStatus(), InventoryStatus.OutOfStock)) {
//            throw new RuntimeException("Product is out of stock!");
//        }
//        if (product.getStockQuantity() < quantity) {
//            throw new RuntimeException("You can't add more products to cart than there is available!");
//        }
//        Long cartItemId = cartItemService.createCartItem(cart.getId(), product.getId(), quantity);
//
//        CartItem cartItem = cartItemRepository
//                .findById(cartItemId)
//                .orElseThrow(
//                        () -> new NoSuchElementException("No cart item found")
//                );
//
//        Set<CartItem> cartProductList = cart.getCartItems();
//        cartProductList.add(cartItem);
//        cart.setCartItems(cartProductList);
//        cart.setTotalPrice(getTotalPrice(cartProductList));
//
//        Long cartId = cartRepository.save(cart).getId();
//
//        return cartRepository
//                .findById(cartId)
//                .map(cartMapper::mapCart)
//                .orElseThrow(
//                        () -> new NullPointerException("No cart found with id: " + cartId)
//                );
//
//    }


    private Double getTotalPrice(Set<CartItem> cartItems) {
        double total = 0.00;
        for (CartItem cartItem : cartItems) {
            double discount = cartItem.getProduct().getDiscount() / 100;
            total += cartItem.getSubTotal() - (cartItem.getSubTotal() * discount);
        }
        return total;
    }

    private CartResponse mapCartResponse(Cart cart) {
        return CartResponse.builder()
                .cartId(cart.getCartId())
                .cartItemSet(cart.getCartItems())
                .total(cart.getTotal())
                .build();
    }

    public CartResponse addItemToCart(Authentication authentication, HttpSession session, Long productId, Integer quantity) {
        CartItem cartItem = new CartItem();

        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("No product found with id " + productId));
        cartItem.setQuantity(quantity);
        cartItem.setProduct(product);
        cartItem.setSubTotal(quantity * product.getPrice());
        CartResponse cartResponse = this.getCart(authentication, session);
        Cart existingCart = cartRepository.findById(cartResponse.getCartId()).orElseThrow(() -> new EntityNotFoundException("No cart found with id: " + cartResponse.getCartId()));
        cartItem.setCart(existingCart);
        final CartItem savedItem = cartItemRepository.save(cartItem);

        existingCart.getCartItems().add(savedItem);
        final Double totalPrice = getTotalPrice(existingCart.getCartItems());
        existingCart.setTotal(totalPrice);

        final Cart cartSaved = cartRepository.save(existingCart);
        return mapCartResponse(cartSaved);
    }

    public void clearCart(Authentication authentication, HttpSession session) {
        if (authentication != null) {
            UserEntityResponse loggedInUser = userService.getLoggedInUserDetails(authentication);
            if (cartRepository.existsById(loggedInUser.userId())) {
                Cart cart = cartRepository.findById(loggedInUser.userId()).orElseThrow(() -> new EntityNotFoundException("No cart found for userId: " + loggedInUser.userId()));
                Set<CartItem> cartItems = cart.getCartItems();
                cartItemRepository.deleteAll(cartItems);
                cartItems.clear();
                cart.setCartItems(cartItems);
                cart.setTotal(0.00);
                cartRepository.save(cart);
            }
        } else {
            Cart cart = cartRepository.findById(session.getId()).orElseThrow(() -> new EntityNotFoundException("No cart found for userId: " + session.getId()));
            Set<CartItem> cartItems = cart.getCartItems();
            cartItemRepository.deleteAll(cartItems);
            cartItems.clear();
            cart.setCartItems(cartItems);
            cart.setTotal(0.00);
            cartRepository.save(cart);
        }
    }

    public void updateCart(Long cartItemId, String up, String down) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new EntityNotFoundException("No cart item found with id: " + cartItemId));

        if (up != null) {
            final Integer newQuantity = cartItem.getQuantity() + 1;
            double newSubTotal = newQuantity * cartItem.getProduct().getPrice();
            cartItem.setQuantity(newQuantity);
            cartItem.setSubTotal(newSubTotal);
        }
        if (down != null) {
            if (cartItem.getQuantity() > 1) {
                final Integer newQuantity = cartItem.getQuantity() - 1;
                double newSubTotal = cartItem.getProduct().getPrice() * newQuantity;
                cartItem.setQuantity(newQuantity);
                cartItem.setSubTotal(newSubTotal);
            } else {
                throw new IllegalArgumentException("You can not lower quantity if it is 1 or lower");
            }
        }
        cartItemRepository.save(cartItem);
    }
}

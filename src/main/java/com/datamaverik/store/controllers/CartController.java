package com.datamaverik.store.controllers;

import com.datamaverik.store.dtos.*;
import com.datamaverik.store.exceptions.CartNotFoundException;
import com.datamaverik.store.exceptions.ProductNotFoundException;
import com.datamaverik.store.mappers.CartMapper;
import com.datamaverik.store.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/carts")
@Tag(name = "Carts", description = "Operations related to shopping cart management and item manipulation")
public class CartController {
    private final CartMapper cartMapper;
    private final CartService cartService;

    @PostMapping
    @Operation(
            summary = "Create a new cart",
            description = "Initializes an empty shopping cart and returns its unique UUID."
    )
    @ApiResponse(responseCode = "201", description = "Cart created successfully")
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriBuilder
    ) {
        var cartDto = cartService.createCart();
        var uri = uriBuilder.path("/carts/{id}")
                .buildAndExpand(cartDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("/{cartId}/items")
    @Operation(
            summary = "Add a product to the cart",
            description = "Adds a product to the specified cart. If the product already exists, the quantity is incremented by 1."
    )
    @ApiResponse(responseCode = "201", description = "Item added/incremented successfully")
    public ResponseEntity<CartItemDto> addToCart(
            @PathVariable UUID cartId,
            @RequestBody AddItemToCartRequest request
    ) {
        var cartItemDto = cartService.addToCart(cartId, request.getProductId());

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("/{cartId}")
    @Operation(
            summary = "Get cart details",
            description = "Retrieves the cart object, including the list of items and the total price calculation."
    )
    public ResponseEntity<CartDto> getCart(@PathVariable UUID cartId) {
        var cart = cartService.getCart(cartId);

        return ResponseEntity.ok(cartMapper.toDto(cart));
    }

    @PutMapping("/{cartId}/items/{productId}")
    @Operation(
            summary = "Update item quantity",
            description = "Updates the quantity of a specific product already present in the cart to a specific value."
    )
    public ResponseEntity<?> updateItem(
            @PathVariable UUID cartId,
            @PathVariable Long productId,
            @Valid @RequestBody UpdateCartItemRequest request
    ) {
        var cartItemDto = cartService.updateItem(cartId, productId, request.getQuantity());

        return ResponseEntity.ok(cartItemDto);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    @Operation(
            summary = "Remove or decrement an item",
            description = "Decrements the quantity of a product. If the quantity reaches 0, the item is removed from the cart."
    )
    public ResponseEntity<?> removeItem(
            @PathVariable UUID cartId,
            @PathVariable Long productId
    ) {
        cartService.deleteItem(cartId, productId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    @Operation(
            summary = "Clear the entire cart",
            description = "Removes all items from the specified cart."
    )
    public ResponseEntity<?> clearCart(@PathVariable UUID cartId) {
        cartService.clearCart(cartId);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCartNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorDto("Cart not found.")
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorDto("Product not found in the cart")
        );
    }

}

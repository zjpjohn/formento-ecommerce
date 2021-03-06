package com.formento.ecommerce.ecommerceOrder.repository;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.model.StatusEcommerceOrder;
import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.product.repository.ProductRepository;
import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.shoppingCart.service.ItemShoppingCartService;
import com.formento.ecommerce.shoppingCart.service.ShoppingCartService;
import com.formento.ecommerce.user.model.User;
import com.formento.ecommerce.user.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class EcommerceOrderRepositoryTemplate {

    @Autowired
    protected EcommerceOrderRepository ecommerceOrderRepository;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected ShoppingCartService shoppingCartService;

    @Autowired
    protected ItemShoppingCartService itemShoppingCartService;

    protected User giveUser() {
        String email = "andreformento.sc@gmail.com";

//        return Optional
//                .ofNullable(userRepository.findByEmail(email))
//                .orElse(userRepository.save(new User
//                        .Builder()
//                        .withEmail(email)
//                        .withPassword("p@ssW0rd")
//                        .withName("Andre Formento")
//                        .build())
//                );
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            return user.get();
        } else {
            return userRepository.save(new User
                    .Builder()
                    .withEmail(email)
                    .withPassword("p@ssW0rd")
                    .withName("Andre Formento")
                    .build());
        }
    }

    protected ShoppingCart giveShoppingCart() {
        // TODO: to resolve!!!
        Product product1 = productRepository.findOne(1l);
        Product product2 = productRepository.findOne(2l);

        ShoppingCart shoppingCart = shoppingCartService.save(new ShoppingCart
                .Builder()
                .withItemShoppingCart(new ItemShoppingCart
                        .Builder()
                        .withProduct(product1)
                        .withQuantity(3)
                        .build())
                .withItemShoppingCart(new ItemShoppingCart
                        .Builder()
                        .withProduct(product2)
                        .withQuantity(5)
                        .build())
                .withUser(giveUser())
                .build());

        itemShoppingCartService.save(shoppingCart.getItemShoppingCarts());

        return shoppingCartService.findOne(shoppingCart.getId());
    }

    protected EcommerceOrder giveEcommerceOrder() {
        return new EcommerceOrder
                .Builder()
                .withShoppingCart(giveShoppingCart())
                .withStatusEcommerceOrder(StatusEcommerceOrder.CREATED)
                .build();
    }

}

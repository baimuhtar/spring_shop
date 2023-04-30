package baimuhtar.shop.service;

import baimuhtar.shop.entity.CartItem;
import baimuhtar.shop.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;


    public void increaseByOne(Long cartItemId){
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow();
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItemRepository.save(cartItem);
    }

    public void decreaseByOne(Long cartItemId){
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow();
        cartItem.setQuantity(cartItem.getQuantity()-1);
        cartItemRepository.save(cartItem);
    }
}

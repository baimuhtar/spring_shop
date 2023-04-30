package baimuhtar.shop.controller;

import baimuhtar.shop.entity.*;
import baimuhtar.shop.repository.*;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ShowInfo {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ValueRepository valueRepository;

    @GetMapping(path = "/show")
    public String showProduct(@RequestParam(required = false) Long productId, Model model) {

        Product product = productRepository.findById(productId).orElseThrow();
        List<Value> values = valueRepository.findById(productId).orElseThrow().getProduct().getValues();
        model.addAttribute("product", product);
        model.addAttribute("values", values);

        return "show_info";
    }

//    @PostMapping(path = "/addToCart/productId")
//    public String addToCart(@RequestParam Long productId,
//                            @RequestParam Long userId){
//        Product product = productRepository.findById(productId).orElseThrow();
//        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
//        User user = userRepository.findById(userId).orElseThrow();
//
//        if (cartItem != null) {
//            cartItem.setQuantity(cartItem.getQuantity() +1);
//            cartItemRepository.save(cartItem);
//        } else {
//            CartItem newCartItem = new CartItem();
//            newCartItem.setProduct(product);
//            newCartItem.setUser(user);
//            newCartItem.setQuantity(1);
//            cartItemRepository.save(newCartItem);
//        }
//        return "redirect:/product/list";
//    }
}

package baimuhtar.shop.controller;

import baimuhtar.shop.entity.CartItem;
import baimuhtar.shop.entity.Product;
import baimuhtar.shop.entity.User;
import baimuhtar.shop.repository.CartItemRepository;
import baimuhtar.shop.repository.ProductRepository;
import baimuhtar.shop.service.CartItemService;
import baimuhtar.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping()
public class CartItemController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartItemService cartService;

    private

    @GetMapping(path = "/user_cart")
    String showUserCart(Model model) {
        User user = userService.getCurrentUser();
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @PostMapping(path = "/addToCart")
    public String addItemToCart(@RequestParam Long productId) {
        cartService.addItemToCart(productId);
        return "redirect:/product/list";
    }

    @GetMapping("/increase")
    public String increaseByOne(@RequestParam Long cartItemId) {
        cartService.increaseByOne(cartItemId);
        return "redirect:/user_cart";
    }

    @GetMapping("/decrease")
    public String decreaseByOne(@RequestParam Long cartItemId) {
        cartService.decreaseByOne(cartItemId);
        return "redirect:/user_cart";
    }
    @GetMapping("/deleteItem")
    public String deleteItemFromCart(@RequestParam Long cartItemId) {
        cartService.deleteItemFromCart(cartItemId);
        return "redirect:/user_cart";
    }
}


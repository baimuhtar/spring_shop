package baimuhtar.shop.controller;

import baimuhtar.shop.entity.CartItem;
import baimuhtar.shop.entity.User;
import baimuhtar.shop.repository.CartItemRepository;
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

    @GetMapping(path = "/user_cart")
    String showUserCart(Model model) {
        User user = userService.getCurrentUser();
        List<CartItem> cartItems = cartItemRepository.findAllByUserId(user.getId());
        model.addAttribute("cartItems", cartItems);
        int price = cartService.getTotalPriceOfProducts(cartItems);
        model.addAttribute("price", price);
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

    @GetMapping("/removeItem")
    public String removeItemFromCart(@RequestParam Long cartItemId) {
        cartService.removeItemFromCart(cartItemId);
        return "redirect:/user_cart";
    }

    @GetMapping("/removeAllItems")
    public String removeAllItemsFromCart() {
        cartService.removeAllItemsFromCart(userService.getCurrentUser().getId());
        return "redirect:/user_cart";
    }
}


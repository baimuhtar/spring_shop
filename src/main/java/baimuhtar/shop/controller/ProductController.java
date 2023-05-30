package baimuhtar.shop.controller;

import baimuhtar.shop.config.PasswordEncoderConfig;
import baimuhtar.shop.entity.*;
import baimuhtar.shop.repository.*;
import baimuhtar.shop.service.FeedbackService;
import baimuhtar.shop.service.ProductService;
import baimuhtar.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public String productListPage(@RequestParam(required = false) Long categoryId, Model model) {
        System.out.println(passwordEncoder.matches("123", "$2a$10$S4nlBDdHZpxwVtIh3q6rDO98IdSVZRvLujL1t.rIuiZQXJOdl8lY6"));

        List<Product> products;

        if (categoryId != null) {
            products = productService.findProductsByCategoryId(categoryId);
        } else products = productService.findAllProducts();

        User user = userService.getCurrentUser();
        model.addAttribute("products", products);
        model.addAttribute("user", user);
        return "product_list";
    }

    @GetMapping("/choose_category")
    public String categoryForm(Model model) {
        List<Category> categories = productService.findAllCategories();
        model.addAttribute("categories", categories);
        return "choose_category";
    }

    @GetMapping("/add_product")
    public String productForm(Long categoryId, Model model) {
        Category category = productService.findCategory(categoryId);
        model.addAttribute("category", category);
        return "create_product";
    }

    @PostMapping("/add_product")
    public String createProductForm(
            @RequestParam Long categoryId, @RequestParam String productName,
            @RequestParam Integer productPrice, @RequestParam("option") List<Long> optionsId,
            @RequestParam("value") List<String> values) {

        productService.addProductToList(categoryId, productName,
                productPrice, optionsId, values);
        return "redirect:/product/list";
    }

    @GetMapping("/update")
    public String updateProductForm(@RequestParam(required = false) Long productId, Model model) {
        Product product = productService.findProduct(productId);
        List<Category> categories = productService.findAllCategories();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);

        return "update_product";
    }

    @PostMapping("/update")
    public String updateProduct(
            @RequestParam Long productId, @RequestParam String productName,
            @RequestParam Integer productPrice, @RequestParam("option") List<Long> optionsId,
            @RequestParam("value") List<String> values) {
        productService.updateProduct(productId, productName, productPrice, optionsId, values);
        return "redirect:/product/list";

    }

    @GetMapping("/show")
    public String showInfo(@RequestParam Long productId, Model model) {
        Product product = productService.findProduct(productId);
        List<Value> values = productService.findValuesByProductId(productId);
        List<Feedback> feedbacks = feedbackService.findAllIsPublishedTrue(productId);
        model.addAttribute("product", product);
        model.addAttribute("values", values);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("feedback", feedbackService.findFeedback(productId));
        return "show_info";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/product/list";
    }
}


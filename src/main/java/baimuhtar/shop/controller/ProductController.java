package baimuhtar.shop.controller;

import baimuhtar.shop.entity.*;
import baimuhtar.shop.repository.*;
import baimuhtar.shop.service.ProductService;
import baimuhtar.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ValueRepository valueRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @GetMapping("/list")
    public String productListPage(@RequestParam Long categoryId, Model model) {
        List<Product> products;
        Sort sort = Sort.by(Sort.Order.by("category"));

        if (categoryId != null) {
            products = categoryRepository.findById(categoryId).orElseThrow().getProducts();
        } else products = productRepository.findAll(sort);

        User user = userService.getCurrentUser();
        model.addAttribute("products", products);
        model.addAttribute("user", user);
        return "product_list";
    }
    @GetMapping("/choose_category")
    public String categoryForm(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "choose_category";
    }

    @GetMapping("/add_product")
    public String productForm(Long categoryId, Model model) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
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
    public String updateProductForm(@RequestParam Long productId, Model model){
        Product product = productRepository.findById(productId).orElseThrow();
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);

        return "update_product";
    }
    @PostMapping("/update")
    public String updateProduct(
            @RequestParam Long categoryId, @RequestParam String productName,
            @RequestParam Integer productPrice, @RequestParam ("option") List<Long> optionsId,
            @RequestParam("value") List<String> values) {
        productService.updateProduct(categoryId, productName, productPrice, optionsId, values);
        return "redirect:/product/list";

    }

    @GetMapping("/show")
    public String showInfo(@RequestParam Long productId, Model model){
        Product product = productRepository.findById(productId).orElseThrow();
        List<Value> values = valueRepository.findById(productId).orElseThrow().getProduct().getValues();
        model.addAttribute("product", product);
        model.addAttribute("values", values);

        return "show_info";
    }
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/product/list";
    }
}


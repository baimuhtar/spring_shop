package baimuhtar.shop.controller;


import baimuhtar.shop.entity.Product;
import baimuhtar.shop.entity.User;
import baimuhtar.shop.repository.CategoryRepository;
import baimuhtar.shop.repository.ProductRepository;
import baimuhtar.shop.repository.UserRepository;
import baimuhtar.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/product")
public class ProductList {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String productListPage(Model model, @RequestParam(required = false) Long categoryId) {
        List<Product> products;
        Sort sort = Sort.by(Sort.Order.by("category"));
        if (categoryId != null) {
            products = categoryRepository.findById(categoryId).orElseThrow().getProducts();
        } else {
            products = productRepository.findAll(sort);
        }
        User user = userService.getCurrentUser();
        model.addAttribute("products", products);
        model.addAttribute("user", user);
        return "product_list";
    }
}

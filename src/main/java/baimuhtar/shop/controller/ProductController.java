package baimuhtar.shop.controller;

import baimuhtar.shop.entity.Category;
import baimuhtar.shop.entity.Option;
import baimuhtar.shop.entity.Product;
import baimuhtar.shop.entity.Value;
import baimuhtar.shop.repository.CategoryRepository;
import baimuhtar.shop.repository.OptionRepository;
import baimuhtar.shop.repository.ProductRepository;
import baimuhtar.shop.repository.ValueRepository;
import baimuhtar.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProductService productService;

    @GetMapping("/choose_category")
    public String categoryForm(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "choose_category";
    }

    @GetMapping("/add_product")
    public String productForm(long categoryId, Model model) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        model.addAttribute("category", category);
        return "create_product";
    }

    @PostMapping("/add_product")
    public String createProductForm(
            @RequestParam Long categoryId, @RequestParam String product_name,
            @RequestParam Integer product_price, @RequestParam("option") List<Long> optionsId,
            @RequestParam("value") List<String> valuesName) {

        productService.addProductToList(categoryId, product_name,
                product_price, optionsId, valuesName);
        return "redirect:/product/list";
    }
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/product/list";
    }
}


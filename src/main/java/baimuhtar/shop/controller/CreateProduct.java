package baimuhtar.shop.controller;

import baimuhtar.shop.entity.Category;
import baimuhtar.shop.entity.Option;
import baimuhtar.shop.entity.Product;
import baimuhtar.shop.entity.Value;
import baimuhtar.shop.repository.CategoryRepository;
import baimuhtar.shop.repository.OptionRepository;
import baimuhtar.shop.repository.ProductRepository;
import baimuhtar.shop.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping(path = "/product")
public class CreateProduct {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private ValueRepository valueRepository;

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
            @RequestParam Long categoryId,
            @RequestParam String product_name,
            @RequestParam Integer product_price,
            @RequestParam("option") List<Long> optionsId,
            @RequestParam("value") List<String> valuesName) {

        Category category = categoryRepository.findById(categoryId).orElseThrow();

        Product product = new Product();
        product.setCategory(category);
        product.setName(product_name);
        product.setPrice(product_price);
        productRepository.save(product);

        for (int i = 0; i < optionsId.size(); i++) {
            Option option = optionRepository.findById(optionsId.get(i)).orElseThrow();
            Value value = new Value();
            value.setProduct(product);
            value.setOption(option);
            value.setValue(valuesName.get(i));
            valueRepository.save(value);
        }

        System.out.println("Name: " + product_name + "\n" + "Price: " + product_price + '\n' + "Объект успешно создан");
        System.out.printf("%s:", product.getName());
        System.out.printf("%d:", product.getPrice());
        System.out.println(product.getCategory().getOptions());
        System.out.println(product.getValues());

        return "redirect:/product/list";
    }

}


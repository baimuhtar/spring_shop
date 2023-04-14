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

import java.util.List;
import java.util.Optional;

@Controller()
@RequestMapping(path = "/product")
public class UpdateProduct {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private ValueRepository valueRepository;

    @GetMapping("/edit")
    public String updateProductForm(@RequestParam Long productId, Model model) {
        Product product = productRepository.findById(productId).orElseThrow();
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);

        return "update_product";
    }

    @PostMapping("/edit")
    public String updateProduct(@RequestParam Long productId,
                                @RequestParam(required = false) String newName,
                                @RequestParam(required = false) Integer newPrice,
                                @RequestParam("option") List<Long> optionsId,
                                @RequestParam("value") List<String> valuesName) {
        Product product = productRepository.findById(productId).orElseThrow();
        if (newName != null) product.setName(newName);
        if (newPrice != null) product.setPrice(newPrice);
        productRepository.save(product);
        for (int i = 0; i < optionsId.size(); i++) {
            List<Option> options = optionRepository.findAllById(optionsId);
            Value value = valueRepository.findValuesByOptionAndProductId(options.get(i), productId);
            value.setProduct(product);
            value.setOption(options.get(i));
            value.setValue(valuesName.get(i));
            valueRepository.save(value);
        }


        return "redirect:/product/list";
    }
}

package baimuhtar.shop.controller;

import baimuhtar.shop.entity.Category;
import baimuhtar.shop.entity.Option;
import baimuhtar.shop.entity.Product;
import baimuhtar.shop.entity.Value;
import baimuhtar.shop.repository.CategoryRepository;
import baimuhtar.shop.repository.OptionRepository;
import baimuhtar.shop.repository.ProductRepository;
import baimuhtar.shop.repository.ValueRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ShowInfo {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping(path = "/show")
    public String showProduct(@RequestParam("productId") Long productId, Model model) {

        Product product = productRepository.findById(productId).orElseThrow();
        model.addAttribute("product", product);

        return "show_info";
    }
}

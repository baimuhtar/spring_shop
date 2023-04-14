package baimuhtar.shop.controller;

import baimuhtar.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/product")
public class DeleteProduct {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/delete")
    public String deleteProduct(@RequestParam Long productId) {
        productRepository.deleteById(productId);
        return "redirect:/product/list";

    }
}

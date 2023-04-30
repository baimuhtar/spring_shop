package baimuhtar.shop.service;

import baimuhtar.shop.entity.Category;
import baimuhtar.shop.entity.Option;
import baimuhtar.shop.entity.Product;
import baimuhtar.shop.entity.Value;
import baimuhtar.shop.repository.CategoryRepository;
import baimuhtar.shop.repository.OptionRepository;
import baimuhtar.shop.repository.ProductRepository;
import baimuhtar.shop.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private ValueRepository valueRepository;


    public void addProductToList(Long categoryId, String productName, Integer productPrice,
                                 List<Long> optionsId, List<String> values) {

        Category category = categoryRepository.findById(categoryId).orElseThrow();

        Product product = new Product();
        product.setCategory(category);
        product.setName(productName);
        product.setPrice(productPrice);
        productRepository.save(product);

        for (int i = 0; i < optionsId.size(); i++) {
            Option option = optionRepository.findById(categoryId).orElseThrow();
            Value value = new Value();
            value.setProduct(product);
            value.setOption(option);
            value.setValue(values.get(i));
            valueRepository.save(value);
        }
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

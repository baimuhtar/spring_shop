package baimuhtar.shop.repository;

import baimuhtar.shop.entity.Product;
import baimuhtar.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


}

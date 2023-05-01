package baimuhtar.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;

    private Integer price;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Value> values;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Feedback> feedbacks;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts;

//    @ManyToOne()
//    @JoinColumn(name = "cartItemId")
//    private CartItem cartItem;
}

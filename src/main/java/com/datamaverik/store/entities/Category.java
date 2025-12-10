package com.datamaverik.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private byte id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> categories = new HashSet<Product>();

    public void addProduct(Product product) {
        categories.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product) {
        categories.remove(product);
        product.setCategory(null);
    }

    public Category(String name) {
        this.name = name;
    }
}

package com.example.batdongsan.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@TableGenerator(name = "tableGenerator", table = "ID_GEN", pkColumnName = "GEN_NAME",
        valueColumnName = "GEN_VALUE", pkColumnValue = "category_gen", allocationSize = 1)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "tableGenerator")
    @Column(name = "category_id")
    private Long id;

    private String name;
}




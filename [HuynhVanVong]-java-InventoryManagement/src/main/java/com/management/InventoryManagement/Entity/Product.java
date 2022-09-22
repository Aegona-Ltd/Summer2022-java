package com.management.InventoryManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    private Integer productId;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer Amount;
    @Column(nullable = false)
    private Date importDate;
    @Column
    private boolean isDeleted = Boolean.FALSE;
    @ManyToOne
    @JoinColumn(name = "categoriesID")
    private Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductImage> productImages;
    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductTransaction> productTransactions;
}

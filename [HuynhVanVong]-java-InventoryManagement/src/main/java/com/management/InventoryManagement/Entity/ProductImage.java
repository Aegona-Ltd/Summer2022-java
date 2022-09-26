package com.management.InventoryManagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ProductImage")
public class ProductImage {
    public ProductImage(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageID")
    private Integer imageID;
    private String name;
    @Lob
    private byte[] data;
    private String type;
    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

}

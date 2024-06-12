package de.mcc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private Category category;
    private double price;
    private double weight;
    private double storageCost;
    private boolean requiresSpecialStorage;
    private double quantity;
}
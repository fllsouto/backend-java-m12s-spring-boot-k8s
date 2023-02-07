package com.cdc.backend.shoppingApi.model;

import com.cdc.backend.shoppingClient.dto.ItemDTO;

import jakarta.persistence.Embeddable;

@Embeddable
public class Item {

    private String productIdentifier;
    private Float price;

    public Float getPrice() {
        return this.price;
    }

    public String getProductIdentifier() {
        return this.productIdentifier;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public static Item convert(ItemDTO itemDTO) {
        Item item = new Item();
        item.setProductIdentifier(itemDTO.getProductIdentifier());
        item.setPrice(itemDTO.getPrice());

        return item;
    }
}

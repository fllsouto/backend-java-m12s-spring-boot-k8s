package com.cdc.backend.shoppingClient.dto;

// import com.cdc.backend.shoppingApi.model.Item;

// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;

public class ItemDTO {

    // @NotBlank
    private String productIdentifier;
    // @NotNull
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


}

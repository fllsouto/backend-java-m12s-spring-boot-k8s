package com.cdc.backend.shoppingClient.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// import com.cdc.backend.shoppingApi.model.Shop;

// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;

public class ShopDTO {

    // @NotBlank
    private String userIdentifier;
    // @NotNull
    private float total;
    // @NotNull
    private Date date;
    // @NotNull
    private List<ItemDTO> items;

    public String getUserIdentifier() {
        return userIdentifier;
    }
    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public List<ItemDTO> getItems() {
        return items;
    }
    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}

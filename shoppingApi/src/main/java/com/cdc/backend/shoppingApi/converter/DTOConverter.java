package com.cdc.backend.shoppingApi.converter;

import com.cdc.backend.shoppingClient.dto.ShopDTO;

import java.util.stream.Collectors;

import com.cdc.backend.shoppingApi.model.Item;
import com.cdc.backend.shoppingApi.model.Shop;
import com.cdc.backend.shoppingClient.dto.ItemDTO;

public class DTOConverter {

    public static ItemDTO convert(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());

        return itemDTO;
    }

    public static ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        shopDTO.setItems(shop
                .getItems()
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList()));

        return shopDTO;
    }
}

package com.cdc.backend.shoppingApi.service;

import java.util.List;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdc.backend.shoppingClient.dto.ItemDTO;
import com.cdc.backend.shoppingClient.dto.ShopDTO;
import com.cdc.backend.shoppingClient.dto.ProductDTO;
import com.cdc.backend.shoppingApi.converter.DTOConverter;
import com.cdc.backend.shoppingApi.model.Shop;
import com.cdc.backend.shoppingApi.repository.ShopRepository;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public List<ShopDTO> getAll() {
        List<Shop> shops = shopRepository.findAll();
        return shops
            .stream()
            .map(DTOConverter::convert)
            .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
        return shops
            .stream()
            .map(DTOConverter::convert)
            .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
        return shops
            .stream()
            .map(DTOConverter::convert)
            .collect(Collectors.toList());
    }

    public ShopDTO findById(long productId) {
        Optional<Shop> shop = shopRepository.findById(productId);
        if (shop.isPresent()) {
            return DTOConverter.convert(shop.get());
        } else {
            return null;
        }
    }

    public ShopDTO save(ShopDTO shopDTO, String key) {

        if (userService.getUserByCpf(shopDTO.getUserIdentifier(), key) == null) {
            return null;
        }

        if (!validateProducts(shopDTO.getItems())) {
            return null;
        }

        shopDTO.setTotal(shopDTO.getItems()
            .stream()
            .map(item -> item.getPrice())
            .reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(shopDTO);
        shop.setDate(new Date());

        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for (ItemDTO item : items) {
            ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());

            if (productDTO == null) {
                return false;
            }
            item.setPrice(productDTO.getPreco());
        }
        return true;
    }
}

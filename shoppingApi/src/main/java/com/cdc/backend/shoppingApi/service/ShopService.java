package com.cdc.backend.shoppingApi.service;

import java.util.List;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdc.backend.shoppingClient.dto.ShopDTO;
import com.cdc.backend.shoppingApi.converter.DTOConverter;
import com.cdc.backend.shoppingApi.model.Shop;
import com.cdc.backend.shoppingApi.repository.ShopRepository;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

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

    public ShopDTO save(ShopDTO shopDTO) {
        shopDTO.setTotal(shopDTO.getItems()
            .stream()
            .map(item -> item.getPrice())
            .reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(shopDTO);
        shop.setDate(new Date());

        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }
}

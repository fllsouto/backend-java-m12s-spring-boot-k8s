package com.cdc.backend.shoppingApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;
import com.cdc.backend.shoppingApi.repository.ShopRepository;
import com.cdc.backend.shoppingApi.model.Shop;
import com.cdc.backend.shoppingApi.dto.ShopDTO;
import com.cdc.backend.shoppingApi.dto.ShopReportDTO;

@Service
public class ReportService {

    @Autowired
    private ShopRepository shopRepository;

    public List<ShopDTO> getShopByFilters(
        Date dataInicio,
        Date dataFim,
        Float valorMinimo
    ) {

        List<Shop> shops = shopRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);

        return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(
        Date dataInicio,
        Date dataFim
    ) {
        return shopRepository.getReportByDate(dataInicio, dataFim);
    }
}
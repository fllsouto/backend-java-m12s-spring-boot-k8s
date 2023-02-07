package com.cdc.backend.shoppingApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;
import com.cdc.backend.shoppingApi.repository.ReportRepository;
import com.cdc.backend.shoppingApi.converter.DTOConverter;
import com.cdc.backend.shoppingApi.model.Shop;
import com.cdc.backend.shoppingClient.dto.ShopDTO;
import com.cdc.backend.shoppingClient.dto.ShopReportDTO;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<ShopDTO> getShopByFilters(
        Date dataInicio,
        Date dataFim,
        Float valorMinimo
    ) {

        List<Shop> shops = reportRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);

        return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(
        Date dataInicio,
        Date dataFim
    ) {
        return reportRepository.getReportByDate(dataInicio, dataFim);
    }
}

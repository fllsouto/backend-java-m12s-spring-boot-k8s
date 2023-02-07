package com.cdc.backend.shoppingApi.repository;

import java.util.Date;
import java.util.List;

import com.cdc.backend.shoppingClient.dto.ShopReportDTO;
import com.cdc.backend.shoppingApi.model.Shop;

public interface ReportRepository {
    public List<Shop> getShopByFilters(
        Date dataInicio,
        Date dataFim,
        Float valorMinimo
    );

    public ShopReportDTO getReportByDate(
        Date dataInicio,
        Date dataFim
    );
}

package com.cdc.backend.shoppingApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Date;

import com.cdc.backend.shoppingApi.service.ShopService;
import com.cdc.backend.shoppingApi.service.ReportService;

import jakarta.validation.Valid;

import com.cdc.backend.shoppingApi.dto.ShopDTO;
import com.cdc.backend.shoppingApi.dto.ShopReportDTO;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ReportService reportService;

    @GetMapping("/shopping")
    public List<ShopDTO> getShops() {
        List<ShopDTO> shops = shopService.getAll();
        return shops;
    }

    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(@PathVariable String userIdentifier) {
        List<ShopDTO> shops = shopService.getByUser(userIdentifier);
        return shops;
    }

    @GetMapping("/shopping/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {
        List<ShopDTO> shops = shopService.getByDate(shopDTO);
        return shops;
    }

    @GetMapping("/shopping/{id}")
    public ShopDTO getShops(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping("/shopping")
    public ShopDTO newShop(@Valid @RequestBody ShopDTO shopDTO) {
        return shopService.save(shopDTO);
    }

    @GetMapping("shopping/search")
    public List<ShopDTO> getShopsByFilter(
        @RequestParam(name = "dataInicio", required = true)
        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
        @RequestParam(name = "dataFim", required = false)
        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
        @RequestParam(name = "valorMinimo", required = false) Float valorMinimo
    ) {
        return reportService.getShopByFilters(dataInicio, dataFim, valorMinimo);
    }

    @GetMapping("shopping/report")
    public ShopReportDTO getReportByDate(
        @RequestParam(name = "dataInicio", required = true)
        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
        @RequestParam(name = "dataFim", required = true)
        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim
    ) {
        return reportService.getReportByDate(dataInicio, dataFim);
    }
}

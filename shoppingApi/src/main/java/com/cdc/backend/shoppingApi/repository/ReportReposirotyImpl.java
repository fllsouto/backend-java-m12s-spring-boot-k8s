package com.cdc.backend.shoppingApi.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdc.backend.shoppingClient.dto.ShopReportDTO;
import com.cdc.backend.shoppingApi.model.Shop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class ReportReposirotyImpl implements ReportRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo) {
        StringBuilder sb = new StringBuilder();
        sb.append("select sp ");
        sb.append("from shop sp ");
        sb.append("where sp.date >= :dataInicio ");

        if (dataFim != null) {
            sb.append("and sp.date <= :dataFim ");
        }

        if (valorMinimo != null) {
            sb.append("and sp.total >= :valorMinimo ");
        }

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("dataInicio", dataInicio);

        if (dataFim != null) {
            query.setParameter("dataFim", dataFim);
        }

        if (valorMinimo != null) {
            query.setParameter("valorMinimo", valorMinimo);
        }

        return query.getResultList();
    }

    @Override
    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim) {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(sp.id), sum(sp.total), avg(sp.total) ");
        sb.append("from shop sp ");
        sb.append("where sp.date >= :dataInicio ");
        sb.append("and sp.date <= :dataFim ");

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);

        Object[] result = (Object[]) query.getSingleResult();

        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setCount(((Long) result[0]).intValue());
        shopReportDTO.setTotal((Double) result[1]);
        shopReportDTO.setMean((Double) result[2]);
        return shopReportDTO;
    }

}

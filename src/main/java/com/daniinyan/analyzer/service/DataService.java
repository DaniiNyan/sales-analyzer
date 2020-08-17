package com.daniinyan.analyzer.service;

import com.daniinyan.analyzer.dao.FilesDAO;
import com.daniinyan.analyzer.domain.Sale;
import com.daniinyan.analyzer.domain.Seller;
import java.util.Arrays;
import java.util.Comparator;

public class DataService {

  public static final String SELLER_ID = "001";
  public static final String CUSTOMER_ID = "002";
  public static final String SALE_ID = "003";
  public static final String TOTAL_CUSTOMERS_FIELD = "total_customers=";
  public static final String TOTAL_SELLERS_FIELD = "total_sellers=";
  public static final String MOST_EXPENSIVE_SALE_FIELD = "most_expensive_sale_id=";
  public static final String WORST_SELLER_FIELD = "worst_seller_name=";

  private final DataMapper dataMapper;
  private final FilesDAO filesDAO;
  // todo: add logger?

  public DataService(FilesDAO filesDAO) {
    dataMapper = new DataMapper();
    this.filesDAO = filesDAO;

    filesDAO.writeReport(Arrays.asList(
        TOTAL_CUSTOMERS_FIELD,
        TOTAL_SELLERS_FIELD,
        MOST_EXPENSIVE_SALE_FIELD,
        WORST_SELLER_FIELD
    ));
  }

  public void updateReport() {
    setTotalByFieldName(TOTAL_CUSTOMERS_FIELD, CUSTOMER_ID);
    setTotalByFieldName(TOTAL_SELLERS_FIELD, SELLER_ID);
    setMostExpensiveSale();
    setWorstSeller();
  }

  private void setTotalByFieldName(String fieldName, String dataId) {
    String totalCount = String.valueOf(countById(dataId));
    updateReportField(fieldName, totalCount);
  }

  private long countById(String dataId) {
    return filesDAO
        .getData()
        .stream()
        .filter(data -> dataMapper.toId(data).equals(dataId))
        .count();
  }

  private void setMostExpensiveSale() {
    Sale mostExpensiveSale = filesDAO
        .getData()
        .stream()
        .filter(data -> dataMapper.toId(data).equals(SALE_ID))
        .map(dataMapper::toSale)
        .max(Comparator.comparing(Sale::getTotal))
        .get();

    updateReportField(MOST_EXPENSIVE_SALE_FIELD, mostExpensiveSale.getId());
  }

  private void setWorstSeller() {
    Seller worstSeller = filesDAO
        .getData()
        .stream()
        .filter(data -> dataMapper.toId(data).equals(SELLER_ID))
        .map(dataMapper::toSeller)
        .min(Comparator.comparing(this::sumTotalSoldBySeller))
        .get();

    updateReportField(WORST_SELLER_FIELD, worstSeller.getName());
  }

  private Double sumTotalSoldBySeller(Seller seller) {
    return filesDAO
        .getData()
        .stream()
        .filter(data -> dataMapper.toId(data).equals(SALE_ID))
        .map(dataMapper::toSale)
        .filter(sale -> sale.getSellerName().equals(seller.getName()))
        .mapToDouble(Sale::getTotal)
        .sum();
  }

  private void updateReportField(String fieldName, String value) {
    filesDAO.updateReportField(fieldName, value);
  }
}

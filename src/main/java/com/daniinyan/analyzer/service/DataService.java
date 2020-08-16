package com.daniinyan.analyzer.service;

import com.daniinyan.analyzer.dao.FilesDAO;
import com.daniinyan.analyzer.domain.Sale;
import java.util.Arrays;
import java.util.Comparator;

public class DataService {

  public static final String SALESMAN_ID = "001";
  public static final String CUSTOMER_ID = "002";
  public static final String SALE_ID = "003";
  public static final String TOTAL_CUSTOMERS_FIELD = "total_customers=";
  public static final String TOTAL_SELLERS_FIELD = "total_sellers=";
  public static final String MOST_EXPENSIVE_SALE_FIELD = "most_expensive_sale_id=";
  public static final String WORST_SALESMAN_FIELD = "worst_salesman=";

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
        WORST_SALESMAN_FIELD
    ));
  }

  public void updateReport() {
    setTotalByFieldName(TOTAL_CUSTOMERS_FIELD, CUSTOMER_ID);
    setTotalByFieldName(TOTAL_SELLERS_FIELD, SALESMAN_ID);
    setMostExpensiveSale();
//      setWorstSalesman();
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
    String mostExpensiveSale = filesDAO
        .getData()
        .stream()
        .filter(data -> dataMapper.toId(data).equals(SALE_ID))
        .map(dataMapper::toSale)
        .max(Comparator.comparing(Sale::getTotal))
        .get()
        .getId();

    updateReportField(MOST_EXPENSIVE_SALE_FIELD, mostExpensiveSale);
  }

  private void updateReportField(String fieldName, String value) {
    filesDAO.updateReportField(fieldName, value);
  }
}

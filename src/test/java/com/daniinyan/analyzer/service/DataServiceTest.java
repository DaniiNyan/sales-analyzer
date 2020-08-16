package com.daniinyan.analyzer.service;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.daniinyan.analyzer.dao.FilesDAO;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class DataServiceTest {

  public static final String SALESMAN_ID = "001";
  public static final String CUSTOMER_ID = "002";
  public static final String SALE_ID = "003";
  public static final String TOTAL_CUSTOMERS_FIELD = "total_customers=";
  public static final String TOTAL_SELLERS_FIELD = "total_sellers=";
  public static final String MOST_EXPENSIVE_SALE_FIELD = "most_expensive_sale_id=";
  public static final String WORST_SALESMAN_FIELD = "worst_salesman=";

  private FilesDAO filesDAO;
  private DataService dataService;

  @Before
  public void setUp() {
    filesDAO = mock(FilesDAO.class);
    dataService = new DataService(filesDAO);
  }

  @Test
  public void shouldCalculateReportFields() {
    when(filesDAO.getData()).thenReturn(getSampleData());
    doNothing().when(filesDAO).updateReportField(anyString(), anyString());

    dataService.updateReport();

    verify(filesDAO)
        .updateReportField(TOTAL_CUSTOMERS_FIELD, "2");

    verify(filesDAO)
        .updateReportField(TOTAL_SELLERS_FIELD, "3");

    verify(filesDAO)
        .updateReportField(MOST_EXPENSIVE_SALE_FIELD, "02");
  }


  private List<String> getSampleData() {
    return Arrays.asList(
        "001ç1234567891234çDianaç3800",
        "001ç3245678865434çMariaç4000.99",
        "001ç1234567891234çBeatrizç3475.50",
        "002ç2345675434544345çJose da SilvaçRural",
        "002ç2345675433444345çEduardoPereiraçHuman Resources",
        "003ç01ç[1-10-2.5,2-10-1.50,3-15-100]çDiana",
        "003ç02ç[1-20-2.5,2-10-1.50,3-50-100]çMaria",
        "003ç03ç[1-30-2.5,2-10-1.50,3-10-100]çBeatriz");
  }

}

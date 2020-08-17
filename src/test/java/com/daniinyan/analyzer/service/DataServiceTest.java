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

  public static final String TOTAL_CUSTOMERS_FIELD = "total_customers=";
  public static final String TOTAL_SELLERS_FIELD = "total_sellers=";
  public static final String MOST_EXPENSIVE_SALE_FIELD = "most_expensive_sale_id=";
  public static final String WORST_SELLER_FIELD = "worst_seller_name=";

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

    verify(filesDAO)
        .updateReportField(WORST_SELLER_FIELD, "Beatriz");
  }


  private List<String> getSampleData() {
    return Arrays.asList(
        "001ç23176086083çDianaç3800",
        "001ç39718995013çMariaç4000.99",
        "001ç28107757025çBeatrizç3475.50",
        "002ç78538206000167çJose da SilvaçRural",
        "002ç93797762000141çEduardoPereiraçHuman Resources",
        "003ç01ç[1-10-2.5,2-10-1.50,3-15-100]çDiana",
        "003ç02ç[1-20-2.5,2-10-1.50,3-50-100]çMaria",
        "003ç03ç[1-30-2.5,2-10-1.50,3-10-100]çBeatriz");
  }

}

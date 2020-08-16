package com.daniinyan.analyzer.service;

import com.daniinyan.analyzer.domain.Item;
import com.daniinyan.analyzer.domain.Sale;
import java.util.ArrayList;
import java.util.List;

public class DataMapper {

  public static final String SALE_DELIMITER = ",";
  public static final String ITEM_DELIMITER = "-";

  public String toId(String data) {
    String[] splitData = data.split(getDelimiter(data));
    return splitData[0];
  }

  public Sale toSale(String data) {
    String[] splitData = data.split(getDelimiter(data));

    Sale sale = new Sale();
    sale.setId(splitData[1]);
    sale.setItems(toItems(splitData[2]));
    sale.setSalesmanName(splitData[3]);

    return sale;
  }

  public List<Item> toItems(String itemsData) {
    itemsData = itemsData.substring(1, itemsData.length() - 1);

    List<Item> items = new ArrayList<>();
    for (String itemData : itemsData.split(SALE_DELIMITER)) {
      String[] splitItem = itemData.split(ITEM_DELIMITER);

      Item item = new Item();
      item.setId(splitItem[0]);
      item.setQuantity(Integer.parseInt(splitItem[1]));
      item.setPrice(Double.parseDouble(splitItem[2]));
      items.add(item);
    }
    return items;
  }

  private String getDelimiter(String data) {
    return data.substring(3, 4);
  }
}

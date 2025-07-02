package com.goshop.test;

import com.google.common.collect.ImmutableMap;
import com.goshop.dao.ProductDao;

import java.util.Map;

public class DynamoDBTest {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();

        /*ImmutableMap<String, String> product = ImmutableMap.of("product_no", "P001",
                "product_nm", "LG OLED 32 Inch Smart Television",
                "description", "Smart Television",
                "manufacturer", "LG",
                "price", "123000");
        productDao.saveProduct(product);*/

        Map<String, String> product = productDao.getProduct("P001");
        System.out.println("{");
        product.forEach((k,v)-> {
            System.out.println(k + " : " + v);
        });
        System.out.println("}");

    }
}

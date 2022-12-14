package org.rozetka.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataReader {

    private static final Properties properties = new Properties();
    private static TestDataReader instance;

    private TestDataReader(){}

    public static TestDataReader get(){
        if(instance == null){
            instance = new TestDataReader();
            try{
                properties.load(new FileInputStream("src/main/resources/test_data.properties"));
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
        }
        return instance;
    }

    public String getAppleBrand() {
        return properties.getProperty("apple_brand");
    }

    public int getMinPrice() {
        return Integer.parseInt(properties.getProperty("min_price"));
    }

    public int getMaxPrice() {
        return Integer.parseInt(properties.getProperty("max_price"));
    }
}

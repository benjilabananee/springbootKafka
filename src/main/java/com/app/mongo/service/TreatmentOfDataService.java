package com.app.mongo.service;

import org.springframework.stereotype.Service;

@Service
public class TreatmentOfDataService {

    public double convertStringIntoNumberFormat(String input){
        double number = 0;

        try {
            number = Double.parseDouble(input.replaceAll("[^0-9.-]", ""));

        } catch (NumberFormatException e) {
            System.out.println("You cannot convert the number, setting it to 0.");
        }
        catch (Exception e){
            System.out.println("problem converter");
        }
        return number;
    }

    public String extractCurrencyFromInput(String input) {
        String currency = "";

        try {
            // Use regex to extract non-numeric characters
            currency = input.replaceAll("[0-9.,-]", "").trim();
        } catch (Exception e) {
            System.out.println("Problem extracting currency: " + e.getMessage());
        }

        return currency;
    }
}

package com.example.travelpackages.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PackagePricingInfo {
    private String currencyCode;
    private String formattedTotalPriceValue;

}
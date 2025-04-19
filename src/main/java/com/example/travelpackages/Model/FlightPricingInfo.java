package com.example.travelpackages.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FlightPricingInfo {
    private String currencyCode;
    private double flightTotalBaseFare;
    private double flightTotalPrice;
}
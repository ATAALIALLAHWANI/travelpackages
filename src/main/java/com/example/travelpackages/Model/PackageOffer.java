package com.example.travelpackages.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageOffer {
    private String travelStartDate;
    private String travelEndDate;
    private Destination destination;
    private Origin origin;
    private HotelInfo hotelInfo;
    private FlightInfo flightInfo;
    private FlightPricingInfo flightPricingInfo;
    private PackagePricingInfo packagePricingInfo;
}

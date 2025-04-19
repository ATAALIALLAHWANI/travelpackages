package com.example.travelpackages.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.travelpackages.Model.Destination;
import com.example.travelpackages.Model.FlightInfo;
import com.example.travelpackages.Model.FlightPricingInfo;
import com.example.travelpackages.Model.HotelInfo;
import com.example.travelpackages.Model.Origin;
import com.example.travelpackages.Model.PackageOffer;
import com.example.travelpackages.Model.PackagePricingInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PackageService {

    private static final String EXPEDIA_API_URL = "https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=test&productType=Package&clientId=test";

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<PackageOffer> findPackages(String originCity, String destinationCity) {
        try {
            String jsonResponse = restTemplate.getForObject(EXPEDIA_API_URL, String.class);

            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode offers = root.path("offers").path("Package");

            List<PackageOffer> results = new ArrayList<>();

            for (JsonNode offer : offers) {
                String origin = offer.path("offerDateRange").path("travelStartDate").toString(); 
                String dest = offer.path("destination").path("city").asText(); 
                String hotelName = offer.path("hotelInfo").path("hotelName").asText();
                String price = offer.path("packagePricingInfo").path("formattedTotalPriceValue").asText();
                
                // Flight information
                String carrierName = offer.path("flightInfo").path("flightDealCarrierName").asText(null);
                String currencyCode = offer.path("flightPricingInfo").path("currencyCode").asText(null);
                double totalPrice = offer.path("flightPricingInfo").path("flightTotalPrice").asDouble(0.0);

                // Travel dates
                String formattedStartDate = offer.path("offerDateRange").path("formattedTravelStartDate").asText();
                String formattedEndDate = offer.path("offerDateRange").path("formattedTravelEndDate").asText();

                PackageOffer pkg = new PackageOffer();

                pkg.setHotelInfo(new HotelInfo());
                pkg.getHotelInfo().setHotelName(hotelName);

                pkg.setOrigin(new Origin());
                pkg.getOrigin().setCity(originCity);

                pkg.setDestination(new Destination());
                pkg.getDestination().setCity(dest);

                pkg.setPackagePricingInfo(new PackagePricingInfo());
                pkg.getPackagePricingInfo().setFormattedTotalPriceValue(price);

                if (carrierName != null) {
                    FlightInfo flightInfo = new FlightInfo();
                    flightInfo.setFlightDealCarrierName(carrierName);
                    pkg.setFlightInfo(flightInfo);
                }

                if (currencyCode != null) {
                    FlightPricingInfo flightPricing = new FlightPricingInfo();
                    flightPricing.setCurrencyCode(currencyCode);
                    flightPricing.setFlightTotalPrice(totalPrice);
                    pkg.setFlightPricingInfo(flightPricing);
                }

                pkg.setTravelStartDate(formattedStartDate);
                pkg.setTravelEndDate(formattedEndDate);

                if (originCity.equalsIgnoreCase(pkg.getOrigin().getCity()) &&
                    destinationCity.equalsIgnoreCase(pkg.getDestination().getCity())) {
                    results.add(pkg);
                }
            }

            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

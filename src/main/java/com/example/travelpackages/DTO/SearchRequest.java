package com.example.travelpackages.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SearchRequest {
    private String originCity;
    private String destinationCity;

}
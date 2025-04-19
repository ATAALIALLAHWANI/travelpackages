package com.example.travelpackages.Model;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Offers {
    private List<PackageOffer> packages;

    public List<PackageOffer> getPackages() { return packages; }
    public void setPackages(List<PackageOffer> packages) { this.packages = packages; }
}
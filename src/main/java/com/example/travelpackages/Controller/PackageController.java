package com.example.travelpackages.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.travelpackages.DTO.SearchRequest;
import com.example.travelpackages.Model.PackageOffer;
import com.example.travelpackages.Service.PackageService;

import org.springframework.ui.Model;
@Controller
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("searchRequest", new SearchRequest());
        return "search";
    }

    @PostMapping("/search")
    public String searchPackages(
        @ModelAttribute SearchRequest searchRequest,
        Model model
    ) {
        List<PackageOffer> packages = packageService.findPackages(
            searchRequest.getOriginCity(),
            searchRequest.getDestinationCity()
        );
        model.addAttribute("packages", packages);
        return "search";
    }
}
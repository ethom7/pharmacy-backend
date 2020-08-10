package com.solutions.backend.controller;

import com.solutions.backend.model.Pharmacy;
import com.solutions.backend.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PharmacyController {
    @Autowired
    private PharmacyRepository pharmacyRepository;

    @GetMapping("/closestPharmacy/{latLongPair}")
    public ResponseEntity<String> getClosestPharmacy(@PathVariable(value = "latLongPair") String latLongPair) {
        String[] pairParts = latLongPair.strip().split(",");  // split at the comma
        Double minDistance = null;
        Pharmacy closestPharmacy = null;

        if (pairParts.length < 2) {
            // is not a valid pair, will not search
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }

        try {
            double latitude = Double.parseDouble(pairParts[0]);
            double longitude = Double.parseDouble(pairParts[1]);

            List<Pharmacy> pharmacies = pharmacyRepository.findAll();

            for (Pharmacy pharmacy : pharmacies) {
                double distance = Pharmacy.distance(pharmacy.getLatitude(), latitude, pharmacy.getLongitude(), longitude);
                if (minDistance == null || minDistance > distance) {
                    minDistance = distance;
                    closestPharmacy = pharmacy;
                }
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }

        if (closestPharmacy == null) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }

        DecimalFormat df = new DecimalFormat(".####");
        String formattedDistanceString = df.format(minDistance);

        String outputString = String.format("%s, {distance=\'%s\'}", closestPharmacy.toString(), formattedDistanceString);

        return ResponseEntity.ok().body(outputString);

    }
}

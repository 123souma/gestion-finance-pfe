package com.example.finance.Controller;

import com.example.finance.Service.PlacementService;
import com.example.finance.entites.Placement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/placements")
public class PlacementController {

    private final PlacementService placementService;

    @Autowired
    public PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Placement> ajouterPlacement(@RequestBody Placement placement) {
        // Définir la date d'acquisition (si elle n'est pas fournie)
        if (placement.getDateAcquisition() == null) {
            placement.setDateAcquisition(new Date());
        }
        // Enregistrer le placement dans la base de données
        Placement newPlacement = placementService.savePlacement(placement);
        return new ResponseEntity<>(newPlacement, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/return")
    public BigDecimal calculateReturn(@PathVariable Long id) {
        Placement placement = placementService.getPlacementById(id);
        return placementService.calculateReturn(placement);
    }

    @GetMapping("tousplacement")
    public List<Placement> getAllPlacements() {
        return placementService.getAllPlacements();
    }
}

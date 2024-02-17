package com.example.finance.Service;

import com.example.finance.Repository.PlacementRepository;
import com.example.finance.entites.Placement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PlacementService {

    private final PlacementRepository placementRepository;

    @Autowired
    public PlacementService(PlacementRepository placementRepository) {
        this.placementRepository = placementRepository;
    }

    public Placement savePlacement(Placement placement) {
        // Validation des données (vous pouvez ajouter des validations ici)
        // Enregistrement du placement dans la base de données
        return placementRepository.save(placement);
    }

    public List<Placement> getAllPlacements() {
        return placementRepository.findAll();
    }

    public BigDecimal calculateReturn(Placement placement) {
        BigDecimal returnAmount = BigDecimal.ZERO;
        // Calcul du rendement en fonction du type de placement
        switch (placement.getType()) {
            case "action":

                returnAmount = placement.getMontantInvesti()
                        .multiply(placement.getTauxRendementAttendu().divide(BigDecimal.valueOf(100)))
                        .setScale(2, BigDecimal.ROUND_HALF_UP); // Arrondi à 2 décimales
                break;

            case "obligation":
                // Calcul du rendement pour les obligations
                // Pour cet exemple, supposons que le rendement des obligations est de 5% par an
                returnAmount = placement.getMontantInvesti()
                        .multiply(placement.getTauxRendementAttendu().divide(BigDecimal.valueOf(100)))
                        .setScale(2, BigDecimal.ROUND_HALF_UP); // Arrondi à 2 décimales
                break;

            case "fonds commun de placement":

                break;
        }
        return returnAmount;
    }

    public Placement getPlacementById(Long id) {
        Optional<Placement> optionalPlacement = placementRepository.findById(id);
        if (optionalPlacement.isPresent()) {
            return optionalPlacement.get();
        } else {
            throw new IllegalArgumentException("Placement with id " + id + " not found");
        }
    }
}

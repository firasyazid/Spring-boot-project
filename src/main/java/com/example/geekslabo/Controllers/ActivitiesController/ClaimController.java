package com.example.geekslabo.Controllers.ActivitiesController;


import com.example.geekslabo.Entities.AppUser;
import com.example.geekslabo.Entities.Claim;
import com.example.geekslabo.IServices.IServiceActivities.IClaimService;
import com.example.geekslabo.Repositories.ActivityRepo.ClaimRepository;
import com.example.geekslabo.Services.Activity.ClaimService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/Claims")
public class ClaimController {
    IClaimService serv;
    ClaimService claimService;;
    ClaimRepository claimRepository;

    @PostMapping("/add-claim")
    public void ajouterReclamation (@RequestBody Claim reclamation) {
        serv.AddClaim (reclamation);
    }

    @GetMapping("/Listclaims")
    public List<Claim> getAllClaims () {
        return serv.GetallClaims ();
    }

    @PutMapping("/{id}")
    public Claim updateClaim (@PathVariable Integer id, @RequestBody Claim claim) {
        return serv.updateClaim (id, claim);
    }


    @DeleteMapping("/delete-claim/{id}")
    public void delete (@PathVariable Integer id) {
        serv.delete (id);
    }

    @GetMapping("/find-claim/{id}")
    public Claim findClaimById (@PathVariable Integer id) {
        return serv.findClaimById (id);
    }

    @GetMapping("/find-claim-title/{title}")
    public ResponseEntity<Claim> getClaimByTitle(@PathVariable String title) {
        Claim claim = serv.findClaimByTitle(title);
        if (claim == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(claim);
        }
    }


    @PostMapping("/{userId}")
    public ResponseEntity<String> addClaimToUser (@RequestBody Claim claim, @PathVariable Integer userId) {
        serv.AddclaimTouser (claim, userId);
        return ResponseEntity.ok ("Claim added successfully!");

    }

    @PostMapping("claimAdvanced/{userId}")
    public Claim addClaimAdvanced (@RequestBody Claim claim, @PathVariable Integer userId) {
        return serv.addClaim_advanced (claim, userId);

    }

    @GetMapping("/stats/claims-per-user")
    public Map<Integer, Integer> getClaimsPerUser () {
        return serv.getClaimsPerUser ();
    }

    @GetMapping("/stats/most-claims-user")
    public AppUser getUserWithMostClaims () {
        return serv.getUserWithMostClaims ();
    }
    @GetMapping("/emergency/{id}")
    public ResponseEntity<String> determineEmergency(@PathVariable("id") int idClaim) {
        boolean isEmergency = serv.determineEmergencyById(idClaim);
        if (isEmergency) {
            return new ResponseEntity<>("Emergency claim", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Non-emergency claim", HttpStatus.OK);
        }
    }


}

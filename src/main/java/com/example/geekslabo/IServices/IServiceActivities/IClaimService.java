package com.example.geekslabo.IServices.IServiceActivities;


import com.example.geekslabo.Entities.AppUser;
import com.example.geekslabo.Entities.Claim;

import java.util.List;
import java.util.Map;

public interface IClaimService {
    public Claim addClaim_advanced ( Claim claim , Integer userId );

    public Map<Integer, Integer> getClaimsPerUser ();

    public AppUser getUserWithMostClaims ();

    List<Claim> GetallClaims ();

    public Claim AddClaim ( Claim claim );

    public Claim findClaimByTitle(String title) ;

    void delete ( Integer id );

    public Claim findClaimById ( Integer id );

    public Claim updateClaim ( Integer id , Claim claim );

    public void AddclaimTouser ( Claim claim , Integer userId );

    public void sendEmail ( String recipientEmail , String subject , String message );

    public boolean determineEmergencyById(Integer idClaim) ;


}

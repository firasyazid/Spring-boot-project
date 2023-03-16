package com.example.geekslabo.Services.Activity;

import ch.qos.logback.core.status.Status;
import com.example.geekslabo.Entities.AppUser;
import com.example.geekslabo.Entities.Claim;
import com.example.geekslabo.IServices.IServiceActivities.IClaimService;
import com.example.geekslabo.Repositories.ActivityRepo.ClaimRepository;
import com.example.geekslabo.Repositories.UserRepo.AppUserCRUDRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ClaimService implements IClaimService {


    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    ClaimRepository repo;
    AppUserCRUDRepository repuser;

    @Override
    public List<Claim> GetallClaims () {
        return repo.findAll ();
    }

    @Override
    public Claim AddClaim ( Claim claim ) {
        return repo.save (claim);
    }

    @Override
    public Claim findClaimById ( Integer id ) {
        return repo.findById (id).orElse (null);
    }

    @Override
    public void delete ( Integer id ) {
        repo.deleteById (id);
    }
    @Override

    public Claim findClaimByTitle(String title) {
        return repo.findByTitle(title);
    }


    @Override
    public Claim updateClaim ( Integer id, Claim claim ) {

        Claim oldclaim = repo.findById (id).orElse (null);
        if (oldclaim == null) {
            throw new ResponseStatusException (HttpStatus.NOT_FOUND, "Claim not found with id " + id);
        }

        oldclaim.setDescription (oldclaim.getDescription ());
        oldclaim.setStatus (oldclaim.getStatus ());
        return repo.save (oldclaim);
    }

    @Override
    public Claim addClaim_advanced ( Claim claim, Integer userId ) {
        AppUser user = repuser.findById (userId).orElse (null);
        if (user == null) {
            throw new ResponseStatusException (HttpStatus.NOT_FOUND, "User not found with id " + userId);
        }

        Set<ConstraintViolation<Claim>> violations = validator.validate(claim);
        if (!violations.isEmpty()) {
            throw new ValidationException ("Claim validation failed: " + violations);
        }
        claim.setUser (user);
        claim.setStatus (Claim.Status.IN_PROGRESS);
        claim.setDate (LocalDate.now ());
        sendEmail (user.getEmail (), "Your claim has been received", "Dear " + user.getNom () + ",\n\nYour claim has been received and is currently being processed. We will keep you updated on the progress of your claim.\n\nThank you,\nThe Support Team");

        return repo.save (claim);
    }

    @Override
    public void AddclaimTouser ( Claim claim, Integer userId ) {
        AppUser user = repuser.findById (userId).orElse (null);
        claim.setUser (user);
        claim.setStatus (Claim.Status.IN_PROGRESS);
        claim.setDate (LocalDate.now ());
        repo.save (claim);
    }
    @Override
    public void sendEmail ( String recipientEmail, String subject, String message ) {
        try {
            Properties properties = new Properties ();
            HtmlEmail email = new HtmlEmail();
            properties.put ("mail.smtp.auth", "true");
            properties.put ("mail.smtp.host", "smtp.gmail.com");
            properties.put ("mail.smtp.port", "465");
            properties.put ("mail.smtp.ssl.enable", "true");
            String username = "yazid.firas@esprit.tn"; // replace with your email address
            String password = "bfhdezhqjwqhvqas"; // replace with your email password
            Session session = Session.getInstance (properties, new Authenticator () {
                @Override
                protected PasswordAuthentication getPasswordAuthentication () {
                    return new PasswordAuthentication (username, password);
                }
            });
            Message mail = new MimeMessage (session);
            mail.setFrom (new InternetAddress (username));
            mail.setRecipient (Message.RecipientType.TO, new InternetAddress (recipientEmail));
            mail.setSubject (subject);
            mail.setText (message);
            Transport.send (mail);
        } catch (MessagingException e) {
            e.printStackTrace ();
        }
        
    }






    @Override
    public Map<Integer, Integer> getClaimsPerUser () {
        Map<Integer, Integer> claimsPerUser = new HashMap<> ();
        List<Claim> allClaims = repo.findAll ();
        for (Claim claim : allClaims) {
            Integer userId = claim.getUser ().getId ();
            claimsPerUser.put (userId, claimsPerUser.getOrDefault (userId, 0) + 1);
        }
        return claimsPerUser;
    }


    @Override
    public AppUser getUserWithMostClaims () {
        Map<Integer, Integer> claimsPerUser = new HashMap<> ();
        List<Claim> allClaims = repo.findAll ();
        for (Claim claim : allClaims) {
            Integer userId = claim.getUser ().getId ();
            claimsPerUser.put (userId, claimsPerUser.getOrDefault (userId, 0) + 1);
        }
        AppUser userWithMostClaims = null;
        int maxClaims = 0;
        for (Integer userId : claimsPerUser.keySet ()) {
            Integer numClaims = claimsPerUser.get (userId);
            if (numClaims > maxClaims) {
                maxClaims = numClaims;
                userWithMostClaims = repuser.findById (userId).orElse (null);
            }
        }
        return userWithMostClaims;
    }
    @Override
    public boolean determineEmergencyById(Integer idClaim) {
       Claim claim = repo.findById(idClaim).orElse (null);
        boolean isEmergency = false;
        if (claim.getTitle().toLowerCase().contains("emergency")) {
            isEmergency = true;
        } else if (claim.getTitle().toLowerCase().contains("urgent")) {
            isEmergency = true;
        } else if (claim.getTitle().toLowerCase().contains("critical")) {
            isEmergency = true;
        } else if (claim.getTitle().toLowerCase().contains("harcélement")) {
            isEmergency = true;
        }
        return isEmergency;}


}


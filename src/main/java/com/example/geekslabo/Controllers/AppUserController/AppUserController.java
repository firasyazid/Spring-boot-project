package com.example.geekslabo.Controllers.AppUserController;

import com.example.geekslabo.Entities.AppUser;
import com.example.geekslabo.Entities.Article;
import com.example.geekslabo.Entities.UserRole;
import com.example.geekslabo.IServices.IServiceAppUser.IServiceCRUDAppUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("/AppUser")
public class AppUserController {
    public IServiceCRUDAppUser ServiceAppUser ;

    @GetMapping("/GetAllAppUsers")
   public List<AppUser> findAllAppUser(){
        return ServiceAppUser.findAllAppUser() ;
    }

    @GetMapping("/GetAppUsersByRole/{userRole}")
    List<AppUser> findAppUserByUserRole(@PathVariable(name = "userRole") UserRole userRole){
        return ServiceAppUser.findAppUserByUserRole(userRole) ;
    }
    @GetMapping("/GetAppUserByUserName/{userName}")
    AppUser findAppUserByUserName(@PathVariable(name = "userName") String userName){
        return ServiceAppUser.findAppUserByUserName(userName) ;
    }
    @GetMapping("/GetAppUserbyId/{id}")
    AppUser findAppUserById(@PathVariable(name = "id") Integer id){
        return ServiceAppUser.findAppUserById(id) ; 
    }
    @PutMapping ("/UpdateAppUser")
    AppUser updateAppUser(@RequestBody AppUser appUser){
        return ServiceAppUser.updateAppUser(appUser);
    }
    @DeleteMapping ("/DeleteAppUser/{id}")
    void deleteAppUser(@PathVariable(name = "id") Integer id){
        ServiceAppUser.deleteAppUser(id);
    }
    @PostMapping("/SignUp")
    AppUser SignUp(@RequestBody AppUser appUser){
        return ServiceAppUser.SignUp(appUser);
    }
}

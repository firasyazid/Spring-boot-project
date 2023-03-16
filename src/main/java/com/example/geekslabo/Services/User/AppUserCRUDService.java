package com.example.geekslabo.Services.User;

import com.example.geekslabo.Entities.AppUser;
import com.example.geekslabo.Entities.UserRole;
import com.example.geekslabo.IServices.IServiceAppUser.IServiceCRUDAppUser;
import com.example.geekslabo.Repositories.UserRepo.AppUserCRUDRepository;
import com.example.geekslabo.Repositories.UserRepo.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor

public class AppUserCRUDService implements IServiceCRUDAppUser {

    public AppUserCRUDRepository appUserRepository;

    @Override
    public List<AppUser> findAllAppUser() {
        return appUserRepository.findAll();
    }

    @Override
    public List<AppUser> findAppUserByUserRole(UserRole userRole) {
        return appUserRepository.findByRole(userRole) ;
    }

    @Override
    public AppUser findAppUserByUserName(String userName) {
        return  appUserRepository.findByUserName(userName) ;
    }

    @Override
    public AppUser findAppUserById(Integer id) {
        return appUserRepository.findById(id).orElse(null) ;
    }

    @Override
    public AppUser updateAppUser(AppUser appUser) {
        AppUser user1 = findAppUserById(appUser.getId()) ;
        user1 = appUser ;
        return appUserRepository.save(user1);
    }

    @Override
    public void deleteAppUser(Integer id) {
         appUserRepository.deleteById(id);
    }

    @Override
    public AppUser SignUp(AppUser appUser) {
        return appUserRepository.save(appUser) ;
    }
}

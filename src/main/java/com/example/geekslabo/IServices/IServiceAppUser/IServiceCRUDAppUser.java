package com.example.geekslabo.IServices.IServiceAppUser;

import com.example.geekslabo.Entities.AppUser;
import com.example.geekslabo.Entities.UserRole;

import java.util.List;

public interface IServiceCRUDAppUser {

    List<AppUser> findAllAppUser();
    List<AppUser> findAppUserByUserRole(UserRole userRole);
    AppUser findAppUserByUserName(String userName);
    AppUser findAppUserById(Integer id);
    AppUser updateAppUser(AppUser appUser);
    void deleteAppUser(Integer id);
    AppUser SignUp(AppUser appUser);
}

package com.example.geekslabo.Repositories.UserRepo;

import com.example.geekslabo.Entities.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    AppUser findByUserName(String userName);
}

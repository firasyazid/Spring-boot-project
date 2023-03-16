package com.example.geekslabo.Repositories.UserRepo;

import com.example.geekslabo.Entities.AppUser;
import com.example.geekslabo.Entities.UserRole;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppUserCRUDRepository extends JpaRepository<AppUser, Integer> {

    List<AppUser> findByRole(UserRole userRole);

    @Query("select u from app_user  where u.user_name = :userName")

            public AppUser findByUserName(@Param("userName") String userName);
}

package com.example.geekslabo.Repositories.UserRepo;
import com.example.geekslabo.Entities.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<UserRole, Integer> {
}

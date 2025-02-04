package com.MyProject.Feature_Tracking_Portal.repository;
import com.MyProject.Feature_Tracking_Portal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    public boolean existsByEmail(String email);
//    public Optional<User> findUserByUsername(String username);
    public Optional<User> findUserByEmail(String email);



//    List<User> findByRole(String role); this will needed when we add feature..
}
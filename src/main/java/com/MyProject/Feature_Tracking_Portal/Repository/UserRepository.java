package com.MyProject.Feature_Tracking_Portal.Repository;
import com.MyProject.Feature_Tracking_Portal.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email); // Correct field
//    List<User> findByRole(String role); this will needed when we add feature..
}
package com.example.springbanksolid.DAO;

import com.example.springbanksolid.model.Role.Role;
import com.example.springbanksolid.model.User.User_table;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User_table,Integer> {
    @Query(value="SELECT * FROM user_table WHERE username = :username", nativeQuery = true)
    Optional<User_table> findUser_tableByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_table (username, password, role) values (:username,:password,:role)", nativeQuery = true)
    void createNewUser (@Param("username")String username,@Param("password") String password, @Param("role") String role);

}

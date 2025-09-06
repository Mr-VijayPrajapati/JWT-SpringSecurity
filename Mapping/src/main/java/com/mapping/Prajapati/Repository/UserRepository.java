package com.mapping.Prajapati.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapping.Prajapati.Entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User,Long>{

}

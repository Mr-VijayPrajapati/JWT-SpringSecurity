package com.mapping.Prajapati.Service;

import java.util.List;

import com.mapping.Prajapati.DTO.StudentDTO;
import com.mapping.Prajapati.DTO.UserDTO;
import com.mapping.Prajapati.Entity.User;

public interface UserService {

    List<UserDTO> getAllUser();
  UserDTO createUser(UserDTO dto);
    UserDTO getById(Long id);
        UserDTO linkStudent(Long userId, Long studentId);
    
}

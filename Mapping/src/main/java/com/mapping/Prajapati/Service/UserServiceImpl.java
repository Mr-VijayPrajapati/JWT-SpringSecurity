package com.mapping.Prajapati.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mapping.Prajapati.DTO.LaptopDTO;
import com.mapping.Prajapati.DTO.StudentDTO;
import com.mapping.Prajapati.DTO.UserDTO;
import com.mapping.Prajapati.Entity.Laptop;
import com.mapping.Prajapati.Entity.Student;
import com.mapping.Prajapati.Entity.User;
import com.mapping.Prajapati.Repository.StudentRepository;
import com.mapping.Prajapati.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    // Convert Entity -> DTO
    private UserDTO mapToDTO(User user) {
        StudentDTO studentDTO = null;
        if (user.getStudent() != null) {
            // Map Laptop also
            Laptop laptop = user.getStudent().getLaptop();
            LaptopDTO laptopDTO = null;
            if (laptop != null) {
                laptopDTO = new LaptopDTO(
                    laptop.getId(),
                    laptop.getBrand()
                );
            }

            studentDTO = new StudentDTO(
                user.getStudent().getId(),
                user.getStudent().getName(),
                user.getStudent().getEmail(),   // only if Student has email field
                laptopDTO
            );
        }

        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getUseremail(),
            studentDTO
        );
    }

    // Convert DTO -> Entity
    private User mapToEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setUseremail(dto.getUseremail());

        if (dto.getStudentDTO() != null && dto.getStudentDTO().getId() != null) {
            Student student = studentRepository.findById(dto.getStudentDTO().getId())
                    .orElseThrow(() -> new NoSuchElementException("Student not found with given id"));
            user.setStudent(student);
        }
        return user;
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = userRepository.save(mapToEntity(dto));
        return mapToDTO(user);
    }

    @Override
    public UserDTO getById(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with given id"));
        return mapToDTO(u);
    }

    // New: link student to user after registration
    @Override
    public UserDTO linkStudent(Long userId, Long studentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with given id"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with given id"));

        user.setStudent(student);
        User updated = userRepository.save(user);
        return mapToDTO(updated);
    }
}

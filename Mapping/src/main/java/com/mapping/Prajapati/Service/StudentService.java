package com.mapping.Prajapati.Service;

import java.util.List;
import com.mapping.Prajapati.DTO.StudentDTO;

public interface StudentService {
    List<StudentDTO> getAllStudent();
    StudentDTO getById(Long id);
    StudentDTO createStudent(StudentDTO dto); // fix typo from 'creaetStudent'
    
    // Add this method
    StudentDTO linkLaptop(Long studentId, Long laptopId);
}

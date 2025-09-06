package com.mapping.Prajapati.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mapping.Prajapati.DTO.LaptopDTO;
import com.mapping.Prajapati.DTO.StudentDTO;
import com.mapping.Prajapati.Entity.Laptop;
import com.mapping.Prajapati.Entity.Student;
import com.mapping.Prajapati.Repository.LaptopRepository;
import com.mapping.Prajapati.Repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final LaptopRepository laptopRepository;

    // Convert Student entity to DTO
    private StudentDTO mStudentDTO(Student student) {
        LaptopDTO laptopDto = null;
        if (student.getLaptop() != null) {
            laptopDto = new LaptopDTO(
                student.getLaptop().getId(),
                student.getLaptop().getBrand()
            );
        }

        return new StudentDTO(
            student.getId(),
            student.getName(),
            student.getEmail(),
            laptopDto
        );
    }

    // Convert DTO to Student entity
    private Student mStudentEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());

        if (dto.getLaptop() != null) {
            Laptop laptop = new Laptop();
            laptop.setId(dto.getLaptop().getId());
            laptop.setBrand(dto.getLaptop().getBrand());

            // Link both sides
            laptop.setStudent(student);
            student.setLaptop(laptop);
        }

        return student;
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        return studentRepository.findAll()
                .stream()
                .map(this::mStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + id));
        return mStudentDTO(student);
    }

    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        Student saved = studentRepository.save(mStudentEntity(dto));
        return mStudentDTO(saved);
    }

    @Override
    public StudentDTO linkLaptop(Long studentId, Long laptopId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + studentId));

        Laptop laptop = laptopRepository.findById(laptopId)
                .orElseThrow(() -> new NoSuchElementException("Laptop not found with id: " + laptopId));

        // Link both sides
        student.setLaptop(laptop);
        laptop.setStudent(student);

        studentRepository.save(student); // Save updated student with linked laptop

        return mStudentDTO(student);
    }
}

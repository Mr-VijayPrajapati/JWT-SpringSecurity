package com.mapping.Prajapati.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapping.Prajapati.DTO.StudentDTO;
import com.mapping.Prajapati.Entity.Student;
import com.mapping.Prajapati.Service.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api-student")
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/AllStudent")
    public List<StudentDTO> getAllStudent(){
 return studentService.getAllStudent();
    }

    @GetMapping("{id}")
  public StudentDTO getStudentById(@PathVariable Long id){
    return studentService.getById(id);
  }
  
@PostMapping("/New-Student")
  public StudentDTO createStudent(@RequestBody StudentDTO dto){
    return studentService.createStudent(dto);
  }
}

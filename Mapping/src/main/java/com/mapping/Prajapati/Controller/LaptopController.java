package com.mapping.Prajapati.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapping.Prajapati.DTO.LaptopDTO;
import com.mapping.Prajapati.DTO.StudentDTO;
import com.mapping.Prajapati.Service.LaptopService;
import com.mapping.Prajapati.Service.StudentService;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api-laptop")
@RequiredArgsConstructor
public class LaptopController {

    private final LaptopService laptopService;
    private final StudentService studentService; // Add this

    @GetMapping("/alllaptop")
    public List<LaptopDTO> getAll() {
        return laptopService.getAllLaptop();
    }

    @GetMapping("/{id}")
    public LaptopDTO getById(@PathVariable Long id) {
        return laptopService.getById(id);
    }

    @PostMapping("/RegisterLaptop")
    public LaptopDTO createLaptop(@RequestBody LaptopDTO dto) {
        return laptopService.createLaptop(dto);
    }

    @PutMapping("/{studentId}/link-laptop/{laptopId}")
    public StudentDTO linkLaptopToStudent(@PathVariable Long studentId, @PathVariable Long laptopId) {
        return studentService.linkLaptop(studentId, laptopId);
    }
}

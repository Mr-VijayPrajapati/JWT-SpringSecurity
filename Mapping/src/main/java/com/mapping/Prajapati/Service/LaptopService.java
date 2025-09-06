package com.mapping.Prajapati.Service;

import java.util.List;

import com.mapping.Prajapati.DTO.LaptopDTO;
import com.mapping.Prajapati.DTO.StudentDTO;
import com.mapping.Prajapati.Entity.Laptop;

public interface LaptopService {
List<LaptopDTO> getAllLaptop();
LaptopDTO createLaptop (LaptopDTO dto );
LaptopDTO getById(Long id);

    
} 

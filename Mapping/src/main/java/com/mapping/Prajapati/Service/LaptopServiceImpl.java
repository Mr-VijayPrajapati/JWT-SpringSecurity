package com.mapping.Prajapati.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Service;

import com.mapping.Prajapati.DTO.LaptopDTO;
import com.mapping.Prajapati.DTO.StudentDTO;
import com.mapping.Prajapati.Entity.Laptop;
import com.mapping.Prajapati.Repository.LaptopRepository;
import com.mapping.Prajapati.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LaptopServiceImpl implements LaptopService{

  private final LaptopRepository laptopRepository;

private LaptopDTO mLaptopDTO(Laptop laptop){
    return new LaptopDTO(laptop.getId(),laptop.getBrand());
}
 private Laptop mLaptopEntity(LaptopDTO dto){
    Laptop l =new Laptop();
    l.setId(dto.getId());
    l.setBrand(dto.getBrand());
    return l;
 }

    @Override
    public List<LaptopDTO> getAllLaptop() {
     return laptopRepository.findAll().stream().map(this :: mLaptopDTO).collect(Collectors.toList());
    }

    @Override
    public LaptopDTO createLaptop(LaptopDTO dto) {
        Laptop Saved=laptopRepository.save(mLaptopEntity(dto));
        return mLaptopDTO(Saved);
    }

    @Override
    public LaptopDTO getById(Long id) {
    Laptop s =laptopRepository.findById(id).orElseThrow(() -> new NoSuchElementException("laptop is not available by given student_id"));
    return mLaptopDTO(s);
    
}
}

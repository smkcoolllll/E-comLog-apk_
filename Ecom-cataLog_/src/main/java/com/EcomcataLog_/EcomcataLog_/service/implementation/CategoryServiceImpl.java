package com.EcomcataLog_.EcomcataLog_.service.implementation;

import com.EcomcataLog_.EcomcataLog_.DTO.CategoryDTO;
import com.EcomcataLog_.EcomcataLog_.entity.Catagory;
import com.EcomcataLog_.EcomcataLog_.exception.NotFoundException;
import com.EcomcataLog_.EcomcataLog_.repository.CategoryRepository;
import com.EcomcataLog_.EcomcataLog_.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Catagory> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        // Map the CategoryDTO to Catagory entity
        Catagory catagory = modelMapper.map(categoryDTO, Catagory.class);

        // Save the category
        Catagory savedCategory = categoryRepository.save(catagory);

        // Map the saved category back to CategoryDTO
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public void deleteMapForCatagory(Long id) {
        Catagory category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with ID: " + id));

        categoryRepository.delete(category);
    }

}

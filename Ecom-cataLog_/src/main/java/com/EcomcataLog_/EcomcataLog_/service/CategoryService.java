package com.EcomcataLog_.EcomcataLog_.service;

import com.EcomcataLog_.EcomcataLog_.DTO.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService  {

    List<CategoryDTO> getAllCategories();
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    void deleteMapForCatagory(Long id);

}

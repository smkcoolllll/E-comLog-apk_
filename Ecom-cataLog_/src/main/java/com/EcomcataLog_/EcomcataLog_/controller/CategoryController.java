package com.EcomcataLog_.EcomcataLog_.controller;
import com.EcomcataLog_.EcomcataLog_.DTO.CategoryDTO;
import com.EcomcataLog_.EcomcataLog_.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }



    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO addedCategory = categoryService.addCategory(categoryDTO);
        return ResponseEntity.ok(addedCategory);
    }

    @DeleteMapping("/catg/{id}")
    public ResponseEntity<String> removeCatagory(@PathVariable Long id){

        categoryService.deleteMapForCatagory(id);
        return ResponseEntity.ok("catagory with specific catagoryId deleted...");
    }
}


package com.EcomcataLog_.EcomcataLog_.service.implementation;
import com.EcomcataLog_.EcomcataLog_.DTO.ProductDTO;
import com.EcomcataLog_.EcomcataLog_.entity.Catagory;
import com.EcomcataLog_.EcomcataLog_.entity.Product;
import com.EcomcataLog_.EcomcataLog_.exception.NotFoundException;
import com.EcomcataLog_.EcomcataLog_.repository.CategoryRepository;
import com.EcomcataLog_.EcomcataLog_.repository.ProductRepository;
import com.EcomcataLog_.EcomcataLog_.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        List<Product> products = productRepository.findByCategory_Id(categoryId);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();

        // Map the list of Product entities to a list of ProductDTO
        return productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        Catagory category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        Product product = modelMapper.map(productDTO, Product.class);

        product.setCategory(category);

        category.getProducts().add(product);

        categoryRepository.save(category);
    }



    @Override
    public void updateProduct(Long productId, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        existingProduct.setName(productDTO.getName());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setProductDesc(productDTO.getProductDesc());


        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    @Transactional
    public void deleteProductsByCategory(Long categoryId) {
        Catagory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        productRepository.deleteAllByCategory(category);
    }


}

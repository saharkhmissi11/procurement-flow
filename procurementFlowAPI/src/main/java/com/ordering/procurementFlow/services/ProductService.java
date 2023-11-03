package com.ordering.procurementFlow.services;
import com.ordering.procurementFlow.DTO.ProductDto;
import com.ordering.procurementFlow.Models.Product;
import com.ordering.procurementFlow.repositories.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ModelMapper modelMapper ;
    private final ProductRepo productRepo;

    public Optional<ProductDto> findProductById(Long productId){
        if (productId==0){log.error("ProductId is null");}
        Optional<Product> product=productRepo.findById(productId);
        return product.map(u->modelMapper.map(u, ProductDto.class));
    }

    public List<ProductDto> findAllProducts(){
        List<Product> products=productRepo.findAll();
        return products.stream().map(u->modelMapper.map(u, ProductDto.class)).collect(Collectors.toList());
    }

    public ProductDto addProduct (ProductDto productDto){
        Product product= modelMapper.map(productDto,Product.class);
        Product savedProduct = productRepo.save(product);
        return modelMapper.map(savedProduct,ProductDto.class);
    }

    public ProductDto UpdateProduct(ProductDto productDto){
        Product product= modelMapper.map(productDto,Product.class);
        Product savedProduct = productRepo.save(product);
        return modelMapper.map(savedProduct,ProductDto.class);
    }
    public void DeleteProductById(long productId) {
        if (productId == 0) {
            log.error("productId is null");
        }
        productRepo.deleteById(productId);
    }
}

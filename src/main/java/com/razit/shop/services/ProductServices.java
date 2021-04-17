package com.razit.shop.services;

import com.razit.shop.models.entity.Product;
import com.razit.shop.models.entity.Supplier;
import com.razit.shop.models.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product){
        return  productRepository.save(product);
    }

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            return null;
        }
        return product.get();
    }

    public void addSupplier(Supplier supplier,Long productId){
        Product product= findById(productId);

        if(product == null){
            throw new RuntimeException("Product with ID: "+productId+"NotFound");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }
}

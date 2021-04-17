package com.razit.shop.services;


import com.razit.shop.models.entity.Product;
import com.razit.shop.models.entity.Supplier;
import com.razit.shop.models.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierService supplierService;

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

    public List<Product> findProductByName(String name){
        return productRepository.findProductByName(name);
    }

    public List<Product> findProductLikeName(String name){
        return productRepository.findProductLikeName("%"+name+"%");
    }

    public List<Product> findByCategoryId(Long id){
        return productRepository.findProductByCategory(id);
    }

    public List<Product> findBySupplier(Long id){
        Supplier supplier = supplierService.findById(id);
        if(supplier == null){
            return new ArrayList<Product>();
        }
        return productRepository.findProductBySupplier(supplier);
    }
}

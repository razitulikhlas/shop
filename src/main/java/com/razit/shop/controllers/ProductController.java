package com.razit.shop.controllers;

import com.razit.shop.dto.ResponseData;
import com.razit.shop.dto.SearchData;
import com.razit.shop.models.entity.Product;
import com.razit.shop.models.entity.Supplier;
import com.razit.shop.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> save(@Valid @RequestBody Product product, Errors errors)
    {
        ResponseData<Product> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error:errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productServices.save(product));
        return ResponseEntity.status(HttpStatus.OK).body(responseData);

    }

    @GetMapping
    public Iterable<Product> findAll(){
        return productServices.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id){
        return productServices.findById(id);
    }

    @PostMapping("/{id}")
    public void addSuppliers(@RequestBody Supplier supplier,@PathVariable("id") Long productId){
        productServices.addSupplier(supplier,productId);
    }

    @PostMapping("search/name")
    public List<Product> findByName(@RequestBody SearchData searchData){
        return productServices.findProductByName(searchData.getSearchKey());
    }

    @PostMapping("search/nameLike")
    public List<Product> findNameLike(@RequestBody SearchData searchData){
        return productServices.findProductLikeName(searchData.getSearchKey());
    }

    @GetMapping("search/category/{id}")
    public List<Product> findProductByCategory(@PathVariable("id") Long id){
        return productServices.findByCategoryId(id);
    }

    @GetMapping("search/suppliers/{id}")
    public List<Product> findProductBySupplierId(@PathVariable("id") Long id){
        return productServices.findBySupplier(id);
    }

}

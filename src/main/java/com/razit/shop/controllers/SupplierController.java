package com.razit.shop.controllers;

import com.razit.shop.dto.ResponseData;
import com.razit.shop.dto.SearchData;
import com.razit.shop.dto.SupplierData;
import com.razit.shop.models.entity.Supplier;
import com.razit.shop.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error:errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Supplier supplier = modelMapper.map(supplierData,Supplier.class);

        responseData.setStatus((true));
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }


    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierData supplierData, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error:errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Supplier supplier = modelMapper.map(supplierData,Supplier.class);

        responseData.setStatus((true));
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @PostMapping("search/email")
    public Supplier findByEmail(@RequestBody SearchData searchData){
        return  supplierService.findByEmail(searchData.getSearchKey());
    }

    @GetMapping
    public Iterable<Supplier> findAll(){
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier findById(@PathVariable("id") Long id ){
        return supplierService.findById(id);
    }

    @PostMapping("search/nameLike")
    public List<Supplier> findNameLike(@RequestBody SearchData searchData){
        return supplierService.findByNameLike(searchData.getSearchKey());
    }

    @PostMapping("search/name/start")
    public List<Supplier> searchFirstName(@RequestBody SearchData searchData){
        return supplierService.searchStartName(searchData.getSearchKey());
    }

    @PostMapping("search/emailorname")
    public List<Supplier> searchEmailOrName(@RequestBody SearchData searchData){
        return supplierService.searchNameOrEmail(searchData.getSearchKey(),searchData.getSearchOther());
    }
}

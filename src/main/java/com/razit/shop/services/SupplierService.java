package com.razit.shop.services;

import com.razit.shop.models.entity.Supplier;
import com.razit.shop.models.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.util.List;
import java.util.Optional;

@Service
@TransactionScoped
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier save(Supplier supplier){
        return supplierRepository.save(supplier);
    }

    public Iterable<Supplier> findAll(){
        return supplierRepository.findAll();
    }

    public Supplier findById(Long id){
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(supplier.isEmpty()){
            return null;
        }
        return supplier.get();
    }

    public void removeById(Long id){
        supplierRepository.deleteById(id);
    }

    public Supplier findByEmail(String email){
        return supplierRepository.findByEmail(email);
    }

    public List<Supplier> findByNameLike(String email){
        return supplierRepository.findByNameContains(email);
    }

    public List<Supplier> searchStartName(String name){
        return supplierRepository.findByNameStartingWith(name);
    }

    public List<Supplier> searchNameOrEmail(String name,String email){
        return  supplierRepository.findByNameContainsOrEmailContains(name,email);
    }


}

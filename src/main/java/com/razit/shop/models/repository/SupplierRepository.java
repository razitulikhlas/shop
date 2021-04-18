package com.razit.shop.models.repository;

import com.razit.shop.models.entity.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier,Long> {

    Supplier findByEmail(String email);

    List<Supplier> findByNameContains(String name);

    List<Supplier> findByNameStartingWith(String name);

    List<Supplier> findByNameContainsOrEmailContains(String name,String email);
}

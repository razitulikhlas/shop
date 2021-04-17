package com.razit.shop.models.repository;

import com.razit.shop.models.entity.Product;
import com.razit.shop.models.entity.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.websocket.server.PathParam;
import java.util.List;


public interface ProductRepository extends CrudRepository<Product,Long> {

 @Query("SELECT p FROM Product p WHERE p.name = :name")
 List<Product> findProductByName(@PathParam("name") String name);

 @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
 List<Product> findProductLikeName(@PathParam("name") String name);

 @Query("SELECT x FROM Product x WHERE x.category.id = :id")
 List<Product> findProductByCategory(@PathParam("id") Long id);

 @Query("SELECT x from Product x WHERE :supplier MEMBER OF x.suppliers")
 List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);

 }

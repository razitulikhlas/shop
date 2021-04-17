package com.razit.shop.models.repository;

import com.razit.shop.models.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
 }

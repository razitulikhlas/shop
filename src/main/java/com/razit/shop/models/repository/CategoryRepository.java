package com.razit.shop.models.repository;

import com.razit.shop.models.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}

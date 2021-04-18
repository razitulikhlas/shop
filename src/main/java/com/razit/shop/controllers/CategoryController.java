package com.razit.shop.controllers;

import com.razit.shop.dto.CategoryData;
import com.razit.shop.dto.ResponseData;
import com.razit.shop.dto.SearchData;
import com.razit.shop.models.entity.Category;
import com.razit.shop.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> save(@Valid @RequestBody CategoryData categoryData, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error:errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setPayload(null);
            responseData.setStatus((false));
        }
        Category category = modelMapper.map(categoryData,Category.class);

        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryData categoryData, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error:errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setPayload(null);
            responseData.setStatus((false));
        }
        Category category = modelMapper.map(categoryData,Category.class);

        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable("id") Long id){
        return categoryService.findById(id);
    }

    @GetMapping()
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }

    @PostMapping("search/{size}/{page}")
    public Iterable<Category> findByName(@PathVariable("size")int size,@PathVariable("page") int page,@RequestBody SearchData searchData){
        Pageable pageable = PageRequest.of(page,size);
        return categoryService.findByName(searchData.getSearchKey(),pageable);
    }

    @PostMapping("search/{size}/{page}/{sort}")
    public Iterable<Category> findByName(@PathVariable("size")int size,@PathVariable("page") int page,@PathVariable("sort") String sort,@RequestBody SearchData searchData){
        Pageable pageable = PageRequest.of(page,size, Sort.by("id"));
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page,size, Sort.by("id").descending());
        }
        return categoryService.findByName(searchData.getSearchKey(),pageable);
    }

    @PostMapping("save/batch")
    public ResponseEntity<ResponseData<Iterable<Category>> >saveBatch(@RequestBody Category[] categories){
        ResponseData<Iterable<Category>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(categoryService.saveBatch(Arrays.asList(categories)));
        return ResponseEntity.status(HttpStatus.OK).body(responseData);

    }
}

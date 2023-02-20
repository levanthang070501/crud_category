package com.example.batdongsan.controller;

import com.example.batdongsan.ResourceNotFoundException;
import com.example.batdongsan.model.ResponseObject;
import com.example.batdongsan.model.Category;
import com.example.batdongsan.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AdminController {
    private final CategoryService categoryservice;


    public AdminController(CategoryService categoryService ){
        this.categoryservice = categoryService;

    }

    @GetMapping("/categories")
    public List<Category> getallcategory() {

        List<Category> cate = categoryservice.getAllCategory();
        return cate;

//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(new ResponseObject("Query success", "ok", categoryservice.getAllCategory()));
    }

    @PostMapping("/categories")
    public ResponseEntity<ResponseObject> postcategory(@RequestBody Category newcate) {
        List<Category> cate = categoryservice.getcateByName(newcate.getName().trim());
        if (cate.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("category name is already taken ", "fail", ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("post cate success", "ok", categoryservice.addcategory(newcate)));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long categoryId,
                                                    @RequestBody Category categoryDetails) throws ResourceNotFoundException {
        Category category = categoryservice.findbyid(categoryId);


        category.setName(categoryDetails.getName());


        final Category updatedCategory = categoryservice.addcategory(category);
        return ResponseEntity.ok(updatedCategory);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ResponseObject> getacate(@PathVariable Long id) {
        Optional<Category> cate = categoryservice.getCategoryById(id);

        if (cate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("can not find category id" + id, "fail", ""));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("query success", "ok", categoryservice.getCategoryById(id)));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<ResponseObject> deletecate(@PathVariable Long id) {
        Optional<Category> cate = categoryservice.getCategoryById(id);

        if (cate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("can not find category id" + id, "fail", ""));
        }
        categoryservice.deleteacate(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("delete success", "ok", ""));
    }




}

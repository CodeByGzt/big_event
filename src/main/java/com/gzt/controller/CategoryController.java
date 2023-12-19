package com.gzt.controller;

import com.gzt.pojo.Category;
import com.gzt.pojo.ResponseResult;
import com.gzt.pojo.vo.CategoryVo;
import com.gzt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: CategoryController
 * Package: com.gzt.controller
 * Description: 文章分类相关
 *
 * @Author gzt
 * @Create 12/11/2023 1:41 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 删除某个文章分类
    @DeleteMapping("")
    public ResponseResult deleteCategory(Integer id){
        return categoryService.deleteCategory(id);
    }

    // 修改某个文章分类
    @PutMapping("")
    public ResponseResult updateCategory(@RequestBody @Validated CategoryVo categoryVo){
        return categoryService.updateCategory(categoryVo);
    }

    // 某个文章分类详情
    @GetMapping("/detail")
    public ResponseResult getCategoryDetailById(Integer id){
        return categoryService.getCategoryDetailById(id);
    }

    // 所有分类数据
    @GetMapping("")
    public ResponseResult getAllCategories(){
        return categoryService.getAllCategories();
    }

    // 新增文章分类
    @PostMapping("")
    public ResponseResult addCategory(@RequestBody @Validated Category category){
        return categoryService.addCategory(category);
    }
}

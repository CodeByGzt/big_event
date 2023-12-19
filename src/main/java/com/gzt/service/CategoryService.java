package com.gzt.service;

import com.gzt.pojo.Category;
import com.gzt.pojo.ResponseResult;
import com.gzt.pojo.vo.CategoryVo;

/**
 * ClassName: CategoryService
 * Package: com.gzt.service
 * Description:
 *
 * @Author gzt
 * @Create 12/11/2023 1:49 PM
 * @Version 1.0
 */
public interface CategoryService {
    ResponseResult addCategory(Category category);

    ResponseResult getAllCategories();

    ResponseResult getCategoryDetailById(Integer id);

    ResponseResult updateCategory(CategoryVo categoryVo);

    ResponseResult deleteCategory(Integer id);
}

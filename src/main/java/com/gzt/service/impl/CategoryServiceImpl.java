package com.gzt.service.impl;

import com.gzt.mapper.CategoryMapper;
import com.gzt.pojo.Category;
import com.gzt.pojo.ResponseResult;
import com.gzt.pojo.vo.CategoryVo;
import com.gzt.service.CategoryService;
import com.gzt.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * ClassName: CategoryServiceImpl
 * Package: com.gzt.service.impl
 * Description:
 *
 * @Author gzt
 * @Create 12/11/2023 1:49 PM
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    // 删除某个文章分类
    @Override
    public ResponseResult deleteCategory(Integer id) {
        categoryMapper.deleteCategory(id);
        return ResponseResult.okResult();
    }

    // 修改某个文章分类
    @Override
    public ResponseResult updateCategory(CategoryVo categoryVo) {
        categoryMapper.updateCategory(categoryVo);
        return ResponseResult.okResult();
    }

    // 某个文章分类详情
    @Override
    public ResponseResult getCategoryDetailById(Integer id) {
        Category category = categoryMapper.selectById(id);
        return ResponseResult.okResult(category);
    }

    // 获取所有文章分类
    @Override
    public ResponseResult getAllCategories() {
        // 获取所有数据并返回
        List<Category> o =  categoryMapper.selectAllCategories();
        return ResponseResult.okResult(o);
    }

    // 添加文章分类
    @Override
    public ResponseResult addCategory(Category category) {
        // 添充属性
        Map<String,Object> userInfo = ThreadLocalUtil.get();
        category.setCreateUser((Integer) userInfo.get("id"));
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.insertCategory(category);
        return ResponseResult.okResult();
    }
}

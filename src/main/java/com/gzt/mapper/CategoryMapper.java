package com.gzt.mapper;

import com.gzt.pojo.Category;
import com.gzt.pojo.ResponseResult;
import com.gzt.pojo.vo.CategoryVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: CategoryMapper
 * Package: com.gzt.mapper
 * Description:
 *
 * @Author gzt
 * @Create 12/11/2023 1:50 PM
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper {
    // 新增文章分类
    @Insert("INSERT INTO category (category_name, category_alias, create_user, create_time, update_time) " +
            "VALUES (#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    void insertCategory(Category category);


    // 获取所有文章分类
    @Select("select * from category")
    List<Category> selectAllCategories();

    // 某个文章分类详情
    @Select("select * from category where id = #{id}")
    Category selectById(Integer id);

    // 修改某个文章分类
    @Update("UPDATE category SET category_name = #{categoryName}, category_alias = #{categoryAlias},update_time = now() WHERE id = #{id}")
    void updateCategory(CategoryVo categoryVo);

    // 删除某个文章分类
    @Delete("DELETE FROM category WHERE id = #{id}")
    void deleteCategory(Integer id);
}

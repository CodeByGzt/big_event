package com.gzt.mapper;

import com.gzt.pojo.Article;
import com.gzt.pojo.vo.ArticleVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: ArticleMapper
 * Package: com.gzt.mapper
 * Description:
 *
 * @Author gzt
 * @Create 12/11/2023 8:22 PM
 * @Version 1.0
 */
@Mapper
public interface ArticleMapper {

    // 添加文章
    @Insert("INSERT INTO article " +
            "(title, content, cover_img, state, category_id, create_user, create_time, update_time) VALUES " +
            "(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, now(), now())")
    void insertArticle(Article article);

    // 分页查询文章
    List<ArticleVo> selectArticleByPage(String categoryId, String state);

    // 查看文章详情
    @Select("select * from article where id = #{id} and create_user = #{userId} ")
    Article selectArticleById(Integer id, Integer userId);

    // 更新文章
    @Update("UPDATE article t SET t.title = #{title}, t.content = #{content}, t.state = #{state}, t.category_id = #{categoryId},t.cover_img = #{coverImg}, t.update_time = now() WHERE t.id = #{id}")
    void updateArticle(Article article);

    // 删除文章
    @Delete("DELETE FROM article WHERE id = #{id}")
    void deleteArticle(Integer id);
}

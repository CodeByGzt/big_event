package com.gzt.service;

import com.gzt.pojo.Article;
import com.gzt.pojo.ResponseResult;

/**
 * ClassName: ArticleService
 * Package: com.gzt.service
 * Description:
 *
 * @Author gzt
 * @Create 12/11/2023 8:18 PM
 * @Version 1.0
 */
public interface ArticleService {
    ResponseResult addArticle(Article article);

    ResponseResult getArticleListByPage(String categoryId, String state, Integer pageNum, Integer pageSize);

    ResponseResult getArticleDetail(Integer id);

    ResponseResult updateArticle(Article article);

    ResponseResult deleteArticle(Integer id);
}

package com.gzt.controller;

import com.gzt.annotation.State;
import com.gzt.pojo.Article;
import com.gzt.pojo.ResponseResult;
import com.gzt.service.ArticleService;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ArticleController
 * Package: com.gzt.controller
 * Description: 文章相关接口
 *
 * @Author gzt
 * @Create 12/11/2023 7:58 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    // 删除文章
    @DeleteMapping
    public ResponseResult deleteArticle(Integer id){
        return articleService.deleteArticle(id);
    }
    // 修改文章
    @PutMapping
    public ResponseResult updateArticle(@RequestBody @Validated Article article){
        return articleService.updateArticle(article);
    }
    // 通过id查看文章详情
    @GetMapping("/detail")
    public ResponseResult getArticleDetail(Integer id){
        return articleService.getArticleDetail(id);
    }

    // 分页查询文章
    @GetMapping
    public ResponseResult getArticleListByPage(@Min(1) Integer pageNum,
                                               @Min(1) Integer pageSize,
                                               @RequestParam(required = false) String categoryId,
                                               @RequestParam(required = false) @State String state
    ){
        return articleService.getArticleListByPage(categoryId,state,pageNum,pageSize);
    }
    // 添加文章
    @PostMapping
    public ResponseResult addArticle(@RequestBody @Validated Article article){
        return articleService.addArticle(article);
    }
}

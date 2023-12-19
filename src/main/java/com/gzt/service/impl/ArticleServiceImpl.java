package com.gzt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gzt.mapper.ArticleMapper;
import com.gzt.pojo.Article;
import com.gzt.pojo.PageBean;
import com.gzt.pojo.ResponseResult;
import com.gzt.pojo.vo.ArticleVo;
import com.gzt.service.ArticleService;
import com.gzt.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ClassName: ArticleServiceImpl
 * Package: com.gzt.service.impl
 * Description:
 *
 * @Author gzt
 * @Create 12/11/2023 8:20 PM
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    // 删除文章

    @Override
    public ResponseResult deleteArticle(Integer id) {
        articleMapper.deleteArticle(id);
        return ResponseResult.okResult();
    }

    // 修改文章
    @Override
    public ResponseResult updateArticle(Article article) {
        articleMapper.updateArticle(article);
        return ResponseResult.okResult();
    }

    // 查看文章详情
    @Override
    public ResponseResult getArticleDetail(Integer id) {
        // 用户只能获取自己的文章
        Map<String,Object> userInfo = ThreadLocalUtil.get();
        Article article = articleMapper.selectArticleById(id,(Integer)userInfo.get("id"));
        return ResponseResult.okResult(article);
    }

    // 分页查询文章
    @Override
    public ResponseResult getArticleListByPage(String categoryId, String state,Integer pageNum, Integer pageSize) {
        // 构建PageBean对象
        PageBean<ArticleVo> articlePageBean = new PageBean<>();
        // 开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        // 调用mapper完成查询
        // 用户只能查询自己的文章
        // Map<String,Object> userInfo = ThreadLocalUtil.get();
        List<ArticleVo> as =  articleMapper.selectArticleByPage(categoryId,state);
        // Page中提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据
        Page<ArticleVo> pageInfo = (Page<ArticleVo>) as;
        // 返回已经封装好的PageBean对象
        articlePageBean.setTotal(pageInfo.getTotal());
        articlePageBean.setItems(pageInfo.getResult());
        return ResponseResult.okResult(articlePageBean);
    }

    // 添加文章
    @Override
    public ResponseResult addArticle(Article article) {
        // 填充属性
        Map<String,Object> userInfo = ThreadLocalUtil.get();
        article.setCreateUser((Integer) userInfo.get("id"));
        articleMapper.insertArticle(article);
        return ResponseResult.okResult();
    }
}

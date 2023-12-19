package com.gzt.pojo.vo;

import com.gzt.annotation.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

/**
 * ClassName: ArticleVo
 * Package: com.gzt.pojo.vo
 * Description:
 *
 * @Author gzt
 * @Create 12/16/2023 9:36 PM
 * @Version 1.0
 */
@Data
public class ArticleVo {
    private Integer id;//主键ID
    @Size(min=1, max=10)
    private String title;//文章标题
    @NotBlank
    private String content;//文章内容
    @URL
    private String coverImg;//封面图像
    @State
    private String state;//发布状态 已发布|草稿
    private Integer categoryId;//文章分类id
    private String categoryName;//文章分类
    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}

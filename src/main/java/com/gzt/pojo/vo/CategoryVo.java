package com.gzt.pojo.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * ClassName: CategoryVo
 * Package: com.gzt.pojo.vo
 * Description:
 *
 * @Author gzt
 * @Create 12/11/2023 4:46 PM
 * @Version 1.0
 */
@Data
public class CategoryVo {
    @NotNull
    private Integer id;//主键ID
    @NotNull
    private String categoryName;//分类名称
    @NotNull
    private String categoryAlias;//分类别名
}

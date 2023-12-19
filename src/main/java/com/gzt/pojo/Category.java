package com.gzt.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    private Integer id;//主键ID
    @NotNull
    private String categoryName;//分类名称
    @NotNull
    private String categoryAlias;//分类别名
    // @JSONField(serialize = false)
    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}

package com.gzt.annotation;

import com.gzt.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: State
 * Package: com.gzt.annotation
 * Description: 自定义注解，判断文章状态参数是否正确
 *
 * @Author gzt
 * @Create 12/11/2023 7:27 PM
 * @Version 1.0
 */
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
// 提供校验规则的类
@Constraint(validatedBy = StateValidation.class)
public @interface State {
    // 提供校验失败后的提示信息
    String message() default "文章状态只能是：已发布或者草稿！";

    // 指定分组
    Class<?>[] groups() default {};

    // 负载获取到State 注解的附加信息,一般用不着
    Class<? extends Payload>[] payload() default {};
}

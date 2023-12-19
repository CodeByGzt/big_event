package com.gzt.validation;

import com.gzt.annotation.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * ClassName: StateValidation
 * Package: com.gzt.validation
 * Description: 对自定义注解@State逻辑的具体实现
 *
 * @Author gzt
 * @Create 12/11/2023 7:32 PM
 * @Version 1.0
 */
// 泛型的第一参数为：为哪个注解提供校验规则
//      第二参数为：要校验的数据类型
public class StateValidation implements ConstraintValidator<State,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null){
            return false;
        }
        if (value.equals("已发布") || value.equals("草稿") || value.equals("全部") ){
            return true;
        }
        return false;
    }
}

package com.xubo.druid.entity;

import com.xubo.druid.validate.AddGroup;
import com.xubo.druid.validate.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * @Author xubo
 * @Date 2022/4/7 17:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateEntity {

    @NotNull(groups = {UpdateGroup.class}, message = "修改时id必须存在！")
    @Null(groups = AddGroup.class, message = "新增id必须为空")
    private Integer id;

//    @Max(groups = {AddGroup.class,UpdateGroup.class}, value = 30, message = "姓名长度不超过30！")
//    @Min(groups = {AddGroup.class,UpdateGroup.class}, value = 2, message = "姓名长度不超过2！")
//    @Size(groups = {AddGroup.class})
    private String name;

    @Size(groups = {AddGroup.class,UpdateGroup.class}, max = 11, min = 4, message = "长度不正确！")
    private String tel;

    private String address;

    @Min(value = 2,message = "长度不小于2")
    @Max(value = 100,message = "最大值不能大于100")
    private Integer num;

}

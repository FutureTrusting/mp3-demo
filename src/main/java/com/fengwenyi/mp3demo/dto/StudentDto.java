package com.fengwenyi.mp3demo.dto;

import com.fengwenyi.mp3demo.enums.GenderEnum;
import com.fengwenyi.mp3demo.vo.StudentVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : Caixin
 * @date 2019/4/30 11:29
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto implements Serializable {

    @NotNull(message = "1111")
    @Valid
    private StudentVO studentVO;

    private Long id;
    private String name;

    private Long orgId;

    private Integer age;
    private String info;
    private Boolean isDelete;
    private Date createTime;
    private Date updateTime;
    private GenderEnum gender;
    private Long idcardId;
    private Long cityId;

    private static final long serialVersionUID = -4038434492498565166L;

}

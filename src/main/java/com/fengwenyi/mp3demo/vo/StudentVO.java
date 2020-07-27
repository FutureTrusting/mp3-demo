package com.fengwenyi.mp3demo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : Caixin
 * @date 2019/4/30 11:27
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentVO implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "id不为空")
    private Long id;
    @NotNull(message = "name不为空")
    private String name;
    private Integer age;
    private String info;
    private Boolean isDelete;
    private Date createTime;
    private Date updateTime;
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long idcardId;
    private String cardCode;
    private String cityName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long cityId;
    private static final long serialVersionUID = 4721378108697629854L;
}

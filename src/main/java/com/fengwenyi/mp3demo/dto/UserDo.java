package com.fengwenyi.mp3demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Caixin
 * @date 2019/7/25 15:54
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDo {

    private String username;
    private Integer age;
    private Boolean famous;
}

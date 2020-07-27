package com.fengwenyi.mp3demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author : Caixin
 * @date 2019/7/25 15:54
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class UserSortedV2 {

    private Long id;
    private String username;
    private Date createTime;

}

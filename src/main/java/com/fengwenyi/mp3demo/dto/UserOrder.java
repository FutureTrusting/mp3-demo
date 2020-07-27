package com.fengwenyi.mp3demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author : Caixin
 * @date 2019/10/16 10:20
 */

@Data
public class UserOrder implements Serializable {
    private static final long serialVersionUID = -8199925234080538662L;

    private Long id;
    private Date createTime;
    private String createTimeDesc;

}

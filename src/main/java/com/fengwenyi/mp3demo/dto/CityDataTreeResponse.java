package com.fengwenyi.mp3demo.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: LiuRenjin
 * @create: 2019-06-21 17:42
 **/
@Data
public class CityDataTreeResponse {
    private String code;
    private String name;
    private String parentCode;
    private Integer level;

    private Boolean isDeleted;


    private List<CityDataTreeResponse> city;
}

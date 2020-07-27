package com.fengwenyi.mp3demo.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: bsp下单顶级父类
 * @author: zhengfei
 * @param:
 * @date: 2019-04-04 13:18
 */
@Getter
@Setter
public class BspCommonRequest {

    private String accessCode;

    private String checkword;
}

package com.fengwenyi.mp3demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: LiuRenjin
 * @create: 2019-04-26 16:31
 **/
@Data
@AllArgsConstructor
public class SecureUriDTO {
    /**
     * token对应的时间戳
     */
    private String ts;
    /**
     * 密钥
     */
    private String uriKey;
    /**
     * 通常为文件名 要唯一
     */
    private String uri;

    @Override
    public String toString() {
        return "SecureUriDTO{" +
                "ts='" + ts + '\'' +
                ", uriKey='" + uriKey + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}

package com.fengwenyi.mp3demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Caixin
 * @date 2019/6/27 15:25
 */

@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = -8772273897757643357L;

    private Integer pid;
    private String menuName;
    private Integer parentId;
    private Integer position;
    private List<Menu> childMenu;

    public Menu(Integer pid, String menuName, Integer parentId, Integer position) {
        this.pid = pid;
        this.menuName = menuName;
        this.parentId = parentId;
        this.position = position;
    }

}

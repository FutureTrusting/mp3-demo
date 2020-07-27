package com.fengwenyi.mp3demo.vo;

import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : Caixin
 * @date 2019/8/28 10:38
 */

@Setter
@AllArgsConstructor
public class StudentDto extends BaseRowModel implements Serializable {

    public StudentDto() {

    }

    //声明一般视图接口 只允许这个视图返回用户名属性
    public interface UserSimpView{};
    //声明完整视图接口 允许返回用户名密码属性 由于集成了一般视图接口  含义是拥有了一般视图所具有的返回属性
    public interface UserDetailView extends UserSimpView{};

    @JsonView(UserSimpView.class)
    public Long getId() {
        return id;
    }
    @JsonView(UserSimpView.class)
    public String getName() {
        return name;
    }
    @JsonView(UserDetailView.class)
    public Integer getAge() {
        return age;
    }
    @JsonView(UserDetailView.class)
    public String getInfo() {
        return info;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getGender() {
        return gender;
    }

    public Long getIdcardId() {
        return idcardId;
    }

    public String getCardCode() {
        return cardCode;
    }

    public String getCityName() {
        return cityName;
    }

    public Long getCityId() {
        return cityId;
    }

    private Long id;
    private String name;
    private Integer age;
    private String info;
    private Boolean isDelete;
    private Date createTime;
    private Date updateTime;
    private String gender;
    private Long idcardId;
    private String cardCode;
    private String cityName;
    private Long cityId;
}

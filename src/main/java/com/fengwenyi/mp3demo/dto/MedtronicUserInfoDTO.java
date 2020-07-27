package com.fengwenyi.mp3demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Caixin
 * @date 2019/7/17 15:37
 */
@Data
public class MedtronicUserInfoDTO implements Serializable {
    private static final long serialVersionUID = 5222434222544249944L;

    private List<UserInfo> list;
    private Pagination pagination;

    @Getter
    @Setter
    @ToString
    private static class Pagination {
        @JsonProperty("current_page")
        private Integer currentPage;
        @JsonProperty("last_page")
        private Integer lastPage;
        @JsonProperty("current_total")
        private Integer currentTotal;
        @JsonProperty("total")
        private Integer total;
    }


    @Getter
    @Setter
    @ToString
    private static class UserInfo {
        @JsonProperty("Employee_Id")
        private String employeeId;

        @JsonProperty("Email_Id_Desc")
        private String emailIdDesc;

        @JsonProperty("Last_Name_Cn")
        private String lastNameCn;
        @JsonProperty("First_Name_Cn")
        private String firstNameCn;
        @JsonProperty("Last_Name_En")
        private String lastNameEn;

        @JsonProperty("First_Name_En")
        private String firstNameEn;

        @JsonProperty("Legal_Name")
        private String legalName;

        @JsonProperty("Preferred_Name")
        private String preferredName;

        @JsonProperty("Mobile_Number")
        private String mobileNumber;

        @JsonProperty("Cost_Center")
        private String costCenter;

        @JsonProperty("Cost_Center_Name")
        private String costCenterName;
    }

}

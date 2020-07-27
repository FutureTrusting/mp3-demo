package com.fengwenyi.mp3demo.dto;

import com.fengwenyi.mp3demo.model.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Caixin
 * @date 2019/6/28 17:44
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonDto implements Serializable {
    private static final long serialVersionUID = 6661438459897024064L;

    private String salaries;

    private Integer[] jobTitleLetterCounts;

    private String firstName;
    private String lastName;
    private List<String> jobTitles;
    private long salary;

    private AddressDto address;
}

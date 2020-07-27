package com.fengwenyi.mp3demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimePeriodCalc {
    private double occupancy;
    private double efficiency;
    private String atDate;
}

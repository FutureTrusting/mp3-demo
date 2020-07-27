package com.fengwenyi.mp3demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Caixin
 * @date 2019/5/30 11:15
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Outer {
    private Nested nested;
}

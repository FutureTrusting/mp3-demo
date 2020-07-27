package com.fengwenyi.mp3demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderReviewPassedDTO implements Serializable {

    private static final long serialVersionUID = 5861569461871630918L;
    private UsOrderDo orderDoList;
    private List<UsMailDo> mailDoList;
    private UsOrderReviewDo reviewDoList;
}

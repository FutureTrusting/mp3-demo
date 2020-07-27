package com.fengwenyi.mp3demo.streamtest;

import com.fengwenyi.mp3demo.dto.OrderReviewPassedDTO;
import com.fengwenyi.mp3demo.dto.UsOrderDo;
import com.fengwenyi.mp3demo.dto.UserVO;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ECHO
 */
public class StreamTest6 {

    public static void main(String[] args) {

        UsOrderDo orderDo1= new UsOrderDo();
        UsOrderDo orderDo2 = new UsOrderDo();
        UsOrderDo orderDo3 = new UsOrderDo();
        orderDo1.setBanchNo(1L);
        orderDo2.setBanchNo(2L);
        orderDo3.setBanchNo(3L);
        OrderReviewPassedDTO passedDTO = new OrderReviewPassedDTO();
        OrderReviewPassedDTO passedDTO1 = new OrderReviewPassedDTO();
        OrderReviewPassedDTO passedDTO3 = new OrderReviewPassedDTO();
        passedDTO.setOrderDoList(orderDo1);
        passedDTO1.setOrderDoList(orderDo2);
        passedDTO3.setOrderDoList(orderDo2);

        List<OrderReviewPassedDTO> falseResult = Lists.newArrayList(passedDTO,passedDTO1);
        List<OrderReviewPassedDTO> trueResult = Lists.newArrayList(passedDTO3);


        List<UsOrderDo> orderDoList = Stream.concat(falseResult.stream(), trueResult.stream())
                .filter(x -> Objects.nonNull(x) && Objects.nonNull(x.getOrderDoList()))
                .map(OrderReviewPassedDTO::getOrderDoList)
                .collect(Collectors.toList());
        System.err.println(new Gson().toJson(orderDoList));
    }
}

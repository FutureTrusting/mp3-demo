package com.fengwenyi.mp3demo.effective.secondchapter;

import cn.hutool.core.util.StrUtil;
import com.fengwenyi.mp3demo.dto.UserSkuInfoAddressRequest;
import com.google.common.base.Preconditions;

import java.util.Optional;

/**
 * @author : Caixin
 * @date 2019/10/14 17:18
 */
public class Elvis {

    public static  final Elvis INSTANCE =new Elvis();

    private Elvis(){}
    public void leaveTheBuilding(){}

    public static  Elvis getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
//        String userId ="";
//        Long  orElse = Optional.ofNullable(userId)
//                .filter(StrUtil::isNotBlank)
//                .map(Long::valueOf)
//                .orElse(null);
//        System.err.println(orElse);

        UserSkuInfoAddressRequest addressRequest = new UserSkuInfoAddressRequest();
        addressRequest.setReceivePhone("131");
       addressRequest.setRemark("11234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        Preconditions.checkArgument(StrUtil.isBlank(addressRequest.getRemark())
                || addressRequest.getRemark().length() <= 100, "备注信息最大100字符");
    }
}

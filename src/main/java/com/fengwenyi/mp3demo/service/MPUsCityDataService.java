package com.fengwenyi.mp3demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fengwenyi.mp3demo.dto.CityDataTreeResponse;
import com.fengwenyi.mp3demo.model.UsCityData;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wenyi Feng
 * @since 2019-06-27
 */
public interface MPUsCityDataService extends IService<UsCityData> {

    List<CityDataTreeResponse> getUsCityDataAll();

}

package com.fengwenyi.mp3demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fengwenyi.mp3demo.model.UsCityData;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wenyi Feng
 * @since 2019-06-27
 */
public interface UsCityDataDao extends BaseMapper<UsCityData> {

    List<UsCityData> selectAll();

}

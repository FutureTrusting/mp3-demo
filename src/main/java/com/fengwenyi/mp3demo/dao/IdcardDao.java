package com.fengwenyi.mp3demo.dao;

import com.fengwenyi.mp3demo.dto.UsOrderDo;
import com.fengwenyi.mp3demo.model.Idcard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wenyi Feng
 * @since 2018-08-31
 */
public interface IdcardDao extends BaseMapper<Idcard> {

    List<Idcard> selectByIdCode(@Param("id") Long id,@Param("code") String code);
}

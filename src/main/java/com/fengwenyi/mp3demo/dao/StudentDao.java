package com.fengwenyi.mp3demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fengwenyi.mp3demo.dto.LoginLog;
import com.fengwenyi.mp3demo.dto.UsOrderDo;
import com.fengwenyi.mp3demo.model.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wenyi Feng
 * @since 2018-08-31
 */
public interface StudentDao extends BaseMapper<Student> {

    List<Student> selectAll();

    @Select("<script>select id,name,age,info from t_student <if test=\"id !=null \">where id = #{id} </if></script>")
     Student findStudentById(Long userId);

    int batchInsertStudent(List<Student> stud);

    List<LoginLog> selectStudent();

    int  insertStudent(LoginLog loginLog);

    int batchUpdateRouteByMailNo(@Param("list") List<UsOrderDo> list);

}

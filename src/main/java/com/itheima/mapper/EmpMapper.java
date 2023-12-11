package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author mqy
 * @version 1.0
 * @date 2023/12/10 16:01
 */
@Mapper
public interface EmpMapper {

//    /*查询总记录数*/
//    @Select("select count(*) from emp ")
//    public Long count();
//
//    /*分页查询，获取数据列表*/
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start, Integer pageSize);

    //使用pageHelper插件完成分页查询
    //员工数据查询
    @Select("select * from emp")
    public List<Emp> list();
}

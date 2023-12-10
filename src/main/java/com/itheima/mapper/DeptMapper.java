package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author mqy
 * @version 1.0
 * @date 2023/12/10 16:01
 */
@Mapper
public interface DeptMapper {
    /*
     * 查询全部部门数据*/
    @Select("select * from dept")
    List<Dept> list();


    /*
     * 删除部门数据
     * */
    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);


    /*新增部门数据
     * */
    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);


    /*
    * 根据id查询部门数据
    * */
    @Select("select * from dept where id = #{id}")
    Dept getById(Integer id);

    /*修改部门数据
    * */
    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}

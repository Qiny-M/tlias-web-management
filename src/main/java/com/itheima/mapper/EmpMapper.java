package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
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
    //@Select("select * from emp")


    public List<Emp> list(@Param("name") String name, @Param("gender") Short gender,
                          @Param("begin") LocalDate begin, @Param("end") LocalDate end);
                            //踩坑，parameter 'xxx' not found
                            //解决办法：在mapper（dao）中,使用@param指定传参


    void delete(@Param("ids") List<Integer> ids);


    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime}) ")
    void insert(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);
}

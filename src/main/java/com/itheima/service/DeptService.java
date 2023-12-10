package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

/**
 * @author mqy
 * @version 1.0
 * @date 2023/12/10 16:02
 */
public interface DeptService {
    /*
    * 查询部门数据
    *
    * */
    List<Dept> list();

    /*
    * 删除部门数据
    * */
    void delete(Integer id);

    /*
    * 新增部门数据*/
    void add(Dept dept);

    /*根据id查询部门
    * */
    Dept getById(Integer id);

    /*
    * 修改部门数据*/
    void update(Dept dept);
}

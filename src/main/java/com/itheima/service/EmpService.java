package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * @author mqy
 * @version 1.0
 * @date 2023/12/10 16:03
 */
public interface EmpService {

    /*
     * 分页查询*/
    PageBean page(Integer page, Integer pageSize,
                  String name, Short gender, LocalDate begin, LocalDate end);

    /*批量删除员工*/
    void delete(List<Integer> ids);

    /*新增员工*/
    void save(Emp emp);
}

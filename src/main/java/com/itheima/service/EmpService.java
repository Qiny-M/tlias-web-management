package com.itheima.service;

import com.itheima.pojo.PageBean;

/**
 * @author mqy
 * @version 1.0
 * @date 2023/12/10 16:03
 */
public interface EmpService {

    /*
    * 分页查询*/
    PageBean page(Integer page, Integer pageSize);



}

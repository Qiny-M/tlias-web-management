package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mqy
 * @version 1.0
 * @date 2023/12/10 16:04
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //1.获取总记录数
        //Long count = empMapper.count();

        //2.获取分页查询结果列表
        //Integer start = (page - 1) * pageSize;
        //List<Emp> empList = empMapper.page(start, pageSize);

        //3.封装成pageBean对象
        //return new PageBean(count, empList);


        //使用pageHelper插件完成
        //1.设置分页参数
        PageHelper.startPage(page, pageSize);

        //2.执行查询
        List<Emp> empList = empMapper.list();
        Page<Emp> p = (Page<Emp>)empList;

        //3.封装成pageBean对象
        return new PageBean(p.getTotal(), p.getResult());
    }
}

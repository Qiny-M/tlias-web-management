package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author mqy
 * @version 1.0
 * @date 2023/12/10 16:04
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }


    /*交给Spring进行事务管理，一般用在service层中有多次数据访问操作的增删改中
    *设置rollback for，所有类型的异常都回滚
    *  */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        //1.删除部门
        deptMapper.delete(id);

        //2.根据部门id，同时删除部门下员工的信息
        empMapper.deleteById(id);
    }


    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {

        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }


}

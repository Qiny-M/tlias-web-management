package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mqy
 * @version 1.0
 * @date 2023/12/10 15:58
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /*
    * 查询所有部门数据*/
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)//指定请求方式为GET
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");
        //调用service查询部门数据
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }


    /*
    * 删除部门
    * */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门{}",id);
        //调用service删除部门
        deptService.delete(id);
        return Result.success();
    }


    /*
    * 新增部门
    * */
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门{}",dept);
        //调用service新增部门
        deptService.add(dept);
        return Result.success();
    }

    /*
    *根据id查询部门数据
    * */
    @GetMapping("/{id}")
    public Result getByID(@PathVariable Integer id){
        log.info("根据id查询部门{}",id);
        //调用service查询部门
        Dept dept = deptService.getById(id);//踩坑，与上面新增部门不同，这里只
                                            //返回一个dept对象，不要用list接受
        return Result.success(dept);
    }

    /*
    * 修改部门数据*/
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门{}数据",dept);
        //调用service修改部门数据
        deptService.update(dept);
        return Result.success();
    }
}

package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author mqy
 * @version 1.0
 * @date 2023/12/10 15:59
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /*分页查询员工数据
     * */
    @GetMapping
    public Result page(//前端不传入page,pageSize则使用默认值
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        log.info("分页查询，参数:{}{}{}{}{}{}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    /*
     *批量删除员工数据
     * */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {

        log.info("批量删除员工数据，ids:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /*
     * 新增员工
     * */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工，emp:{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /*
     * 编辑员工-查询回显-根据id查询
     * */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询员工：{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /*
     * 修改员工-根据id*/
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工信息：{}", emp);
        empService.update(emp);
        return Result.success();
    }

}

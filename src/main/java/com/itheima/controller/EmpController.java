package com.itheima.controller;

import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mqy
 * @version 1.0
 * @date 2023/12/10 15:59
 */
@Slf4j

@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /*分页查询员工数据
     * */
    @GetMapping("/emps")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
                        //前端不传入page,pageSize则使用默认值

        log.info("分页查询参数:{}{}", page, pageSize);
        PageBean pageBean = empService.page(page, pageSize);
        return Result.success(pageBean);
    }
}

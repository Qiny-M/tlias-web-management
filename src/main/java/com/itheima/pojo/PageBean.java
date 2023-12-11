package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author mqy
 * @version 1.0
 * @date 2023/12/11 09:49
 * 分页查询结果包装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    private long total;//总记录数
    private List<Emp> rows;//数据列表
}

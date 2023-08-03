package com.example.controller;

import com.example.anno.Log;
import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 查询员工列表
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
     @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
         log.info("获取分页信息");
         PageBean pageBean = empService.page1(page,pageSize,name,gender,begin,end);
         return Result.success(pageBean);
     }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Log
     @DeleteMapping("/{ids}")
    public Result delemp(@PathVariable List<Integer> ids){
        log.info("批量删除操作：{}",ids);
        empService.delete(ids);
        return Result.success();
     }

    /**
     * 新增员工
     * @param emp
     * @return
     */
    @Log
     @PostMapping
    public Result insert(@RequestBody Emp emp){
         log.info("插入人员：{}",emp);
         empService.save(emp);
         return Result.success();
     }
    /**
     * 通过id员工信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询员工：{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 修改员工信息
     * @param emp
     * @return
     */
    @Log
    @PutMapping()
    public Result updateEmp(@RequestBody Emp emp){
        log.info("修改员工信息");
        empService.update(emp);
        return Result.success();
    }

}

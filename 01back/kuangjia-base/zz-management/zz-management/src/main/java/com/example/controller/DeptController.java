package com.example.controller;

import com.example.anno.Log;
import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

//    声明日志记录对象(使用@slf4j代替)
//    private static Logger log= LoggerFactory.getLogger(DeptController.class);
    @RequestMapping(value = "/depts",method = RequestMethod.GET)//指定请求方式为GET
      public Result list(){
        log.info("查询所有部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    /**
     * 删除部门数据
     * @return
     */
    @Log
    @DeleteMapping("/depts/{id}")
    public Result delDept(@PathVariable Integer id){
        log.info("删除部门数据:{}",id  );
        deptService.delDept(id);
        return Result.success();
    }

    /**
     * 添加部门
     * @param dept
     * @return
     */
    @Log
    @PostMapping("/depts")
    public Result addDept(@RequestBody Dept dept){
        log.info("添加部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     *
     * @param id
     * @return
     */

    @GetMapping("/depts/{id}")
    public Result selDept(@PathVariable Integer id){
        log.info("查询要编辑的部门");
        List<Dept> deptList = deptService.selDept(id);
        return Result.success(deptList);
    }
    @PutMapping("/depts")
    public Result updateName(@RequestBody Dept dept){
        log.info("修改部门名称");
        deptService.update(dept);
        return Result.success();
    }
}

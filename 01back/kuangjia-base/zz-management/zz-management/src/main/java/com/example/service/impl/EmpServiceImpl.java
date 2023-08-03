package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;


    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }

    //    方法一
    @Override
    public PageBean page1(Integer page, Integer pageSize,String name, Short gender, LocalDate begin, LocalDate end  ) {
        //获取总记录数
        Integer count = empMapper.count();

//        获取分页查询的结果列表
        Integer start = (page-1)*pageSize;
        List<Emp> empList = empMapper.page(start, pageSize,name,gender,begin,end);
        //封装结果到PageBean对象
        PageBean pageBean = new PageBean(count, empList);
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public Emp getById(Integer id) {
        Emp emp = empMapper.getById(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.login(emp);
    }


//    方法二
/*    @Override
    public PageBean page1(Integer page, Integer pageSize) {
        //设置分页参数
        PageHelper.startPage(page, pageSize,false);
        //执行查询
        List<Emp> empList = empMapper.list();
        Page<Emp> pageResult = (Page<Emp>) empList;
        //封装结果到PageBean对象
        PageBean pageBean = new PageBean(pageResult.getTotal(), pageResult.getResult());
        return pageBean;
    }*/
}

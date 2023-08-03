package com.example.service;

import com.example.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> list();

    void delDept(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 查询要编辑的部门
     * @param id
     * @return
     */
    List<Dept> selDept(Integer id);

    void update(Dept dept);
}

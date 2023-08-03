package com.example.service.impl;

import com.example.mapper.DeptMapper;
import com.example.pojo.Dept;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    /**
     * 查询全部部门数据
     * @return
     */
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    /**
     * 删除部门
     * @param id
     */
    @Override
    public void delDept(Integer id) {
       deptMapper.deleteById(id);
    }

    /**
     * 新增部门
     * @param dept
     */

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    /**
     * 查询要编辑的部门
     * @param id
     * @return
     */
    @Override
    public List<Dept> selDept(Integer id) {
        return deptMapper.selDeptById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateName(dept);
    }
}

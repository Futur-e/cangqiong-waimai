package com.example.mapper;

import com.example.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 查询全部部门的数据
     * @return
     */
    @Select("select * from dept")
    List<Dept> list();

    /**
     * 根据ID删除部门
     *
     * @param id
     */

    @Delete("delete  from dept where id=#{id}")
    void deleteById(Integer id) ;

    /**
     * 新增部门
     * @param dept
     */
    @Options(keyProperty = "id",useGeneratedKeys = true)
    @Insert("insert into dept (name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    /**
     * 通过id查找对应部门
     * @param id
     * @return
     */
    @Select("select * from dept where id=#{id}")
    List<Dept> selDeptById(Integer id);

    /**
     * 修改部门名称
     *
     * @param dept
     */
    @Update("update dept set name=#{name} where id=#{id}")
    void updateName(Dept dept);
}

package com.example.mapper;

import com.example.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

   
    //原始分页的方法

    /**
     * 查询总记录数
     *
     * @return
     */
    @Select("select count(*) from emp;")
    public Integer count();

    /**
     * 分页查询获取列表数据的查询
     *
     * @param start
     * @param pageSize
     * @return
     */
//    @Select("select * from emp limit  #{start},#{pageSize}")
    public List<Emp> page(Integer start, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);


    @Insert("insert into emp(username,name,gender,image,job,entrydate,dept_id,create_time,update_time) values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void save(Emp emp);

    @Select("SELECT * FROM emp where id=#{id}")
    Emp getById(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password};")
    Emp login(Emp emp);


    /**
     * 查询员工的全部信息
     * @return
     */
//使用插件的方法(只需要查询全部数据即可)
/*
    @Select("select *from emp")
    public List<Emp> list();
*/

}

package com.study.mpdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.mpdemo.bean.User;
import com.study.mpdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MpdemoApplicationTests {

    @Autowired
    private UserMapper userMapper;
    //查询user表中的所有数据
    @Test
    void findUsers() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
    
    //添加操作
    @Test
    void addUsers(){
        User user = new User();
        user.setName("mary");
        user.setAge(16);
        user.setEmail("987654321");
        int insert = userMapper.insert(user);
        System.out.println("插入数据"+insert);
    }

    //删除操作
    @Test
    void deleteUser(){
        int i = userMapper.deleteById(6);
        System.out.println("删除数据"+i);
    }

    //修改操作
    @Test
    void updateUser(){
        User user = new User();
        user.setId(1363021359618150401L);
        user.setAge(55);
        int i = userMapper.updateById(user);
    }

    //测试乐观锁
    @Test
    public void testOptimisticLocker(){
        //根据id查询数据
        User user = userMapper.selectById(1363021359618150401L);
        //进行修改
        user.setName("mary");
        user.setAge(16);
        user.setEmail("987654321");
        int i =  userMapper.updateById(user);
    }

    //多个id批量查询
    @Test
    void testSelectDemo1(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 6));
        System.out.println(users);
    }

    //分页查询
    @Test
    void testPage(){
        //1.创建page对象
        //传入两个参数：当前页 和 每页显示记录时
        Page<User> page = new Page<>(1,3);
        //调用mp分页查询的方法
        //调用mp分页查询过程中，底层封装
        //把分页所有数据封装到page对象里面
        userMapper.selectPage(page,null);

        //通过page对象获取分页数=数据
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页数据list集合
        System.out.println(page.getSize());//每页显示的记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.getPages());//总页数

        System.out.println(page.hasNext());//下一页
        System.out.println(page.hasPrevious());//上一页
    }

    //mp实现复杂一些的查询操作
    @Test
    void selectQuery(){
        //创建QueryWrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //通过QueryWrapper设置条件
        //ge、gt、le、lt
        //查询age>=20的记录
        /*wrapper.ge("age",20);//第一个参数字段名称，第二个参数设置值
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);*/

        //eq、ne
        /*wrapper.ne("name","jack");
        List<User> user = userMapper.selectList(wrapper);
        System.out.println(user);*/

        //between
        //查询年龄在15-20之间的记录
        /*wrapper.between("age",15,20);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);*/

        //like
        /*wrapper.like("name","o");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);*/

        //orderByDesc
        /*wrapper.orderByDesc("age");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);*/

        //last
        /*wrapper.last("limit 1");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);*/

        //指定要查询的列select
        wrapper.select("id","name");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }
}

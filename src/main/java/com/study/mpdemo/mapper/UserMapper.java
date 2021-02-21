package com.study.mpdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.mpdemo.bean.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {
}

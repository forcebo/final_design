package com.finaldesign.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.finaldesign.backend.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}

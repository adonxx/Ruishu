package com.ruishu.admin.mapper;

import com.ruishu.admin.entity.Order;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM orders ORDER BY create_time DESC")
    List<Order> listAll();

    @Update("UPDATE orders SET status=#{status} WHERE id=#{id}")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

    @Delete("DELETE FROM orders WHERE id=#{id}")
    int delete(Long id);
}
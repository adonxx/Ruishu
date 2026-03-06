package com.ruishu.admin.mapper;

import com.ruishu.admin.entity.Product;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM product ORDER BY create_time DESC")
    List<Product> listAll();

    @Insert("INSERT INTO product(name,category,price,stock,status,description,create_time) " +
            "VALUES(#{name},#{category},#{price},#{stock},#{status},#{description},NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Product product);

    @Update("UPDATE product SET name=#{name}, category=#{category}, price=#{price}, " +
            "stock=#{stock}, status=#{status}, description=#{description} WHERE id=#{id}")
    int update(Product product);

    @Delete("DELETE FROM product WHERE id=#{id}")
    int delete(Long id);

    @Select("SELECT * FROM product WHERE id=#{id}")
    Product getById(Long id);
}
package com.ruishu.admin.service;

import com.ruishu.admin.common.Result;
import com.ruishu.admin.entity.Product;
import com.ruishu.admin.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public Result list() {
        List<Product> list = productMapper.listAll();
        return Result.ok(list);
    }

    public Result add(Product product) {
        product.setStatus(1);
        productMapper.insert(product);
        return Result.ok("添加成功");
    }

    public Result update(Product product) {
        productMapper.update(product);
        return Result.ok("更新成功");
    }

    public Result delete(Long id) {
        productMapper.delete(id);
        return Result.ok("删除成功");
    }
}
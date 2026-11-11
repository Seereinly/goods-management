package com.yyy.product.controller;

import com.yyy.product.common.Result;
import com.yyy.product.entity.Product;
import com.yyy.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public Result<List<Product>> list(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String category
    ) {
        return Result.success(productService.list(productName,category));
    }


    @PostMapping("/add")
    public Result<Void> add(@RequestBody Product product) {
        productService.add(product);
        return Result.success(null);
    }

    @PutMapping("/update")
    public Result<Void> update(@RequestBody Product product) {
        productService.update(product);
        return Result.success(null);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return Result.success(null);
    }

    @GetMapping("/statistics")
    public Result<Map<String,Object>> statistics(){
        List<Product> list = productService.list(null, null);
        long totalProduct = list.size();
        int totalStock = list.stream().mapToInt(Product::getStock).sum();
        long warnCount = list.stream().filter(p->p.getStock() <50).count();
        Map<String,Object> map = new HashMap<>();
        map.put("totalProduct",totalProduct);
        map.put("totalStock",totalStock);
        map.put("warnStockCount",warnCount);
        return Result.success(map);
    }

}

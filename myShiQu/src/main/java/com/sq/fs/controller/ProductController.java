package com.sq.fs.controller;

import com.sq.fs.dto.R;
import com.sq.fs.pojo.ImgList;
import com.sq.fs.pojo.Product;
import com.sq.fs.service.ImgListService;
import com.sq.fs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/22.
 */
@Controller
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImgListService imgListService;
    @ResponseBody
    @RequestMapping("/add")
    public R add( Product product){
        productService.save(product);
        return R.ok("保存成功");
    }

    @ResponseBody
    @RequestMapping("/read/{id}")
    public R read(@PathVariable Integer id){

        Product product = productService.queryById(id);
        List<ImgList> imgLists = imgListService.queryList(id);
       // product.setImglist(imgLists);
        return R.ok().put("data",product);
    }

    @ResponseBody
    @RequestMapping("/show")
    public R show(){

        List<Product> productList = productService.queryList();

//        for (Product product : productList) {
//            Integer id=product.getId();
//            List<ImgList> imgLists = imgListService.queryList(id);
//           // product.setImglist(imgLists);
//        }
        return R.ok().put("data",productList);
    }


    @ResponseBody
    @RequestMapping("/remove")
    public R del(@RequestBody Map<String, Integer[]> paramters){

       // System.out.println(paramters);
        Integer[] ids = paramters.get("id");
        productService.deleteBatch(ids);
        return R.ok("删除成功");
    }


    @ResponseBody
    @RequestMapping("/mod/{id}")
    public R update(@RequestBody Product product,@PathVariable Integer id){

        System.out.println(product.toString());
        productService.update(id, product);
        return R.ok("更新成功");
    }
}

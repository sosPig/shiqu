package com.sq.fs.controller;

import com.sq.fs.dto.R;
import com.sq.fs.pojo.ImgList;
import com.sq.fs.pojo.Product;
import com.sq.fs.service.ImgListService;
import com.sq.fs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2018/6/22.
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImgListService imgListService;


    @RequestMapping("/add")
    public R add( Product product,
                  @RequestParam("file1") MultipartFile[] files
                  ){

        Serializable id = productService.save(product);
        String s = id.toString();
        int productId = Integer.parseInt(s);

//        System.out.println(files);
        if(files!=null&&files.length>0){
            List<ImgList> imglist=new ArrayList<>();
            for (MultipartFile file : files) {
                String oldFilename = file.getOriginalFilename();


                String dex = oldFilename.substring(oldFilename.indexOf(".")+1);
                List<String> dexs=new ArrayList<>();
                dexs.add("jpg");
                dexs.add("png");
                if (!dexs.contains(dex)) {
//                System.out.println("不支持的文件格式");
                    return R.error("不支持的文件格式");
                }
                String newFilename= UUID.randomUUID().toString()+oldFilename.substring(oldFilename.lastIndexOf("."));
                String fileAdress="D:\\shiqu\\picture\\";
                File file1=new File(fileAdress+newFilename);

                try {
                    file.transferTo(file1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImgList imgList = new ImgList();
                String url="/picture/"+newFilename;

                imgList.setPhotoPath(url);
                imgList.setProductId(productId);
                Serializable save = imgListService.save(imgList);
                String s1 = save.toString();
                int i = Integer.parseInt(s1);
                imgList.setId(i);
                imglist.add(imgList);
            }
            product.setImglist(imglist);
            return R.ok("保存成功").put("data",product);
        }

        return R.error("服务器发生错误，请稍后再试");
    }


    @RequestMapping("/read/{id}")
    public R read(@PathVariable Integer id){

        Product product = productService.queryById(id);
        List<ImgList> imgLists = imgListService.queryList(id);
//        List<String> imglist=new ArrayList<>();
//        for (ImgList imgList : imgLists) {
//
//
//            imglist.add( imgList.getPhotoPath());
//        }
        product.setImglist(imgLists);
        return R.ok().put("data",product);
    }


    @RequestMapping("/show")
    public R show(){

        List<Product> productList = productService.queryList();

        for (Product product : productList) {
            Integer id=product.getId();
            List<ImgList> imgLists = imgListService.queryList(id);
//            List<String> imglist=new ArrayList<>();
//            for (ImgList imgList : imgLists) {
//
//
//                imglist.add( imgList.getPhotoPath());
//            }
            product.setImglist(imgLists);
        }
        return R.ok().put("data",productList);
    }



    @RequestMapping("/remove")
    public R del(@RequestBody Map<String, Integer[]> paramters){

       // System.out.println(paramters);
        Integer[] ids = paramters.get("idList");
        productService.deleteBatch(ids);
        imgListService.deleteBatchs(ids);
        return R.ok("删除成功");
    }



    @RequestMapping("/mod/{id}")
    public R update(@RequestBody Product product,@PathVariable Integer id){

//        System.out.println(product.toString());
        productService.update(id, product);
        return R.ok("更新成功");
    }
}

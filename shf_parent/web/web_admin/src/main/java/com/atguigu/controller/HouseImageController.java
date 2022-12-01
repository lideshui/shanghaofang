package com.atguigu.controller;

import com.atguigu.entity.HouseImage;
import com.atguigu.result.Result;
import com.atguigu.service.HouseImageService;
import com.atguigu.util.QiniuUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/houseImage")
public class HouseImageController {

    @DubboReference
    private HouseImageService houseImageService;

    private final static String LIST_ACTION = "redirect:/house/show/";
    private final static String PAGE_UPLOED_SHOW = "house/upload";

    /**
     * 处理/uploadShow/houseId/type请求，跳转到上传页面
     * 通过参数决定传递哪个房源的房源图或者房产图
     */
    @RequestMapping("/uploadShow/{houseId}/{type}")
    public String uploadShow(@PathVariable Long houseId, @PathVariable Integer type, Map map) {
        //参数决定传递的房源和传递的图片类型，放到请求域，发异步请求需要
        map.put("houseId", houseId);
        map.put("type", type);
        return PAGE_UPLOED_SHOW;
    }


    /**
     * 处理/upload/houseId/type请求，图片上传操作
     * 通过参数决定传递哪个房源的房源图或房产图
     */
    @RequestMapping("/upload/{houseId}/{type}")
    @ResponseBody
    public Result upload(
            @PathVariable Long houseId,
            @PathVariable Integer type,
            //必须叫file，插件里写死了，可能有多个，所以用数组
            @RequestParam("file") MultipartFile[] files) throws IOException {
        //第一步：先将图片循环上传到七牛云服务器
        for (MultipartFile file : files) {

            //循环的file就是需要上传的文件，使用UUID随机为其生成个名字
            String fileName = UUID.randomUUID().toString();

            //将file转换为byte数组，使用七牛云传递byte[]的方式上传图片
            QiniuUtil.upload2Qiniu(file.getBytes(), fileName);

            //第二步：需要在数据库内添加记录，设置了house_id，图片名字，图片路径，type
            HouseImage houseImage = new HouseImage();
            houseImage.setHouseId(houseId);
            houseImage.setImageName(fileName);

            //使用七牛云空间域名+图片名字拼凑图片的完整URL
            houseImage.setImageUrl("http://rm5n3wdxr.hb-bkt.clouddn.com/" + fileName);
            houseImage.setType(type);

            houseImageService.insert(houseImage);
        }
        return Result.ok();
    }


    /**
     * 处理/delete/houseId/houseImageId请求，删除图片
     * 1.从七牛云删除可以做或不做
     * 2.从数据库将图片信息删除(逻辑删除,并非真正删除)
     * 3.删除完成后回到详情页面
     */
    @RequestMapping("/delete/{houseId}/{houseImageId}")
    public String delete(@PathVariable Long houseId, @PathVariable Long houseImageId) {
        //1. 从七牛云将图片删除，这一步可以不做，实现删除记录之类功能
        HouseImage houseImage = houseImageService.getById(houseImageId);
        QiniuUtil.deleteFileFromQiniu(houseImage.getImageName());
        //2. 从数据库将图片信息删除(逻辑删除)
        houseImageService.delete(houseImageId);
        //3. 删除完成重定向到详情页面
        return LIST_ACTION + houseId;
    }
}
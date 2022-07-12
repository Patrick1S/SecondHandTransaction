package com.patrick.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.common.Result;
import com.patrick.springboot.config.AuthAccess;
import com.patrick.springboot.entity.Files;
import com.patrick.springboot.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;


//文件上传相关接口
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private FileMapper fileMapper;

    /**
     * 文件上传接口
     * @param file 前端传递过来的文件
     * @return
     * @throws IOException
     */
    @AuthAccess
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUuid = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUuid);

        File uploadParentFile = uploadFile.getParentFile();
        //判断配置文件目录是否存在，若不存在则创建一个新的文件目录
        if(!uploadParentFile.exists()){
            uploadParentFile.mkdirs();
        }


//        /**
//         * 通过文件的md5查询文件，获取文件的md5，通过对比md5避免重复上传相同内容的文件
//         */
//        String md5;
//        String url;
//        //当文件存在时，获取文件的md5
//        if(uploadFile.exists()){
//            //获取文件的md5，通过对比md5避免重复上传相同内容的文件
//            md5 = SecureUtil.md5(uploadFile);
//
//            //从数据库查询是否存在相同的记录
//            QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("md5",md5);
//            List<Files> filesList = fileMapper.selectList(queryWrapper);
//            Files files = filesList.size() == 0 ? null : filesList.get(0);
//            //获取文件的url
//            if(files != null){
//                url = files.getUrl();
//            }else {
//                //把获取到的文件存储到磁盘目录
//                file.transferTo(uploadFile);
//                url = "http://localhost:9090/file/" + fileUuid;
//            }
//        }else {
//            //把获取到的文件存储到磁盘目录
//            file.transferTo(uploadFile);
//            md5 = SecureUtil.md5(uploadFile);
//            url = "http://localhost:9090/file/" + fileUuid;
//        }

        String md5;
        String url;
        //上传文件到磁盘
        file.transferTo(uploadFile);
        //获取文件的md5
        md5 = SecureUtil.md5(uploadFile);

        //从数据库查询是否存在相同的记录
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5",md5);
        List<Files> filesList = fileMapper.selectList(queryWrapper);
        Files files = filesList.size() == 0 ? null : filesList.get(0);

        //获取文件的url
        if(files != null){
            url = files.getUrl();
            //由于文件已存在，所以删除刚才上传的重复文件
            uploadFile.delete();
        }else {
            //数据库若不存在重复文件，则不删除刚才上传的文件
            url = "http://localhost:9090/file/" + fileUuid;
        }

        //存储数据库
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size/1024);//单位为kb
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        saveFile.setEnable(true);
        fileMapper.insert(saveFile);
        return url;
    }


    /**
     * 文件下载接口 http://localhost:9090/file/{fileUuid}
     * @param fileUuid
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUuid}")
    public void download(@PathVariable String fileUuid, HttpServletResponse response) throws IOException {
        //根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUuid);
        //设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileUuid,"UTF-8"));
        response.setContentType("application/octet-stream");

        //读取文件字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();;
        os.close();

    }

    @GetMapping("/front/all")
    public Result frontAll(){
        //查询所有的文件
        return Result.success(fileMapper.selectList(null));
    }




    //新增和修改
    @PostMapping("/update")
    public Result update(@RequestBody Files files){
        //新增或更新都在这里
        return Result.success(fileMapper.updateById(files));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Files files = fileMapper.selectById(id);
        files.setDelete(true);
        return Result.success(fileMapper.updateById(files));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        //select * from sys_file where id in (id,id,id...)
        QueryWrapper<Files> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("id",ids);
        List<Files> files = fileMapper.selectList(queryWrapper);
        for(Files file : files){
            file.setDelete(true);
            fileMapper.updateById(file);
        }
        return Result.success();
    }

    /**
     * 分页查询接口
     * @param pageNum
     * @param pageSize
     * @param name
     */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name){

        Page<Files> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Files> queryWrapper=new QueryWrapper<>();
        //查询未删除的记录
        queryWrapper.eq("is_delete",false);
        queryWrapper.orderByDesc("id");
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }


        return Result.success(fileMapper.selectPage(page,queryWrapper));
    }

}

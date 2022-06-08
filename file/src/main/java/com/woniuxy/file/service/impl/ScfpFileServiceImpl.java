package com.woniuxy.file.service.impl;

import com.aliyun.oss.OSSClient;
import com.woniuxy.commons.entity.ResStatus;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpFile;
import com.woniuxy.file.dao.ScfpFileDao;
import com.woniuxy.file.service.ScfpFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * @author lijunbo
 * @date 2022/6/8 14:41
 */
@Service
public class ScfpFileServiceImpl implements ScfpFileService {
    @Resource
    private OSSClient ossClient;
    @Resource
    private ScfpFileDao scfpFileDao;
    private static final String BUCKET_NAME = "woniuxylijunbo";   //桶的名字

    private static final String OSS_ADDRESS = "http://woniuxylijunbo.oss-cn-chengdu.aliyuncs.com/"; //服务器地址

    @Override
    public ResponseResult<ScfpFile> upload(MultipartFile file,int idnum) throws IOException {
        //获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        String[] split = fileName.split("\\.");
//        System.out.println(split[1]);
        String type=split[1];
        //重新生成一个文件名（避免不同用户上传同名同类型的文件，造成文件覆盖）
        fileName = UUID.randomUUID().toString() +
                fileName.substring(fileName.lastIndexOf("."));

        //将文件上传到aliyun
        //获取文件流对象
        InputStream inputStream = file.getInputStream();

        //设置aliyun客户端参数
        ossClient.putObject(BUCKET_NAME, fileName, inputStream);

        //设置文件的有效时间
        Date date = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30);

        //上传文件
        URL url = ossClient.generatePresignedUrl(BUCKET_NAME, fileName, date);
        System.out.println(url);

        //封装结果
        ScfpFile files = new ScfpFile();
        files.setFile_name(fileName);
        files.setFile_url(OSS_ADDRESS + fileName);
        Date date1=new Date();
        files.setCreate_time(date1);
        files.setChain_id(idnum);
        files.setFile_type(type);
        int i = scfpFileDao.upload(files);
        if (i>0){
            return new ResponseResult<ScfpFile>(200,"上传成功",files, ResStatus.SUCCESS);
        }else {
            return new ResponseResult<ScfpFile>(500,"上传失败",null, ResStatus.FAIL);
        }

    }

    @Override
    public ResponseResult<Object> delete(String file_name) {
        ossClient.deleteObject(BUCKET_NAME,file_name);
        int i=scfpFileDao.delete(file_name);
        if(i>0){
            return ResponseResult.SUCCESS;
        }else {
        return ResponseResult.FAIL;
        }
    }
}

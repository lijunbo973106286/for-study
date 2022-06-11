package com.woniuxy.file.service;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lijunbo
 * @date 2022/6/8 14:40
 */
public interface ScfpFileService {

    ResponseResult<ScfpFile> upload(MultipartFile file,int idnum,String type) throws IOException;

    ResponseResult<Object> delete(String file_name);

    ResponseResult<ScfpFile> getPic(int chain_id);
}

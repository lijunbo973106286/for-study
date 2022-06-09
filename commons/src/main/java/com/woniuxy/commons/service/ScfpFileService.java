package com.woniuxy.commons.service;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpFile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lijunbo
 * @date 2022/6/8 16:35
 */
@FeignClient(name = "file")
public interface ScfpFileService {
    @PostMapping("/file/upload/{idnum}")
    public ResponseResult<ScfpFile> upload(MultipartFile file, @PathVariable("idnum") int idnum) throws IOException;
    @RequestMapping("/file/delete/{file_name}")
    public ResponseResult<Object> delete(@PathVariable("file_name") String file_name);
}

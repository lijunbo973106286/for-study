package com.woniuxy.file.controller;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpFile;
import com.woniuxy.file.service.ScfpFileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author lijunbo
 * @date 2022/6/8 14:29
 */
@RestController
@RequestMapping("/file")
public class ScfpFileController {
    @Resource
    ScfpFileService scfpFileService;
    @PostMapping("/upload/{idnum}/{type}")
    public ResponseResult<ScfpFile> upload(MultipartFile file, @PathVariable("idnum") int idnum,@PathVariable("type") String type) throws IOException {
            return scfpFileService.upload(file,idnum,type);
    }

    @DeleteMapping("/delete/{file_name}")
    public ResponseResult<Object> delete(@PathVariable("file_name") String file_name){
        return scfpFileService.delete(file_name);
    }

    @GetMapping("/getPic/{chain_id}")
    public ResponseResult<ScfpFile> getPic(@PathVariable("chain_id") int chain_id){
        return scfpFileService.getPic(chain_id);
    }


 }

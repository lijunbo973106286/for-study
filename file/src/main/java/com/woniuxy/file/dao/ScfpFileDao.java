package com.woniuxy.file.dao;

import com.woniuxy.commons.entity.ScfpFile;

/**
 * @author lijunbo
 * @date 2022/6/8 15:04
 */

public interface ScfpFileDao {
    int upload(ScfpFile file);

    int delete(String file_name);
}

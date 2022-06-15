package com.woniuxy.file.dao;

import com.woniuxy.commons.entity.ScfpFile;

import java.util.List;

/**
 * @author lijunbo
 * @date 2022/6/8 15:04
 */

public interface ScfpFileDao {
    int upload(ScfpFile file);

    int delete(String file_name);

    List<ScfpFile> getPic(int chain_id);

    int deletePic(int chain_id);
}

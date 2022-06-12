package com.woniuxy.user.dao;

import com.woniuxy.user.entity.ScfpUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.dao
 * @className: SubAccountDao
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/7 15:05
 * @version: 1.0
 */
@Mapper
public interface AccountDao {

    int newsub(ScfpUser user);

    int delsub(int id);

    int modsub(ScfpUser user);

    List<ScfpUser> qrysub(ScfpUser id);
}

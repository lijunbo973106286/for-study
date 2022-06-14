package com.woniuxy.user.dao;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;
import com.woniuxy.user.entity.UserDTO;
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

    int modsub(UserDTO user);

    List<UserDTO> qrysub(UserDTO user);

    UserDTO login(ScfpUser user);

    int newsubrole(UserDTO user);

    int delrole(UserDTO user);

    int inrole(UserDTO user);

    int modstatus(ScfpUser user);

    int modpwd(ScfpUser user);

    UserDTO userInfo(int id);

    ScfpUser checkOldPwd(ScfpUser user);
}

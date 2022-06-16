package com.woniuxy.user.dao;

import com.woniuxy.user.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    int getUserId(UserDTO user);

    int newsubrole(@Param("user_id") int user_id, @Param("role_id") int role_id);

    EnterpriseDTO corpInfo(int id);


    int corpRegist(Register register);

    int corpID(Register register);

    int managerRID(Register register);

    int managerRegist(Register register);

    int managerID(Register register);
}

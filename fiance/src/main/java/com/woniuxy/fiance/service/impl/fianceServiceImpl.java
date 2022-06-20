package com.woniuxy.fiance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.commons.entity.DTO.EnterpriseAccountDTO;
import com.woniuxy.commons.entity.ScfpEnterpriseAccount;
import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.fiance.mapper.fianceMapper;
import com.woniuxy.fiance.service.fianceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class fianceServiceImpl implements fianceService {
  ResponseResult responseResult = ResponseResult.getResponseResult();
    @Resource
    fianceMapper fianceMapper;
    @Override
    public ResponseResult<EnterpriseAccountDTO> findbankaccount(int currentPate, int pageSize, int id) {
        PageHelper.startPage(currentPate, pageSize);
        List<EnterpriseAccountDTO> bankaccount = fianceMapper.findbankaccount(id);
        if(bankaccount!=null){
            PageInfo<EnterpriseAccountDTO> list = PageInfo.of(bankaccount);
            return responseResult.success(list);
        }
        return responseResult.fail();
    }
    @Override
    public ResponseResult delaccount(int id) {
        int i = fianceMapper.delaccount(id);
        if (i!=0){
            responseResult.setCode(200);
            responseResult.setMsg("删除成功");
            return responseResult;
        }
        return responseResult.fail();
    }

    @Override
    public ResponseResult modifyaccount(ScfpEnterpriseAccount scfpEnterpriseAccount) {
        int i = fianceMapper.modifyaccount(scfpEnterpriseAccount);
        System.out.println(i);
        if (i!=0){
            responseResult.setCode(200);
            responseResult.setMsg("编辑成功");
            return responseResult;
        }
        return responseResult.fail();
    }

    @Override
    public ResponseResult add(ScfpEnterpriseAccount scfpEnterpriseAccount) {
        int i = fianceMapper.addaccount(scfpEnterpriseAccount);
        System.out.println(i);
        if (i!=0){
            responseResult.setCode(200);
            responseResult.setMsg("增加成功");
            return responseResult;
        }
        return responseResult.fail();
    }

}

package com.woniuxy.chain.controller;

import com.alibaba.fastjson.JSON;
import com.woniuxy.chain.dao.ScfpChainDao;
import com.woniuxy.chain.service.ScfpChainService;
import com.woniuxy.commons.entity.*;
import com.woniuxy.commons.service.ScfpFileService;
import com.woniuxy.commons.util.ConvertTime;
import com.woniuxy.commons.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;
import java.util.List;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-07 10:28
 * \* @Description：
 */
@RestController
@RequestMapping("/chain")
public class ScfpChainController {

    @Resource
    ScfpChainService scfpChainService;
    @Resource
    ScfpFileService scfpFileService;
    /** 通过传入的chain对象插入scfp_chain表中，没有的信息默认为null或者0 */
    @PostMapping("/insert")
    public ResponseResult<Object> insert(@RequestBody ScfpChain scfpChain) {
        System.out.println(scfpChain);
        return scfpChainService.insert(scfpChain);
    }

    /**
     * 通过传入chain对象动态修改scfp_chain表中的信息
     */
    @PutMapping("/update")
    public ResponseResult<Object> update(@RequestBody ScfpChain scfpChain) {
        return scfpChainService.update(scfpChain);
    }
    /** 通过id进行软删除*/
    @DeleteMapping("/delete/{id}")
    public  ResponseResult<Object> delete(@PathVariable("id") int id){
        return scfpChainService.delete(id);
    }

    /** 通过scfpchain对象动态查询ScfpChain对象*/
    @PostMapping("/search")
    public ResponseResult<ScfpChain> search(@RequestBody ScfpChain scfpChain){
        return scfpChainService.search(scfpChain);
    }

    /** 分页查询所有链单信息 */
    @PostMapping("/findAll")
    public ResponseResult<Object> findAll(@RequestBody ScfpChain scfpChain){
        return scfpChainService.findAll(scfpChain);
    }

    /** 查找所有链单数量 */
    @GetMapping("/findAllCount/{eid}")
    public ResponseResult<Object> findAllCount(@PathVariable("eid") int eid){
        return scfpChainService.findAllCount(eid);
    }
    /** 查找各分类链单数量 */
    @PostMapping("/findCount")
    public ResponseResult<Object> findCount(@RequestBody ScfpChain scfpChain){
        return scfpChainService.findCount(scfpChain);
    }

    /** 单个兑付处理 */
    @PutMapping("/updateLoan/{chain_id}")
    public ResponseResult<Object> updateLoan(@PathVariable("chain_id") int chain_id) {
        return scfpChainService.updateLoan(chain_id);
    }

    /** 批处理兑付 */
    @PutMapping("/updateBatLoan")
    public ResponseResult<Object> updateLoan(@RequestBody List<Integer> ids) {
        return scfpChainService.updateBatLoan(ids);
    }

    /** 检查交易密码 */
    @PostMapping("/checkPayPass")
    public ResponseResult<Object> checkPayPass(@RequestBody ScfpEnterprise scfpEnterprise) {
        return scfpChainService.checkPayPass(scfpEnterprise);
    }
    /** 查找银行相关信息*/
    @GetMapping("/getEnterprise/{bankName}")
    public ResponseResult<ScfpEnterprise> getEnterprise(@PathVariable("bankName") String bankName){
        return scfpChainService.getEnterprise(bankName);
    }

    /** 一次查询未兑付及未兑付的链单信息 */
    @PostMapping("/findAllLoan")
    public ResponseResult<ScfpEnterprise> findAllLoan(@RequestBody ScfpChain scfpChain){
        return scfpChainService.findAllLoan(scfpChain);
    }

    /** 查找所有未兑付和已兑付链单数量 */
    @PostMapping("/findLoanCount")
    public ResponseResult<Object> findLoanCount(@RequestBody ScfpChain scfpChain){
        return scfpChainService.findLoanCount(scfpChain);
    }

    @GetMapping("/findAllBank")
    public ResponseResult<ScfpEnterprise> findAll(){

        return scfpChainService.findAllEnterprise();
    }

    @GetMapping("/findAllEnterprise")
    public ResponseResult<ScfpEnterprise> findAllEnterprise(){

        return scfpChainService.findEnterprise();
    }

    @GetMapping("/findAllFund")
    public ResponseResult<ScfpFund> findAllFund(){
        return scfpChainService.findAllFund();
    }

    /** 根据链单id查询链单详细信息 */
    @PostMapping("/findById")
    public ResponseResult<ScfpChain> findById(@RequestBody ScfpChain scfpChain){
        return scfpChainService.findById(scfpChain);
    }

    /** 根据状态查找状态名 */
    @GetMapping("/findStatus/{status}")
    public ResponseResult<Object> findStatus(@PathVariable("status") int status){
        return scfpChainService.findStatus(status);
    }

    /** 根据公司名字查找公司id */
    @GetMapping("/findEnterpriseByName/{ename}")
    public ResponseResult<Object> findEnterpriseByName(@PathVariable("ename") String ename){
        return scfpChainService.findEnterpriseByName(ename);
    }

    @Resource
    private ScfpChainDao scfpChainDao;

    @PostMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取数据
        String json = request.getParameter("json");
        ScfpChain scfpChain = JSON.parseObject(json, ScfpChain.class);

        List<ScfpChain> list = scfpChainDao.search(scfpChain);
        List<ScfpChainStatus> status_list = new ArrayList<>();
        for (ScfpChain s : list){
            status_list.add((ScfpChainStatus)scfpChainService.findStatus(s.getStatus()).getData());
        }

        //excel标题
        String[] title = {"链单编号", "链单金额", "截止兑付时间", "开单人", "开单日", "链单状态"};

        //excel文件名
        String fileName = "链单信息表" + ".xls";
        //sheet名
        String sheetName = "链单信息表";

        String[][] content = new String[255][255];
        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            ScfpChain obj = list.get(i);
            ScfpChainStatus status = status_list.get(i);
            content[i][0] = obj.getChain_no();
            content[i][1] = obj.getMoney() + "";
            content[i][2] = obj.getDeadline();
            content[i][3] = obj.getScfpUser().getName();
            content[i][4] = obj.getCreate_time();
            content[i][5] = status.getStatus();
        }
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        //响应到客户端
        try {
            ExcelUtil.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

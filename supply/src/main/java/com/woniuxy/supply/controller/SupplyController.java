package com.woniuxy.supply.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.woniuxy.commons.entity.DTO.SupplyDTO;
import com.woniuxy.commons.entity.PageInfomation;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpChain;
import com.woniuxy.commons.entity.ScfpChainStatus;
import com.woniuxy.commons.util.ExcelUtil;
import com.woniuxy.supply.dao.SuppluDao;
import com.woniuxy.supply.service.SupplyService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.aspectj.weaver.ast.Var;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.controller
 * @Author qfx
 * @CreateTime 2022-06-07  11:02
 * @Description 供应链管理
 * @Version 1.0
 */
@RestController
@RequestMapping("/supply")
public class SupplyController {
    @Resource
    SupplyService supplyService;
    @Resource
    SuppluDao suppluDao;

    /**
     * @param pageInfomation
     * @return ResponseResult
     * @description 查询所有供应链
     * @author qfx
     * @date 2022/6/7 19:43
     */
    @PostMapping("/all")
    public ResponseResult findAllSupply(@RequestBody PageInfomation pageInfomation) {
        return supplyService.findAllSupply(pageInfomation);
    }

    /**
     * @param
     * @return ResponseResult
     * @description 根据核心企业id查询供应链
     * @author qfx
     * @date 2022/6/7 19:43
     */
    @GetMapping("/findById/{coreId}")
    public ResponseResult findById(@PathVariable("coreId") int coreId) {
        return supplyService.findById(coreId);
    }

    @PostMapping("/findByEid")

    /**
     * @description 根据企业id分页查询供应链
     * @author qfx
     * @date 2022/6/9 14:50
     * @param supplyDTO
     * @return ResponseResult
     */
    public ResponseResult findByEid(@RequestBody SupplyDTO supplyDTO) {
        return supplyService.findByEid(supplyDTO);
    }

    /**
     * @param supplyDTO
     * @return ResponseResult
     * @description 条件查询供应链
     * @author qfx
     * @date 2022/6/9 12:03
     */
    @PostMapping("/findBy")
    public ResponseResult findByCondition(@RequestBody SupplyDTO supplyDTO) {
        return supplyService.findByCondition(supplyDTO);
    }

    /**
     * @param supplyDTO
     * @return ResponseResult
     * @description 根据id条件查询供应链
     * @author qfx
     * @date 2022/6/9 15:47
     */
    @PostMapping("/findByConditionAndEid")
    public ResponseResult findByConditionAndEid(@RequestBody SupplyDTO supplyDTO) {
        return supplyService.findByConditionAndEid(supplyDTO);
    }

    /**
     * @param supplyDTO
     * @return ResponseResult
     * @description 根据id查询所有非供应链上的企业
     * @author qfx
     * @date 2022/6/9 20:31
     */
    @PostMapping("/findAllEnterprises")
    public ResponseResult findAllEnterprises(@RequestBody SupplyDTO supplyDTO) {
        return supplyService.findAllEnterprises(supplyDTO);
    }

    /**
     * @param supplyDTO
     * @return ResponseResult
     * @description 邀请企业加入供应链
     * @author qfx
     * @date 2022/6/10 10:22
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody SupplyDTO supplyDTO) {
        return supplyService.add(supplyDTO);
    }

    /**
     * @param fid
     * @param eid
     * @return ResponseResult
     * @description 移除供应链上的企业
     * @author qfx
     * @date 2022/6/10 15:30
     */
    @DeleteMapping("/delete/{fid}/{eid}")
    public ResponseResult delete(@PathVariable("fid") int fid, @PathVariable("eid") int eid) {
        return supplyService.delete(fid, eid);
    }

    /**
     * @return ResponseResult
     * @description 查询当前登录企业的所有邀请
     * @author qfx
     * @date 2022/6/11 11:57
     */
    @PostMapping("/findAllInvite")
    public ResponseResult findAllInvite(@RequestBody SupplyDTO supplyDTO) {
        return supplyService.findAllInvite(supplyDTO);
    }

    /**
     * @param supplyDTO
     * @return ResponseResult
     * @description 条件查询邀请
     * @author qfx
     * @date 2022/6/13 15:29
     */
    @PostMapping("/findByInvite")
    public ResponseResult findByInvite(@RequestBody SupplyDTO supplyDTO) {
        return supplyService.findByInvite(supplyDTO);
    }

    /**
     * @param fid
     * @param eid
     * @return ResponseResult
     * @description 同意邀请
     * @author qfx
     * @date 2022/6/11 16:25
     */
    @GetMapping("/aggre/{fid}/{eid}")
    public ResponseResult aggre(@PathVariable("fid") int fid, @PathVariable("eid") int eid) {
        return supplyService.aggre(fid, eid);
    }

    /**
     * @param fid
     * @param eid
     * @return ResponseResult
     * @description 拒绝邀请
     * @author qfx
     * @date 2022/6/11 16:40
     */
    @DeleteMapping("/refuse/{fid}/{eid}")
    public ResponseResult refuse(@PathVariable("fid") int fid, @PathVariable("eid") int eid) {
        return supplyService.refuse(fid, eid);
    }

    /**
     * @param
     * @param response
     * @description 导出excel
     * @author qfx
     * @date 2022/6/14 14:02
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("55456465");
        //获取数据
//        String json = request.getParameter("json");
//        SupplyDTO supplyDTO = JSON.parseObject(json, SupplyDTO.class);
//        List<SupplyDTO> list = suppluDao.findByCondtion(supplyDTO);
        List<SupplyDTO> list = supplyService.findAll();

        //excel标题
        String[] title = {"客户编号", "客户名称", "管理员", "管理员手机", "供应链层级", "下级客户数量", "客户状态"};

        //excel文件名
        String fileName = "核心企业供应链表" + ".xls";
        //sheet名
        String sheetName = "核心企业供应链表";

        String[][] content = new String[255][255];
        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            SupplyDTO obj = list.get(i);
            content[i][0] = String.valueOf(obj.getEid());
            content[i][1] = obj.getEname();
            content[i][2] = obj.getUsername();
            content[i][3] = obj.getPhonenum();
            content[i][4] = String.valueOf(obj.getTier());
            content[i][5] = String.valueOf(obj.getNum());
            content[i][6] = obj.getEstatus();

        }
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

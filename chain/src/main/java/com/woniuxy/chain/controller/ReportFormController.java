package com.woniuxy.chain.controller;

import com.alibaba.fastjson.JSON;
import com.woniuxy.chain.dao.ScfpChainDao;
import com.woniuxy.chain.service.ScfpChainService;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpChain;
import com.woniuxy.commons.entity.ScfpChainStatus;
import com.woniuxy.commons.util.ExcelUtil;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 \* @author: ZJH
 \* @DateTime: 2022/6/8 16:04
 \* @Description：
 */
@RestController
@RequestMapping(value = "/report")
public class ReportFormController {
    @Resource
    private ScfpChainService scfpChainService;
    @Resource
    private ScfpChainDao scfpChainDao;
    /**
     * 导出所有链单记录
     * @return
     */
    @RequestMapping(value = "/export")
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
        //String[] title = {"订单编号", "链单金额", "截止兑付时间", "开单人", "开单日"};
        String[] title = {"订单编号", "链单金额", "截止兑付时间", "开单人", "开单日", "链单状态"};

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
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
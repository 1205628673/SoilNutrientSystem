package com.jlau.algsystem.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.jlau.algsystem.entity.MonitorLocation;
import com.jlau.algsystem.entity.NutrientStandard;
import com.jlau.algsystem.entity.XlsMonitorData;
import com.jlau.algsystem.service.MonitorLocationService;
import com.jlau.algsystem.utils.CodeUtil;
import com.jlau.algsystem.utils.ExcelListener;
import com.jlau.algsystem.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by cxr1205628673 on 2020/4/30.
 */
@RestController()
public class MonitorLocationController {
    @Autowired
    MonitorLocationService monitorLocationService;

    @GetMapping("/user/monitor/all")
    public Object getAllMonitor(){
        Result result = monitorLocationService.getAllMonitor();
        return result;
    }
    @GetMapping("/user/monitor/find/{id}")
    public Object getOne(@PathVariable int id) {
        Result result = monitorLocationService.getOneMonitorById(id);
        return result;
    }
    @GetMapping("/admin/monitor/page/{page}")
    public Object getAllMonitorByPage(@PathVariable Integer page){
        Result result = monitorLocationService.getAllMonitor(page);
        return result;
    }
    @PutMapping("/admin/monitor/save")
    public Object saveMonitor(@RequestBody MonitorLocation monitor){
        Result result = monitorLocationService.updateMonitor(monitor);
        return result;
    }
    @DeleteMapping("/admin/monitor/delete/{id}")
    public Object deleteMonitor(@PathVariable("id") Integer id){
        Result result = monitorLocationService.deleteMonitorById(id);
        return result;
    }
    @GetMapping("/admin/nutrientstandard")
    public Object getNutrientStandard(){
        Result result = monitorLocationService.getNutrientStandard();
        return result;
    }
    @PutMapping("/admin/nutrientstandard/save")
    public Object saveNutrientStandard(@RequestBody  NutrientStandard standard){
        Result result = monitorLocationService.saveNutrientStandard(standard);
        return result;
    }
    @PostMapping("/admin/monitor/upload")
    public Object uploadMonitorXls(MultipartHttpServletRequest request) throws Exception{
        if(request != null) {
            Result result = monitorLocationService.handleUpload(request.getMultiFileMap().getFirst("file"));
            return result;
        }else {
            throw new Exception("empty file");
        }
    }
    @GetMapping("/admin/monitor/download")
    public Object downloadMonitorXls(HttpServletResponse response) throws Exception{
        OutputStream out = response.getOutputStream();
        try {
            response.setHeader("Content-disposition", "attachment; filename=" + "测土配方监控数据.xls");
            response.setContentType("application/msexcel;charset=UTF-8");//设置类型
            response.setHeader("Pragma", "No-cache");//设置头
            response.setHeader("Cache-Control", "no-cache");//设置头
            response.setDateHeader("Expires", 0);//设置日期头
            ExcelWriter excelWriter = new ExcelWriter(out, ExcelTypeEnum.XLS, true);
            Sheet sheet = new Sheet(1, 0, XlsMonitorData.class);
            sheet.setSheetName("测土配方施肥数据");
            Result result = monitorLocationService.getAllXlsMonitorData();
            excelWriter.write((List<? extends BaseRowModel>) result.getData(), sheet);
            excelWriter.finish();
            out.flush();
        }catch (Exception e){
            throw e;
        }finally {
            response.getOutputStream().close();
        }
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),"success");
    }
}

package com.jlau.algsystem.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.jlau.algsystem.entity.MonitorLocation;
import com.jlau.algsystem.entity.Nutrient;
import com.jlau.algsystem.entity.NutrientStandard;
import com.jlau.algsystem.entity.XlsMonitorData;
import com.jlau.algsystem.repository.MonitorLocationRepository;
import com.jlau.algsystem.repository.NutrientStandardRepository;
import com.jlau.algsystem.utils.CodeUtil;
import com.jlau.algsystem.utils.ExcelListener;
import com.jlau.algsystem.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cxr1205628673 on 2020/4/30.
 */
@Service
@Transactional
public class MonitorLocationService {
    @Autowired
    MonitorLocationRepository monitorLocationRepository;
    @Autowired
    NutrientStandardRepository nutrientStandardRepository;
    public Result getAllMonitor(){
        List<MonitorLocation> monitors = monitorLocationRepository.findAllMonitors();
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),monitors);
    }
    public Result getAllMonitor(int page){
        Pageable pageable = PageRequest.of(page-1,20);
        Page<MonitorLocation> monitors = monitorLocationRepository.findAllMonitorsByPage(pageable);
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),monitors);
    }
    public Result getOneMonitorById(Integer id){
        MonitorLocation monitorLocation = monitorLocationRepository.findById(id).get();
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),monitorLocation);
    }
    public Result updateMonitor(MonitorLocation monitor){
        monitorLocationRepository.save(monitor);
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),"success");
    }
    public Result deleteMonitorById(Integer id){
        monitorLocationRepository.deleteById(id);
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),"success");
    }
    public Result getNutrientStandard(){
        List<NutrientStandard> nutrientStandards = nutrientStandardRepository.findAll();
        NutrientStandard standard = null;
        if(nutrientStandards != null && nutrientStandards.size() != 0){
            standard = nutrientStandards.get(0);
        }
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),standard);
    }
    public Result saveNutrientStandard(NutrientStandard standard){
        nutrientStandardRepository.save(standard);
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),"success");
    }

    public Result saveXlsData(List<XlsMonitorData> datas){
        for (XlsMonitorData data:datas) {
            MonitorLocation monitor = new MonitorLocation();
            if("".equals(data.getName()) || data.getName() == null){
                continue;
            }
            monitor.setProvince(data.getProvince());
            monitor.setCity(data.getCity());
            monitor.setCounty(data.getCounty());
            monitor.setCountryside(data.getCountryside());
            monitor.setVillage(data.getVillage());
            monitor.setLatitude(data.getLatitude());
            monitor.setLongitude(data.getLongitude());
            monitor.setAltitude(data.getAltitude());
            monitor.setName(data.getName());
            monitor.setBlock(data.getBlock());
            monitor.setTime(data.getTime());
            Nutrient nutrient = new Nutrient();
            nutrient.setpH(data.getpH());
            nutrient.setAPA(data.getApa());
            nutrient.setPPM(data.getPpm());
            nutrient.setSOM(data.getSom());
            nutrient.setAK(data.getAk());
            monitor.setNutrient(nutrient);
            monitorLocationRepository.save(monitor);
        }
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),"success");
    }
    public Result handleUpload(MultipartFile multipartFile) throws Exception{
        String originalFilename = multipartFile.getOriginalFilename();
        if (!originalFilename.endsWith(ExcelTypeEnum.XLS.getValue()) && !originalFilename.endsWith(ExcelTypeEnum.XLSX.getValue())) {
            throw new RuntimeException("请上传Excel格式文件");
        }
        InputStream in = null;
        try{
            in = new BufferedInputStream(multipartFile.getInputStream());
            if(in == null){
                throw new Exception("文件流错误");
            }
            //EasyExcelFactory.readBySax(in, new Sheet(1,1,XlsMonitorData.class),listenerl);
            ExcelReader excelReader = new ExcelReader(in,null,new ExcelListener(this),false);
            excelReader.read(new Sheet(1,1,XlsMonitorData.class));
        }catch (Exception ioe){
            throw new Exception("请检查excel格式是否符合要求");
        }finally {
            in.close();
        }
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),"success");
    }
    public Result getAllXlsMonitorData(){
        MonitorLocation monitorLocation = null;
        List<MonitorLocation> monitorLocations = monitorLocationRepository.findAllMonitors();
        List<XlsMonitorData> xlsDatas = new ArrayList<>();
        for (MonitorLocation monitor: monitorLocations) {
            XlsMonitorData xlsMonitorData = new XlsMonitorData();
            xlsMonitorData.setProvince(monitor.getProvince());
            xlsMonitorData.setCity(monitor.getCity());
            xlsMonitorData.setCounty(monitor.getCounty());
            xlsMonitorData.setCountryside(monitor.getCountryside());
            xlsMonitorData.setVillage(monitor.getVillage());
            xlsMonitorData.setName(monitor.getName());
            xlsMonitorData.setBlock(monitor.getBlock());
            xlsMonitorData.setLatitude(monitor.getLatitude());
            xlsMonitorData.setLongitude(monitor.getLongitude());
            xlsMonitorData.setAltitude(monitor.getAltitude());
            xlsMonitorData.setTime(monitor.getTime());
            xlsMonitorData.setSom(monitor.getNutrient().getSOM());
            xlsMonitorData.setpH(monitor.getNutrient().getpH());
            xlsMonitorData.setApa(monitor.getNutrient().getAPA());
            xlsMonitorData.setAk(monitor.getNutrient().getAK());
            xlsMonitorData.setPpm(monitor.getNutrient().getPPM());
            xlsDatas.add(xlsMonitorData);
        }
        Result result = new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),xlsDatas);
        return result;
    }
}

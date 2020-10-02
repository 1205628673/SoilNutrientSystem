package com.jlau.algsystem.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.jlau.algsystem.entity.XlsMonitorData;
import com.jlau.algsystem.service.MonitorLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxr1205628673 on 2020/5/8.
 */
public class ExcelListener extends AnalysisEventListener<XlsMonitorData>{

    List datas = new ArrayList<>();
    MonitorLocationService monitorLocationService;
    public ExcelListener(MonitorLocationService monitorLocationService) {
        this.monitorLocationService = monitorLocationService;
    }

    public void saveXlsData(List datas){
        monitorLocationService.saveXlsData(datas);
    }
    @Override
    public void invoke(XlsMonitorData o, AnalysisContext analysisContext) {
        System.out.println(analysisContext.getCurrentSheet());
        datas.add(o);
        if (datas.size()>100) {
            saveXlsData(datas);
            datas.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveXlsData(datas);
        datas.clear();
    }

    public List getDatas() {
        return datas;
    }

    public void setDatas(List datas) {
        this.datas = datas;
    }
}

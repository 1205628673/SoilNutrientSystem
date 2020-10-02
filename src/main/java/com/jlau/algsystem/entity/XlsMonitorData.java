package com.jlau.algsystem.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * Created by cxr1205628673 on 2020/5/8.
 */
public class XlsMonitorData extends BaseRowModel{
    @ExcelProperty(value = "采样日期",index = 1)
    private String time;
    @ExcelProperty(value = "省名称",index = 2)
    private String province;
    @ExcelProperty(value = "市名称",index = 3)
    private String city;
    @ExcelProperty(value = "县名称",index = 4)
    private String county;
    @ExcelProperty(value = "乡(镇)名称",index = 5)
    private String countryside;
    @ExcelProperty(value = "村名称",index = 6)
    private String village;
    @ExcelProperty(value = "农户名称",index = 7)
    private String name;
    @ExcelProperty(value = "地块名称",index = 8)
    private String block;
    @ExcelProperty(value = "北纬",index = 9)
    private Double latitude;
    @ExcelProperty(value = "东经",index = 10)
    private Double longitude;
    @ExcelProperty(value = "海拔",index = 11)
    private Double altitude;
    @ExcelProperty(value = "pH",index = 12)
    private Double pH;
    @ExcelProperty(value = "有机质",index = 13)
    private Double som;
    @ExcelProperty(value = "碱解氮",index = 14)
    private Double ppm;
    @ExcelProperty(value = "有效磷",index = 15)
    private Double apa;
    @ExcelProperty(value = "速效钾",index = 16)
    private Double ak;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountryside() {
        return countryside;
    }

    public void setCountryside(String countryside) {
        this.countryside = countryside;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getpH() {
        return pH;
    }

    public void setpH(Double pH) {
        this.pH = pH;
    }

    public Double getSom() {
        return som;
    }

    public void setSom(Double som) {
        this.som = som;
    }

    public Double getPpm() {
        return ppm;
    }

    public void setPpm(Double ppm) {
        this.ppm = ppm;
    }

    public Double getApa() {
        return apa;
    }

    public void setApa(Double apa) {
        this.apa = apa;
    }

    public Double getAk() {
        return ak;
    }

    public void setAk(Double ak) {
        this.ak = ak;
    }
}

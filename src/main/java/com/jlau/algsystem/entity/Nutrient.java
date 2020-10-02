package com.jlau.algsystem.entity;

import javax.persistence.*;

/**
 * Created by cxr1205628673 on 2020/4/30.
 */
@Entity
@Table(name = "Nutrient")
public class Nutrient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private Double pH;

    @Column
    private Double SOM;

    @Column
    private Double PPM;

    @Column
    private Double APA;

    @Column
    private Double AK;

    @OneToOne(mappedBy = "nutrient")
    private MonitorLocation monitorLocation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getpH() {
        return pH;
    }

    public void setpH(Double pH) {
        this.pH = pH;
    }

    public Double getSOM() {
        return SOM;
    }

    public void setSOM(Double SOM) {
        this.SOM = SOM;
    }

    public Double getPPM() {
        return PPM;
    }

    public void setPPM(Double PPM) {
        this.PPM = PPM;
    }

    public Double getAPA() {
        return APA;
    }

    public void setAPA(Double APA) {
        this.APA = APA;
    }

    public Double getAK() {
        return AK;
    }

    public void setAK(Double AK) {
        this.AK = AK;
    }
}

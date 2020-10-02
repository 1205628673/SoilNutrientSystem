package com.jlau.algsystem.entity;

import javax.persistence.*;

/**
 * Created by cxr1205628673 on 2020/5/13.
 */
@Entity
@Table(name = "nutrient_standard")
public class NutrientStandard {
    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private Double SOMExcellent;

    @Column
    private Double SOMGreat;

    @Column
    private Double SOMNormal;

    @Column
    private Double SOMLack;

    @Column
    private Double PPMExcellent;

    @Column
    private Double PPMGreat;

    @Column
    private Double PPMNormal;

    @Column
    private Double PPMLack;

    @Column
    private Double APAExcellent;

    @Column
    private Double APAGreat;

    @Column
    private Double APANormal;

    @Column
    private Double APALack;

    @Column
    private Double AKExcellent;

    @Column
    private Double AKGreat;

    @Column
    private Double AKNormal;
    @Column
    private Double AKLack;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSOMExcellent() {
        return SOMExcellent;
    }

    public void setSOMExcellent(Double SOMExcellent) {
        this.SOMExcellent = SOMExcellent;
    }

    public Double getSOMGreat() {
        return SOMGreat;
    }

    public void setSOMGreat(Double SOMGreat) {
        this.SOMGreat = SOMGreat;
    }

    public Double getSOMNormal() {
        return SOMNormal;
    }

    public void setSOMNormal(Double SOMNormal) {
        this.SOMNormal = SOMNormal;
    }

    public Double getSOMLack() {
        return SOMLack;
    }

    public void setSOMLack(Double SOMLack) {
        this.SOMLack = SOMLack;
    }

    public Double getPPMExcellent() {
        return PPMExcellent;
    }

    public void setPPMExcellent(Double PPMExcellent) {
        this.PPMExcellent = PPMExcellent;
    }

    public Double getPPMGreat() {
        return PPMGreat;
    }

    public void setPPMGreat(Double PPMGreat) {
        this.PPMGreat = PPMGreat;
    }

    public Double getPPMNormal() {
        return PPMNormal;
    }

    public void setPPMNormal(Double PPMNormal) {
        this.PPMNormal = PPMNormal;
    }

    public Double getPPMLack() {
        return PPMLack;
    }

    public void setPPMLack(Double PPMLack) {
        this.PPMLack = PPMLack;
    }

    public Double getAPAExcellent() {
        return APAExcellent;
    }

    public void setAPAExcellent(Double APAExcellent) {
        this.APAExcellent = APAExcellent;
    }

    public Double getAPAGreat() {
        return APAGreat;
    }

    public void setAPAGreat(Double APAGreat) {
        this.APAGreat = APAGreat;
    }

    public Double getAPANormal() {
        return APANormal;
    }

    public void setAPANormal(Double APANormal) {
        this.APANormal = APANormal;
    }

    public Double getAPALack() {
        return APALack;
    }

    public void setAPALack(Double APALack) {
        this.APALack = APALack;
    }

    public Double getAKExcellent() {
        return AKExcellent;
    }

    public void setAKExcellent(Double AKExcellent) {
        this.AKExcellent = AKExcellent;
    }

    public Double getAKGreat() {
        return AKGreat;
    }

    public void setAKGreat(Double AKGreat) {
        this.AKGreat = AKGreat;
    }

    public Double getAKNormal() {
        return AKNormal;
    }

    public void setAKNormal(Double AKNormal) {
        this.AKNormal = AKNormal;
    }

    public Double getAKLack() {
        return AKLack;
    }

    public void setAKLack(Double AKLack) {
        this.AKLack = AKLack;
    }
}

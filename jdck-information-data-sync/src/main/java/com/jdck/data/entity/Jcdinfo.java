package com.jdck.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Jcdinfo")
public class Jcdinfo {
    @Id
    private String jcdbh;

    private String tybh;

    private String dname;

    private Double x;

    private Double y;

    private Integer ucode;

    private String eaddress;

    private String topography;

    private Double altitude;

    private String traffic;

    private String material;

    private String protective;

    private String cpname;

    private String cpphone;

    private String econdition;

    public String getJcdbh() {
        return jcdbh;
    }

    public void setJcdbh(String jcdbh) {
        this.jcdbh = jcdbh == null ? null : jcdbh.trim();
    }

    public String getTybh() {
        return tybh;
    }

    public void setTybh(String tybh) {
        this.tybh = tybh == null ? null : tybh.trim();
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Integer getUcode() {
        return ucode;
    }

    public void setUcode(Integer ucode) {
        this.ucode = ucode;
    }

    public String getEaddress() {
        return eaddress;
    }

    public void setEaddress(String eaddress) {
        this.eaddress = eaddress == null ? null : eaddress.trim();
    }

    public String getTopography() {
        return topography;
    }

    public void setTopography(String topography) {
        this.topography = topography == null ? null : topography.trim();
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic == null ? null : traffic.trim();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    public String getProtective() {
        return protective;
    }

    public void setProtective(String protective) {
        this.protective = protective == null ? null : protective.trim();
    }

    public String getCpname() {
        return cpname;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname == null ? null : cpname.trim();
    }

    public String getCpphone() {
        return cpphone;
    }

    public void setCpphone(String cpphone) {
        this.cpphone = cpphone == null ? null : cpphone.trim();
    }

    public String getEcondition() {
        return econdition;
    }

    public void setEcondition(String econdition) {
        this.econdition = econdition == null ? null : econdition.trim();
    }
}
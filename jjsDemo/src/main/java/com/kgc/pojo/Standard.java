package com.kgc.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Standard {
    private Integer id;

    private String stdNum;

    private String zhname;

    private String standardversion;

    private String standardkeys;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date implDate;

    private String packagePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStdNum() {
        return stdNum;
    }

    public void setStdNum(String stdNum) {
        this.stdNum = stdNum == null ? null : stdNum.trim();
    }

    public String getZhname() {
        return zhname;
    }

    public void setZhname(String zhname) {
        this.zhname = zhname == null ? null : zhname.trim();
    }

    public String getStandardversion() {
        return standardversion;
    }

    public void setStandardversion(String standardversion) {
        this.standardversion = standardversion == null ? null : standardversion.trim();
    }

    public String getStandardkeys() {
        return standardkeys;
    }

    public void setStandardkeys(String standardkeys) {
        this.standardkeys = standardkeys == null ? null : standardkeys.trim();
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getImplDate() {
        return implDate;
    }

    public void setImplDate(Date implDate) {
        this.implDate = implDate;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath == null ? null : packagePath.trim();
    }
}
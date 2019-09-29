package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.Date;

public class FkStlDayEndStep {
    private BigDecimal id;

    private String dailydate;

    private String stepname;

    private String funcname;

    private String libname;

    private BigDecimal execStatus;

    private BigDecimal status;

    private Date begintime;

    private Date endtime;

    private BigDecimal pid;

    private String comments;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDailydate() {
        return dailydate;
    }

    public void setDailydate(String dailydate) {
        this.dailydate = dailydate == null ? null : dailydate.trim();
    }

    public String getStepname() {
        return stepname;
    }

    public void setStepname(String stepname) {
        this.stepname = stepname == null ? null : stepname.trim();
    }

    public String getFuncname() {
        return funcname;
    }

    public void setFuncname(String funcname) {
        this.funcname = funcname == null ? null : funcname.trim();
    }

    public String getLibname() {
        return libname;
    }

    public void setLibname(String libname) {
        this.libname = libname == null ? null : libname.trim();
    }

    public BigDecimal getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(BigDecimal execStatus) {
        this.execStatus = execStatus;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public BigDecimal getPid() {
        return pid;
    }

    public void setPid(BigDecimal pid) {
        this.pid = pid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }
}
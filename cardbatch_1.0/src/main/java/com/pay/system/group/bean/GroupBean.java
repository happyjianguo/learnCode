package com.pay.system.group.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.StringUtils;

public class GroupBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String teamno = "";
    private String teamname = "";
    private String teamdescribe = "";

    public GroupBean() {

    }

    public GroupBean(HashMap record) {
        if (record.get("teamno") == null) {
            this.setTeamno("");
        } else {
            this.setTeamno(StringUtils.innerToOuter((String) record.get("teamno")).trim());
        }
        if (record.get("teamname") == null) {
            this.setTeamname("");
        } else {
            this.setTeamname(StringUtils.innerToOuter((String) record.get("teamname")).trim());
        }
        if (record.get("teamdescribe") == null) {
            this.setTeamdescribe("");
        } else {
            this.setTeamdescribe(StringUtils.innerToOuter((String) record.get("teamdescribe")).trim());
        }
    }

    public String getTeamdescribe() {
        return teamdescribe;
    }

    public void setTeamdescribe(String teamdescribe) {
        this.teamdescribe = teamdescribe;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getTeamno() {
        return teamno;
    }

    public void setTeamno(String teamno) {
        this.teamno = teamno;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(teamno);
        sb.append("|");
        sb.append(teamname);
        sb.append("|");
        sb.append(teamdescribe);
        sb.append("|");

        return new String(sb);
    }
}

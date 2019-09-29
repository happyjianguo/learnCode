package cn.yufu.cortex.entity;

public class NumDescrKey {
    private String descrtype;

    private Integer id;

    private String lang;

    public String getDescrtype() {
        return descrtype;
    }

    public void setDescrtype(String descrtype) {
        this.descrtype = descrtype == null ? null : descrtype.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang == null ? null : lang.trim();
    }
}
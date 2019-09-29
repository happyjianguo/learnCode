package cn.yufu.posp.common.domain.model;

import java.io.Serializable;

public class FileNumberInfoModel implements Serializable 
{

   
    private String prefix;//前缀

    private String postfix;//后缀

    private String fileType;//文号类型001收文类型,002发文类型
    
    private int inceptNum;//起始号

    private int yearNum;//年号

    private long templateId;//发文模版编号
    
    private long nonceNum;//当前号
    
    private String brackedType;//括号类型01小括号,02中括号
    
    private String typeNum;//类型编号
    
    private String abateNum;//是否作废

    public String getAbateNum() {
		return abateNum;
	}

	public void setAbateNum(String abateNum) {
		this.abateNum = abateNum;
	}

	/**
     * @return Returns the typeNum.
     */
    public String getTypeNum()
    {
        return typeNum;
    }

    /**
     * @param typeNum The typeNum to set.
     */
    public void setTypeNum(String typeNum)
    {
        this.typeNum = typeNum;
    }

    public String getBrackedType() {
		return brackedType;
	}

	public void setBrackedType(String brackedType) {
		this.brackedType = brackedType;
	}

	/**
     * @return Returns the nonceNum.
     */
    public long getNonceNum()
    {
        return nonceNum;
    }

    /**
     * @param nonceNum The nonceNum to set.
     */
    public void setNonceNum(long nonceNum)
    {
        this.nonceNum = nonceNum;
    }

    /**
     * @return Returns the fileType.
     */
    public String getFileType()
    {
        return fileType;
    }

    /**
     * @param fileType
     *            The fileType to set.
     */
    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }
    /**
     * @return Returns the inceptNum.
     */
    public int getInceptNum()
    {
        return inceptNum;
    }
    /**
     * @param inceptNum The inceptNum to set.
     */
    public void setInceptNum(int inceptNum)
    {
        this.inceptNum = inceptNum;
    }
   
    /**
     * @return Returns the postfix.
     */
    public String getPostfix()
    {
        return postfix;
    }
    /**
     * @param postfix The postfix to set.
     */
    public void setPostfix(String postfix)
    {
        this.postfix = postfix;
    }
    /**
     * @return Returns the prefix.
     */
    public String getPrefix()
    {
        return prefix;
    }
    /**
     * @param prefix The prefix to set.
     */
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }
   
	public int getYearNum() {
		return yearNum;
	}

	public void setYearNum(int yearNum) {
		this.yearNum = yearNum;
	}

	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	

}

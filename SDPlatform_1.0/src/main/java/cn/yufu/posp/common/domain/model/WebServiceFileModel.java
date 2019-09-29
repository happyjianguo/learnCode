package cn.yufu.posp.common.domain.model;

import java.io.Serializable;

public class WebServiceFileModel implements Serializable 
{
    private String name;//文件名

	private byte[] content;//文件内容

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}   
}

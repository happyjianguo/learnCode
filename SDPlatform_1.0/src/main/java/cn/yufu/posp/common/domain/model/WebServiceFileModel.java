package cn.yufu.posp.common.domain.model;

import java.io.Serializable;

public class WebServiceFileModel implements Serializable 
{
    private String name;//�ļ���

	private byte[] content;//�ļ�����

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

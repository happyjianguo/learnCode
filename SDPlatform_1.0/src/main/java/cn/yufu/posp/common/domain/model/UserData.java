package cn.yufu.posp.common.domain.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * �ܿ����ݣ��û���¼���ݣ� �û���¼�󣬻�ȡ�û���¼����UserData,��UserData�л�ȡ�����ݡ�
 */
public class UserData implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8657701525151496455L;

	//�˵���ťMAP
	public HashMap groupMap;
	public HashMap getGroupMap() {
		return groupMap;
	}
	public String userName ;

	public void setGroupMap(HashMap groupMap) {
		this.groupMap = groupMap;
	}

	/**
	 * �û�ID
	 */
	public String yhid;

	/**
	 * �û�����
	 */
	public String yhmc;

	/**
	 * E-mail
	 */
	public String email;

	/**
	 * ������λ����
	 */
	public List ssdwdm = new ArrayList();

	/**
	 * ������λ����
	 */
	public List ssdwmc = new ArrayList();
//��ɫ
	public String groupId;
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * ְλ����
	 */
	public String zwdm;

	/**
	 * ְλ����
	 */
	public String zwmc;

	/**
	 * ϵͳʶ����1 �Թ���ϵͳ�����Լ����û���Ψһ����
	 */
	public String xtsbm1;

	/**
	 * ϵͳʶ����2
	 */
	public String xtsbm2;

	/**
	 * ϵͳʶ����3
	 */
	public String xtsbm3;

	/**
	 * ϵͳʶ����4
	 */
	public String xtsbm4;

	/**
	 * �û�����
	 */
	public String yhjb;

	/**
	 * ��¼IP
	 */
	public String ip = "";

	/**
	 * ��ǰ�û�����ҵ�����ID(�����嵥���jdid)
	 */
	public int jdid;

	/**
	 * ��¼ʱ��
	 */
	public Date loginTime;

	/**
	 * ��ѯ���ÿҳ��ʾ�������
	 */
	public int myxszds;

	/**
	 * �û���¼�Ľ�ɫ ��������Ȩ��ʱ��
	 */
	public List roleId = new ArrayList();

	/**
	 * �û���� ����ϵͳ��ͬ������
	 */
	public String yhlb;

	/**
	 * �û�����
	 */
	public String yhlx;

	/**
	 * ��λ���ʹ���
	 */
	public String dwlxdm;

	/**
	 * ��λ��������
	 */
	public String dwlxmc;

	/**
	 * ������ҵ����
	 */
	public String hydm;

	/**
	 * ������ҵ����
	 */
	public String hymc;

	/**
	 * ��ϵ�绰
	 */
	public String lxdh;

	/**
	 * ע������
	 */
	public java.sql.Date zcrq;

	/**
	 * �ʺ�״̬
	 */
	public int zhzt;

	/**
	 * ����������
	 */
	public String gxswjgzzjgmc;

	/**
	 * �û���ǰ���е�ҵ���嵥�����Ĳ�����
	 */
	public Properties parameter;
	
	public String clientId;
	public String userId;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ��Ͻ���ش��롣
	 */
	public String gxswjgzzjgdm;
	/**
	 * ��Ҫ���ұ��
	 */
	public String csbh;
    /**
     * ��Ҫ��������
     */
    public String csmc;
	/**
	 * @roseuid 43D46A2801F8
	 */
	public UserData()
	{

	}

	/**
	 * @return java.lang.String
	 * @roseuid 43D46A280270
	 */
	public String getGxswjgzzjgdm()
	{
		return null;
	}

	/**
	 * @param gxswjgzzjgdm
	 * @roseuid 43D46A28027A
	 */
	public void setGxswjgzzjgdm(String gxswjgzzjgdm)
	{

	}

	/**
	 * @return int
	 * @roseuid 43D46A280285
	 */
	public int getJdid()
	{
		return 0;
	}

	/**
	 * @param jdid
	 * @roseuid 43D46A28028F
	 */
	public void setJdid(int jdid)
	{

	}

	/**
	 * @return java.util.Properties
	 * @roseuid 43D46A28029A
	 */
	public Properties getParameter()
	{
		return null;
	}

	/**
	 * @param parameter
	 * @roseuid 43D46A2802C1
	 */
	public void setParameter(Properties parameter)
	{

	}

	/**
	 * /** �û����ͣ������û� public static final String YHLX_WSYH = "02"; /** �û����ͣ�ϵͳ�û�
	 * public static final String YHLX_XTYH = "01"; /** �û����:�����û� public static
	 * final String YHLB_GRYH = "02";
	 */

	public String getDwlxdm()
	{
		return dwlxdm;
	}

	public void setDwlxdm(String dwlxdm)
	{
		this.dwlxdm = dwlxdm;
	}

	public String getDwlxmc()
	{
		return dwlxmc;
	}

	public void setDwlxmc(String dwlxmc)
	{
		this.dwlxmc = dwlxmc;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getGxswjgzzjgmc()
	{
		return gxswjgzzjgmc;
	}

	public void setGxswjgzzjgmc(String gxswjgzzjgmc)
	{
		this.gxswjgzzjgmc = gxswjgzzjgmc;
	}

	public String getHydm()
	{
		return hydm;
	}

	public void setHydm(String hydm)
	{
		this.hydm = hydm;
	}

	public String getHymc()
	{
		return hymc;
	}

	public void setHymc(String hymc)
	{
		this.hymc = hymc;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public Date getLoginTime()
	{
		return loginTime;
	}

	public void setLoginTime(Date loginTime)
	{
		this.loginTime = loginTime;
	}

	public String getLxdh()
	{
		return lxdh;
	}

	public void setLxdh(String lxdh)
	{
		this.lxdh = lxdh;
	}

	public int getMyxszds()
	{
		return myxszds;
	}

	public void setMyxszds(int myxszds)
	{
		this.myxszds = myxszds;
	}

	public List getRoleId()
	{
		return roleId;
	}

	public void setRoleId(List roleId)
	{
		this.roleId = roleId;
	}

	public List getSsdwdm()
	{
		return ssdwdm;
	}

	public void setSsdwdm(List ssdwdm)
	{
		this.ssdwdm = ssdwdm;
	}

	public List getSsdwmc()
	{
		return ssdwmc;
	}

	public void setSsdwmc(List ssdwmc)
	{
		this.ssdwmc = ssdwmc;
	}

	public String getXtsbm1()
	{
		return xtsbm1;
	}

	public void setXtsbm1(String xtsbm1)
	{
		this.xtsbm1 = xtsbm1;
	}

	public String getXtsbm2()
	{
		return xtsbm2;
	}

	public void setXtsbm2(String xtsbm2)
	{
		this.xtsbm2 = xtsbm2;
	}

	public String getXtsbm3()
	{
		return xtsbm3;
	}

	public void setXtsbm3(String xtsbm3)
	{
		this.xtsbm3 = xtsbm3;
	}

	public String getXtsbm4()
	{
		return xtsbm4;
	}

	public void setXtsbm4(String xtsbm4)
	{
		this.xtsbm4 = xtsbm4;
	}

	public String getYhid()
	{
		return yhid;
	}

	public void setYhid(String yhid)
	{
		this.yhid = yhid;
	}

	public String getYhjb()
	{
		return yhjb;
	}

	public void setYhjb(String yhjb)
	{
		this.yhjb = yhjb;
	}

	public String getYhlb()
	{
		return yhlb;
	}

	public void setYhlb(String yhlb)
	{
		this.yhlb = yhlb;
	}

	public String getYhlx()
	{
		return yhlx;
	}

	public void setYhlx(String yhlx)
	{
		this.yhlx = yhlx;
	}

	public String getYhmc()
	{
		return yhmc;
	}

	public void setYhmc(String yhmc)
	{
		this.yhmc = yhmc;
	}

	public java.sql.Date getZcrq()
	{
		return zcrq;
	}

	public void setZcrq(java.sql.Date zcrq)
	{
		this.zcrq = zcrq;
	}

	public int getZhzt()
	{
		return zhzt;
	}

	public void setZhzt(int zhzt)
	{
		this.zhzt = zhzt;
	}

	public String getZwdm()
	{
		return zwdm;
	}

	public void setZwdm(String zwdm)
	{
		this.zwdm = zwdm;
	}

	public String getZwmc()
	{
		return zwmc;
	}

	public void setZwmc(String zwmc)
	{
		this.zwmc = zwmc;
	}

    /**
     * @return Returns the csmc.
     */
    public String getCsmc()
    {
        return csmc;
    }

    /**
     * @param csmc The csmc to set.
     */
    public void setCsmc(String csmc)
    {
        this.csmc = csmc;
    }

    /**
     * @return Returns the csbh.
     */
    public String getCsbh()
    {
        return csbh;
    }

    /**
     * @param csbh The csbh to set.
     */
    public void setCsbh(String csbh)
    {
        this.csbh = csbh;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}

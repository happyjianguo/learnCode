/**
 *包名:com.pay.merchant.bean
 *描述:package com.pay.merchant.bean;
 */
package com.pay.merchant.bean;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;

import com.pay.util.StringUtils;

/**
 * CrdroutingBean.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年4月26日
 * 描述:卡BIN
 */
public class CrdroutingBean {
	private static final long serialVersionUID = 1L;

    private String iid;

    private String verno_ctx;

    private String descr;

    private String exclude;

    private String authsvc;

    private String finsvc;

    private String ichgsvc;

    private String fecode;

    private String scheme;

    private String allowsvc;

    private String local_use;

    private String settletype;

    private String panlens;

    private String svccodes;

    private BigDecimal luhnlen;

    private String startfmt;

    private String endfmt;

    private BigDecimal isslen;

    private BigDecimal posniss;

    private BigDecimal posnexp;

    private BigDecimal posnstart;

    private BigDecimal posnsvc;

    private BigDecimal posncvv;

    private BigDecimal posnpvv;

    private BigDecimal outbtchtype;

    private BigDecimal outatmbtchtype;

    private String subaddress;

    private String curbill;

    private String curset;

    private BigDecimal rateinst_id;

    private BigDecimal rateprod_id;

    private String emvadvsvc;

    private String options;

public CrdroutingBean(){
		
	}
	
	public CrdroutingBean (HashMap record) throws Exception{
		for (Object key : record.keySet()) {
			Class[] cargs = new Class[1];
			String realArgs = "";
			cargs[0] = realArgs.getClass();
			Method method = this.getClass().getMethod(
					"set"
							+ StringUtils.toUpperCaseFirstOne(((String) key)
									.toLowerCase()), cargs);
			Object arglist[] = new Object[1];
			if (record.get(key) == null) {
				arglist[0] = "";
			} else {
				arglist[0] = StringUtils.innerToOuter((String) record.get(key))
						.trim();
			}
			method.invoke(this, arglist);
		}
		
	}
	
    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid == null ? null : iid.trim();
    }



    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude == null ? null : exclude.trim();
    }

    public String getAuthsvc() {
        return authsvc;
    }

    public void setAuthsvc(String authsvc) {
        this.authsvc = authsvc == null ? null : authsvc.trim();
    }

    public String getFinsvc() {
        return finsvc;
    }

    public void setFinsvc(String finsvc) {
        this.finsvc = finsvc == null ? null : finsvc.trim();
    }

    public String getIchgsvc() {
        return ichgsvc;
    }

    public void setIchgsvc(String ichgsvc) {
        this.ichgsvc = ichgsvc == null ? null : ichgsvc.trim();
    }

    public String getFecode() {
        return fecode;
    }

    public void setFecode(String fecode) {
        this.fecode = fecode == null ? null : fecode.trim();
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme == null ? null : scheme.trim();
    }

    public String getAllowsvc() {
        return allowsvc;
    }

    public void setAllowsvc(String allowsvc) {
        this.allowsvc = allowsvc == null ? null : allowsvc.trim();
    }


    public String getSettletype() {
        return settletype;
    }

    public void setSettletype(String settletype) {
        this.settletype = settletype == null ? null : settletype.trim();
    }

    public String getPanlens() {
        return panlens;
    }

    public void setPanlens(String panlens) {
        this.panlens = panlens == null ? null : panlens.trim();
    }

    public String getSvccodes() {
        return svccodes;
    }

    public void setSvccodes(String svccodes) {
        this.svccodes = svccodes == null ? null : svccodes.trim();
    }

    public BigDecimal getLuhnlen() {
        return luhnlen;
    }

    public void setLuhnlen(BigDecimal luhnlen) {
        this.luhnlen = luhnlen;
    }

    public String getStartfmt() {
        return startfmt;
    }

    public void setStartfmt(String startfmt) {
        this.startfmt = startfmt == null ? null : startfmt.trim();
    }

    public String getEndfmt() {
        return endfmt;
    }

    public void setEndfmt(String endfmt) {
        this.endfmt = endfmt == null ? null : endfmt.trim();
    }

    public BigDecimal getIsslen() {
        return isslen;
    }

    public void setIsslen(BigDecimal isslen) {
        this.isslen = isslen;
    }

    public BigDecimal getPosniss() {
        return posniss;
    }

    public void setPosniss(BigDecimal posniss) {
        this.posniss = posniss;
    }

    public BigDecimal getPosnexp() {
        return posnexp;
    }

    public void setPosnexp(BigDecimal posnexp) {
        this.posnexp = posnexp;
    }

    public BigDecimal getPosnstart() {
        return posnstart;
    }

    public void setPosnstart(BigDecimal posnstart) {
        this.posnstart = posnstart;
    }

    public BigDecimal getPosnsvc() {
        return posnsvc;
    }

    public void setPosnsvc(BigDecimal posnsvc) {
        this.posnsvc = posnsvc;
    }

    public BigDecimal getPosncvv() {
        return posncvv;
    }

    public void setPosncvv(BigDecimal posncvv) {
        this.posncvv = posncvv;
    }

    public BigDecimal getPosnpvv() {
        return posnpvv;
    }

    public void setPosnpvv(BigDecimal posnpvv) {
        this.posnpvv = posnpvv;
    }

    public BigDecimal getOutbtchtype() {
        return outbtchtype;
    }

    public void setOutbtchtype(BigDecimal outbtchtype) {
        this.outbtchtype = outbtchtype;
    }

    public BigDecimal getOutatmbtchtype() {
        return outatmbtchtype;
    }

    public void setOutatmbtchtype(BigDecimal outatmbtchtype) {
        this.outatmbtchtype = outatmbtchtype;
    }

    public String getSubaddress() {
        return subaddress;
    }

    public void setSubaddress(String subaddress) {
        this.subaddress = subaddress == null ? null : subaddress.trim();
    }

    public String getCurbill() {
        return curbill;
    }

    public void setCurbill(String curbill) {
        this.curbill = curbill == null ? null : curbill.trim();
    }

    public String getCurset() {
        return curset;
    }

    public void setCurset(String curset) {
        this.curset = curset == null ? null : curset.trim();
    }

    public String getEmvadvsvc() {
        return emvadvsvc;
    }

    public void setEmvadvsvc(String emvadvsvc) {
        this.emvadvsvc = emvadvsvc == null ? null : emvadvsvc.trim();
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options == null ? null : options.trim();
    }

	public String getVerno_ctx() {
		return verno_ctx;
	}

	public void setVerno_ctx(String verno_ctx) {
		this.verno_ctx = verno_ctx;
	}

	public String getLocal_use() {
		return local_use;
	}

	public void setLocal_use(String local_use) {
		this.local_use = local_use == null ? null : local_use.trim();
	}

	public BigDecimal getRateinst_id() {
		return rateinst_id;
	}

	public void setRateinst_id(BigDecimal rateinst_id) {
		this.rateinst_id = rateinst_id;
	}

	public BigDecimal getRateprod_id() {
		return rateprod_id;
	}

	public void setRateprod_id(BigDecimal rateprod_id) {
		this.rateprod_id = rateprod_id;
	}

}

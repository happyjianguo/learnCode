/**
 * aa.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月20日
 */
package cn.com.jansh.model.cf;

/**
 * 套餐model
 * @author duanmuyn
 * @version 1.0
 */
public class RecMenu{
	
		private String apid;//供应商报价id
		private String acid;//接入者id
		private String price;//价格
		private String ispno;//运营商
		private String ipstype;//充值类型
		private String province;//省份
		private String facevalue;//面额
		private String facunit;//面额单位
		private String status;//状态
		private String acname;//接入者名称
		private String pname;
		private String proqg;//使用范围
		private String urange;//使用范围
		
		/**
		 * @return the facunit
		 */
		public String getFacunit() {
			return facunit;
		}
		/**
		 * @param facunit the facunit to set
		 */
		public void setFacunit(String facunit) {
			this.facunit = facunit;
		}
		/**
		 * @return the acid
		 */
		public String getAcid() {
			return acid;
		}
		/**
		 * @param acid the acid to set
		 */
		public void setAcid(String acid) {
			this.acid = acid;
		}
		
		/**
		 * @return the apid
		 */
		public String getApid() {
			return apid;
		}
		/**
		 * @param apid the apid to set
		 */
		public void setApid(String apid) {
			this.apid = apid;
		}
		/**
		 * @return the price
		 */
		public String getPrice() {
			return price;
		}
		/**
		 * @param price the price to set
		 */
		public void setPrice(String price) {
			this.price = price;
		}
		/**
		 * @return the ispno
		 */
		public String getIspno() {
			return ispno;
		}
		/**
		 * @param ispno the ispno to set
		 */
		public void setIspno(String ispno) {
			this.ispno = ispno;
		}
		/**
		 * @return the ipstype
		 */
		public String getIpstype() {
			return ipstype;
		}
		/**
		 * @param ipstype the ipstype to set
		 */
		public void setIpstype(String ipstype) {
			this.ipstype = ipstype;
		}
		/**
		 * @return the province
		 */
		public String getProvince() {
			return province;
		}
		/**
		 * @param province the province to set
		 */
		public void setProvince(String province) {
			this.province = province;
		}
		/**
		 * @return the facevalue
		 */
		public String getFacevalue() {
			return facevalue;
		}
		/**
		 * @param facevalue the facevalue to set
		 */
		public void setFacevalue(String facevalue) {
			this.facevalue = facevalue;
		}
		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}
		/**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}
		/**
		 * @return the acname
		 */
		public String getAcname() {
			return acname;
		}
		/**
		 * @param acname the acname to set
		 */
		public void setAcname(String acname) {
			this.acname = acname;
		}
		/**
		 * @return the pname
		 */
		public String getPname() {
			return pname;
		}
		/**
		 * @param pname the pname to set
		 */
		public void setPname(String pname) {
			this.pname = pname;
		}
		/**
		 * @return the proqg
		 */
		public String getProqg() {
			return proqg;
		}
		/**
		 * @param proqg the proqg to set
		 */
		public void setProqg(String proqg) {
			this.proqg = proqg;
		}
		/**
		 * @return the urange
		 */
		public String getUrange() {
			return urange;
		}
		/**
		 * @param urange the urange to set
		 */
		public void setUrange(String urange) {
			this.urange = urange;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "RecMenu [apid=" + apid + ", acid=" + acid + ", price=" + price + ", ispno=" + ispno + ", ipstype="
					+ ipstype + ", province=" + province + ", facevalue=" + facevalue + ", status=" + status
					+ ", acname=" + acname + ", pname=" + pname + ", proqg=" + proqg + ", urange=" + urange + "]";
		}
		

}

package cn.yufu.posp.merchant.web.form;
import cn.yufu.core.web.form.BaseForm;

public class TabBusRoleMenuForm extends BaseForm{

	// Fields
		private String busRoleId;
		private String busRoleName;
		private String menuList;
		/**更新柜员*/
		private String updateOper;
		/**更新日期*/
		private String updateDate;
		/**更新时间*/
		private String updateTime;
		public String getBusRoleId() {
			return busRoleId;
		}
		public void setBusRoleId(String busRoleId) {
			this.busRoleId = busRoleId;
		}
		public String getBusRoleName() {
			return busRoleName;
		}
		public void setBusRoleName(String busRoleName) {
			this.busRoleName = busRoleName;
		}
		public String getMenuList() {
			return menuList;
		}
		public void setMenuList(String menuList) {
			this.menuList = menuList;
		}
		public String getUpdateOper() {
			return updateOper;
		}
		public void setUpdateOper(String updateOper) {
			this.updateOper = updateOper;
		}
		public String getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(String updateDate) {
			this.updateDate = updateDate;
		}
		public String getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}
		
}
package cn.yufu.posp.usermanager.domain.model;

/**
 * AbstractOaConsoleMenuId entity provides the base persistence definition of
 * the OaConsoleMenuId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOaConsoleMenuId implements java.io.Serializable {

	// Fields

	private String menuId;
	private String menuName;
	private String menuUrl;
	private Long menuSort;
	private String menuParentId;
	private String menuType;
	private String menuSysId;

	// Constructors

	/** default constructor */
	public AbstractOaConsoleMenuId() {
	}

	/** minimal constructor */
	public AbstractOaConsoleMenuId(String menuId, String menuName,
			Long menuSort, String menuParentId, String menuType,
			String menuSysId) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuSort = menuSort;
		this.menuParentId = menuParentId;
		this.menuType = menuType;
		this.menuSysId = menuSysId;
	}

	/** full constructor */
	public AbstractOaConsoleMenuId(String menuId, String menuName,
			String menuUrl, Long menuSort, String menuParentId,
			String menuType, String menuSysId) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.menuSort = menuSort;
		this.menuParentId = menuParentId;
		this.menuType = menuType;
		this.menuSysId = menuSysId;
	}

	// Property accessors

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Long getMenuSort() {
		return this.menuSort;
	}

	public void setMenuSort(Long menuSort) {
		this.menuSort = menuSort;
	}

	public String getMenuParentId() {
		return this.menuParentId;
	}

	public void setMenuParentId(String menuParentId) {
		this.menuParentId = menuParentId;
	}

	public String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuSysId() {
		return this.menuSysId;
	}

	public void setMenuSysId(String menuSysId) {
		this.menuSysId = menuSysId;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractOaConsoleMenuId))
			return false;
		AbstractOaConsoleMenuId castOther = (AbstractOaConsoleMenuId) other;

		return ((this.getMenuId() == castOther.getMenuId()) || (this
				.getMenuId() != null
				&& castOther.getMenuId() != null && this.getMenuId().equals(
				castOther.getMenuId())))
				&& ((this.getMenuName() == castOther.getMenuName()) || (this
						.getMenuName() != null
						&& castOther.getMenuName() != null && this
						.getMenuName().equals(castOther.getMenuName())))
				&& ((this.getMenuUrl() == castOther.getMenuUrl()) || (this
						.getMenuUrl() != null
						&& castOther.getMenuUrl() != null && this.getMenuUrl()
						.equals(castOther.getMenuUrl())))
				&& ((this.getMenuSort() == castOther.getMenuSort()) || (this
						.getMenuSort() != null
						&& castOther.getMenuSort() != null && this
						.getMenuSort().equals(castOther.getMenuSort())))
				&& ((this.getMenuParentId() == castOther.getMenuParentId()) || (this
						.getMenuParentId() != null
						&& castOther.getMenuParentId() != null && this
						.getMenuParentId().equals(castOther.getMenuParentId())))
				&& ((this.getMenuType() == castOther.getMenuType()) || (this
						.getMenuType() != null
						&& castOther.getMenuType() != null && this
						.getMenuType().equals(castOther.getMenuType())))
				&& ((this.getMenuSysId() == castOther.getMenuSysId()) || (this
						.getMenuSysId() != null
						&& castOther.getMenuSysId() != null && this
						.getMenuSysId().equals(castOther.getMenuSysId())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		result = 37 * result
				+ (getMenuName() == null ? 0 : this.getMenuName().hashCode());
		result = 37 * result
				+ (getMenuUrl() == null ? 0 : this.getMenuUrl().hashCode());
		result = 37 * result
				+ (getMenuSort() == null ? 0 : this.getMenuSort().hashCode());
		result = 37
				* result
				+ (getMenuParentId() == null ? 0 : this.getMenuParentId()
						.hashCode());
		result = 37 * result
				+ (getMenuType() == null ? 0 : this.getMenuType().hashCode());
		result = 37 * result
				+ (getMenuSysId() == null ? 0 : this.getMenuSysId().hashCode());
		return result;
	}

}
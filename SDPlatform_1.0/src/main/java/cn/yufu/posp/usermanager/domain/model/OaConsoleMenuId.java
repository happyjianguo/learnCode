package cn.yufu.posp.usermanager.domain.model;

/**
 * OaConsoleMenuId entity. @author MyEclipse Persistence Tools
 */
public class OaConsoleMenuId extends AbstractOaConsoleMenuId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OaConsoleMenuId() {
	}

	/** minimal constructor */
	public OaConsoleMenuId(String menuId, String menuName, Long menuSort,
			String menuParentId, String menuType, String menuSysId) {
		super(menuId, menuName, menuSort, menuParentId, menuType, menuSysId);
	}

	/** full constructor */
	public OaConsoleMenuId(String menuId, String menuName, String menuUrl,
			Long menuSort, String menuParentId, String menuType,
			String menuSysId) {
		super(menuId, menuName, menuUrl, menuSort, menuParentId, menuType,
				menuSysId);
	}

}

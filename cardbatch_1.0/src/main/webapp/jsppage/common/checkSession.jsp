<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="com.pay.system.user.dao.UserDao" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/user.tld" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<html>
	<logic:present name="menu_level">
		<bean:define id="menu_level" name="menu_level" />
	</logic:present>
	<logic:notPresent name="menu_level">
		<bean:define id="menu_level" value="" />
	</logic:notPresent>
</html>

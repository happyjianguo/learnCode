<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="taglib.jsp"%>
<div id="header">
    <form class="form-inline" action="${logoutUrl}" method="post">
      <input type="submit" value="Log out" />
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <a href="${ctx }/index"><button type="button">index</button></a>
    </form>
</div>
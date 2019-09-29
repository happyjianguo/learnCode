package cn.yufu.SDMTPlatform.commons.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RemoveSession {
	private static Log log = Log.getLog(RemoveSession.class);

	public static void removeRightSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration e = session.getAttributeNames();
		while (e.hasMoreElements()) {
			String sessionName = (String) e.nextElement();
			if (sessionName.startsWith("modelNo_")) {
				log.info("清除的session：   " + sessionName);
				session.removeAttribute(sessionName);
			}
		}
	}

	public static void removeExitSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration e = session.getAttributeNames();
		while (e.hasMoreElements()) {
			String sessionName = (String) e.nextElement();
			log.info("清除的session：   " + sessionName);
			session.removeAttribute(sessionName);
		}
	}
}

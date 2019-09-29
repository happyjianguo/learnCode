/**
 * MyWebAuthenticationDetails.java
 * 2016年1月20日 上午11:23:00
 * 
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 */
package com.jansh.core.security.web.authentication;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.jansh.core.web.util.AppUtil;

/**
 * @author nie
 *
 */
public class MyWebAuthenticationDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3954043991408086723L;
	private final String remoteAddress;
	private final String sessionId;

	// ~ Constructors
	// ===================================================================================================

	/**
	 * Records the remote address and will also set the session Id if a session
	 * already exists (it won't create one).
	 *
	 * @param request
	 *            that the authentication request was received from
	 */
	public MyWebAuthenticationDetails(HttpServletRequest request) {
//		this.remoteAddress = request.getRemoteAddr();
		
		this.remoteAddress = AppUtil.getRemoteIpAddr(request);

		HttpSession session = request.getSession(false);
		this.sessionId = (session != null) ? session.getId() : null;
	}

	// ~ Methods
	// ========================================================================================================

	public boolean equals(Object obj) {
		if (obj instanceof WebAuthenticationDetails) {
			WebAuthenticationDetails rhs = (WebAuthenticationDetails) obj;

			if ((remoteAddress == null) && (rhs.getRemoteAddress() != null)) {
				return false;
			}

			if ((remoteAddress != null) && (rhs.getRemoteAddress() == null)) {
				return false;
			}

			if (remoteAddress != null) {
				if (!remoteAddress.equals(rhs.getRemoteAddress())) {
					return false;
				}
			}

			if ((sessionId == null) && (rhs.getSessionId() != null)) {
				return false;
			}

			if ((sessionId != null) && (rhs.getSessionId() == null)) {
				return false;
			}

			if (sessionId != null) {
				if (!sessionId.equals(rhs.getSessionId())) {
					return false;
				}
			}

			return true;
		}

		return false;
	}

	/**
	 * Indicates the TCP/IP address the authentication request was received
	 * from.
	 *
	 * @return the address
	 */
	public String getRemoteAddress() {
		return remoteAddress;
	}

	/**
	 * Indicates the <code>HttpSession</code> id the authentication request was
	 * received from.
	 *
	 * @return the session ID
	 */
	public String getSessionId() {
		return sessionId;
	}

	public int hashCode() {
		int code = 7654;

		if (this.remoteAddress != null) {
			code = code * (this.remoteAddress.hashCode() % 7);
		}

		if (this.sessionId != null) {
			code = code * (this.sessionId.hashCode() % 7);
		}

		return code;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		sb.append("RemoteIpAddress: ").append(this.getRemoteAddress()).append("; ");
		sb.append("SessionId: ").append(this.getSessionId());

		return sb.toString();
	}

}

package com.pay.tag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.logic.ConditionalTagBase;
import org.apache.struts.util.RequestUtils;

public class MatchTag extends ConditionalTagBase
{ 
    private String value;
     
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    protected boolean condition(boolean desired)
        throws JspException {
        String variable = null;
        if(cookie != null) {
            Cookie cookies[] = ((HttpServletRequest)pageContext.getRequest()).getCookies();
            if(cookies == null)
                cookies = new Cookie[0];
            for(int i = 0; i < cookies.length; i++) {
                if(!cookie.equals(cookies[i].getName()))
                    continue;
                variable = cookies[i].getValue();
                break;
            }

        } else if(header != null)
            variable = ((HttpServletRequest)pageContext.getRequest()).getHeader(header);
        else if(name != null) {
            Object value = RequestUtils.lookup(pageContext, name, property, scope);
            if(value != null)
                variable = value.toString();
        } else if(parameter != null) {
            variable = pageContext.getRequest().getParameter(parameter);
        } else {
            JspException e = new JspException(ConditionalTagBase.messages.getMessage("logic.selector"));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }
        if(variable == null) {
            JspException e = new JspException(ConditionalTagBase.messages.getMessage("logic.variable", this.value));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }
        boolean matched = false;
        Pattern pat = Pattern.compile(this.value);
        Matcher mat = pat.matcher(variable);
        matched = mat.find();
//        matched = mat.matches();
        return matched == desired;
    }

    protected boolean condition() throws JspException {
        return condition(true);
    }
} 

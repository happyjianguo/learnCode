/*
 * Created on 2006-3-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package cn.yufu.posp.common.common.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ExceptionConfig;


/**
 * @author zhangst1
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OAExceptionHandler extends ExceptionHandler {    
    @Override
	public ActionForward execute(Exception ex,
                                    ExceptionConfig config,
                                    ActionMapping mapping,
                                    ActionForm formInstance,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
    throws ServletException {
        ActionForward forward = null;
        ActionMessage error = null;
        String property = null;
   
        /* Get the path for the forward either from the exception element
         * or from the input attribute.
         */
        String path = null;
        if (config.getPath(  ) != null) {
         path = config.getPath(  );
        }else{
          path = mapping.getInput(  );
        }
        // Construct the forward object
        forward = new ActionForward(path);
   
        /* Figure out what type of exception has been thrown. The Struts
         * AppException is not being used in this example.
         */
        if( ex instanceof OAException) {
          // This is the specialized behavior
            OAException baseException = (OAException)ex;
          String messageKey = baseException.getMessage(  );
            error = new ActionMessage( messageKey,false);

        }else{
          error = new ActionMessage(config.getKey(  ));
          property = error.getKey(  );
        }
   
        // Store the ActionError into the proper scope
        // The storeException method is defined in the parent class
        storeException(request, property, error, forward, config.getScope(  ));   
        return forward;
      }
  }


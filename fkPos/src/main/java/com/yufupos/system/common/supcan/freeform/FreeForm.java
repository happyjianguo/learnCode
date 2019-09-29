/**
* Copyright &copy; 2015 All rights reserved.
 */
package com.yufupos.system.common.supcan.freeform;

import com.yufupos.system.common.supcan.common.Common;
import com.yufupos.system.common.supcan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 硕正FreeForm
 * @author WangZhen
 * @version 2013-11-04
 */
@XStreamAlias("FreeForm")
public class FreeForm extends Common {

	public FreeForm() {
		super();
	}
	
	public FreeForm(Properties properties) {
		this();
		this.properties = properties;
	}
	
}

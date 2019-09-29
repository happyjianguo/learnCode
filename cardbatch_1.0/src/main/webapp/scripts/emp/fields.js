if (!EMP.field.Base) {
	alert('[data.js] must be include ！');	
} 

if (!EMP.field.Text) {

	EMP.field.Text = function() {
	};
	
	EMP.field.Text.prototype = new EMP.field.Base();
	
	EMP.field.Text.prototype._initialize = function() {
		
		this._parseAttribute("maxlength");
		this._parseAttribute("minlength");
		
		this.element = this.tag.getElementsByTagName("INPUT")[0];
			
		if (this.config.maxlength)	
			this.element.setAttribute("maxLength",this.config.maxlength); 
		
		//定义缺省的onblur事件，以校验输入的数据是否合法
		EMPTools.addEvent(this.element, "blur", this._defaultEventHandler, this);
		
	};
	
	EMP.field.Text.prototype._renderHidden = function(hidden) {
		EMP.field.Base.prototype._renderHidden.call(this, hidden);	
		this.element.hidden = hidden;
	};
		
	EMP.field.Text.prototype._renderReadonly = function(readonly) {
		EMP.field.Base.prototype._renderReadonly.call(this, readonly);		
		this.element.readOnly = readonly;
	};
	
	EMP.field.Text.prototype._renderDisabled = function(disabled) {
		EMP.field.Base.prototype._renderDisabled.call(this, disabled);
		this.element.disabled = disabled;		
	};
	
	/**
	 * 默认onblur方法
	 */
	EMP.field.Text.prototype._defaultEventHandler = function() {
		
		//如果什么都没有输入，则不做任何校验，也不显示任何错误信息
		if(this.element.value == null || this.element.value == ""){
			this.clearError();
			return true;
		}
		if(this.config.datatype != null){
			
			var retMsg = EMP.type.Base.checkAndDisplay(this.element.value, this.config.validatejs, this.config.convertorjs, this.config.formaterrormsg, this.config.rangeerrormsg);
			if(retMsg.result == false){
				var errorMsg = retMsg.message;
				if(errorMsg == null || errorMsg == "")
					errorMsg = this.config.formaterrormsg;
				
				this.reportError(errorMsg);
				
				this.focus();
				
				return false;
			}
			
			this.element.value = retMsg.message;
		}
		this.clearError();//在校验通过后，清除原来的错误信息
		
		//进行业务层次的校验
		var ret = this.doCheckBussiness();
		if(ret == false)
			this.focus();
		return ret;
	};
	
	/**
	 * 设置数据域取值
	 * 设置的值可以是输入值、显示值、真实值中任何一种格式
	 * 这里根据李嘉的定义的逻辑，setValue输入值应该为真实值。getValue输出值应该是真实值。
	 * 只有这样才能从一个对象中把真实值get出来存入另一个。显示还能正确modify by xubin
	 * @param value
	 */
	EMP.field.Text.prototype.setValue = function(value) {	
		this.value = value;	
		
		if (this.config.datatype != null) {
			this.element.value = EMP.type.Base.display(this.value, this.config.convertorjs);
		} else {
			this.element.value = this.value;
		}
	};

	/**
	 * 获得数据域取值
	 */
	EMP.field.Text.prototype.getValue = function() {	
		var value = this.element.value;
		if (this.config.datatype != null){
			var retMsg = EMP.type.Base.check(value, this.config.validatejs, this.config.formaterrormsg, this.config.rangeerrormsg);
			if(retMsg.result == false){
				value = null;
			}else{
				value = retMsg.message;
			}
		}
		return value;
	};
	
	EMP.field.Text.prototype.doCheckLength = function() {
		
		var len = EMPTools.getByteLength(this.getValue());
		
		//判断最长输入字符、最短输入字符
		if(this.config.maxlength && len > this.config.maxlength){
			return EMPTools.message(false, EMPTools.parseResource(EMPTools.getResource("outOfMaxLengthInput","{0}输入超过{1}个字节！"),this.title,this.config.maxlength));
		}else if(len != 0 && this.config.minlength && len < this.config.minlength){
			return EMPTools.message(false, EMPTools.parseResource(EMPTools.getResource("outOfMinLengthInput","{0}输入少于{1}个字节！"),this.title,this.config.minlength));
		}
		
		//判断必输项
		if (!this.config.required){
			return EMPTools.message(true);
		} else if (len == 0) {
			return EMPTools.message(false, EMPTools.parseResource(EMPTools.getResource("noInputForRequired","请输入{0}！"),this.title));
		}
		
		return EMPTools.message(true);	
	};
	
	/**
	 * 检查数据类型
	 */
	EMP.field.Text.prototype.doCheckDataType = function() {
		var value = this.element.value;
		if(this.config.datatype != null){
			var retMsg = EMP.type.Base.check(value, this.config.validatejs, this.config.formaterrormsg, this.config.rangeerrormsg);
			return retMsg;
		}
		return EMPTools.message(true);
	};
	
	EMP.field.Text.prototype.focus = function() {
		this.element.focus();
		this.element.select();
	};
};



if (!EMP.field.DynPassword) {

	EMP.field.DynPassword = function() {
	};
	
	EMP.field.DynPassword.prototype = new EMP.field.Text();
	
	EMP.field.DynPassword.prototype._initialize = function() {
		
		this._parseAttribute("maxlength");
		this._parseAttribute("minlength");
		this._parseAttribute("keyBoardTitle");
		this._parseAttribute("keyBoardHeight");
		this._parseAttribute("keyBoardWidth");
		
		this.element = this.tag.getElementsByTagName("INPUT")[0];
		
		if (this.config.maxlength)	
			this.element.setAttribute("maxLength",this.config.maxlength); 
		
		//定义缺省的onblur事件，以校验输入的数据是否合法
		EMPTools.addEvent(this.element, "blur", this._defaultEventHandler, this);
		
		//定义onfocus事件，用于弹出动态键盘
		EMPTools.addEvent(this.element, "click", this._clickEventHandler, this);
	};
	
	EMP.field.DynPassword.prototype._clickEventHandler = function() {
		if(this.keyBoard == null )
			this.keyBoard = new EMP.widget.SoftKeyboard(this);
		this.keyBoard.showKeyboard();
	};
};



if (!EMP.field.AppendPin) {

	EMP.field.AppendPin = function() {
	};
	
	EMP.field.AppendPin.prototype = new EMP.field.Text();
	
	
	EMP.field.AppendPin.prototype._initialize = function() {
		
		this._parseAttribute("maxlength");
		this._parseAttribute("minlength");
		this._parseAttribute("codeLength");
		
		this.element = this.tag.getElementsByTagName("INPUT")[0];
		this.image = this.tag.getElementsByTagName("IMG")[0];
			
		this.element.setAttribute("maxLength",this.config.codelength); 
		
		//定义缺省的onblur事件，以校验输入的数据是否合法
		EMPTools.addEvent(this.element, "blur", this._defaultEventHandler, this);
		
	};
	
	EMP.field.AppendPin.prototype.doCheckLength = function() {
		
		var len = EMPTools.getByteLength(this.getValue());
		
		//判断输入字符长度
		if(len != 0 && len != this.config.codelength){
			return EMPTools.message(false, EMPTools.parseResource(EMPTools.getResource("inputLengthNotEqual","{0}输入必须是{1}个字节!"),this.title,this.config.codelength));
		}
		
		//判断必输项
		if (!this.config.required){
			return EMPTools.message(true);
		} else if (len == 0) {
			return EMPTools.message(false, EMMPTools.parseResource(EMPTools.getResource("noInputForRequired","请输入{0}!"),this.title));
		}
		
		return EMPTools.message(true);	
	};
};
	


if (!EMP.field.Link) {

	EMP.field.Link = function() {
	};
	
	EMP.field.Link.prototype = new EMP.field.Base();
	
	EMP.field.Link.prototype._initialize = function() {
		
		this._parseAttribute("operation");
		this._parseAttribute("target");
		this._parseAttribute("opName");
		
		this.element = this.tag.getElementsByTagName("A")[0];
		
		this.config.href = this.element.href;
		
		if (this.config.operation != null) {
			EMP.util.Tools.addEvent(this.element, "click", this.click, this);
		}
	};	
	
	EMP.field.Link.prototype._renderDisabled = function(disabled) {
		EMP.field.Base.prototype._renderDisabled.call(this, disabled);
		this.element.disabled = this.config.disabled;
		if (disabled)
			this.element.href = "#";
		else
			this.element.href = this.config.href;
	};	
	
	
	EMP.field.Link.prototype.click = function() {
		if (!this.config.disabled) {
			if (this.table) {
				var idx = this.tag.parentNode.parentNode.sectionRowIndex;
				this.table.select(idx, true);
			}
			window['do'+this.config.operation.substring(0,1).toUpperCase()
					+this.config.operation.substring(1)]();
		}
	};
	
	EMP.field.Link.prototype.setValue = function(value) {
		if (this.config.opname == null) {
			EMP.field.Base.prototype.setValue.call(this,value);			
		}
	};
	
	/**
	 * 进行业务层次的校验(Link没有业务层次的校验)
	 */
	EMP.field.Link.prototype.doCheckBussiness = function() {
	
	};
};



if (!EMP.field.MultiLink) {

	EMP.field.MultiLink = function() {
	};
	
	EMP.field.MultiLink.prototype = new EMP.field.Base();
	
	EMP.field.MultiLink.prototype._initialize = function() {
	
		this.elements = this.tag.getElementsByTagName("A");
		this.config.hrefs = new Array();
		for (var i=0;i<this.elements.length;i++) {
			var href = this.elements[i].href;
			this.config.hrefs[i] = href;
			var sharpidx = href.lastIndexOf('#');
			if (sharpidx != -1 && sharpidx != href.length-1) {
				var operation = href.substring(sharpidx+1);
				EMPTools.addEvent(this.elements[i], "click", this.click, this);
			}
		}
		this.config.linkcount = this.elements.length;
	};	
	
	/**
	 * 进行业务层次的校验(MultiLink没有业务层次的校验)
	 */
	EMP.field.MultiLink.prototype.doCheckBussiness = function() {
	
	};
	
	EMP.field.MultiLink.prototype._renderDisabled = function(disabled) {
		EMP.field.Base.prototype._renderDisabled.call(this, disabled);
		for (var i=0; i<this.config.linkcount; i++) {
			this.elements[i].disabled = disabled;
			if (disabled)
				this.elements[i].href = '#';
			else
				this.elements[i].href = this.config.hrefs[i];		
		}
	};
	
	EMP.field.MultiLink.prototype.setDisabled = function(disabled,idx) {
		if (idx == null) {
			this._renderDisabled(disabled);
		} else {
			if (idx >=0 && idx<this.config.linkcount) {
				this.elements[idx].disabled = disabled;
				if (disabled)
					this.elements[idx].href = '#';
				else
					this.elements[idx].href = this.config.hrefs[i];
			}else{
				alert(EMPTools.parseResource(EMPTools.getResource("designatedLinkNotFound","{0}没有第{1}个链接!"),this.title,(idx+1)));
			}
		}
	};
	
	
	EMP.field.MultiLink.prototype.getDisabled = function(idx) {
		if (idx == null) {
			return this.config.disabled;
		} else {
			if (idx >=0 && idx<this.config.linkcount) {
				return this.elements[idx].disabled;
			}else{
				alert(EMPTools.parseResource(EMPTools.getResource("designatedLinkNotFound","{0}没有第{1}个链接!"),this.title,(idx+1)));
				return false;
			}
		}
	};
	
	EMP.field.MultiLink.prototype.click = function(e) {
		
		if (EMP.util.Tools.Browser.ie) {
			var href = e.srcElement.href;
		} else {
			var href = e.target.href;
		}
		var sharpidx = href.lastIndexOf('#');
		var operation = href.substring(sharpidx+1);
		if (operation!='' && !this.config.disabled) {
			if (this.table) {
				var idx = this.tag.parentNode.parentNode.sectionRowIndex;
				this.table.select(idx, true);
			}
			window['do'+operation.substring(0,1).toUpperCase()
					+operation.substring(1)]();
		}

	};
	
	EMP.field.MultiLink.prototype.setValue = function(value) {
		
	};
};


if (!EMP.field.TextArea) {

	EMP.field.TextArea = function() {
	};
	
	EMP.field.TextArea.prototype = new EMP.field.Text();
	
	EMP.field.TextArea.prototype._initialize = function() {
		
		this._parseAttribute("maxlength");
		this._parseAttribute("minlength");
		this._parseAttribute("cols");
		this._parseAttribute("rows");
		
		this.element = this.tag.getElementsByTagName("TEXTAREA")[0];
		
		if (this.config.maxlength){//通过JS的方式来限制TextArea的字数
			//EMPTools.addEvent(this.element, "keypress", this._defaultOnKeyPressEvent, this);
		}
		
		EMPTools.addEvent(this.element, "blur", this._defaultEventHandler, this);
	};
	
	EMP.field.TextArea.prototype._defaultOnKeyPressEvent = function(){
		var len = EMPTools.getByteLength(this.getValue());
		return (len < this.config.maxlength);
	};
	
};

if (!EMP.field.Select) {

	EMP.field.Select = function() {
	};
	
	EMP.field.Select.prototype = new EMP.field.Base();
	
	EMP.field.Select.prototype._initialize = function() {
		
		this._parseAttribute("defMsg");
		
		this.element = this.tag.getElementsByTagName("SELECT")[0];
		this.fakeinput = this.tag.getElementsByTagName("INPUT")[0];
		
		EMPTools.addEvent(this.element, "blur", this._defaultEventHandler, this);
		EMPTools.addEvent(this.element, "change", this._changeValueEventHandler, this);
		
	};

	EMP.field.Select.prototype._renderDisabled = function(disabled) {
		EMP.field.Base.prototype._renderDisabled.call(this, disabled);
		this.element.disabled = disabled;		
	};
	
	EMP.field.Select.prototype.setValue = function(value) {
		
		value = EMPTools.trim(value);
		this.value = value;
		
		this.element.value = value;		
		if (this.element.selectedIndex == -1) {
			this.element.value = "";	//置为"请选择"的情况
			if (!this.config.flat)
				this.fakeinput.value = "";
		} else {
			if (!this.config.flat)
				this.fakeinput.value = this.element.options[this.element.selectedIndex].text;
		}
		
		if(this.relatedSelectDefine && this.relatedSelectDefine.next){
			var nextSelect = this.relatedSelectDefine.next;
			//如果置在"请选择"的选项上，则不需要向后台发起请求，直接将下一联动下拉框的选项清空
			if(this.element.selectedIndex == -1 || this.element.selectedIndex == 0){
				var selectSrc = [];
				this.relatedSelectDefine.group.updateSelectInnerHTML(nextSelect,selectSrc);
			}else{
				this.relatedSelectDefine.group.doInitSelectContent(nextSelect);
			}
		}
	};
	
	EMP.field.Select.prototype.getValue = function() {
		return this.element.value;
	};
	
	EMP.field.Select.prototype.doCheckLength = function() {
		if (!this.config.required) 
			return EMPTools.message(true);
		if (this.element.value == null || this.element.value == "") {
			return EMPTools.message(false, EMPTools.parseResource(EMPTools.getResource("noOptionSelected","请选择一项作为{0}！"), this.title));
		}
		return EMPTools.message(true);	
	};
	
	
	/**
	 * 默认onblur方法
	 */
	EMP.field.Select.prototype._defaultEventHandler = function() {
		
		this.clearError();//先清除原来的错误信息
		
		//如果什么都没有输入，则不做任何校验，也不显示任何错误信息
		if(this.element.value == null || this.element.value == ""){
			return true;
		}
		
		//只进行业务层次的校验
		var ret = this.doCheckBussiness();
		return ret;
	};
	
	/**
	 * 只适合于联动下拉框情况
	 */
	EMP.field.Select.prototype._changeValueEventHandler = function() {
		if(this.relatedSelectDefine && this.relatedSelectDefine.next){
			this.setValue(this.getValue());
		}
	};
};


if (!EMP.field.Date) {

	EMP.field.Date = function() {
	};
	
	EMP.field.Date.prototype = new EMP.field.Text();
	
	EMP.field.Date.prototype._initialize = function() {

		this.element = this.tag.getElementsByTagName("INPUT")[0];
		
		EMPTools.addEvent(this.element, "blur", this._defaultEventHandler, this);
		
		if (!Calendar)
			alert(EMPTools.getResource("noCalendarConponentFound","未包含Calendar.js，日期域不可用，请检查页面编辑内容！"));
		else
			EMPTools.addEvent(this.element, "click", this._openCalendar, this);
	};
	
	EMP.field.Date.prototype._openCalendar = function() {
		//只读或不可操作的情况下，不弹出日期选择框
		if(this.config.readonly || this.config.disabled)
			return;
		if (page.calendarObj == null) {
			page.calendarObj = new Calendar("page.calendarObj");
			var tempDiv = document.createElement("DIV");
			document.body.appendChild(tempDiv);
			tempDiv.innerHTML += page.calendarObj;	
		}
        page.calendarObj.show(this.element);
	};
	
};

if (!EMP.field.Pop) {

	EMP.field.Pop = function() {
	};
	
	EMP.field.Pop.prototype = new EMP.field.Text();
	
	EMP.field.Pop.prototype._initialize = function() {
		
		this._parseAttribute("url");
		this._parseAttribute("returnMethod");
		this._parseAttribute("popParam");
		this._parseAttribute("dataMapping");
		
		this.element = this.tag.getElementsByTagName("INPUT")[0];
		this.button = this.tag.getElementsByTagName("BUTTON")[0];
		
		EMPTools.addEvent(this.element, "blur", this._defaultEventHandler, this);
		
		var popFunc = function () {
			
			var returnMethod = this.dataName+"._obj.dataMappingHandler";
			var url = EMPTools.setParam(this.config.url,"popReturnMethod",returnMethod);
			
			EMPTools.openWindow(url, this.id, this.config.popparam);
		}
		EMPTools.addEvent(this.button,"click", popFunc, this);
	};
	
	EMP.field.Pop.prototype.dataMappingHandler = function(data) {
		
		this.clearError();//先清除原来的错误信息
		
		if(this.config.datamapping){
			var mappings = this.config.datamapping.split(";");
			for(var i=0; i<mappings.length; i++){
				var mappingStr = mappings[i];
				var idx = mappingStr.indexOf("=");
				if(idx == -1)
					continue;
				var targetObj = mappingStr.substring(0,idx);
				var srcObj = mappingStr.substring(idx+1);
				eval(targetObj+"._obj.setValue(data."+srcObj+")");
			}
		}
		//执行自定义的返回方法
		if(this.config.returnmethod)
			eval(this.config.returnmethod+"(data)");
		
		//进行业务层次的校验
		this.doCheckBussiness();
	};
	
	/**
	 * 默认onblur方法
	 */
	EMP.field.Pop.prototype._defaultEventHandler = function() {
	
		this.clearError();//先清除原来的错误信息
		
		//如果什么都没有输入，则不做任何校验，也不显示任何错误信息
		if(this.element.value == null || this.element.value == ""){
			return true;
		}
		
		//只进行业务层次的校验
		var ret = this.doCheckBussiness();
		return ret;
	};
	
};


if (!EMP.field.Radio) {

	EMP.field.Radio = function() {
	};
	
	EMP.field.Radio.prototype = new EMP.field.Base();
	
	EMP.field.Radio.prototype._initialize = function() {
		this.elements = this.tag.getElementsByTagName("INPUT");//this.element是一个INPUT框的集合
		for (var i=0;i<this.elements.length;i++) {
			EMPTools.addEvent(this.elements[i], "click", this.click, this);
		}
		this.isCheckColumn = false;//缺省不作为列表的单、复选列
	};
	
	EMP.field.Radio.prototype.click = function() {
	
		this.clearError();//先清除原来的错误信息
		
		//只进行业务层次的校验
		var ret = this.doCheckBussiness();
		return ret;
	};
	
	
	EMP.field.Radio.prototype.setValue = function(value) {
		
		this.value = value;
		
		for(var i=0; i<this.elements.length; i++){
			this.elements[i].checked = false;
			if(this.elements[i].value == value){
				this.elements[i].checked = true;
			}
		}
	};
	
	EMP.field.Radio.prototype.getValue = function() {
		
		var value = "";
		for(var i=0; i<this.elements.length; i++) {
			if(this.elements[i].checked == true){
				value = this.elements[i].value;
				break;
			}
		}
		return value;
	};
	
	EMP.field.Radio.prototype.doCheckLength = function() {
		if (!this.config.required) 
			return EMPTools.message(true);
		for(var i=0;i<this.elements.length;i++){
			if(this.elements[i].checked)
				return EMPTools.message(true);
		}
		return EMPTools.message(false, EMPTools.parseResource(EMPTools.getResource("noOptionSelected","请选择一项作为{0}！"),this.title));
	};
	
	EMP.field.Radio.prototype._renderDisabled = function(disabled) {
		EMP.field.Base.prototype._renderDisabled.call(this, disabled);
		for(var i=0;i<this.elements.length;i++){
			this.elements[i].disabled = disabled;
		}
	};
};


if (!EMP.field.CheckBox) {

	EMP.field.CheckBox = function() {
	};
	
	EMP.field.CheckBox.prototype = new EMP.field.Radio();
	
	EMP.field.CheckBox.prototype._initialize = function() {
		
		this._parseAttribute("valueCollection");
		var sao=this.tag.getAttribute("selectAllOption");
		//var checkboxname=this.tag.getAttribute("id").substring(10);
		//this.elements = this.tag.getElementsByTagName("INPUT");//this.element是一个INPUT框的集合
		var els=this.tag.getElementsByTagName("INPUT");
		this.elements = new Array();
		for (var i=0;i<els.length;i++) {
			if(i==0&&sao=="true"){
				continue;
			}
			this.elements.push(els[i]);
			EMPTools.addEvent(els[i], "click", this.click, this);
		}
		this.isCheckColumn = false;//缺省不作为列表的单、复选列
	};
	
	EMP.field.CheckBox.prototype.setValue = function(valueList) {
		
		if(valueList == null){
			valueList = new Array();
		}
		this.value = valueList;
		
		for(var i=0; i<this.elements.length; i++){
			this.elements[i].checked = false;
			for(var j=0; j<valueList.length; j++){
				if(this.elements[i].value == valueList[j]){
					this.elements[i].checked = true;
					break;
				}
			}
		}
	};
	
	EMP.field.CheckBox.prototype.getValue = function() {
		
		var valueList = new Array();
		for(var i=0; i<this.elements.length; i++) {
			if(this.elements[i].checked == true){
				valueList.push(this.elements[i].value);
			}
		}
		return valueList;
	};
	
	
	/**
	 * 将该数据域的取值复制到指定表单
	 * @param form 指定表单DOM对象
	 */
	EMP.field.CheckBox.prototype.toForm = function(form, prefix, parentAppend) {
		if (form == null) 
			return;
		var name = this.dataName;
		if (prefix!=null) 
			name = prefix + name;
			
		var parent = parentAppend;
		if(!parent)
			parent = form;
		
		var valueList = this.getValue();
		
		if(this.config.valuecollection == null){//将多选框当作一个数据对象看待
			var value = "";
			for(var i=0; i<valueList.length; i++){
				if(i == 0)
					value = valueList[i];
				else
					value = value + "," + valueList[i];
			}
			
			var input = null;
			for(var i in form){
				if(form[i] && form[i].name == name){
					input = form[i];
					break;
				}
			}
			
			//若form中没有同名的input，则新建一个隐藏的input放在form中
			if(input == null){
				input = document.createElement("input");
				input.type="hidden";
				input.name = name;
				parent.appendChild(input);
			}
			if(value != null)
				input.value = value;
			else
				input.value = "";
			
			EMP.util.Tools.log('EMP.field.CheckBox',0
						,'数据域['+name+']值['+value+']被复制到表单['+form.id+'].');
		} else {//将多选框的数据当作列表数据进行提交
			//TODO 将集合中的数据域名称都统一成enname
			
			if(valueList.length == 0)
				return;
			
			var valueStr = '<iColl id="'+this.config.valuecollection+'">';;
			for(var i=0; i<valueList.length; i++){
				var value = valueList[i];
				valueStr += '<kColl><field id="enname" value="'+value+'"/></kColl>';
			}
			valueStr += '</iColl>';
			
			var input = null;
			for(var i in form){
				if(form[i] && form[i].name == "_xmlFormData"){
					input = form[i];
					break;
				}
			}
			if(input == null){
				input = document.createElement("input");
				input.type="hidden";
				input.name = "_xmlFormData";
				parent.appendChild(input);
			}
			
			EMPTools.addXMLFormData(input, valueStr);
		}
	};
};



/**
 * 用于普通输入框的区间查询
 */
if (!EMP.field.TextSpace) {

	EMP.field.TextSpace = function() {
	};
	
	EMP.field.TextSpace.prototype = new EMP.field.Text();
	
	EMP.field.TextSpace.prototype._initialize = function() {
		
		this._parseAttribute("maxlength");
		this._parseAttribute("minlength");
		
		this.element_begin = this.tag.getElementsByTagName("INPUT")[0];
		this.element_end = this.tag.getElementsByTagName("INPUT")[1];
		
		if (this.config.maxlength){
			this.element_begin.setAttribute("maxLength",this.config.maxlength); 
			this.element_end.setAttribute("maxLength",this.config.maxlength); 
		}
		
		EMP.util.Tools.addEvent(this.element_begin, "blur", this._defaultEventHandler, this);
		EMP.util.Tools.addEvent(this.element_end, "blur", this._defaultEventHandler, this);
		
	};
	
	
	/**
	 * 判断输入区间是否正确
	 */
	EMP.field.TextSpace.prototype.doCheckLength = function() {
		
	};
	
	/**
	 * 进行业务层次的校验(区间查询没有业务层次的校验)
	 */
	EMP.field.TextSpace.prototype.doCheckBussiness = function() {
	
	};
	
	
	/**
	 * 默认onblur方法
	 * 检查数据类型，并调用自定义onblur方法(若存在)
	 */
	EMP.field.TextSpace.prototype._defaultEventHandler = function(e) {		//事件方法参数	
		
		var el = EMP.util.Tools.Browser.ie?e.srcElement:e.target;//IE与firefox对事件处理不同
		
		//如果什么都没有输入，则不做任何校验，也不显示任何错误信息
		if(el.value == null || el.value == ""){
			this.clearError();
			return true;
		}
		
		if(this.config.datatype != null){
			var retMsg = EMP.type.Base.checkAndDisplay(el.value, this.config.validatejs, this.config.convertorjs, this.config.formaterrormsg, this.config.rangeerrormsg);
			if(retMsg.result == false){
				var errorMsg = retMsg.message;
				if(errorMsg == null || errorMsg == "")
					errorMsg = this.config.formaterrormsg;
				
				this.reportError(errorMsg);
				this.focus();
				return false;
			}
			el.value = retMsg.message;
		}
		
		//判断区间(必须是后面输入框的内容大于前面输入框的内容)
		var valueObj = this.getValue();
		if(valueObj.begin && valueObj.end){
			var beginValue = parseFloat(valueObj.begin);
			var endValue = parseFloat(valueObj.end);
			if(!isNaN(beginValue) && !isNaN(endValue)){
				if(beginValue > endValue){
					this.reportError(EMPTools.parseResource(EMPTools.getResource("outOfRangeInput","{0}输入的区间错误！"), this.title));
					return false;
				}
			} else if(valueObj.begin > valueObj.end){
				this.reportError(EMPTools.parseResource(EMPTools.getResource("outOfRangeInput","{0}输入的区间错误！"), this.title));
				return false;
			}
		}
		
		this.clearError();//在校验通过后，清除原来的错误信息
		
		return true;
	};
	
	/**
	 * 检查数据类型
	 */
	EMP.field.TextSpace.prototype.doCheckDataType = function() {
		if(this.config.datatype != null){
			//校验前面的输入框
			var retMsg = EMP.type.Base.check(this.element_begin.value, this.config.validatejs, this.config.formaterrormsg, this.config.rangeerrormsg);
			if(retMsg.result == false){
				this.focus(this.element_begin);//焦点在前面的输入框
				return retMsg;
			}
			
			//校验后面的输入框
			retMsg = EMP.type.Base.check(this.element_end.value, this.config.validatejs, this.config.formaterrormsg, this.config.rangeerrormsg);
			if(retMsg.result == false){
				this.focus(this.element_end);//焦点在后面的输入框
				return retMsg;
			}
		}
		return EMPTools.message(true);
	};
	
	/**
	 * 数据域取值
	 */
	EMP.field.TextSpace.prototype.setValue = function(valueObj) {	
		this.value = valueObj;	
		
		if (this.config.datatype!=null) {
			//设置前一个输入框的值
			this.element_begin.value = EMP.type.Base.display(this.value.begin, this.config.convertorjs);
			//设置后一个输入框的值
			this.element_end.value = EMP.type.Base.display(this.value.end, this.config.convertorjs);
		} else {
			this.element_begin.value = valueObj.begin;
			this.element_end.value = valueObj.end;
		}
	};

	/**
	 * 获得数据域取值
	 */
	EMP.field.TextSpace.prototype.getValue = function() {	
		var valueObj = {};
		if (this.config.datatype!=null){
			//取得前一个输入框的值
			var retMsg = EMP.type.Base.check(this.element_begin.value, this.config.validatejs, this.config.formaterrormsg, this.config.rangeerrormsg);
			if(retMsg.result == true){
				valueObj.begin = retMsg.message;
			}
			//取得后一个输入框的值
			retMsg = EMP.type.Base.check(this.element_end.value, this.config.validatejs, this.config.formaterrormsg, this.config.rangeerrormsg);
			if(retMsg.result == true){
				valueObj.end = retMsg.message;
			}
		} else{
			valueObj.begin = this.element_begin.value;
			valueObj.end = this.element_end.value;
		}
		return valueObj;
	};
	
	EMP.field.TextSpace.prototype._renderHidden = function() {
		EMP.field.Base.prototype._renderHidden.call(this);		
	};
	
	EMP.field.TextSpace.prototype._renderReadonly = function() {
		EMP.field.Base.prototype._renderReadonly.call(this);		
		this.element_begin.readOnly = this.config.readonly;
		this.element_end.readOnly = this.config.readonly;
	};
	
	EMP.field.TextSpace.prototype._renderDisabled = function() {
		EMP.field.Base.prototype._renderDisabled.call(this);
		this.element_begin.disabled = this.config.disabled;	
		this.element_end.disabled = this.config.disabled;	
	};
	
	EMP.field.TextSpace.prototype.focus = function(element) {	
		if(!element)
			return;
		element.focus();
		element.select();
	};
	
	
	/**
	 * 将该数据域的取值复制到指定表单
	 * @param form 指定表单DOM对象
	 */
	EMP.field.TextSpace.prototype.toForm = function(form, prefix, parentAppend) {
		if (form == null) 
			return;
		var name = this.dataName;
		if (prefix!=null) 
			name = prefix + name;
		
		var parent = parentAppend;
		if(!parent)
			parent = form;
		
		var valueObj = this.getValue();//取值
		
		//先toForm前一个输入框
		var value = valueObj.begin;
		var inputName = name + "_begin";
		
		var input = null;
		for(var i in form){
			if(form[i] && form[i].name == inputName){
				input = form[i];
				break;
			}
		}
		if(input == null){
			input = document.createElement("input");
			input.type="hidden";
			input.name = inputName;
			parent.appendChild(input);
		}
		if(value != null)
			input.value = value;
		else
			input.value = "";
		
		EMP.util.Tools.log('EMP.field.TextSpace',0
					,'数据域['+inputName+']值['+value+']被复制到表单['+form.id+'].');
			
		//然后toForm后一个输入框
		value = valueObj.end;
		inputName = name + "_end";
		for(var i in form){
			if(form[i] && form[i].name == inputName){
				input = form[i];
				break;
			}
		}
		if(input == null){
			input = document.createElement("input");
			input.type="hidden";
			input.name = inputName;
			parent.appendChild(input);
		}
		if(value != null)
			input.value = value;
		else
			input.value = "";
		
		EMP.util.Tools.log('EMP.field.TextSpace',0
					,'数据域['+inputName+']值['+value+']被复制到表单['+form.id+'].');
	};
};



/**
 * 用于日期类型的区间查询
 */
if (!EMP.field.DateSpace) {

	EMP.field.DateSpace = function() {
	};
	
	EMP.field.DateSpace.prototype = new EMP.field.TextSpace();
	
	EMP.field.DateSpace.prototype._initialize = function() {
		this.element_begin = this.tag.getElementsByTagName("INPUT")[0];
		this.element_end = this.tag.getElementsByTagName("INPUT")[1];
		
		EMP.util.Tools.addEvent(this.element_begin, "blur", this._defaultEventHandler, this);
		EMP.util.Tools.addEvent(this.element_end, "blur", this._defaultEventHandler, this);
		
		if (!Calendar)
			alert(EMPTools.getResource("noCalendarConponentFound","未包含Calendar.js，日期域不可用，请检查页面编辑内容！"));
		else{
			EMP.util.Tools.addEvent(this.element_begin, "click", this._openCalendar, this);
			EMP.util.Tools.addEvent(this.element_end, "click", this._openCalendar, this);
		}
	};
	
	EMP.field.DateSpace.prototype._openCalendar = function(e) {
		//只读情况下，不弹出日期选择框
		if(this.config.readonly || this.config.disabled)
			return;
		
		var el=EMP.util.Tools.Browser.ie?e.srcElement:e.target;//IE与firefox对事件处理不同
		
		if (page.calendarObj == null) {
			page.calendarObj = new Calendar("page.calendarObj");
			var tempDiv = document.createElement("DIV");
			document.body.appendChild(tempDiv);
			tempDiv.innerHTML+=page.calendarObj;	
		}
        page.calendarObj.show(el);
	};
};

function doSelAll(me) {
	var field=document.getElementsByName(me.value);
	for (i = 0; i < field.length; i++) {
		if(me.checked)
			field[i].checked =true;
		else
			field[i].checked =false;				
	}	
};





<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<fmt:setBundle basename="MyResource"/>

<script>
	var maxCount = 5;
	var attaIdx = 0;
	var IsIE;
	
	function fInitMSIE()
	{
		if (navigator.userAgent.indexOf("MSIE") != -1){
			IsIE = true;
		}else{
			IsIE = false;
		}
	}
	fInitMSIE();
	
	function add() {
	
		if(attaIdx >= maxCount)
			return false;
		addfile("idfilespan",attaIdx);
		attaIdx++;
		return false;
	}
	
	
	
	function addfile(spanId,index)
	{
	       var strIndex = "" + index;
		   var fileId = "attachFile("+ strIndex+")";
		   var brId = "idAttachBr" + strIndex;
		   addInputFile(spanId,fileId);
	
		   adddel(spanId,index);
	
		   addbr(spanId,brId);
		   return;
	}
	
	function addInputFile(spanId,fileId)
	{
		  var span = document.getElementById(spanId);
		  if ( span !=null ) {
		                if ( !IsIE ) {
							var fileObj = document.createElement("input");
							if ( fileObj != null ) {
								fileObj.type="file";
								fileObj.name = fileId;
								fileObj.id = fileId;
								fileObj.size="45";
								var clickEvent = "exist('" + fileId + "')";
								fileObj.setAttribute("onclick",clickEvent,0);  
								span.appendChild(fileObj);
							}
						}
	
						if ( IsIE ) {
							var fileTag = "<input type='file' id ='" + fileId + "' name='" + fileId + "' size=30 >";
							var fileObj = document.createElement(fileTag); 
							span.appendChild(fileObj);
						}
				
		  }
	}
	
	function addbr(spanId,brId)
	{
		  var span = document.getElementById(spanId);
		  if ( span !=null ) {
				var brObj = document.createElement("br");
				if ( brObj !=null ) {
					brObj.name = brId;
					brObj.id = brId;
					span.appendChild(brObj);
	            }
	     }
		 return;
	}
	
	function adddel(spanId,index)
	{
	      var strIndex = "" + index;
		  var delId = "idAttachOper(" + strIndex + ")";
		  var span = document.getElementById(spanId);
		  if ( span != null ) {
				var oTextNode = document.createElement("SPAN");
				oTextNode.style.width = "5px";
				span.appendChild(oTextNode);
			    if ( IsIE ) {
		        var tag = "<input type='button' style='height:23px;padding-top:1px;' id='" + delId + "' onclick=delfile('" + spanId + "',"+strIndex+")></input>";
				var delObj = document.createElement(tag);
				if ( delObj != null ) {
					span.appendChild(delObj);
				}
	
				}
				
		        if ( !IsIE ) {
					var delObj = document.createElement("input");
					if ( delObj != null ) {
						delObj.name = delId;
						delObj.id = delId;
						delObj.type = "button";
						var clickEvent = "return delfile('" + spanId + "',"+strIndex+");";
						delObj.setAttribute("onclick",clickEvent);  
						span.appendChild(delObj);
					}
				}
				if( delObj != null) delObj.value = " 删 除 ";
			}
			return;
	}
	
	
	function delfile(spanId,index)
	{
		   var strIndex = "" + index;
		   var fileId = "attachfile("+ strIndex + ")";
		   var brId = "idAttachBr" + strIndex;
		   var delId = "idAttachOper(" + strIndex + ")";
	
	       var span = document.getElementById(spanId);
	
		   if ( span == null ) return false;
	
		   var fileObj = document.getElementById(fileId);
		   if ( fileObj == null ) return false;
	
		   var brObj = document.getElementById(brId);
		   if ( brObj ==null ) return false;
	
		   var delObj = document.getElementById(delId );
	
		   if ( delObj == null ) return false;
	
	
		   var temp= document.createElement("SPAN");
	
		    span.replaceChild(temp,fileObj);
			span.replaceChild(temp,brObj);
	
			span.removeChild(delObj.previousSibling);
			var attach = document.getElementById("attach");
			if(span.getElementsByTagName("INPUT").length == 0) attach.childNodes[0].nodeValue='新增附件';
			
			span.replaceChild(temp,delObj);	
			attaIdx--;
			
			return false;
	}			
</script>
<span id="idfilespan"></span>
<img align="absmiddle" border=0 name="imageField322" src="<c:out value="${pageContext.request.contextPath}" />/<fmt:message key='CommonImagePath' />icoAdd.gif" alt="新增附件">
<FONT STYLE="cursor:hand" onMouseOver="this.color='red'" onMouseOut="this.color='black'" onclick="add();this.childNodes[0].nodeValue='继续新增';">新增附件</FONT> 

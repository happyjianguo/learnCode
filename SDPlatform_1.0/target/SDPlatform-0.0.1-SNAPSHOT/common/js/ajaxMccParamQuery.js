
var xmlHttp;
        var completeDiv;
        var inputField;
        var nameTable;
        var nameTableBody;

        function createXMLHttpRequest() {
			//alert("create");
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            else if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();                
            }
        }

        function initMccParam() {
//        	alert('initMccParam');
            inputField = document.getElementById("mcc");            
//            mccMark = document.getElementById("mccMark");            
            nameTable = document.getElementById("name_table");
            completeDiv = document.getElementById("popup");
            nameTableBody = document.getElementById("name_table_body");
        }

        function findMccParam() {
            initMccParam();
//            if (inputField.value.length > 0) {                                                                          
                createXMLHttpRequest();   
                var url = "merchant.do?method=findMccName&mcc="+escape(inputField.value)+"";                        
                xmlHttp.open("GET", url, true);
                xmlHttp.onreadystatechange = callbackmcc;
                xmlHttp.send(null);
//            } else {
//                clearNames();
//                popup.style.display = "none";
//            }
        }

        function callbackmcc() {
            if (xmlHttp.readyState == 4) {
//				alert("MccParam-------xmlHttp.readyState == 4");
                if (xmlHttp.status == 200) {
//					alert("MccParam-------qqqqqqqqqqqqq---");
                   // var name = xmlHttp.responseXML.getElementsByTagName("param")[0].firstChild.data;
//                    alert("∑µªÿ÷µ£∫"+name);
                	var params = xmlHttp.responseXML.getElementsByTagName("param");
                	if(params.length<=0){
                		popup.style.display = "none";
                	}else{
	                    setParams(params);
	                    popup.style.display = "block";
                	}
                } else if (xmlHttp.status == 204){
                    clearNames();
                }else{
					clearNames();
					popup.style.display = "none";
//					mccMark.value = "N" ;
				}
				
            }
        }
        
        function setParams(the_names) { 
		        
            clearParams();
            var size = the_names.length;
			//alert("size="+size);
			//alert(nameTableBody.style.height);
            setOffsetsMcc();

            var row, cell, txtNode;
            for (var i = 0; i < size; i++) {
                var nextNode = the_names[i].firstChild.data;
                row = document.createElement("tr");
                cell = document.createElement("td");
                
                cell.onmouseout = function() {this.className='mouseOver';};
                cell.onmouseover = function() {this.className='mouseOut';};
                cell.setAttribute("bgcolor", "#FFFAFA");
                cell.setAttribute("border", "0");
                cell.onmousedown = function() { populateParam(this); popup.style.display = "none";} ;                             

                txtNode = document.createTextNode(nextNode);
                cell.appendChild(txtNode);
                row.appendChild(cell);
                nameTableBody.appendChild(row);
            }
        }

        function setOffsetsMcc() {
            var end = inputField.offsetWidth;
            var left = calculateOffsetLeftmcc(inputField);
            var top = calculateOffsetTopMcc(inputField) + inputField.offsetHeight;

            completeDiv.style.border = "black 1px solid";
            completeDiv.style.left = left + "px";
            completeDiv.style.top = top + "px";
			completeDiv.style.height = 200+"px";
			completeDiv.style.width = 195+"px";
			
			completeDiv.style.zIndex="9999";
			completeDiv.style.backgroundColor= "#FFFAFA";
			
			completeDiv.style.overflow="auto";
			document.getElementById("aa").style.display="none";
			document.getElementById("bb").style.display="none";
			
			
            nameTable.style.width = end + "px";
        }
        
        function calculateOffsetLeftmcc(field) {
          return calculateOffsetMcc(field, "offsetLeft");
        }

        function calculateOffsetTopMcc(field) {
          return calculateOffsetMcc(field, "offsetTop");
        }

        function calculateOffsetMcc(field, attr) {
          var offset = 0;
          while(field) {
            offset += field[attr]; 
            field = field.offsetParent;
          }
          return offset;
        }

        function populateParam(cell) {
			var allValue = cell.firstChild.nodeValue ;
			var intPos = allValue.indexOf("-");
			
			var mccParam=cell.firstChild.nodeValue.substr(0,intPos);
			inputField.value = mccParam;
//			mccMark.value = "Y" ;
            //inputField.value = cell.firstChild.nodeValue;
            
            document.getElementById("aa").style.display="block";
            document.getElementById("bb").style.display="block";
            
            clearNames();
            
        }

        function clearParams() {
        //alert("111111111111");
            var ind = nameTableBody.childNodes.length;
            for (var i = ind - 1; i >= 0 ; i--) {
                 nameTableBody.removeChild(nameTableBody.childNodes[i]);
            }
            completeDiv.style.border = "none";
            document.getElementById("aa").style.display="block";
            document.getElementById("bb").style.display="block";
        }
        
        function closeDiv() {
        	if(document.getElementById("divflag").value != "Y"){
        		popup.style.display = "none";
        	}
        }
        
        function onmouseDiv() {
        	document.getElementById("divflag").value="Y" ;
        }
        function onmouseoutDiv() {
        	document.getElementById("divflag").value="N" ;
        }

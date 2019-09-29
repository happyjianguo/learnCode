
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
           // alert(xmlHttp);
        }

        function initVars() {
        	//alert('initVars is begin');
            inputField = document.getElementById("signBankId");            
            inputHostId = document.getElementById("signHostId");            
            nameTable = document.getElementById("name_table");
            completeDiv = document.getElementById("popup");
            nameTableBody = document.getElementById("name_table_body");
           // alert('initVars is over');
        }

        function findNames() {
            initVars();
           // alert("this is findNames(),and the value is :"+inputField.value);
           // alert("inputField.value.length="+inputField.value.length);
//            if (inputField.value.length > 0) {                                                                          
                createXMLHttpRequest();   
               //alert("11111111111111") ;
                var url = "merchant.do?method=findBankandHost&signBankId="+escape(inputField.value)+"";  
                //alert("url:"+url);
                xmlHttp.open("GET", url, true);
                xmlHttp.onreadystatechange = callbackSi;
                xmlHttp.send(null);
//            } else {
//                clearNames();
//                popup.style.display = "none";
//				inputHostId.value = "" ;
//            }
        }

        function callbackSi() {
        //alert("this is callback");
            if (xmlHttp.readyState == 4) {
				//alert("xmlHttp.readyState == 4");
                if (xmlHttp.status == 200) {
					//alert("xmlHttp.status == 200");
                	var params = xmlHttp.responseXML.getElementsByTagName("name");
                	if(params.length<=0){
                		popup.style.display = "none";
                	}else{
	                    setNames(params);
	                    popup.style.display = "block";
                	}
                } else if (xmlHttp.status == 204){
                    clearNames();
                }else{
					clearNames();
					popup.style.display = "none";
					inputHostId.value = "" ;
				}
				
            }
        }
        
        function setNames(the_names) { 
		    //alert("this is setNames");
		    
            clearNames();
            var size = the_names.length;
			//alert("size="+size);
			//alert(nameTableBody.style.height);
            setOffsets();

            var row, cell, txtNode;
            for (var i = 0; i < size; i++) {
                var nextNode = the_names[i].firstChild.data;
                row = document.createElement("tr");
                cell = document.createElement("td");
                
                cell.onmouseout = function() {this.className='mouseOver';};
                cell.onmouseover = function() {this.className='mouseOut';};
                cell.setAttribute("bgcolor", "#FFFAFA");
                cell.setAttribute("border", "0");
                cell.onmousedown = function() { populateName(this); 
                							popup.style.display = "none"; 
                							inputField.focus() ;
                							} ;                             

                txtNode = document.createTextNode(nextNode);
                cell.appendChild(txtNode);
                row.appendChild(cell);
                nameTableBody.appendChild(row);
            }
        }

        function setOffsets() {
            var end = inputField.offsetWidth;
            var left = calculateOffsetLeft(inputField);
            var top = calculateOffsetTop(inputField) + inputField.offsetHeight;

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
			
			
            nameTable.style.width = end+end + "px";
        }
        
        function calculateOffsetLeft(field) {
          return calculateOffset(field, "offsetLeft");
        }

        function calculateOffsetTop(field) {
          return calculateOffset(field, "offsetTop");
        }

        function calculateOffset(field, attr) {
          var offset = 0;
          while(field) {
            offset += field[attr]; 
            field = field.offsetParent;
          }
          return offset;
        }

        function populateName(cell) {
			var allValue = cell.firstChild.nodeValue ;
			var intPos = allValue.indexOf("-");
			

			var bankId=cell.firstChild.nodeValue.substr(0,intPos);
			var hostId=cell.firstChild.nodeValue.substr(intPos+1,2);
			
			inputField.value = bankId;
			inputHostId.value = hostId;
            //inputField.value = cell.firstChild.nodeValue;
            
            document.getElementById("aa").style.display="block";
            document.getElementById("bb").style.display="block";
            
            clearNames();
            
        }

        function clearNames() {
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

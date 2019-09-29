
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

        function initVars() {
            inputField = document.getElementById("names");            
            nameTable = document.getElementById("name_table");
            completeDiv = document.getElementById("popup");
            nameTableBody = document.getElementById("name_table_body");
        }

        function findNames() {
            initVars();
			//alert("11111111111");
            if (inputField.value.length > 0) {                                                                          
                createXMLHttpRequest();   
               
                var url = "ajaxcorpornoAction.do?op=findno&queryValue="+escape(inputField.value)+"";                        
                xmlHttp.open("GET", url, true);
                xmlHttp.onreadystatechange = callback;
                xmlHttp.send(null);
            } else {
                clearNames();
            }
        }

        function callback() {
            if (xmlHttp.readyState == 4) {
				//alert("xmlHttp.readyState == 4");
                if (xmlHttp.status == 200) {
					//alert("qqqqqqqqqqqqq---");
                    var name = xmlHttp.responseXML.getElementsByTagName("name")[0].firstChild.data;
					
					//var no=xmlHttp.responseXML.getElementsByTagName("no")[0].firstChild.data;
					//alert(no);
					//alert(name);
					
                    setNames(xmlHttp.responseXML.getElementsByTagName("name"));
                } else if (xmlHttp.status == 204){
                    clearNames();
                }else{
					clearNames();
				}
				
            }
        }
        
        function setNames(the_names) { 
		        
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
                cell.onclick = function() { populateName(this); } ;                             

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
			
			completeDiv.style.zIndex="9999";
			completeDiv.style.backgroundColor= "#FFFAFA";
			
			completeDiv.style.overflow="auto";
			
			document.getElementById("aa").style.display="none";
			document.getElementById("bb").style.display="none";
			document.getElementById("cc").style.display="none";
			document.getElementById("dd").style.display="none";
			
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
			
			var no=cell.firstChild.nodeValue.substr(0,15);
			//alert(no);
			inputField.value = no;
            //inputField.value = cell.firstChild.nodeValue;
            clearNames();
            document.getElementById("aa").style.display="block";
            document.getElementById("bb").style.display="block";
            document.getElementById("cc").style.display="block";
            document.getElementById("dd").style.display="block";
            callRefresh('1','')
        }

        function clearNames() {
        //alert("111111111111");
            var ind = nameTableBody.childNodes.length;
            for (var i = ind - 1; i >= 0 ; i--) {
                 nameTableBody.removeChild(nameTableBody.childNodes[i]);
            }
            completeDiv.style.border = "none";
            
            document.getElementById("aa").style.display="block";
            document.getElementById("bb").style.display="block";
            document.getElementById("cc").style.display="block";
            document.getElementById("dd").style.display="block";
        }

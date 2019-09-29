/**
 * @author dmyu
 */
var xmlHttp;
        var merchantDiv;
        var inputField;
        var merchantTable;
        var merchantTableBody;

        function createXMLHttpRequest2() {
			//alert("create");
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            else if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();                
            }
        }

        function initVars2() {
            inputField = document.getElementById("merchantNo");            
            merchantTable = document.getElementById("merchant_table");
            merchantDiv = document.getElementById("merchantDiv");
            merchantTableBody = document.getElementById("merchant_table_body");
        }

        function findMerchantNo() {
            initVars2();
			//alert("11111111111");
            if (inputField.value.length > 0) {                                                                          
                createXMLHttpRequest2();   
               
                var url = "ajaxmerchantnoAction.do?op=findno&queryValue="+escape(inputField.value)+"";                        
                xmlHttp.open("GET", url, true);
                xmlHttp.onreadystatechange = callback2;
                xmlHttp.send(null);
            } else {
                clearNames2();
            }
        }

        function callback2() {
            if (xmlHttp.readyState == 4) {
				//alert("xmlHttp.readyState == 4");
                if (xmlHttp.status == 200) {
					
                    var name = xmlHttp.responseXML.getElementsByTagName("merchant")[0].firstChild.data;
					
					
                    setNames2(xmlHttp.responseXML.getElementsByTagName("merchant"));
                } else if (xmlHttp.status == 204){
                    clearNames2();
                }else{
					clearNames2();
				}
				
            }
        }
        
        function setNames2(the_names) { 
		        
            clearNames2();
            var size = the_names.length;
			//alert("size="+size);
			//alert(nameTableBody.style.height);
            setOffsets2();

            var row, cell, txtNode;
            for (var i = 0; i < size; i++) {
                var nextNode = the_names[i].firstChild.data;
                row = document.createElement("tr");
                cell = document.createElement("td");
                
                cell.onmouseout = function() {this.className='mouseOver';};
                cell.onmouseover = function() {this.className='mouseOut';};
                cell.setAttribute("bgcolor", "#FFFAFA");
                cell.setAttribute("border", "0");
                cell.onclick = function() { populateName2(this); } ;                             

                txtNode = document.createTextNode(nextNode);
                cell.appendChild(txtNode);
                row.appendChild(cell);
                merchantTableBody.appendChild(row);
            }
        }

        function setOffsets2() {
            var end = inputField.offsetWidth;
            var left = calculateOffsetLeft2(inputField);
            var top = calculateOffsetTop2(inputField) + inputField.offsetHeight;

            merchantDiv.style.border = "black 1px solid";
            merchantDiv.style.left = left + "px";
            merchantDiv.style.top = top + "px";
			merchantDiv.style.height = 200+"px";
			
			merchantDiv.style.zIndex="9999";
			merchantDiv.style.backgroundColor= "#FFFAFA";
			
			merchantDiv.style.overflow="auto";
			
			//document.getElementById("aa").style.display="none";
			//document.getElementById("bb").style.display="none";
			//document.getElementById("cc").style.display="none";
			//document.getElementById("dd").style.display="none";
			
            merchantTable.style.width = end+end + "px";
        }
        
        function calculateOffsetLeft2(field) {
          return calculateOffset2(field, "offsetLeft");
        }

        function calculateOffsetTop2(field) {
          return calculateOffset2(field, "offsetTop");
        }

        function calculateOffset2(field, attr) {
          var offset = 0;
          while(field) {
            offset += field[attr]; 
            field = field.offsetParent;
          }
          return offset;
        }

        function populateName2(cell) {
			
			var no=cell.firstChild.nodeValue.substr(0,15);
			//alert(no);
			inputField.value = no;
            //inputField.value = cell.firstChild.nodeValue;
            clearNames2();
            //document.getElementById("aa").style.display="block";
            //document.getElementById("bb").style.display="block";
            //document.getElementById("cc").style.display="block";
            //document.getElementById("dd").style.display="block";
            //callRefresh('1','')
        }

        function clearNames2() {
        
            var ind = merchantTableBody.childNodes.length;
            for (var i = ind - 1; i >= 0 ; i--) {
                 merchantTableBody.removeChild(merchantTableBody.childNodes[i]);
            }
            merchantDiv.style.border = "none";
            
            //document.getElementById("aa").style.display="block";
            //document.getElementById("bb").style.display="block";
            //document.getElementById("cc").style.display="block";
            //document.getElementById("dd").style.display="block";
        }

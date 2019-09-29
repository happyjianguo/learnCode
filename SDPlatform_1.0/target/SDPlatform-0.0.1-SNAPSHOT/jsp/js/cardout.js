/**
 * @author dmyu
 */
var xmlHttp;
        var startCardNo;
        var inputField;
        var cardNumber;
        var endCardNo;

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
            inputField = document.getElementById("cardType");            
            startCardNo = document.getElementById("startCardNo");
            cardNumber=document.getElementById("cardNumber");
			endCardNo=document.getElementById("endCardNo");
        }

        function getstartno() {
			
            initVars();
			
            if (inputField.value.length > 0) {                                                                          
                createXMLHttpRequest();   
               
                var url = "cardOutApplAction.do?op=startCardNo&queryValue="+escape(inputField.value)+"";                        
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
					
                    var name = xmlHttp.responseXML.getElementsByTagName("startno")[0].firstChild.data;
					
                    setNames(xmlHttp.responseXML.getElementsByTagName("startno"));
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
			
            for (var i = 0; i < size; i++) {
                var nextNode = the_names[i].firstChild.data;
				
				//alert(nextNode=="");
				//alert(nextNode=='null');
				if(nextNode!=='null'){
					document.getElementById("startCardNo").value=nextNode;
				}else{
					start();
					//alert("您所选的卡类型,暂无卡段.");
					//document.getElementById("startCardNo").value="";
				}
                
            }
        }

        function setOffsets() {
            var end = inputField.offsetWidth;
            var left = calculateOffsetLeft(inputField);
            var top = calculateOffsetTop(inputField) + inputField.offsetHeight;

            merchantDiv.style.border = "black 1px solid";
            merchantDiv.style.left = left + "px";
            merchantDiv.style.top = top + "px";
			merchantDiv.style.height = 200+"px";
			
			merchantDiv.style.zIndex="9999";
			merchantDiv.style.backgroundColor= "#FFFAFA";
			
			merchantDiv.style.overflow="auto";
			
			
			
            merchantTable.style.width = end+end + "px";
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
           
        }

        function clearNames() {
			document.getElementById("startCardNo").value="";
			document.getElementById("endCardNo").value="";
			document.getElementById("cardNumber").value="";
        }
		
		
		
		
		function findendno(){
			
			initVars();
			
            if (inputField.value.length > 0 && cardNumber.value.length>0 ) {                                                                          
                createXMLHttpRequest();   
             
                var url = "cardOutApplAction.do?op=endCardNo&queryValue="+escape(inputField.value)+"-"+escape(cardNumber.value)+"";                        
                xmlHttp.open("GET", url, true);
                xmlHttp.onreadystatechange = callback2;
                xmlHttp.send(null);
            } else {
                clearNames2();
            }
		}
		
		function callback2() {
            if (xmlHttp.readyState == 4) {
				
                if (xmlHttp.status == 200) {
					
                    var name = xmlHttp.responseXML.getElementsByTagName("names")[0].firstChild.data;
					
                    setNames2(xmlHttp.responseXML.getElementsByTagName("endno"));
					setNames3(xmlHttp.responseXML.getElementsByTagName("number"));
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
			//alert("size="+size)
            for (var i = 0; i < size; i++) {
                var nextNode = the_names[i].firstChild.data;
				//alert("nextNode="+nextNode);
                document.getElementById("endCardNo").value=nextNode;
				//document.getElementById("cardnumber2").value=nextNode;
				
            }
        }
		
		function setNames3(the_names) { 
		        
            clearNames3();
            var size = the_names.length;
			//alert("size="+size)
            for (var i = 0; i < size; i++) {
                var nextNode = the_names[i].firstChild.data;
				
				document.getElementById("cardnumber2").value=nextNode;
				
				
            }
			cardnum();
			
        }
		
		function clearNames3() {
			
			document.getElementById("cardnumber2").value="";
        }
		
		
		function clearNames2() {
			document.getElementById("endCardNo").value="";
			
        }
		

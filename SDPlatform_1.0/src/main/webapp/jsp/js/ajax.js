/* Simple AJAX Code-Kit (SACK) */
/* ©2005 Gregory Wild-Smith */
/* www.twilightuniverse.com */
/* Software licenced under a modified X11 licence, see documentation or authors website for more details */

function sack(file){
	this.AjaxFailedAlert = "Your browser does not support the enhanced functionality of this website, and therefore you will have an experience that differs from the intended one.\n";
	this.requestFile = file;
	this.method = "POST";
	this.URLString = "";
	this.encodeURIString = true;
	this.execute = false;
    
	this.onLoading = function() { };
	this.onLoaded = function() { };
	this.onInteractive = function() { };
	this.onCompletion = function() { };

	this.createAJAX = function() {
		try {
this.xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			
		} catch (e1) {
			try {
				this.xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (err) {
				this.xmlhttp = null;
			}
		}
		
		if(!this.xmlhttp && typeof XMLHttpRequest != "undefined")
			this.xmlhttp = new XMLHttpRequest();
		if (!this.xmlhttp){
			this.failed = true; 
		}
	}
	
	this.setVar = function(name, value){
		if (this.URLString.length < 3){
			this.URLString = name + "=" + value;
		} else {
			this.URLString += "&" + name + "=" + value;
		}
	}
	
	this.encVar = function(name, value){
		var varString = encodeURIComponent(name) + "=" + encodeURIComponent(value);
	return varString;
	}
	
	this.encodeURLString = function(string){
		varArray = string.split('&');
		for (i = 0; i < varArray.length; i++){
			urlVars = varArray[i].split('=');
			if (urlVars[0].indexOf('amp;') != -1){
				urlVars[0] = urlVars[0].substring(4);
			}
			varArray[i] = this.encVar(urlVars[0],urlVars[1]);
		}
	return varArray.join('&');
	}
	
	this.runResponse = function(){
		eval(this.response);
	}
	
	this.runAJAX = function(urlstring,boxIndex,isFrame){
		this.responseStatus = new Array(2);
		if(this.failed && this.AjaxFailedAlert){ 
			alert(this.AjaxFailedAlert); 
		} else {
			if(isFrame=='true')
			{
				showRSSData(boxIndex-1,boxIndex);
				document.getElementById('dragableBoxContent'+boxIndex).innerHTML ="<iframe src='"+urlstring+"' width='100%' marginwidth='0' height='100%' marginheight='0' align='middle' scrolling='yes' frameborder='0' ></iframe>";
				return;
				}
			if (urlstring){ 
				if (this.URLString.length){
					this.URLString = this.URLString + "&" + urlstring; 
				} else {
					this.URLString = urlstring; 
				}
			}
			if (this.encodeURIString){
				var timeval = new Date().getTime(); 
				this.URLString = this.encodeURLString(this.URLString);
				this.setVar("rndval", timeval);
			}
			if (this.element) { this.elementObj = document.getElementById(this.element); }
			if (this.xmlhttp) {
				var self = this;
				if (this.method == "GET") {
					var totalurlstring = this.requestFile + "?" + this.URLString;
					this.xmlhttp.open(this.method, totalurlstring, true);
				} else {
					
					document.getElementById('dragableBoxContent'+boxIndex).innerHTML = 'loading RSS data111111111111';
					this.xmlhttp.open(this.method, urlstring, true);
					
				}
				if (this.method == "POST"){
  					try {
						this.xmlhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
						this.xmlhttp.onreadystatechange = function() {
					switch (self.xmlhttp.readyState){
						case 1:
							self.onLoading();
						break;
						case 2:
							self.onLoaded();
						break;
						case 3:
							self.onInteractive();
						break;
						case 4:
							self.response = self.xmlhttp.responseText;
							
							self.responseXML = self.xmlhttp.responseXML;
							self.responseStatus[0] = self.xmlhttp.status;
							self.responseStatus[1] = self.xmlhttp.statusText;
							self.onCompletion();
							
							document.getElementById('dragableBoxContent'+boxIndex).innerHTML =self.response;
						break;
					}
				};
					} catch (e) {alert("sssss");}
				}

				this.xmlhttp.send('Untitled-1.htm');
				
			}
		}
	};
this.createAJAX();
}
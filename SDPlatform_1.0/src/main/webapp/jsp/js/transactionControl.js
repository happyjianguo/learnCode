function runCheck(){
	h=document.all("YGP").length;
	ss="";
	rr="";
	d=8;
	h=h/d;
	
	for(i=1;i<=h;i++){
		r=0;
		for(w=(i-1)*d;w<=((i-1)*d+d-1);w++){
			
			if(document.all("YGP")[w].checked){
				ss="1"+ss;
				r=Math.pow(2,(w-(i-1)*d))+r;
				
			}else{
				ss="0"+ss;
			}
			
		}
		rr=rr+String.fromCharCode(r);
		//rr=rr+String.fromCharCode(r)+"_";
		//ss="_"+ss;
		ss=ss;
	}
	alert(ss);
	document.form1.aa.value=ss;
	alert(document.form1.aa.value);
	//alert(String.fromCharCode(0));
}
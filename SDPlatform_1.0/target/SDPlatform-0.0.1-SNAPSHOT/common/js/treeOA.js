var imgPath="common/images/tree/";
function HideAll(eSrc,iGroupNumber)
{
if(typeof(old)=="object" && eSrc==old.parentElement.parentElement){							//如果当前被隐藏（收缩）的节点是被打开的节点，则将要收缩的子树的根结点打开
  window.event.srcElement.parentElement.lastChild.click();
}
if (("IMG" == eSrc.tagName) && ("TR" == eSrc.parentElement.parentElement.tagName))
  eSrc = eSrc.parentElement.parentElement;
else if ("TR" != eSrc.tagName) 
  return false;
iGroupNumber = Number(iGroupNumber);
if (eSrc.nextSibling && !isNaN(iGroupNumber)) {
  var eNext = eSrc.nextSibling;
  var iNextGroupNumber = Number(eNext.GroupNumber);  
  if (iGroupNumber<iNextGroupNumber) {
    i=eNext.firstChild.children.length-3  // 倒数第三个是要变化的
    if(i>0 && eNext.firstChild.children(i).tagName.toUpperCase()=="IMG"){
    eNext.firstChild.children(i).src=eNext.firstChild.children(i).src.replace("minus","plus");
  }
  eNext.style.display="none";
  HideAll(eNext,iGroupNumber);
  }else{
    return true;
  }
}
}

//********************************************************************************
function showAll(eSrc,iGroupNumber,eBase)     //eBase是被点击的节点（要处理节点的父节点），在整个递归过程中不会变
{
  if (("IMG" == eSrc.tagName) && ("TR" == eSrc.parentElement.parentElement.tagName)) {
    eSrc = eSrc.parentElement.parentElement;
  }
  else if ("TR" != eSrc.tagName)
    return false;
  iGroupNumber = Number(iGroupNumber);
  if (eSrc.nextSibling && !isNaN(iGroupNumber)) {
	eSrc.icon.replace(".gif","n.gif");
    var eNext = eSrc.nextSibling;
    var iNextGroupNumber = Number(eNext.GroupNumber);
    if (iGroupNumber<iNextGroupNumber) {
      if ((iGroupNumber+1)==iNextGroupNumber) {       //如果eNext是eBase的直接子节点，则处理后显示出来
      //************************************************************
      //下面的程序根据eBase(eNext的父节点)的前导图标和标志图标，定出eNext的前导图标
      for(i=0;i<=eNext.firstChild.children.length-4;i++){              
         if(eBase.firstChild.children(i).tagName=="IMG"){
             if(eBase.firstChild.children(i).src.indexOf("l.gif")>=0 || eBase.firstChild.children(i).src.indexOf("blank.gif")>=0){
                 eNext.firstChild.children(i).src=imgPath+"blank.gif";
             }else{
                 eNext.firstChild.children(i).src=imgPath+"noblk.gif";
             }                   
         }             
      }
      //************************************************************
      //下面的程序根据eNext的后续节点，定出eNext的标志图标      
      lastChild=getlastChild(eNext,iNextGroupNumber);      //第一步：调用递归函数，判断eNext是否是lastChild（它的父母的最后一个子孙节点）      
      var parentNode=false;                                //第二步：判读eNext是否是parentNode（它是否是父母节点，即非叶子节点）
      if(eNext.nextSibling){
        var etmp = eNext.nextSibling;
        var tmpGroupNumber = Number(etmp.GroupNumber);        
        if(tmpGroupNumber>iNextGroupNumber)parentNode=true;
      }      
      if(parentNode){tagsrc=imgPath+"plus";tagsrc2=eNext.icon}                       //第三步：根据lastChild和parentNode的值，定出eNext的标志图标
      else{tagsrc=imgPath+"line";tagsrc2=eNext.icon.replace(".gif","n.gif")}//////////如果是最后一个子节点替换前面图片
      if(lastChild) tagsrc+="l"
      eNext.firstChild.children(i).src=tagsrc+".gif"           
      eNext.firstChild.children(i+1).src=tagsrc2
            
      //***********************************************************
       eNext.style.display="";    
	   
      }
      showAll(eNext,iGroupNumber,eBase);
    } else {
      return true;
    }
  }
}

function getlastChild(eSrc,iGroupNumber){ //判断某个节点是否为LastChild的递归函数
  if (eSrc.nextSibling && !isNaN(iGroupNumber)) {
     var eNext = eSrc.nextSibling;
     var iNextGroupNumber = Number(eNext.GroupNumber);
     if (iGroupNumber<iNextGroupNumber) {
       return getlastChild(eNext,iGroupNumber)
     }else if(iGroupNumber==iNextGroupNumber){
       return false
     }else if(iGroupNumber>iNextGroupNumber){
       return true
     }else {
     	return getlastChild(eNext,iGroupNumber)
     	}
  }else{                                 //在前序顺序存储结构的树图中，最后一个节点所在的所有子树的根结点都是LastChild。
    return true
  }
}
//********************************************************************************
function Toc1_click()
{
var eSrc = window.event.srcElement;
var eNext = eSrc.parentElement.parentElement.nextSibling;
//如果点击的是标志图标或td，则触发A的点击事件(最根节点除外)
if(eSrc.tagName=='IMG' && eSrc==eSrc.parentElement.children(eSrc.parentElement.children.length-2) && parseInt(eSrc.parentElement.parentElement.GroupNumber)!=-1)
    eSrc.parentElement.children(eSrc.parentElement.children.length-3).click();
//if(eSrc.tagName=='TD' && parseInt(eSrc.parentElement.GroupNumber)!=-1)
//    eSrc.children(eSrc.children.length-3).click();
//如果是<A>，则换颜色
if (eSrc.tagName.toUpperCase()=="A"){    
   // eSrc.parentElement.children(eSrc.parentElement.children.length-3).click();
  if(typeof(old)=="object"){
    old.style.backgroundColor='white';
    old.style.color='black';    
    i=old.parentElement.children.length-2;
   // old.parentElement.children(i).src=old.parentElement.children(i).src.replace("no.gif",".gif");
  }
  eSrc.style.backgroundColor='#08246b';
  eSrc.style.color='#ffffff';    
  i=eSrc.parentElement.children.length-2;
  //eSrc.parentElement.children(i).src=eSrc.parentElement.children(i).src.replace(".gif","no.gif");
  old=eSrc;//http://localhost:8080/WebPIS/item/step/module_step.asp?project_id=1&step_id=&scheme_id=1
}else{
  if(typeof(old)=="object"){
    old.style.backgroundColor='#d4d0c8';
    old.style.color='black';    
  }
}

if ( eNext != null){                                 //展开或收缩节点 
  if("IMG" == eSrc.tagName.toUpperCase()){
    if(eSrc.src.indexOf("plus")>=0){
      eSrc.src=eSrc.src.replace("plus","minus");
	  i=eSrc.parentElement.children.length-2;
	  eSrc.parentElement.children(i).src=eSrc.parentElement.children(i).src.replace(".gif","no.gif");
      showAll(eSrc.parentElement.parentElement,eSrc.parentElement.parentElement.GroupNumber,eSrc.parentElement.parentElement);
    } else if (eSrc.src.indexOf("minus")>=0) {
        i=eNext.firstChild.children.length-3
        eNext.firstChild.children(i).src=eNext.firstChild.children(i).src.replace("minus","plus");
        eNext.style.display="none";
      eSrc.src=eSrc.src.replace("minus","plus");
	  i=eSrc.parentElement.children.length-2;
	  eSrc.parentElement.children(i).src=eSrc.parentElement.children(i).src.replace("no.gif",".gif");
      HideAll(eNext,eSrc.parentElement.parentElement.GroupNumber);
    }
  }
}
}

//***************************************************
//以下是树图初始化排序的程序
//Code By Kmsj ,2002-6-1
//*************************************************
function fnMove(tbody){
oldparentid=0
var lastroot;
for(i=1;i<=tbody.children.length-1;i++){ //i是tr的个数。注意：在这段程序中生成节点的图标时，也可利用节点的GroupNuber来循环生成，程序非常简洁，但效率只有
  parentid=tbody.children(i).parentid;   //现在的1/5。所以，要尽量减少修改节点的innerHTML的次数。
  if(parentid!='0'){
    if(parentid==oldparentid){              //如果这个节点的parentid与上一个移动的节点的parentid相同，则将这个节点移到上一个移动的节点的后面
        j=oldj                              //而且这个节点的GroupNumber以及图标的个数都与上一个移动的节点相同。注意：这个if纯粹为了提高效率而写！
        tbody.children(i).GroupNumber=tbody.children(j).GroupNumber;                    //可提高约8%的速度
        abgn=tbody.children(j).firstChild.innerHTML.indexOf("&nbsp;<A")        
        tbody.children(i).firstChild.innerHTML=tbody.children(j).firstChild.innerHTML.substr(0,abgn)+tbody.children(i).firstChild.innerHTML   
        tbody.parentElement.moveRow(i,j+1);   
        oldj=j+1;
        if(j>i){i--;};
        continue;
    }
    for(j=1;j<=tbody.children.length-1;j++){
      if(tbody.children(j).id==parentid){
        tbody.children(i).GroupNumber=1+Number(tbody.children(j).GroupNumber);
        if(tbody.children(i).GroupNumber==1){     //如果GroupNumber=1（第三层），直接给出图标的个数（不能直接在它的父节点的图标前加一个）
          tbody.children(i).firstChild.innerHTML="<img align='absmiddle'><img align='absmiddle'><img align='absmiddle'>"+tbody.children(i).firstChild.innerHTML
        }else{                                    //如果GroupNumber>1（第四层及更深），则如果它的父节点已经被移动过（图标的个数已经确定），它的图标个数
          if(tbody.children(j).firstChild.innerHTML.indexOf("<IMG")>=0){          //就是父节点的图标个数加一
          abgn=tbody.children(j).firstChild.innerHTML.indexOf("&nbsp;<A")        
          tbody.children(i).firstChild.innerHTML="<img align='absmiddle'>"+tbody.children(j).firstChild.innerHTML.substr(0,abgn)+tbody.children(i).firstChild.innerHTML
          }
        }
        tbody.parentElement.moveRow(i,j+1);  
        oldj=j+1;
        oldparentid=parentid;    
        if(j>i){i--;};
        break;
      }
    }
  }else{   //次根结点
     if(tbody.children(i)!=tbody.lastChild && tbody.children(i+1).parentid!=0){
        tbody.children(i).firstChild.innerHTML="<IMG src='"+imgPath+"plus.gif' align='absmiddle'><img src="+tbody.children(i).icon+" align='absmiddle'>"+tbody.children(i).firstChild.innerHTML
     }else{
        tbody.children(i).firstChild.innerHTML="<IMG src='"+imgPath+"line.gif' align='absmiddle'><img src="+tbody.children(i).icon.replace(".gif",".gif")+" align='absmiddle'>"+tbody.children(i).firstChild.innerHTML
     }
     tbody.parentElement.moveRow(i,1); 
     if(!lastroot){
     lastroot=tbody.children(i)//lastroot是最后一个次根结点，需单独处理它的标志图标
     }
    //var 
  }
}
lastroot.firstChild.innerHTML=lastroot.firstChild.innerHTML.replace(".gif","l.gif")
}

//*************************************************
//以下是树图搜索的程序
//Code By Kmsj ,2002-6-1
//*************************************************
function searchitem(tbody,item,type,beginpos){   //item:要搜索的内容；type:搜索类别（0:项目名称 1:项目id）；beginpos:搜索开始的位置
if(item==null || item==''){return false}
for(i=1;i<=tbody.children.length-1;i++){                     //收缩所有的节点
  if(tbody.children(i).GroupNumber==0){
      if(tbody.children(i).firstChild.children(0).src.indexOf('minus')>=0){
        tbody.children(i).firstChild.children(0).click()
      }
  }
}
for(i=1+beginpos;i<=tbody.children.length-1;i++){                             //从指定的位置开始，向下搜索
 // if(tbody.children(i).GroupNumber==0){continue}
  if(type==0){b=(tbody.children(i).innerText.indexOf(item)>=0)}
  if(type==1){b=(tbody.children(i).id==item)}                
  if(b){
    parentstr=''
    L=i
    OpenSearchItem(tbody,i);
    return false
  }
}
alert('没有找到匹配的项目！')
L=0
tbody.children(L).lastChild.children(1).click()
}

function OpenSearchItem(tbody,i){  
  for(j=i;j>=0;j--){
    if(tbody.children(j).id==tbody.children(i).parentid){
      parentstr=''+j+'/'+parentstr
      OpenSearchItem(tbody,j)      
      return false;
    }
  }  
  i=parentstr.indexOf('/')
  while(i>=0){
    m=parseInt(parentstr.substr(0,i))  
    k=tbody.children(m).firstChild.children.length  
    tbody.children(m).firstChild.children(k-3).click()   
    parentstr=parentstr.substr(i+1)
    i=parentstr.indexOf('/')
  }
  tbody.children(L).lastChild.lastChild.click();
}


//------------------------------------------------------------------
	var tempDelIds="";
	function openAttachmentDetail(tpid)
	{
		var tUrl = "showallmeeting.do?method=viewMeetingTopic&type=open&ytid="+tpid;
		window.open(tUrl,"topicDetail","height=400, width=600,top=200,left=200,toolbar=no,menubar=no,scrollbars=yes, location=no, status=yes,title=no");
	}
	
	var Topic = Class.create();
	Topic.prototype = {
		
		initialize: function(id,topic,date1,date2,personId,personName) {
			this.topic = topic;
			this.id = id;
			this.date1 = date1;
			this.date2 = date2;
			this.personId = personId;
			this.personName = personName;
		},
	
		show:function() {
			id = document.getElementsByClassName('meetingTopicTitle').length;
			//alert(id);
			//����һ��
			var row = $("selectedTopics").insertRow(-1);
			row.style.background="#E8F0FD";
			//���뵥Ԫ��
			var cell1 = row.insertCell(-1);
			cell1.innerHTML="<li><span class='meetingTopicTitle'>"+this.topic+"</span><input type=hidden name=topic"+id+" id=topic"+id+" value="+this.topic+"><input type=hidden name=topicId"+id+" id=topicId"+id+" value="+this.id+"></li>";
			var cell2 = row.insertCell(-1);
			var tpid = this.id;
			cell2.innerHTML="<a href='#'><img alt='��ϸ��Ϣ' border=0 src='common/images/icoAddFl.gif' onclick='openAttachmentDetail(\""+tpid+"\")'></a>&nbsp;<a href='#'><img alt='ɾ��' border=0 src='common/images/icoDelete.gif' onclick='removeTopic()'></a>";
			cell2.style.width="42px";
			
			
			var row2 = $("selectedTopics").insertRow(-1);
			
			var cell3 = row2.insertCell(-1);
			var cell4 = row2.insertCell(-1);
			
			cell3.innerHTML="��ʼʱ�� <input name=myrqa"+id+" id=myrqa"+id+" value='"+this.date1+"' readonly=readonly size=16>"+"<img width=16 height=16 border=0 src='common/images/tan.gif' onClick=getTime('myrqa"+id+"',1)> ����ʱ�� <input name=myrqb"+id+" id=myrqb"+id+" value='"+this.date2+"' readonly=readonly size=16>"+"<img width=16 height=16 name=mytanb"+id+" border=0 src='common/images/tan.gif' onClick=getTime('myrqb"+id+"',1)>";
			cell3.innerHTML += "<br>��ϯ��Ա <input readonly=readonly size=18 name=selectPeople"+id+" id=selectPeople"+id+" value='"+this.personName+"'><input type=hidden name=selectPeopleId"+id+" id=selectPeopleId"+id+" value='"+this.personId+"'>";
			//alert(cell3.innerHTML);
			//cell3.innerHTML += "<a href='#' onclick=removeTopic();>ɾ��</a>";
			cell4.innerHTML += "<a href='#'><img border=0 src='common/images/btnSelect.gif' onclick=selectPeo('selectPeople"+id+"','selectPeopleId"+id+"')></a>";
		},
		
		showReadOnly:function() {
			id = document.getElementsByClassName('meetingTopicTitle').length;
			//alert(id);
			//����һ��
			var row = $("selectedTopics").insertRow(-1);
			row.style.background="#E8F0FD";
			//���뵥Ԫ��
			var cell1 = row.insertCell(-1);
			cell1.innerHTML="<li><span class='meetingTopicTitle'>"+this.topic+"</span><input type=hidden name=topic"+id+" id=topic"+id+" value="+this.topic+"><input type=hidden name=topicId"+id+" id=topicId"+id+" value="+this.id+"></li>";
			var cell2 = row.insertCell(-1);
			cell2.innerHTML="";
			cell2.style.width="42px";
			
			
			var row2 = $("selectedTopics").insertRow(-1);
			
			var cell3 = row2.insertCell(-1);
			var cell4 = row2.insertCell(-1);
			
			
			
			
			cell3.innerHTML="��ʼʱ�� <input name=myrqa"+id+" id=myrqa"+id+" value='"+this.date1+"' readonly=readonly size=16>"+" ����ʱ�� <input name=myrqb"+id+" id=myrqb"+id+" value='"+this.date2+"' readonly=readonly size=16>"+"";
			cell3.innerHTML += "<br>��ϯ��Ա <input readonly=readonly size=18 name=selectPeople"+id+" id=selectPeople"+id+" value='"+this.personName+"'><input type=hidden name=selectPeopleId"+id+" id=selectPeopleId"+id+" value='"+this.personId+"'>";
			//alert(cell3.innerHTML);
			//cell3.innerHTML += "<a href='#' onclick=removeTopic();>ɾ��</a>";
			cell4.innerHTML += "<a href='#'><img border=0 src='common/images/btnSelect.gif' onclick=selectPeo('selectPeople"+id+"','selectPeopleId"+id+"')></a>";
			
		}
	}
	
	function test()
	{
		eles = document.getElementsByTagName('input');
		for(i = 0; i < eles.length; i++)
		{
			str = eles[i].id.match("selectPeople([0-9]+)");
			if(str!=null)
			{
				alert(eles[i].id);
			}
		}
		alert($("topicTbDiv").innerHTML);
	}
	
	
	function selectTopics(hyyt,hyid)
	{	
		var arrTp = hyyt.split(",");
		var arrId = hyid.split(",");
		
		for(var i = 0; i < arrTp.length; i++)
		{
			var tp = new Topic(arrId[i],arrTp[i],"","","","");
			tp.show();
		}
	}
	
	
	
	//�Ƴ�һ��ѡ�������
	function removeTopic()
	{
		//������
		totalNum = document.getElementsByClassName('meetingTopicTitle').length;
		i = event.srcElement.parentElement.parentElement.parentElement.rowIndex;
		
		/*if(tempDelIds.length==0)
		{
			tempDelIds = $("topicId"+i).value;
		}else
		{
			tempDelIds = tempDelIds + "-" + $("topicId"+i).value;
		}*/
		
		//alert(tempDelIds);
		
		var tb = $("selectedTopics");
		tb.deleteRow(i);
		tb.deleteRow(i);
		
		
		for(j = i; j < totalNum-1; j++)
		{
			tmpTopic = $("topic"+(j+1)).value;
			tmpId = $("topicId"+(j+1)).value;
			tmpRqa = $("myrqa"+(j+1)).value;
			tmpRqb = $("myrqb"+(j+1)).value;
			tmpSp = $("selectPeople"+(j+1)).value;
			tmpSpId = $("selectPeopleId"+(j+1)).value;
			
			var cu = (j-i)*2+i; 

			//alert(tmpTopic);
			tb.rows[cu].cells[0].innerHTML = "<li><span class='meetingTopicTitle'>"+tmpTopic+"</span><input type=hidden name=topic"+j+" id=topic"+j+" value="+tmpTopic+"><input type=hidden name=topicId"+j+" id=topicId"+j+" value="+tmpId+"></li>";
			//tb.rows[cu].cells[1].innerHTML = "<a href='#'><img border=0 src='common/images/icoDelete.gif' onclick='removeTopic()'></a>";
			tb.rows[cu].cells[1].innerHTML="<a href='#'><img alt='��ϸ��Ϣ' border=0 src='common/images/icoAddFl.gif' onclick='openAttachmentDetail(\""+tmpId+"\")'></a>&nbsp;<a href='#'><img alt='ɾ��' border=0 src='common/images/icoDelete.gif' onclick='removeTopic()'></a>";
			//"<span class='meetingTopicTitle'>"+tmpTopic+"</span><input type=hidden name=topic"+j+" id=topic"+j+" value="+tmpTopic+"><input type=hidden class='topicIdStyle' name=topicId"+j+" id=topicId"+j+" value="+tmpId+">";
			tb.rows[cu+1].cells[0].innerHTML = "��ʼʱ�� <input name=myrqa"+j+" id=myrqa"+j+" value='"+tmpRqa+"' readonly=readonly size=16>"+"<img width=16 height=16 border=0 src='common/images/tan.gif' onClick=getTime('myrqa"+j+"',1)> ����ʱ�� <input name=myrqb"+j+" id=myrqb"+j+" value='"+tmpRqb+"' readonly=readonly size=16>"+"<img width=16 height=16 name=mytanb"+j+" border=0 src='common/images/tan.gif' onClick=getTime('myrqb"+j+"',1)>";
			tb.rows[cu+1].cells[0].innerHTML += "<br>��ϯ��Ա <input readonly=readonly size=18 name=selectPeople"+j+" id=selectPeople"+j+" value='"+tmpSp+"'><input type=hidden name=selectPeopleId"+j+" id=selectPeopleId"+j+" value='"+tmpSpId+"'>";
			
			//"��<input value='"+tmpRqa+"' name=myrqa"+j+" id=myrqa"+j+" readonly=readonly size=16>"+"<img width=16 height=16 border=0 src='common/images/tan.gif' onClick=getTime('myrqa"+j+"',1)>��<input value='"+tmpRqb+"' name=myrqb"+j+" id=myrqb"+j+" readonly=readonly size=16>"+"<img width=16 height=16 name=mytanb"+j+" border=0 src='common/images/tan.gif' onClick=getTime('myrqb"+j+"',1)>";
			//tb.rows[cu+1].cells[1].innerHTML = "<input value='"+tmpSp+"'  readonly=readonly size=40 name=selectPeople"+j+" id=selectPeople"+j+"><a href='#' onclick='selectPeo();'>��ϯ��Ա</a><input value='"+tmpSpId+"' type=hidden name=selectPeopleId"+j+" id=selectPeopleId"+j+">";
			tb.rows[cu+1].cells[1].innerHTML = "<a href='#'><img border=0 src='common/images/btnSelect.gif' onclick=selectPeo('selectPeople"+j+"','selectPeopleId"+j+"')></a>";
		}
		//alert(tb.rows[3].cells[0].innerHTML);
	}
	
	//��������ѡ�˱�־
	var selectPeoFlag = 0;
	
	function selectPeo(person,personId)
	{	
		selectPeoFlag = parseInt(event.srcElement.parentElement.parentElement.parentElement.rowIndex/2);
		
		globalSelPeo = "sMeetingTopic";
		
		mul_person_name = document.getElementById(person).value;
		mul_person_id = document.getElementById(personId).value;
		
		var sUrl = "jsp/org/show_peo_many.jsp?type=1";
		
		window.open (sUrl, "newwindow", "height=400, width=600,top=200,left=200,toolbar=no,menubar=no,scrollbars=yes, location=no, status=yes,title=no");
	}
	
	function callback(userId,userName)
	{
		if(globalSelPeo == "sMeetingTopic")
		{
			$("selectPeopleId"+selectPeoFlag).value=userId;
			$("selectPeople"+selectPeoFlag).value=userName;
		}
		else if(globalSelPeo == "sCompere")
		{
			document.forms[0].compere.value=userName;
			document.forms[0].compereId.value=userId;
		}
		else if(globalSelPeo == "sAttendPerson")
		{
			document.forms[0].attendPerson.value=userName;
			document.forms[0].attendPersonId.value=userId;
		}
	}
	
	//��֤��������
	function validMeetingTopics()
	{
		var msg = "";
		var tb = $(selectedTopics);
		var rowsLength = tb.rows.length/2;
		if(rowsLength==0)
		{
			msg += "��ѡ������һ���������⣡\n";
		}
		else
		{
			for(var i = 0; i < rowsLength; i++)
			{
				var rqa = $("myrqa"+i);
				if(rqa.value.length==0)
				{
					
					msg += $("topic"+i).value;
					msg += "�Ŀ�ʼʱ�䲻��Ϊ�գ�\n";
				}

				var rqb = $("myrqb"+i);
				if(rqb.value.length==0)
				{
					msg += $("topic"+i).value;
					msg += "�Ľ���ʱ�䲻��Ϊ�գ�\n";
				}

				/*var spl = $("selectPeople"+i);
				if(spl.value.length==0)
				{
					msg += $("topic"+i).value;
					msg += "�����Ա����Ϊ�գ�\n";
				}*/
			}
		}
		
		return msg;
	}
	
//������������������������������������������������������������������������
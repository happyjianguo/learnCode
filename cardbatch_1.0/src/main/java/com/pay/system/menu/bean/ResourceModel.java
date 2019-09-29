package  com.pay.system.menu.bean;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.pay.util.JSONUtil;



public class ResourceModel {
	//��ԴID
	public String resourceId;
	
	//��������
	public String label;
	
	public String label_fn;
	
	//ϵͳ����
	public String systemid;
	
	//��ԴURL
	public String url;
	
	//�ϼ���ԴID
	public String parentId;
	
	//��������
	public String tableName;
	
	//�������������̱�ʶ
	public String procId;
	
	//����
	public String orderId;
	
	//��ע
	public String memo;
	
	//��Դͼ��
	public String icon;
	
	//��������Դ�ڵ�
	public List childs;
	
	//���еĲ���
	public List acts;
	
	public String resourcelevel;
	
	//ת�����캯��
	public ResourceModel(MenuBean bean){
		this.resourceId=bean.getMenuno();
		this.parentId=bean.getMenuparent();
		this.url=bean.getMenupath();
		this.label=bean.getMenuname();
		this.memo=bean.getMenudesc();
		if(bean.getLst()!=null&&!bean.getLst().isEmpty()){
			this.childs=bean.getLst();
		}
	}
	
	public ResourceModel(){
		
	}
	public void addCMISResource(ResourceModel resource){
		if(this.childs == null)
			this.childs = new LinkedList();
		this.childs.add(resource);
	}
	
//	public void addCMISAct(ActModel act){
//		if(this.acts == null)
//			this.acts = new LinkedList();
//		this.acts.add(act);
//	}
	
//	public ActModel getCMISAct(String actId){
//		if(this.acts == null || this.acts.size() == 0)
//			return null;
//		for(int i=0;i<this.acts.size();i++){
//			ActModel act = (ActModel)this.acts.get(i);
//			if(actId.equals(act.actId))
//				return act;
//		}
//		return null;
//	}
	
	public ResourceModel getChild(String resourceId){
		if(this.childs == null || this.childs.size() == 0)
			return null;
		for(int i=0;i<this.childs.size();i++){
			ResourceModel child = (ResourceModel)this.childs.get(i);
			if(resourceId.equals(child.resourceId)){
				return child;
			}
		}
		return null;
	}
	
	public String toString(int tabCount){
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < tabCount; i++)
			buf.append("\t");
		buf.append("<CMISResource id=\""+this.resourceId+"\"");
		buf.append(" name=\""+this.label+"\"");
		if(this.parentId != null)
			buf.append(" parentId=\""+this.parentId+"\"");
		if(this.tableName != null)
			buf.append(" tableName=\""+this.tableName+"\"");
		if(this.procId != null)
			buf.append(" procId=\""+this.procId+"\"");
		if(this.orderId != null)
			buf.append(" orderId=\""+this.orderId+"\"");
		buf.append(" systemid=\""+this.systemid+"\"");
		buf.append(" url=\""+this.url+"\"");
		int actCount = 0;
		if(this.acts != null)
			actCount = this.acts.size();
		buf.append(" actCount=\""+actCount+"\"");
		buf.append(">\n");
		
		if(this.childs != null){
			for(int i=0;i<this.childs.size();i++){
				ResourceModel resource = (ResourceModel)this.childs.get(i);
				buf.append(resource.toString((tabCount+1)));
				buf.append("\n");
			}
		}
		for (int i = 0; i < tabCount; i++)
			buf.append("\t");
		buf.append("</CMISResource>");
		return buf.toString();
	}
	
	public String toString(){
		return this.toString(0);
	}
	
	public String toJSONString(){
		return this.toJSONString(false);
	}
	
	public String toJSONString(boolean fn){
		StringBuffer buf = new StringBuffer();
		buf.append("{id:'").append(JSONUtil.normalizeString(this.resourceId));
		
		String label = this.label;
		//�ж��Ƿ�����Ӣ�ĵ���Դ����
		if(fn && this.label_fn != null && this.label_fn.length()>0)
			label = this.label_fn;
		buf.append("',label:'").append(JSONUtil.normalizeString(label)).append("'");
		if(this.url != null&&!this.url.equals(""))
			buf.append(",action:'").append(JSONUtil.normalizeString(this.url)).append("'");
		if(this.acts != null){
			buf.append(",operations:[");
			Iterator i = this.acts.iterator();
	        boolean hasNext = i.hasNext();
	        while (hasNext) {
	        	ActModel act = (ActModel)i.next();
	            hasNext = i.hasNext();
	            buf.append(act.toJSONString());
	            if (hasNext)
	                buf.append(",");
	        }
	        buf.append("]");
		}
		if(this.childs != null){
			
			buf.append(",children:[");
			Iterator child = this.childs.iterator();
	        boolean hasNext = child.hasNext();
	        while (hasNext) {
	        	ResourceModel resource = (ResourceModel)child.next();
	        	hasNext = child.hasNext();
	            buf.append(resource.toJSONString(fn));
	            if (hasNext)
	                buf.append(",");
	        }
	        buf.append("]");
		}
		buf.append("}");
		
		return buf.toString();
	}
	 
}

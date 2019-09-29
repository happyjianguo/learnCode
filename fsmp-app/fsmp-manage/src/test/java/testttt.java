import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class testttt{
	private static int counter;

	private static List<String> list;
	public static void main(String[] args){
		List<String> list = new LinkedList<String>();
		list.add("11");
		list.add("12");
		list.add("13");
		list.add("14");
		Object[] ss = list.toArray();
		System.out.println(list);
		System.out.println(Arrays.toString(ss)  );  
	}

	public static String overHtml(String str) {
		String reStr ="";
		List<String> li1 = new LinkedList<String>();
		List<String> li2 = new LinkedList<String>();
		List<String> li = new LinkedList<String>();
		String[] arr=str.split("/&gt;");
		for(int i=0;i<arr.length;i++){
			//偶数为image
			if(i%2==0){
//				System.out.println(arr[i]);
				li1.add(arr[i].substring(0, arr[i].indexOf("src=")+4));
			}
			//奇数为input
			if(i%2==1){
				//判断隐藏域前是否有文本
				if(arr[i].indexOf("&lt;")==0){
					li2.add(arr[i].substring(arr[i].indexOf("value=")+6)+"/&gt;");
				}else{
					li2.add(arr[i].substring(arr[i].indexOf("value=")+6)+"/&gt;"+arr[i].substring(0,arr[i].indexOf("&lt;")));
				}
			}
		}
		for(int i=0; i<li1.size();i++){
			li.add(li1.get(i)+li2.get(i));
		}
		for(int i=0; i<li.size();i++){
			reStr=reStr+li.get(i);
		}
		return reStr.trim();
	}
	
	 public static void stringMap(String str)  
	    {  
			if(str.contains("/&gt;"))  
	        {  
				list .add(str.substring(0, str.indexOf("/&gt;")+5));
	            System.out.println(str.substring(0, str.indexOf("/&gt;")+5));
	            stringMap(str.substring(str.indexOf("/&gt;")+5));  
	        }  
	   } 
	
	 public static int stringNumbers(String str)  
	    {  
			if(!str.contains("/&gt;")){
		 		return counter;
		 	}
	        else if(str.contains("/&gt;"))  
	        {  
	            counter++;  
	            str.substring(0, str.indexOf("/&gt;")+5);
	            stringNumbers(str.substring(str.indexOf("/&gt;")+5));  
	        }  
	        return counter;  
	    }  
}
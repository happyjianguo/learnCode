package cn.yufu.posp.exportAndimport.utils;

import org.springframework.jdbc.core.support.JdbcDaoSupport;




public class DB2ConnectTest extends JdbcDaoSupport  implements DB2Connects{
	
	/**
	 * 
	 * @param source:源表
	 * @param refId：引用的外键
	 */
//	public void dawjExportTodgdwj(String source,String refId){
//		
//	}
	
	/**
	 * 
	 * @param target:目标表
	 * @param refId:引用外键
	 */
//	public void dgdwjExportTodawj(String target,String refId){
//		String sql = "";
//		
//	}
	
	public void testCreateSequence(){
		String seqName = "SQ_DGDWJFJ_ID";
		String sql = "CREATE SEQUENCE "+seqName+" START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCYCLE CACHE 24";
		try
	   {
			System.out.println(sql);
			this.getJdbcTemplate().execute(sql);
			System.out.println("创建sequence成功");
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	}
	
	public void test(){
		System.out.println("dddddddddddddddd");
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DB2ConnectTest t = new DB2ConnectTest();
//		t.test();
		t.testCreateSequence();
	}

}

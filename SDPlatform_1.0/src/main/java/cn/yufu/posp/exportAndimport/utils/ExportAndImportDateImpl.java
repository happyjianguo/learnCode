package cn.yufu.posp.exportAndimport.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.yufu.posp.common.common.exception.OAException;

public class ExportAndImportDateImpl extends JdbcDaoSupport implements ExportAndImportDate
{

	/**
	 * 把档案文件导出到待归档文件 sourceM 案卷文件表表名 sourceS待归档
	 */
	public void ExportDateFromDawjtoDgdwj(final String sourceM, final String sourceS, final String sourceSubM, final String refId) throws OAException
	{
		final String selectDaSQL = " SELECT * FROM " + sourceM + " where ID = " + refId + "";
		
		getJdbcTemplate().execute(selectDaSQL, new PreparedStatementCallback()
		{
			public Object doInPreparedStatement(PreparedStatement p) throws SQLException, DataAccessException
			{
				ResultSet rs = p.executeQuery();

				while (rs.next())
				{
					String id = rs.getObject(1).toString();
					
					doInsertDaxxToDgdwj(sourceM, sourceS,sourceSubM, id);
				}
				return null;
			}
		});
	}

	/**
	 * 把待归档文件导入到档案文件
	 */
	public void ImportDateFromDgdwjToDawj(final String targetM, final String targetS, final String refId, final String dgdwjbh, final String targetSub) throws OAException
	{

		final String deleteSourceSQL = "DELETE FROM " + targetS + " WHERE DGDWJBH = " + dgdwjbh;
		String id=getId("SEQ_"+targetM.trim());
		
		//String insertFJxxSQL = "INSERT INTO " + targetM + "(ID,REFERID,DWMC,DH,CJH,ZTLX,KZSYF,SJ,RQ,FLH,ZTGJC,RW,JG,DD,WZ,FZ,WH,ZRZ,TM,YH,BZ,MJQX,BGQX) " + " SELECT BIGINT('"+id+"'),BIGINT('" + refId + "'),LJDW,DH,CJH,ZTLX,KZSYF,SJ,RQ,FLH,ZTGJC,RW,JG,DD,WZ,FZ,WH,ZRZ,TM,YH,BZ,MJQX,BGQX FROM " + targetS + " WHERE DGDWJBH = " + dgdwjbh;
		String insertFJxxSQL = "INSERT INTO " + targetM + "(ID,REFERID,DWMC,DH,CJH,ZTLX,KZSYF,SJ,RQ,FLH,ZTGJC,RW,JG,DD,WZ,FZ,WH,ZRZ,TM,YH,BZ,MJQX,BGQX) " + " SELECT "+id+"," + refId + ",LJDW,DH,CJH,ZTLX,KZSYF,SJ,RQ,FLH,ZTGJC,RW,JG,DD,WZ,FZ,WH,ZRZ,TM,YH,BZ,MJQX,BGQX FROM " + targetS + " WHERE DGDWJBH = " + dgdwjbh;
		doInsertTodawj(dgdwjbh, targetSub,targetM, targetS,id);
		this.getJdbcTemplate().execute(insertFJxxSQL);
		this.getJdbcTemplate().execute(deleteSourceSQL);
	}

	public void doInsertTodawj(final String id, final String targetSub,final String targetM, final String targetS,final String appId)
	{
		// String insertSQL = "insert into
		// "+targetSub+"(ID,FJMC,REFERID,FJLX,FJWJ,FJDX) SELECT " +
		// "CHAR(FJBH),FJMC,WJBH,FJLX,FJ,WJDX FROM OA_JL_DGDWJFJ WHERE WJBH =
		// '"+id+"'";
		
		//String insertSQL = "insert into " + targetSub + "(ID,FJMC,REFERID,FJLX,FJDX,fjwj,SXH,GLST) SELECT (nextval FOR "+"SEQ_"+targetM.trim()+"), FJMC,BIGINT('"+appId+"'),FJLX,WJDX,fj,CHAR(ROW_NUMBER() over()),'WJ' FROM OA_JL_DGDWJFJ WHERE WJBH = '" + id + "'";
		
		String insertSQL = "insert into " + targetSub + "(ID,FJMC,REFERID,FJLX,FJDX,fjwj,SXH,GLST) SELECT "+"SEQ_"+targetM.trim()+".NEXTVAL, FJMC,"+appId+",FJLX,WJDX,fj,rownum,'WJ' FROM OA_JL_DGDWJFJ WHERE WJBH = '" + id + "'";

		String deleteSQL = "delete from OA_JL_DGDWJFJ WHERE WJBH= '" + id + "'";
		this.getJdbcTemplate().execute(insertSQL);
		this.getJdbcTemplate().execute(deleteSQL);
	}

	public void doInsertDaFjToDgdwjFj(String sourceSubM, String id,String appId)
	{
		// final String subFjSQL = " INSERT INTO
		// OA_JL_DGDWJFJ(FJBH,WJBH,FJMC,FJLX,FJ,WJDX)"
		// +" SELECT BIGINT(ID),CHAR(REFERID),FJMC,FJLX,FJWJ,FJDX FROM
		// "+sourceSubM
		// +" WHERE REFERID = "+id;
		//final String subFjSQL = " INSERT INTO OA_JL_DGDWJFJ(FJBH,WJBH,FJMC,FJLX,WJDX,fj) SELECT " + "(nextval FOR SQ_DGDWJFJ_ID),'"+appId+"',FJMC,FJLX,FJDX,fjwj FROM " + sourceSubM + " WHERE REFERID =" + id +"";
		System.out.println("**********fj st*********");
		final String subFjSQL = " INSERT INTO OA_JL_DGDWJFJ(FJBH,WJBH,FJMC,FJLX,WJDX,fj) SELECT " + "SQ_DGDWJFJ_ID.NEXTVAL,'"+appId+"',FJMC,FJLX,FJDX,fjwj FROM " + sourceSubM + " WHERE REFERID =" + id +"";
		System.out.println("**********subFjSQL*********"+subFjSQL);
		final String deleteFjSQL = "DELETE FROM " + sourceSubM + " WHERE REFERID =" + id ;
		System.out.println("**********deleteFjSQL*********"+deleteFjSQL);
		this.getJdbcTemplate().execute(subFjSQL);
		System.out.println("**********insert fj ok*********");
		this.getJdbcTemplate().execute(deleteFjSQL);
		System.out.println("**********delete fj ok*********");
	}

	public void doInsertDaxxToDgdwj(String sourceM, String sourceS,String sourceSubM,String id)
	{
		System.out.println("**********拆卷*********");
		String id1=getId("SQ_DGDWJ_ID");
		String rollTableName=sourceM.substring(0,sourceM.length()-2)+"AJ";
		//String insertSQL = " INSERT INTO " + sourceS + "(DGDWJBH,LJDW,DH,CJH,ZTLX,KZSYF,SJ,RQ," + "FLH,ZTGJC,RW,JG,DD,WZ,FZ,WH,ZRZ,TM,YH,BZ,MJQX,BGQX,LJDWBH) " + "SELECT BIGINT('"+id1+"'),A.LJDW,W.DH,W.CJH,W.ZTLX,W.KZSYF,W.SJ,W.RQ,W.FLH,W.ZTGJC,W.RW,W.JG,W.DD,W.WZ,W.FZ,W.WH,W.ZRZ,W.TM,W.YH," + "W.BZ,W.MJQX,W.BGQX,A.LJDWBH FROM " + sourceM + " W,"+rollTableName+" A WHERE W.ID=" + id + " AND A.ID=W.REFERID";
		System.out.println("**********id1*********"+id1);
		String insertSQL = " INSERT INTO " + sourceS + "(DGDWJBH,LJDW,DH,CJH,ZTLX,KZSYF,SJ,RQ," + "FLH,ZTGJC,RW,JG,DD,WZ,FZ,WH,ZRZ,TM,YH,BZ,MJQX,BGQX,LJDWBH) " + "SELECT "+id1+",A.LJDW,W.DH,W.CJH,W.ZTLX,W.KZSYF,W.SJ,W.RQ,W.FLH,W.ZTGJC,W.RW,W.JG,W.DD,W.WZ,W.FZ,W.WH,W.ZRZ,W.TM,W.YH," + "W.BZ,W.MJQX,W.BGQX,A.LJDWBH FROM " + sourceM + " W,"+rollTableName+"  A WHERE W.ID=" + id + " AND A.ID=W.REFERID";
		System.out.println("**********insertSQL*********"+insertSQL);
		String deleteSQL = "DELETE FROM " + sourceM + " WHERE ID = " + id +"";
		System.out.println("**********deleteSQL*********"+deleteSQL);
		doInsertDaFjToDgdwjFj(sourceSubM, id,id1);
		System.out.println("**********fj do Ok*********");
		this.getJdbcTemplate().execute(insertSQL);
		System.out.println("**********insert wj ok*********");
		this.getJdbcTemplate().execute(deleteSQL);
		System.out.println("**********del wj ok*********");
	}
	public String getId(String sequenceName)
    {

			
	    	//String query = "values nextval FOR " + sequenceName.trim()+ "";//DB2 type
	    	String query = "SELECT " + sequenceName.trim()+ ".NEXTVAL FROM DUAL";//Oracle type
 
	        return (String) this.getJdbcTemplate().execute(query,
	                new PreparedStatementCallback()
	                {
	                    public Object doInPreparedStatement(PreparedStatement p)
	                            throws SQLException, DataAccessException
	                    {
	                        long result = 1;
	                        ResultSet rs = p.executeQuery();
	                        if (rs.next())
	                        {
	                            result = rs.getLong(1);
	                        }
	                        rs.close();
	                        return result+"";
	                    }
	                });		
    }
}

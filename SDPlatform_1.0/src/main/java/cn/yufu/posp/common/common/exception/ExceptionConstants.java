package cn.yufu.posp.common.common.exception;

public interface ExceptionConstants
{

    String OA_JG_ADD = "添加失败【问题：机构代码不能重复！！！】"; 
	String OA_USER_ADD = "添加失败【问题：用户名不能重复！！！】"; 
	String OA_GROUP_ADD = "添加失败【问题：角色标识不能重复！！！】"; 
	String OA_Client_ADD = "添加失败【问题：MAC地址不能重复！！！】"; 
	
	String OA_COMMON_DBACCESS = "系统当前不能提供服务"; 
	String OA_COMMON_STRUTSACTION = "系统当前不能提供服务";
	String OA_COMMON_LOGIC = "系统当前不能提供服务";
	String OA_COMMON_NOTVALIDOPERATION="无效操作";
	String OA_OFFICEPEOPLENOTFOUND = "值班人员没有发现（无效的ID）！";
	
	String OA_LOWER_VERSION = "并发错误，版本太低了。";
	
	//值班安排部分
	String OA_OFFICEPLAN_HAVECONFIRMED = "值班计划已经确认，不能重新自动生成";
	String OA_OFFICEPLANLAST_NOTCOMPLETE = "上个月的值班安排未完成，不能生成本月的值班安排";
	
	//发文管理
	String OA_SIGNATURE_SIGNATURENAME = "该用户已存在相同的印章名称。";
	
	String OA_MEETINGMANAGMENT_DEPTNAME = "该部门不在审核部门范围内。";
	String OA_ATTENDANCE_PERSONID = "该人员在此日期中已经添加过。";
	String OA_ATTENDANCECollect_exist = "检查到考勤汇总信息已存在该选定月中";
	//工作流WorkItem并发冲突
	String OA_MFCHECK_WORK_ITEM_CLASH = "检出或检入WorkItem时并发冲突。";
	//加载表单定义出错
	String OA_FORMDESIGNER_LOADFORMDEF = "加载表单定义出错。";
//	创建表单定义出错
	String OA_FORMDESIGNER_CREATEFORMDEF = "创建表单定义出错。";
//	修改表单定义出错
	String OA_FORMDESIGNER_SAVEFORMDEF = "修改表单定义出错。";
//	转换表单的建表语句错误
	String OA_FORMDESIGNER_GENERATECREATETableSQL = "根据表单定义产生建表sql错误";	

	//数据格式错误
	String OA_ORG_NODATA = "数据不存在!!!";
	String OA_ORG_DATANUMBERERROR = "数据列数错误!!!";
    //加班汇总
    String OA_OVERTIMECOLLECT_EXISTED = "您所填写的‘加班汇总信息’已经存在,请重新填写！";
    //工作流错误
    String OA_COMMON_MFWORKFLOW = "执行工作流时出现异常。";
    
    //撤回任务时出现错误
    String OA_MFWORKFLOW_WITHDRAW_ERROR="该任务已被处理，不允许撤回。";

}

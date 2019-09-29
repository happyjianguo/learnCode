package cn.yufu.posp.common.common.util;

public interface SystemVars
{
	/**
     * 全局金格控件mClientName
     */
    final static String GOLDGRID_CLIENTNAME="iWebOffice2006.cab#version=7,4,0,0";
	/**
     * 全局处理状态-未处理
     */
    final static String GLOBAL_INFOR_STATE_WCL="01";
    /**
     * 上报信息的处理状态-处理中
     */
    final static String GLOBAL_INFOR_STATE_CLZ="02";
    /**
     * 上报信息的处理状态-已完成
     */
    final static String GLOBAL_INFOR_STATE_YWC="03";
    
    /**
     * 全局　－　意见有效标志　－　无效
     */
    final static String GLOBAL_ADVICE_VALID_N = "01";
    /**
     * 全局　－　意见有效标志　－　有效
     */
    final static String GLOBAL_ADVICE_VALID_Y = "02";
    
    
    //上报要事等查看标志:01未查看，02查看未采用，03已采用
    String XXSB_CKZT_WCK = "01";
    String XXSB_CKZT_YCK = "02";
    String XXSB_CKZT_YCY = "03";
    
    
//  流程类别与图片常量
    
    final static String FW_QITA_MZ = "其它";
    final static String FW_QITA_JPG = "fw_other.jpg";
    final static String FW_HAN_MZ = "函";
    final static String FW_HAN_JPG = "fw_han.jpg";
    final static String FW_TONGZHI_MZ = "通知";
    final static String FW_TONGZHI_JPG = "fw_tongzhi.jpg";
    final static String FW_CPJ_MZ = "呈批件";
    final static String FW_CPJ_JPG = "fw_cpj.jpg";
    final static String FW_JUEDING_MZ = "决定";
    final static String FW_JUEDING_JPG = "fw_jueding.jpg";
    final static String FW_QINGSHI_MZ = "请示";
    final static String FW_QINGSHI_JPG = "fw_qingshi.jpg";
    final static String FW_BAOGAO_MZ = "报告";
    final static String FW_BAOGAO_JPG = "fw_baogao.jpg";
    final static String FW_TONGBAO_MZ = "通报";
    final static String FW_TONGBAO_JPG = "fw_tongbao.jpg";
    final static String FW_PIFU_MZ = "批复";
    final static String FW_PIFU_JPG = "fw_pifu.jpg";
    
    final static long XXSB_MBBH = 481;
    final static long DA_MBBH_1 = 373;
    final static long DA_MBBH_2 = 374;
    
    final static long DA_456 = 456;
    final static long DA_457 = 457;
    final static long DA_458 = 458;
    final static long DA_459 = 459;
    final static long DA_460 = 460;
    
    final static String REP_TYPE_01 = "01";
    final static String REP_TYPE_02 = "02";
    
    final static String NBSX_CHELIANG_MZ = "车辆";
    final static String NBSX_CHELIANG_JPG = "nbsx_cheliang.jpg";
    final static String NBSX_QINGJIA_MZ = "请假";
    final static String NBSX_QINGJIA_JPG = "nbsx_qingjia.jpg";
    final static String NBSX_WEIXIU_MZ = "维修";
    final static String NBSX_WEIXIU_JPG = "nbsx_weixiu.jpg";
    final static String NBSX_QITA_MZ = "其它";
    final static String NBSX_QITA_JPG = "nbsx_other.jpg";
    
    
    final static String RSSX_RSDRSP_MZ = "人事调入审批";
    final static String RSSX_ENTERSP_JPG = "rssx_entersp.jpg";
    final static String RSSX_RSDRLC_MZ = "人事调入";
    final static String RSSX_ENTERSX_JPG = "rssx_entersx.jpg";
    final static String RSSX_RSDCSP_MZ = "人事调出审批";
    final static String RSSX_OUTSP_JPG = "rssx_outsp.jpg";
    final static String RSSX_RSDCLC_MZ = "人事调出";
    final static String RSSX_OUTSX_JPG = "rssx_entersx.jpg";
    final static String RSSX_QITA_MZ = "其它";
    final static String RSSX_QITA_JPG = "nbsx_other.jpg";
    
    //公文类型
    final static String GLOBAL_ADVICE_FWLC = "01";
    final static String GLOBAL_ADVICE_NBSX = "02";
    final static String GLOBAL_ADVICE_RSSX = "03";
    ////档案管理模块
    final static String DAGL_QUERYCONDITION_DAY = "up";//编目查询条件符号，大于
    final static String DAGL_QUERYCONDITION_XY = "down";//编目查询条件符号，小于
    final static String DAGL_QUERYCONDITION_DAYDEY = "upEqual";//编目查询条件符号，大于等于
    final static String DAGL_QUERYCONDITION_XYDEY = "downEqual";//编目查询条件符号，小于等于
    final static String DAGL_QUERYCONDITION_DEY = "equal";//编目查询条件符号，等于
    final static String DAGL_QUERYCONDITION_BDEY = "noEqual";//编目查询条件符号，不等于
    final static String DAGL_DEFINETYPE_AJDY = "AJ";//案卷定义
    final static String DAGL_DEFINETYPE_WJDY = "WJ";//文件定义
    final static String DAGL_DZDAXH_LCMC = "电子案卷销毁审批";//文件定义
    final static String DAGL_ZZDAXH_LCMC = "纸质案卷销毁审批";//文件定义
    
    final static String DAGL_AJLX_DZ = "01";//电子案卷
    final static String DAGL_AJLX_ZZ = "02";//纸质案卷
	
    final static String SWGL_LCMC = "收文管理"; //工作流-流程名称-收文管理
    final static String SWGL_FLOWSTATE_NB = "nb"; //收文管理-步骤类型-拟办
    final static String SWGL_FLOWSTATE_ZB= "zb"; //收文管理-步骤类型-主办
    final static String SWGL_FLOWSTATE_GD = "gd"; //收文管理-步骤类型-归档
    final static String SWGL_FLOWSTATE_TJ = "tj"; //收文管理-步骤类型-提交
    final static String SWGL_FLOWSTATE_XB = "xb"; //收文管理-步骤类型-协办
    final static String SWGL_FLOWSTATE_PS = "ps"; //收文管理-步骤类型-批示
    final static String SWGL_FLOWSTATE_ZBBL = "zbbl"; //收文管理-步骤类型-主办办理
    final static String SWGL_FLOWSTATE_XBBL = "xbbl"; //收文管理-步骤类型-协办办理
    final static String SWGL_FLOWSIGN = "01"; //收文管理-流程标志-走流程
    final static String SWGL_FLOWSIGN_N = "02"; //收文管理-流程标志-不走流程
    final static String SWGL_NONCESTATE_DJZ = "01"; //状态-登记中
    final static String SWGL_NONCESTATE_BLZ = "02"; //状态-办理中
    final static String SWGL_NONCESTATE_BLWB = "03"; //状态-办理完毕
    final static String SWGL_FILETYPE_DGD = "01"; //收文管理-待归档文件类型
    
    final static String SUBMITSIGN_N = "01"; //提交标志-未提交
    final static String SUBMITSIGN_Y = "02"; //提交标志-已提交
    final static String RWDB_SUBMITSIGN_N = "01"; //提交标志-未提交
    final static String RWDB_SUBMITSIGN_Y = "02"; //提交标志-已提交
    final static String RWDB_NONCESTATE_DJZ = "01"; //状态-登记中
    final static String RWDB_NONCESTATE_BLZ = "02"; //状态-办理中
    final static String RWDB_NONCESTATE_BLWB = "03"; //状态-办理完毕
    final static String RWDB_LCMC = "阶段进度报告"; //工作流-流程名称
    final static String RWDB_LCMC_JDXXJDBG = "季度形象进度报告"; //工作流-流程名称
    final static String RWDB_SY = "next"; //工作流-送审阅
    final static String RWDB_TH = "return"; //工作流-退回
    final static String RWDB_CYYD_SQGQ="modification";    //改期
    final static String RWDB_GB = "ok"; //工作流-公布
    final static String HY_SUBMITSIGN_Y = "02"; //提交标志-已提交
    final static String RWDB_HDZT_SY = "sybg"; //工作流-活动状态-审阅
    final static String RWDB_HDZT_SH = "shbg"; //工作流-活动状态-审核
    final static String RWDB_HDZT_ZDBG = "zdbg"; //工作流-活动状态-制定报告
    final static String RWDB_LCMC_DCTB = "督察通报"; //工作流-流程名称
    final static String RWDB_LCMC_DCTZ = "督察通知"; //工作流-流程名称
    final static String SBAQ_LCMC_DCTZ = "安全通报"; //工作流-流程名称
    final static String SBAQ_LCMC_WYWB = "物业维保"; //工作流-流程名称
    
    final static String NBSX_SUBMITSIGN_N = "01"; //提交标志-未提交
    final static String NBSX_SUBMITSIGN_Y = "02"; //提交标志-已提交
    final static String NBSX_NONCESTATE_DJZ = "01"; //状态-登记中
    final static String NBSX_NONCESTATE_BLZ = "02"; //状态-办理中
    final static String NBSX_NONCESTATE_BLWB = "03"; //状态-办理完毕
    
    final static String NBSX_SPCETYPE_NXJ = "01"; //状态-年休假
    final static String NBSX_SPCETYPE_TQJ = "02"; //状态-探亲假
    final static String NBSX_SPCETYPE_BSJ = "03"; //状态-病事假
    
    final static String NBSX_YCSC = "未超过期限不能请探亲假"; //探亲假输出语句
    
    final static String PARENT_ID = "root"; //组织机构-根目录Id
    final static String PARENT_NAME = "局领导"; //组织机构-根目录名称
    
    final static String JBHZ_NONCESTATE_DJZ = "01"; //加班汇总-状态-登记中
    final static String JBHZ_NONCESTATE_BLZ = "02"; //加班汇总-状态-办理中
    final static String JBHZ_NONCESTATE_BLWB = "03"; //加班汇总-状态-办理完毕
    final static int JBHZ_MONEY_PS = 20; //加班汇总-平时值班金额
    final static int JBHZ_MONEY_JR = 30; //加班汇总-假日值班金额
    final static int JBHZ_MONEY_TSJR = 40; //加班汇总-特殊假日值班金额
    final static double JBHZ_WORKINGDAY = 20.92; //加班汇总-特殊假日值班金额   
    final static String JBHZ_LCMCf_JBHZ = "加班汇总"; //工作流-流程名称
    final static String JBHZ_LCMCf_JDJBHZ = "季度加班汇总"; //工作流-流程名称
    final static String JBHZ_LCMC_JBHZ_YFHZ = "加班汇总月份汇总"; //工作流-流程名称
    final static String JBHZ_SIGN_N = "01"; //标志-否
    final static String JBHZ_SIGN_Y = "02"; //标志-是

    final static String JBHZ_TH = "return"; //工作流-退回
    final static String JBHZ_PZ = "ok"; //工作流-批准
    final static String JBHZ_HDZT_SH = "sh"; //工作流-活动状态-审核
    final static String JBHZ_HDZT_XG = "xg"; //工作流-活动状态-制定报告
    final static String JBHZ_HDZT_CK = "ck"; //工作流-活动状态-制定报告
    
    final static String JJJC_NONCESTATE_DJZ = "01"; //纪检监查-状态-登记中
    final static String JJJC_NONCESTATE_BLZ = "02"; //纪检监查-状态-办理中
    final static String JJJC_NONCESTATE_BLWB = "03"; //纪检监查-状态-办理完毕
    final static String JJJC_LCMC_SLBG = "述廉报告"; //工作流-流程名称
    final static String JJJC_LCMC_YGSXBG = "有关事项报告"; //工作流-流程名称
    final static String JJJC_LCMC_YGSXBGZD = "总队有关事项报告"; //工作流-流程名称
    final static String JJJC_LCMC_GBSRSB = "干部收入申报"; //工作流-流程名称
    
    final static String HQGL_NONCESTATE_DJZ = "01"; //后勤管理-状态-登记中
    final static String HQGL_NONCESTATE_BLZ = "02"; //后勤管理-状态-办理中
    final static String HQGL_NONCESTATE_BLWB = "03"; //后勤管理-状态-办理完毕
    final static String HQGL_NONCESTATE_GLYBD="06";    //后勤管理_装态- 管理员补登
    final static String HQGL_LCMC_HYSSQSH = "会议室申请审核"; //工作流-流程名称
    
    
    final static String HQGL_HDZT_SQ = "cxsq"; //工作流-活动状态-重新申请
    final static String HQGL_HDZT_STSH = "stsh"; //工作流-活动状态-食堂批示
    final static String HQGL_HDZT_SQCG = "sqcg";//工作流-活动状态-查看
    final static String HQGL_HDZT_CYSH="cysh";    //工作流-活动状态-审核
  //  final static String HQGL_HDZT_GLYSH="glysh";    //工作流-活动状态-食堂管理员审核
    
    final static String HQGL_LCMC_YCSQSH = "用车申请审核"; //工作流-流程名称
    final static String HQGL_LCMC_SBBXSQSH = "设备报修申请审核"; //工作流-流程名称
    final static String HQGL_LCMC_YLFWSQSH = "医疗服务申请审核"; //工作流-流程名称
    final static String HQGL_HDZT_CZSQ = "czsp"; //工作流-活动状态-处长审批
    final static String HQGL_HDZT_CK = "ck"; //工作流-活动状态-查看用车申请

    final static String HQGL_LCMC_CYYDSQSH = "预定用餐申请"; //工作流-流程名称-mf

    final static String HQGL_HYS_NBHY = "内部会议"; //后勤管理-会议室-内部会议
    final static String HQGL_HYS_WBHY = "外部会议"; //后勤管理-会议室-外部会议
    final static String HQGL_HYS_NBHY_VALUE = "01"; //后勤管理-会议室-内部会议
    final static String HQGL_HYS_WBHY_VALUE = "02"; //后勤管理-会议室-外部会议

    
    final static String XFGL_NONCESTATE_DJZ = "01"; //信访管理-状态-登记中
    final static String XFGL_NONCESTATE_BLZ = "02"; //信访管理-状态-办理中
    final static String XFGL_NONCESTATE_BLWB = "03"; //信访管理-状态-办理完毕
    final static String XFGL_LCMC_XFSP = "信访审批"; //工作流-流程名称
    final static String XFGL_LCMC_ZXWT = "咨询问题"; //工作流-流程名称
    final static String XFGL_LCMC_LDXX= "领导信箱"; //工作流-流程名称
    final static String XFGL_LCMC_WSZTC= "网上直通车"; //工作流-流程名称
    final static String XFGL_LCMC_JBTS= "举报投诉"; //工作流-流程名称
    final static String XFGL_LCMC_JCXFSP = "监察信访审批"; //工作流-流程名称
    
    final static String XFGL_LVTYPE_BGS = "01"; //信访管理-信访类型-办公室
    final static String XFGL_LVTYPE_ZXWT = "02"; //信访管理-信访类型-咨询问题
    final static String XFGL_LVTYPE_LDXX = "03"; //信访管理-信访类型-领导信箱
    final static String XFGL_LVTYPE_WSZTC = "04"; //信访管理-信访类型-网上直通车
    final static String XFGL_LVTYPE_JBTS = "05"; //信访管理-信访类型-举报投诉
    final static String XFGL_LVTYPE_JJJC = "06"; //信访管理-信访类型-纪检监察
    
    final static String FlOW_NEXT = "next"; //工作流-下一步
    final static String FlOW_RETURN= "return"; //工作流-退回
    final static String FlOW_OK = "ok"; //工作流-公布
    final static String XFGL_HFLB_SLHF = "0"; //信访管理-信访类型-受理回复
    final static String XFGL_HFLB_LCHF = "1"; //信访管理-信访类型-流程回复
    
    final static int XFGL_HFCLZT_WSL = 1; //信访管理-信访回复处理状态-未受理
    final static int XFGL_HFCLZT_SLZ = 2; //信访管理-信访回复处理状态-受理中
    final static int XFGL_HFCLZT_SLWB = 3; //信访管理-信访回复处理状态-受理完毕
    final static int XFGL_HFCLZT_DSL = 5; //信访管理-信访回复处理状态-待受理
    
    final static String XFGL_FLOWTYPE_TJYJ = "tjyj"; //信访管理-步骤类型-添加意见
    final static String XFGL_FLOWTYPE_YJHF = "yjhf"; //信访管理-步骤类型-意见回复
    final static String XFGL_FLOWTYPE_YJCL = "yjcl"; //信访管理-步骤类型-意见处理
    final static String XFGL_FLOWTYPE_CLHF = "clhf"; //信访管理-步骤类型-处理回复
    final static String XFGL_FLOWTYPE_FBHF = "fbhf"; //信访管理-步骤类型-发布回复
    final static String XFGL_FLOWTYPE_XZBM = "xzbm"; //信访管理-步骤类型-选择部门
    final static String XFGL_FLOWTYPE_FBYJ = "fbyj"; //信访管理-步骤类型-发布意见
    
    //值班排班模块的数据字典常量
    /**
     * 值班人员的性别-男
     */
    final static String ZBPB_RYXB_M = "01";
    
    /**
     * 值班人员的性别-女
     */
    final static String ZBPB_RYXB_F = "02";
    
    /**
     * 值班人员的性别-不限
     */
    final static String ZBPB_RYXB_BX = "03";
    
    /**
     * 值班人员的级别-局级
     */
    final static String ZBPB_RYJB_JJ = "01";
    
    /**
     * 值班人员的级别-处级
     */
    final static String ZBPB_RYJB_CJ = "02";
    /**
     * 值班人员的级别-处级
     */
    final static String ZBPB_RYJB_YJ = "03";
    /**
     * 生成的值班安排是否确认-是
     */
    final static String ZBPB_CONFIRM_Y = "01";
    
    /**
     * 生成的值班安排是否确认-否
     */
    final static String ZBPB_CONFIRM_N = "02";
    
    /**
     * 一天的类型-白天
     */
    final static String ZBPB_DAYTYPE_DAY = "01";
    
    /**
     * 一天的类型-晚上
     */
    final static String ZBPB_DAYTYPE_NIGHT = "02";
    
    /**
     * 一天的类型-一般节假日（白天）
     */
    final static String ZBPB_DAYTYPE_COMMONDAY = "03";
    
    /**
     * 一天的类型-特殊节假日（白天）
     */
    final static String ZBPB_DAYTYPE_SPECIALDAY = "04";
    
    
    //信息上报模块的数据字典常量
    /**
     * 上报信息的类型-上报要事
     */
    final static String XXSB_INFOR_TYPE_YS="01";
    
    /**
     * 汇总要事的类型-局内要事
     */
    final static String XXSB_ACCIDENT_TYPE_JNYS="01";
    
    /**
     * 汇总要事的类型-工作动态
     */
    final static String XXSB_ACCIDENT_TYPE_GZDT="02";
    
    /**
     * 汇总要事的类型-大事记
     */
    final static String XXSB_ACCIDENT_TYPE_DSJ="01";
    
    
    /**
     * 上报信息的类型-局内要事
     */
    final static String XXSB_INFOR_TYPE_JNYS="02";
    
    /**
     * 上报信息的类型-工作动态
     */
    final static String XXSB_INFOR_TYPE_GZDT="03";
    
    /**
     * 上报信息的类型-大事记
     */
    final static String XXSB_INFOR_TYPE_DSJ="04";
    
    /**
     * 上报信息的类型-经济动态
     */
    final static String XXSB_INFOR_TYPE_JJDT="05";
    
    /**
     * 上报信息的类型-信息摘编
     */
    final static String XXSB_INFOR_TYPE_XXZB="06";
    
    /**
     * 上报信息的类型-上报市政府信息
     */
    final static String XXSB_INFOR_TYPE_SZFXX="07";
     
    /**
     * 上报信息是否提交标志-未提交
     */
    final static String XXSB_INFOR_SUBMIT_N="01";
    
    /**
     * 上报信息是否提交标志-已提交
     */
    final static String XXSB_INFOR_SUBMIT_Y="02";
    
    /**
     * 上报信息是否汇总标志-未汇总
     */
    final static String XXSB_INFOR_COLLECT_N="01";
    
    /**
     * 上报信息是否汇总标志-已汇总
     */
    final static String XXSB_INFOR_COLLECT_Y="02";
    
    /**
     * 上报信息的处理状态-未处理
     */
    final static String XXSB_INFOR_STATE_WCL="01";
    /**
     * 上报信息的处理状态-处理中
     */
    final static String XXSB_INFOR_STATE_CLZ="02";
    /**
     * 上报信息的处理状态-已完成
     */
    final static String XXSB_INFOR_STATE_YWC="03";
    
    /**
     * 上报信息意见有效标志 - 无效
     */
    final static String XXSB_ADVICE_VALID_N="01";
    
    /**
     * 上报信息意见有效标志 - 有效
     */
    final static String XXSB_ADVICE_VALID_Y="02";
   
    
    /**
     * 领导日程－未提交
     */
    final static String LDRC_SUBMIT_N = "01";
    
    /**
     * 领导日程－已提交
     */
    final static String LDRC_SUBMIT_Y = "02";
    
    /**
     * 领导日程－未发布
     */
    final static String LDRC_RELEASE_N = "01";
    
    /**
     * 领导日程－已发布
     */
    final static String LDRC_RELEASE_Y = "02";
    
    
    /**
     * Display排序方式-顺序
     */
    final static String DISPLAYTAG_ORDER_ASC="2";
    
    /**
     * Display排序方式-顺序
     */
    final static String DISPLAYTAG_ORDER_DESC="1";
    
    //会议管理模块
    final static String HYSQD_HYLB_DANG="1";//党组
    final static String HYSQD_HYLB_JU="2";//局长
    final static String HYSQD_HYLB_OTHER="3";//其它
    
    final static String HYSQD_QCHY_QCZ="0";//起草中
    final static String HYSQD_QCHY_CLZ="1";//处理中
    final static String HYSQD_QCHY_CLJS="2";//处理完毕
    //缺勤管理模块
    final static String GZKQXXGL_QQXX_QJ="0";//请假
    final static String GZKQXXGL_QQXX_KG="1";//旷工
    
    final static String GZKQXXGL_QQXX_LCJ="0";//路程假
    final static String GZKQXXGL_QQXX_CJ="1";//产假
    final static String GZKQXXGL_QQXX_XJ="2";//学习
    final static String GZKQXXGL_QQXX_TJ="3";//探亲假
    final static String GZKQXXGL_QQXX_HJ="4";//婚假
    final static String GZKQXXGL_QQXX_GLJ="5";//工龄假
    final static String GZKQXXGL_QQXX_GJ="6";//公假
    final static String GZKQXXGL_QQXX_SJ="7";//事假
    final static String GZKQXXGL_QQXX_BJ="8";//病假
    
    final static String GZKQXXGL_QQXX_SBSJ="8:00";//上班时间
    final static String GZKQXXGL_QQXX_XBSJ="17:00";//下班时间
    
    final static String GZKQXXGL_KQXX_JU="1";//局领导
    final static String GZKQXXGL_KQXX_CS="0";//处室
    
    final static String GZKQXXGL_KQHZ_QCZ="0";//起草中
    final static String GZKQXXGL_KQHZ_CLZ="1";//处理中
    final static String GZKQXXGL_KQHZ_CLJS="2";//处理完毕
    
    final static String GZKQXXGL_KQHZ_WFB="0";//未发布
    final static String GZKQXXGL_KQHZ_FB="1";//已发布
    
    final static String GZKQXXGL_KQHZ_VALID_N = "0";//意见无效标志
    final static String GZKQXXGL_KQHZ_VALID_Y = "1";//意见有效标志
    /**
     * 内部事项　－　意见有效标志　－　无效
     */
    final static String NBSX_ADVICE_VALID_N = "01";
    /**
     * 内部事项　－　意见有效标志　－　有效
     */
    final static String NBSX_ADVICE_VALID_Y = "02";

    final static String GZKQXXGL_GSWC_Y="1";
    final static String GZKQXXGL_GSWC_N="0";
    /**
     * 档案移交
     */
    //当前状态
    final static String DAYJ_INFOR_STATE_WCL="01";
    final static String DAYJ_INFOR_STATE_CLZ="02";
    final static String DAYJ_INFOR_STATE_YWC="03";
    //意见是否有效
    final static String DAYJ_ADVICE_VALID_Y = "02";
    final static String DAYJ_ADVICE_VALID_N = "01";
    //是否提交
    final static String DAYJ_INFOR_SUBMIT_N="01";
    final static String DAYJ_INFOR_SUBMIT_Y="02";
    /**
     * 用户信息类型类别
     */
    final static String ORG_USERINFOTYPE_BASIC_VALUE="01";
    final static String ORG_USERINFOTYPE_BASIC_LABEL="基本信息";
    final static String ORG_USERINFOTYPE_EXTEND_VALUE="02";
    final static String ORG_USERINFOTYPE_EXTEND_LABEL="扩展信息";
    final static String ORG_USERINFOTYPE_RELATION_VALUE="03";
    final static String ORG_USERINFOTYPE_RELATION_LABEL="相关信息";
    final static String ORG_USERINFOTABLE_PREFIX="OA_JL_USERINFO";
    final static String ORG_USERINFOFIELD_PREFIX="YHXX";
    
    final static String ORG_USERINFOModifyMode_INITIAL="01";
    final static String ORG_USERINFOModifyMode_MODIFY="02";
   
    /*
     * 事项类别定义
     */
    final static String THING_INTERIOR_VALUE="01";
    final static String THING_INTERIOR_LABEL="内部事项";
    final static String THING_HUMANRESOURCE_VALUE="02";
    final static String THING_HUMANRESOURCE_LABEL="人事事项";
    final static String THING_ECONOMIZEREPORT_VALUE="03";
    //final static String THING_ECONOMIZEREPORT_VALUE="04";
    final static String THING_RECEIVEDFILE_VALUE="05";
    /**
     * 执法检查单位类型-表种类型
     */
    //工业
    final static String TJZF_PARAM_TABLETYPE_B = "B";
    //房地产
    final static String TJZF_PARAM_TABLETYPE_X = "X";
    //餐饮
    final static String TJZF_PARAM_TABLETYPE_A = "A";
    //建筑   
    final static String TJZF_PARAM_TABLETYPE_C = "C";
    //服务业
    final static String TJZF_PARAM_TABLETYPE_F = "F";
    //批零    
    final static String TJZF_PARAM_TABLETYPE_D = "D";

    final static String TJZF_PARAM_TABLETYPE_PL = "01"; 	//批零
    final static String TJZF_PARAM_TABLETYPE_CY = "02";	//餐饮
    final static String TJZF_PARAM_TABLETYPE_FDC = "03";	//房地产
    final static String TJZF_PARAM_TABLETYPE_FW = "04";	//服务业
    final static String TJZF_PARAM_TABLETYPE_GY = "05";	//工业
    final static String TJZF_PARAM_TABLETYPE_JZ = "06";	//建筑
    /**
     * 执法统计检查单位区不走流程
     */
    final static String TJZF_PLANTYPE_02 = "02";
    /**
     * 执法统计检查单位 走流程
     */
    final static String TJZF_PLANTYPE_01 = "01";
    
    /**
     * 执法统计检查参数类型-违法类型
     */
    final static String TJZF_PARAM_TYPE_ILLEGAL = "01";
    /**
     * 执法统计检查参数类型-行政处罚类型
     */
    final static String TJZF_PARAM_TYPE_PUNISH = "02";
    /**
     * 执法统计检查参数类型-信用类型
     */
    final static String TJZF_PARAM_TYPE_CREDIT = "03";
    
    /**
     * 执法统计检查参数类型-缴款类型
     */
    final static String TJZF_PARAM_TYPE_JKLX = "04";
    /**
     * 执法统计检查参数类型-处理程序类型
     */
    final static String TJZF_PARAM_TYPE_CLCXLX = "05";
    
    /**
     * 执法统计月报明细类型-违法
     */
    final static String TJZF_MONTHLYPAPER_TYPE_ILLEGAL = "01";

    /**
     * 执法统计月报明细类型-处理程序
     */
    final static String TJZF_MONTHLYPAPER_TYPE_CLCX = "06";
    /**
     * 执法统计月报明细类型-处理程序(结安)
     */
    final static String TJZF_MONTHLYPAPER_TYPE_CLCXJA = "07";
    
    /**
     * 执法统计月报明细类型-决定处罚
     */
    final static String TJZF_MONTHLYPAPER_TYPE_DECIDEDPUNISH="02";
    
    /**
     * 执法统计月报明细类型-执行处罚之非法类型
     */
    final static String TJZF_MONTHLYPAPER_TYPE_EXECUTEDILLEGAL="03";
    
    /**
     * 执法统计月报明细类型-执行处罚之处罚类型
     */
    final static String TJZF_MONTHLYPAPER_TYPE_EXECUTEDTYPE="04";
    
    /**
     * 执法统计月报明细类型-信用记录类型
     */
    final static String TJZF_MONTHLYPAPER_TYPE_CREDIT = "05";
    /**
     * 执法信息-指标权限-无
     */
    final static String TJZF_INDEX_ACCESS_NONE = "01";
    /**
     * 执法信息-指标权限-只读
     */
    final static String TJZF_INDEX_ACCESS_READONLY= "02";
    /**
     * 执法信息-指标权限-编辑
     */
    final static String TJZF_INDEX_ACCESS_EDIT= "03";
    /**
     * 执法信息基本信息-单位信息
     */
    final static String TJZF_BASICINFO_TYPE_UNITINFO = "01";
    /**
     * 执法信息基本信息-检查信息
     */
    final static String TJZF_BASICINFO_TYPE_CHECKINFO = "02";
    /**
     * 执法信息基本信息-审理信息
     */
    final static String TJZF_BASICINFO_TYPE_INQUISITINFO = "03";
    /**
     * 执法信息基本信息-后期处理信息
     */
    final static String TJZF_BASICINFO_TYPE_LATERHANDLEINFO = "04";   
    /**
     * 执法信息基本信息-行政复议信息
     */
    final static String TJZF_BASICINFO_TYPE_EXECREPEATADVINFO = "05";   
    /**
     * 执法信息基本信息-听证信息
     */
    final static String TJZF_BASICINFO_TYPE_AUDITINFO = "06"; 
    /**
     * 执法信息基本信息-执行罚款
     */
    final static String TJZF_BASICINFO_TYPE_EXTPUNISH = "07"; 
    /**
     * 执法信息基本信息-文号
     */
    final static String TJZF_BASICINFO_TYPE_WH = "08"; 
    
    final static String TJZF_BASICINFO_TYPE_WHHZ = "00";    
    /**
     * 执法信息基本信息显示类型-编辑类型
     */
    final static String TJZF_BASICINFO_SHOWTYPE_EDIT = "01";
    /**
     * 执法信息基本信息显示类型-隐藏类型
     */
    final static String TJZF_BASICINFO_SHOWTYPE_HIDE = "02";
    /**
     * 执法信息基本信息显示类型-只读类型
     */
    final static String TJZF_BASICINFO_SHOWTYPE_READONLY = "03";
    
    /**
     * 电子借阅档案－无查看权限
     */
    final static int DAGL_DZDACKQX_N=0;
    
    /**
     * 电子借阅档案－有查看权限
     */
    final static int DAGL_DZDACKQX_Y=1;

    
    /**
     * 电子借阅档案－有查看权限
     */
    final static int DAGL_DZDACKQX_BORROW=2;
    
    
    /**
     * 案卷状态-未提交
     */
    String DAAJ_ZT_WTJ = "01";
    
    /**
     * 案卷状态-待移交
     */
    String DAAJ_ZT_DYJ = "02";
    
    /**
     * 案卷状态-移交中
     */
    String DAAJ_ZT_YJZ = "03";
    /**
     * 案卷状态-已移交
     */
    String DAAJ_ZT_YYJ = "04";

    /**
     * 导入档案类型－文书
     */
    final static String DAGL_ARCHIVETYPE_TEXT="01";
    /**
     * 导入档案类型－统计
     */
    final static String DAGL_ARCHIVETYPE_STATISTICS="02";
    /**
     * 导入档案类型－科技
     */
    final static String DAGL_ARCHIVETYPE_TECHNOLOGY="03";
    
    
    /**
     * 待归档表名
     */
    final static String DGD_TABLE_NAME="OA_DGDWJ";
    
    /**
     * 催办类型－－流程实例
     */
    final static String CB_TYPE_LCSL = "01";
    
    /**
     * 值班人员管理－－正常
     */
    final static String ZBRYGL_ZC = "01";
    /**
     * 值班人员管理－－正常
     */
    final static String ZBRYGL_ZT = "02";
    
    /**
     * 催办类型－－活动实例
     */
    final static String CB_TYPE_HDSL = "02";
    
    /**
     * 催办等级－－中级
     */
    final static int CB_LEVEL_MIDDLE = 1;
    
    /**
     * 催办等级－－高级
     */
    final static int CB_LEVEL_HIGH = 2;
    
    /**
     * 催办等级－－特级
     */
    final static int CB_LEVEL_SPECIAL = 3;
    static final String HQGL_LCMC_CZYCSQSH = "处长用车申请审核";
    
    
    /**
     * 值班排班业务日历ID
     */
    String ZBPB_CALENDAR_ID ="zbpb";
    
    /**
     * 存储普通档案查询条件的关键字
     */
    String DAGL_COMMONQUERYCONDITIONS_KEY="commonquery";
    /**
     * 存储普通档案查询条件的关键字
     */
    String DAGL_FULLTEXTCONDITIONS_KEY="fulltextquery";
    
	//频道变动修改变量声明
    final static String D_NW_PDID="1380";//内网发布频道编号
    final static String D_WW_PDID="1421";//外网发布频道编号

    
    final static String FBPD_JNTZ="1000";//局内通知 1000
    final static String FBPD_QXTZ="1535";//区县通知 1535
    final static String FBPD_ZJXTTZ="1536";//统计系统通知 1536

    final static String XXSB_YSLL_YQXX="要情信息";
    final static String XXSB_YSLL_GZDT="工作动态";
    final static String XXSB_YSLL_DSJ="大事记";
    final static String XXSB_YSLL_ZWXX="政务信息";
    final static String XXSB_YSLL_QT="其它(信息上报)";
    
    final static String DOCNUMBER_NUMBER_FLAG_EDIT ="03";    	//执法文号维护状态
    final static String DOCNUMBER_NUMBER_FLAG_DELETE ="02";  	//执法文号删除
    final static String DOCNUMBER_NUMBER_FLAG_OK ="01";	 	//执法文号末删除

    final static String DOCNUMBER_NUMBER_TYPE_01 ="01";		//执法类别_01 检查通知书文号
    final static String DOCNUMBER_NUMBER_TYPE_02 ="02";		//执法类别_02 立案文号
    final static String DOCNUMBER_NUMBER_TYPE_03 ="03";		//执法类别_03 事先告知文号
    final static String DOCNUMBER_NUMBER_TYPE_04 ="04";		//执法类别    听证告知文号
    final static String DOCNUMBER_NUMBER_TYPE_05 ="05";		//执法类别    处理呈批文号
    final static String DOCNUMBER_NUMBER_TYPE_06 ="06";		//执法类别    处罚决定文号
    final static String DOCNUMBER_NUMBER_TYPE_07 ="07";		//执法类别    结案呈批文号
    final static String DOCNUMBER_NUMBER_TYPE_08 ="08";		//执法类别    销案文号
    final static String DOCNUMBER_NUMBER_TYPE_00 ="00";		//执法类别    手动生成文号
    
}

    

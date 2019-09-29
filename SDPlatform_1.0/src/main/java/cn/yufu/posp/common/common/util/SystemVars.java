package cn.yufu.posp.common.common.util;

public interface SystemVars
{
	/**
     * ȫ�ֽ��ؼ�mClientName
     */
    final static String GOLDGRID_CLIENTNAME="iWebOffice2006.cab#version=7,4,0,0";
	/**
     * ȫ�ִ���״̬-δ����
     */
    final static String GLOBAL_INFOR_STATE_WCL="01";
    /**
     * �ϱ���Ϣ�Ĵ���״̬-������
     */
    final static String GLOBAL_INFOR_STATE_CLZ="02";
    /**
     * �ϱ���Ϣ�Ĵ���״̬-�����
     */
    final static String GLOBAL_INFOR_STATE_YWC="03";
    
    /**
     * ȫ�֡����������Ч��־��������Ч
     */
    final static String GLOBAL_ADVICE_VALID_N = "01";
    /**
     * ȫ�֡����������Ч��־��������Ч
     */
    final static String GLOBAL_ADVICE_VALID_Y = "02";
    
    
    //�ϱ�Ҫ�µȲ鿴��־:01δ�鿴��02�鿴δ���ã�03�Ѳ���
    String XXSB_CKZT_WCK = "01";
    String XXSB_CKZT_YCK = "02";
    String XXSB_CKZT_YCY = "03";
    
    
//  ���������ͼƬ����
    
    final static String FW_QITA_MZ = "����";
    final static String FW_QITA_JPG = "fw_other.jpg";
    final static String FW_HAN_MZ = "��";
    final static String FW_HAN_JPG = "fw_han.jpg";
    final static String FW_TONGZHI_MZ = "֪ͨ";
    final static String FW_TONGZHI_JPG = "fw_tongzhi.jpg";
    final static String FW_CPJ_MZ = "������";
    final static String FW_CPJ_JPG = "fw_cpj.jpg";
    final static String FW_JUEDING_MZ = "����";
    final static String FW_JUEDING_JPG = "fw_jueding.jpg";
    final static String FW_QINGSHI_MZ = "��ʾ";
    final static String FW_QINGSHI_JPG = "fw_qingshi.jpg";
    final static String FW_BAOGAO_MZ = "����";
    final static String FW_BAOGAO_JPG = "fw_baogao.jpg";
    final static String FW_TONGBAO_MZ = "ͨ��";
    final static String FW_TONGBAO_JPG = "fw_tongbao.jpg";
    final static String FW_PIFU_MZ = "����";
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
    
    final static String NBSX_CHELIANG_MZ = "����";
    final static String NBSX_CHELIANG_JPG = "nbsx_cheliang.jpg";
    final static String NBSX_QINGJIA_MZ = "���";
    final static String NBSX_QINGJIA_JPG = "nbsx_qingjia.jpg";
    final static String NBSX_WEIXIU_MZ = "ά��";
    final static String NBSX_WEIXIU_JPG = "nbsx_weixiu.jpg";
    final static String NBSX_QITA_MZ = "����";
    final static String NBSX_QITA_JPG = "nbsx_other.jpg";
    
    
    final static String RSSX_RSDRSP_MZ = "���µ�������";
    final static String RSSX_ENTERSP_JPG = "rssx_entersp.jpg";
    final static String RSSX_RSDRLC_MZ = "���µ���";
    final static String RSSX_ENTERSX_JPG = "rssx_entersx.jpg";
    final static String RSSX_RSDCSP_MZ = "���µ�������";
    final static String RSSX_OUTSP_JPG = "rssx_outsp.jpg";
    final static String RSSX_RSDCLC_MZ = "���µ���";
    final static String RSSX_OUTSX_JPG = "rssx_entersx.jpg";
    final static String RSSX_QITA_MZ = "����";
    final static String RSSX_QITA_JPG = "nbsx_other.jpg";
    
    //��������
    final static String GLOBAL_ADVICE_FWLC = "01";
    final static String GLOBAL_ADVICE_NBSX = "02";
    final static String GLOBAL_ADVICE_RSSX = "03";
    ////��������ģ��
    final static String DAGL_QUERYCONDITION_DAY = "up";//��Ŀ��ѯ�������ţ�����
    final static String DAGL_QUERYCONDITION_XY = "down";//��Ŀ��ѯ�������ţ�С��
    final static String DAGL_QUERYCONDITION_DAYDEY = "upEqual";//��Ŀ��ѯ�������ţ����ڵ���
    final static String DAGL_QUERYCONDITION_XYDEY = "downEqual";//��Ŀ��ѯ�������ţ�С�ڵ���
    final static String DAGL_QUERYCONDITION_DEY = "equal";//��Ŀ��ѯ�������ţ�����
    final static String DAGL_QUERYCONDITION_BDEY = "noEqual";//��Ŀ��ѯ�������ţ�������
    final static String DAGL_DEFINETYPE_AJDY = "AJ";//������
    final static String DAGL_DEFINETYPE_WJDY = "WJ";//�ļ�����
    final static String DAGL_DZDAXH_LCMC = "���Ӱ�����������";//�ļ�����
    final static String DAGL_ZZDAXH_LCMC = "ֽ�ʰ�����������";//�ļ�����
    
    final static String DAGL_AJLX_DZ = "01";//���Ӱ���
    final static String DAGL_AJLX_ZZ = "02";//ֽ�ʰ���
	
    final static String SWGL_LCMC = "���Ĺ���"; //������-��������-���Ĺ���
    final static String SWGL_FLOWSTATE_NB = "nb"; //���Ĺ���-��������-���
    final static String SWGL_FLOWSTATE_ZB= "zb"; //���Ĺ���-��������-����
    final static String SWGL_FLOWSTATE_GD = "gd"; //���Ĺ���-��������-�鵵
    final static String SWGL_FLOWSTATE_TJ = "tj"; //���Ĺ���-��������-�ύ
    final static String SWGL_FLOWSTATE_XB = "xb"; //���Ĺ���-��������-Э��
    final static String SWGL_FLOWSTATE_PS = "ps"; //���Ĺ���-��������-��ʾ
    final static String SWGL_FLOWSTATE_ZBBL = "zbbl"; //���Ĺ���-��������-�������
    final static String SWGL_FLOWSTATE_XBBL = "xbbl"; //���Ĺ���-��������-Э�����
    final static String SWGL_FLOWSIGN = "01"; //���Ĺ���-���̱�־-������
    final static String SWGL_FLOWSIGN_N = "02"; //���Ĺ���-���̱�־-��������
    final static String SWGL_NONCESTATE_DJZ = "01"; //״̬-�Ǽ���
    final static String SWGL_NONCESTATE_BLZ = "02"; //״̬-������
    final static String SWGL_NONCESTATE_BLWB = "03"; //״̬-�������
    final static String SWGL_FILETYPE_DGD = "01"; //���Ĺ���-���鵵�ļ�����
    
    final static String SUBMITSIGN_N = "01"; //�ύ��־-δ�ύ
    final static String SUBMITSIGN_Y = "02"; //�ύ��־-���ύ
    final static String RWDB_SUBMITSIGN_N = "01"; //�ύ��־-δ�ύ
    final static String RWDB_SUBMITSIGN_Y = "02"; //�ύ��־-���ύ
    final static String RWDB_NONCESTATE_DJZ = "01"; //״̬-�Ǽ���
    final static String RWDB_NONCESTATE_BLZ = "02"; //״̬-������
    final static String RWDB_NONCESTATE_BLWB = "03"; //״̬-�������
    final static String RWDB_LCMC = "�׶ν��ȱ���"; //������-��������
    final static String RWDB_LCMC_JDXXJDBG = "����������ȱ���"; //������-��������
    final static String RWDB_SY = "next"; //������-������
    final static String RWDB_TH = "return"; //������-�˻�
    final static String RWDB_CYYD_SQGQ="modification";    //����
    final static String RWDB_GB = "ok"; //������-����
    final static String HY_SUBMITSIGN_Y = "02"; //�ύ��־-���ύ
    final static String RWDB_HDZT_SY = "sybg"; //������-�״̬-����
    final static String RWDB_HDZT_SH = "shbg"; //������-�״̬-���
    final static String RWDB_HDZT_ZDBG = "zdbg"; //������-�״̬-�ƶ�����
    final static String RWDB_LCMC_DCTB = "����ͨ��"; //������-��������
    final static String RWDB_LCMC_DCTZ = "����֪ͨ"; //������-��������
    final static String SBAQ_LCMC_DCTZ = "��ȫͨ��"; //������-��������
    final static String SBAQ_LCMC_WYWB = "��ҵά��"; //������-��������
    
    final static String NBSX_SUBMITSIGN_N = "01"; //�ύ��־-δ�ύ
    final static String NBSX_SUBMITSIGN_Y = "02"; //�ύ��־-���ύ
    final static String NBSX_NONCESTATE_DJZ = "01"; //״̬-�Ǽ���
    final static String NBSX_NONCESTATE_BLZ = "02"; //״̬-������
    final static String NBSX_NONCESTATE_BLWB = "03"; //״̬-�������
    
    final static String NBSX_SPCETYPE_NXJ = "01"; //״̬-���ݼ�
    final static String NBSX_SPCETYPE_TQJ = "02"; //״̬-̽�׼�
    final static String NBSX_SPCETYPE_BSJ = "03"; //״̬-���¼�
    
    final static String NBSX_YCSC = "δ�������޲�����̽�׼�"; //̽�׼�������
    
    final static String PARENT_ID = "root"; //��֯����-��Ŀ¼Id
    final static String PARENT_NAME = "���쵼"; //��֯����-��Ŀ¼����
    
    final static String JBHZ_NONCESTATE_DJZ = "01"; //�Ӱ����-״̬-�Ǽ���
    final static String JBHZ_NONCESTATE_BLZ = "02"; //�Ӱ����-״̬-������
    final static String JBHZ_NONCESTATE_BLWB = "03"; //�Ӱ����-״̬-�������
    final static int JBHZ_MONEY_PS = 20; //�Ӱ����-ƽʱֵ����
    final static int JBHZ_MONEY_JR = 30; //�Ӱ����-����ֵ����
    final static int JBHZ_MONEY_TSJR = 40; //�Ӱ����-�������ֵ����
    final static double JBHZ_WORKINGDAY = 20.92; //�Ӱ����-�������ֵ����   
    final static String JBHZ_LCMCf_JBHZ = "�Ӱ����"; //������-��������
    final static String JBHZ_LCMCf_JDJBHZ = "���ȼӰ����"; //������-��������
    final static String JBHZ_LCMC_JBHZ_YFHZ = "�Ӱ�����·ݻ���"; //������-��������
    final static String JBHZ_SIGN_N = "01"; //��־-��
    final static String JBHZ_SIGN_Y = "02"; //��־-��

    final static String JBHZ_TH = "return"; //������-�˻�
    final static String JBHZ_PZ = "ok"; //������-��׼
    final static String JBHZ_HDZT_SH = "sh"; //������-�״̬-���
    final static String JBHZ_HDZT_XG = "xg"; //������-�״̬-�ƶ�����
    final static String JBHZ_HDZT_CK = "ck"; //������-�״̬-�ƶ�����
    
    final static String JJJC_NONCESTATE_DJZ = "01"; //�ͼ���-״̬-�Ǽ���
    final static String JJJC_NONCESTATE_BLZ = "02"; //�ͼ���-״̬-������
    final static String JJJC_NONCESTATE_BLWB = "03"; //�ͼ���-״̬-�������
    final static String JJJC_LCMC_SLBG = "��������"; //������-��������
    final static String JJJC_LCMC_YGSXBG = "�й������"; //������-��������
    final static String JJJC_LCMC_YGSXBGZD = "�ܶ��й������"; //������-��������
    final static String JJJC_LCMC_GBSRSB = "�ɲ������걨"; //������-��������
    
    final static String HQGL_NONCESTATE_DJZ = "01"; //���ڹ���-״̬-�Ǽ���
    final static String HQGL_NONCESTATE_BLZ = "02"; //���ڹ���-״̬-������
    final static String HQGL_NONCESTATE_BLWB = "03"; //���ڹ���-״̬-�������
    final static String HQGL_NONCESTATE_GLYBD="06";    //���ڹ���_װ̬- ����Ա����
    final static String HQGL_LCMC_HYSSQSH = "�������������"; //������-��������
    
    
    final static String HQGL_HDZT_SQ = "cxsq"; //������-�״̬-��������
    final static String HQGL_HDZT_STSH = "stsh"; //������-�״̬-ʳ����ʾ
    final static String HQGL_HDZT_SQCG = "sqcg";//������-�״̬-�鿴
    final static String HQGL_HDZT_CYSH="cysh";    //������-�״̬-���
  //  final static String HQGL_HDZT_GLYSH="glysh";    //������-�״̬-ʳ�ù���Ա���
    
    final static String HQGL_LCMC_YCSQSH = "�ó��������"; //������-��������
    final static String HQGL_LCMC_SBBXSQSH = "�豸�����������"; //������-��������
    final static String HQGL_LCMC_YLFWSQSH = "ҽ�Ʒ����������"; //������-��������
    final static String HQGL_HDZT_CZSQ = "czsp"; //������-�״̬-��������
    final static String HQGL_HDZT_CK = "ck"; //������-�״̬-�鿴�ó�����

    final static String HQGL_LCMC_CYYDSQSH = "Ԥ���ò�����"; //������-��������-mf

    final static String HQGL_HYS_NBHY = "�ڲ�����"; //���ڹ���-������-�ڲ�����
    final static String HQGL_HYS_WBHY = "�ⲿ����"; //���ڹ���-������-�ⲿ����
    final static String HQGL_HYS_NBHY_VALUE = "01"; //���ڹ���-������-�ڲ�����
    final static String HQGL_HYS_WBHY_VALUE = "02"; //���ڹ���-������-�ⲿ����

    
    final static String XFGL_NONCESTATE_DJZ = "01"; //�ŷù���-״̬-�Ǽ���
    final static String XFGL_NONCESTATE_BLZ = "02"; //�ŷù���-״̬-������
    final static String XFGL_NONCESTATE_BLWB = "03"; //�ŷù���-״̬-�������
    final static String XFGL_LCMC_XFSP = "�ŷ�����"; //������-��������
    final static String XFGL_LCMC_ZXWT = "��ѯ����"; //������-��������
    final static String XFGL_LCMC_LDXX= "�쵼����"; //������-��������
    final static String XFGL_LCMC_WSZTC= "����ֱͨ��"; //������-��������
    final static String XFGL_LCMC_JBTS= "�ٱ�Ͷ��"; //������-��������
    final static String XFGL_LCMC_JCXFSP = "����ŷ�����"; //������-��������
    
    final static String XFGL_LVTYPE_BGS = "01"; //�ŷù���-�ŷ�����-�칫��
    final static String XFGL_LVTYPE_ZXWT = "02"; //�ŷù���-�ŷ�����-��ѯ����
    final static String XFGL_LVTYPE_LDXX = "03"; //�ŷù���-�ŷ�����-�쵼����
    final static String XFGL_LVTYPE_WSZTC = "04"; //�ŷù���-�ŷ�����-����ֱͨ��
    final static String XFGL_LVTYPE_JBTS = "05"; //�ŷù���-�ŷ�����-�ٱ�Ͷ��
    final static String XFGL_LVTYPE_JJJC = "06"; //�ŷù���-�ŷ�����-�ͼ���
    
    final static String FlOW_NEXT = "next"; //������-��һ��
    final static String FlOW_RETURN= "return"; //������-�˻�
    final static String FlOW_OK = "ok"; //������-����
    final static String XFGL_HFLB_SLHF = "0"; //�ŷù���-�ŷ�����-����ظ�
    final static String XFGL_HFLB_LCHF = "1"; //�ŷù���-�ŷ�����-���̻ظ�
    
    final static int XFGL_HFCLZT_WSL = 1; //�ŷù���-�ŷûظ�����״̬-δ����
    final static int XFGL_HFCLZT_SLZ = 2; //�ŷù���-�ŷûظ�����״̬-������
    final static int XFGL_HFCLZT_SLWB = 3; //�ŷù���-�ŷûظ�����״̬-�������
    final static int XFGL_HFCLZT_DSL = 5; //�ŷù���-�ŷûظ�����״̬-������
    
    final static String XFGL_FLOWTYPE_TJYJ = "tjyj"; //�ŷù���-��������-������
    final static String XFGL_FLOWTYPE_YJHF = "yjhf"; //�ŷù���-��������-����ظ�
    final static String XFGL_FLOWTYPE_YJCL = "yjcl"; //�ŷù���-��������-�������
    final static String XFGL_FLOWTYPE_CLHF = "clhf"; //�ŷù���-��������-����ظ�
    final static String XFGL_FLOWTYPE_FBHF = "fbhf"; //�ŷù���-��������-�����ظ�
    final static String XFGL_FLOWTYPE_XZBM = "xzbm"; //�ŷù���-��������-ѡ����
    final static String XFGL_FLOWTYPE_FBYJ = "fbyj"; //�ŷù���-��������-�������
    
    //ֵ���Ű�ģ��������ֵ䳣��
    /**
     * ֵ����Ա���Ա�-��
     */
    final static String ZBPB_RYXB_M = "01";
    
    /**
     * ֵ����Ա���Ա�-Ů
     */
    final static String ZBPB_RYXB_F = "02";
    
    /**
     * ֵ����Ա���Ա�-����
     */
    final static String ZBPB_RYXB_BX = "03";
    
    /**
     * ֵ����Ա�ļ���-�ּ�
     */
    final static String ZBPB_RYJB_JJ = "01";
    
    /**
     * ֵ����Ա�ļ���-����
     */
    final static String ZBPB_RYJB_CJ = "02";
    /**
     * ֵ����Ա�ļ���-����
     */
    final static String ZBPB_RYJB_YJ = "03";
    /**
     * ���ɵ�ֵ�ల���Ƿ�ȷ��-��
     */
    final static String ZBPB_CONFIRM_Y = "01";
    
    /**
     * ���ɵ�ֵ�ల���Ƿ�ȷ��-��
     */
    final static String ZBPB_CONFIRM_N = "02";
    
    /**
     * һ�������-����
     */
    final static String ZBPB_DAYTYPE_DAY = "01";
    
    /**
     * һ�������-����
     */
    final static String ZBPB_DAYTYPE_NIGHT = "02";
    
    /**
     * һ�������-һ��ڼ��գ����죩
     */
    final static String ZBPB_DAYTYPE_COMMONDAY = "03";
    
    /**
     * һ�������-����ڼ��գ����죩
     */
    final static String ZBPB_DAYTYPE_SPECIALDAY = "04";
    
    
    //��Ϣ�ϱ�ģ��������ֵ䳣��
    /**
     * �ϱ���Ϣ������-�ϱ�Ҫ��
     */
    final static String XXSB_INFOR_TYPE_YS="01";
    
    /**
     * ����Ҫ�µ�����-����Ҫ��
     */
    final static String XXSB_ACCIDENT_TYPE_JNYS="01";
    
    /**
     * ����Ҫ�µ�����-������̬
     */
    final static String XXSB_ACCIDENT_TYPE_GZDT="02";
    
    /**
     * ����Ҫ�µ�����-���¼�
     */
    final static String XXSB_ACCIDENT_TYPE_DSJ="01";
    
    
    /**
     * �ϱ���Ϣ������-����Ҫ��
     */
    final static String XXSB_INFOR_TYPE_JNYS="02";
    
    /**
     * �ϱ���Ϣ������-������̬
     */
    final static String XXSB_INFOR_TYPE_GZDT="03";
    
    /**
     * �ϱ���Ϣ������-���¼�
     */
    final static String XXSB_INFOR_TYPE_DSJ="04";
    
    /**
     * �ϱ���Ϣ������-���ö�̬
     */
    final static String XXSB_INFOR_TYPE_JJDT="05";
    
    /**
     * �ϱ���Ϣ������-��Ϣժ��
     */
    final static String XXSB_INFOR_TYPE_XXZB="06";
    
    /**
     * �ϱ���Ϣ������-�ϱ���������Ϣ
     */
    final static String XXSB_INFOR_TYPE_SZFXX="07";
     
    /**
     * �ϱ���Ϣ�Ƿ��ύ��־-δ�ύ
     */
    final static String XXSB_INFOR_SUBMIT_N="01";
    
    /**
     * �ϱ���Ϣ�Ƿ��ύ��־-���ύ
     */
    final static String XXSB_INFOR_SUBMIT_Y="02";
    
    /**
     * �ϱ���Ϣ�Ƿ���ܱ�־-δ����
     */
    final static String XXSB_INFOR_COLLECT_N="01";
    
    /**
     * �ϱ���Ϣ�Ƿ���ܱ�־-�ѻ���
     */
    final static String XXSB_INFOR_COLLECT_Y="02";
    
    /**
     * �ϱ���Ϣ�Ĵ���״̬-δ����
     */
    final static String XXSB_INFOR_STATE_WCL="01";
    /**
     * �ϱ���Ϣ�Ĵ���״̬-������
     */
    final static String XXSB_INFOR_STATE_CLZ="02";
    /**
     * �ϱ���Ϣ�Ĵ���״̬-�����
     */
    final static String XXSB_INFOR_STATE_YWC="03";
    
    /**
     * �ϱ���Ϣ�����Ч��־ - ��Ч
     */
    final static String XXSB_ADVICE_VALID_N="01";
    
    /**
     * �ϱ���Ϣ�����Ч��־ - ��Ч
     */
    final static String XXSB_ADVICE_VALID_Y="02";
   
    
    /**
     * �쵼�ճ̣�δ�ύ
     */
    final static String LDRC_SUBMIT_N = "01";
    
    /**
     * �쵼�ճ̣����ύ
     */
    final static String LDRC_SUBMIT_Y = "02";
    
    /**
     * �쵼�ճ̣�δ����
     */
    final static String LDRC_RELEASE_N = "01";
    
    /**
     * �쵼�ճ̣��ѷ���
     */
    final static String LDRC_RELEASE_Y = "02";
    
    
    /**
     * Display����ʽ-˳��
     */
    final static String DISPLAYTAG_ORDER_ASC="2";
    
    /**
     * Display����ʽ-˳��
     */
    final static String DISPLAYTAG_ORDER_DESC="1";
    
    //�������ģ��
    final static String HYSQD_HYLB_DANG="1";//����
    final static String HYSQD_HYLB_JU="2";//�ֳ�
    final static String HYSQD_HYLB_OTHER="3";//����
    
    final static String HYSQD_QCHY_QCZ="0";//�����
    final static String HYSQD_QCHY_CLZ="1";//������
    final static String HYSQD_QCHY_CLJS="2";//�������
    //ȱ�ڹ���ģ��
    final static String GZKQXXGL_QQXX_QJ="0";//���
    final static String GZKQXXGL_QQXX_KG="1";//����
    
    final static String GZKQXXGL_QQXX_LCJ="0";//·�̼�
    final static String GZKQXXGL_QQXX_CJ="1";//����
    final static String GZKQXXGL_QQXX_XJ="2";//ѧϰ
    final static String GZKQXXGL_QQXX_TJ="3";//̽�׼�
    final static String GZKQXXGL_QQXX_HJ="4";//���
    final static String GZKQXXGL_QQXX_GLJ="5";//�����
    final static String GZKQXXGL_QQXX_GJ="6";//����
    final static String GZKQXXGL_QQXX_SJ="7";//�¼�
    final static String GZKQXXGL_QQXX_BJ="8";//����
    
    final static String GZKQXXGL_QQXX_SBSJ="8:00";//�ϰ�ʱ��
    final static String GZKQXXGL_QQXX_XBSJ="17:00";//�°�ʱ��
    
    final static String GZKQXXGL_KQXX_JU="1";//���쵼
    final static String GZKQXXGL_KQXX_CS="0";//����
    
    final static String GZKQXXGL_KQHZ_QCZ="0";//�����
    final static String GZKQXXGL_KQHZ_CLZ="1";//������
    final static String GZKQXXGL_KQHZ_CLJS="2";//�������
    
    final static String GZKQXXGL_KQHZ_WFB="0";//δ����
    final static String GZKQXXGL_KQHZ_FB="1";//�ѷ���
    
    final static String GZKQXXGL_KQHZ_VALID_N = "0";//�����Ч��־
    final static String GZKQXXGL_KQHZ_VALID_Y = "1";//�����Ч��־
    /**
     * �ڲ�������������Ч��־��������Ч
     */
    final static String NBSX_ADVICE_VALID_N = "01";
    /**
     * �ڲ�������������Ч��־��������Ч
     */
    final static String NBSX_ADVICE_VALID_Y = "02";

    final static String GZKQXXGL_GSWC_Y="1";
    final static String GZKQXXGL_GSWC_N="0";
    /**
     * �����ƽ�
     */
    //��ǰ״̬
    final static String DAYJ_INFOR_STATE_WCL="01";
    final static String DAYJ_INFOR_STATE_CLZ="02";
    final static String DAYJ_INFOR_STATE_YWC="03";
    //����Ƿ���Ч
    final static String DAYJ_ADVICE_VALID_Y = "02";
    final static String DAYJ_ADVICE_VALID_N = "01";
    //�Ƿ��ύ
    final static String DAYJ_INFOR_SUBMIT_N="01";
    final static String DAYJ_INFOR_SUBMIT_Y="02";
    /**
     * �û���Ϣ�������
     */
    final static String ORG_USERINFOTYPE_BASIC_VALUE="01";
    final static String ORG_USERINFOTYPE_BASIC_LABEL="������Ϣ";
    final static String ORG_USERINFOTYPE_EXTEND_VALUE="02";
    final static String ORG_USERINFOTYPE_EXTEND_LABEL="��չ��Ϣ";
    final static String ORG_USERINFOTYPE_RELATION_VALUE="03";
    final static String ORG_USERINFOTYPE_RELATION_LABEL="�����Ϣ";
    final static String ORG_USERINFOTABLE_PREFIX="OA_JL_USERINFO";
    final static String ORG_USERINFOFIELD_PREFIX="YHXX";
    
    final static String ORG_USERINFOModifyMode_INITIAL="01";
    final static String ORG_USERINFOModifyMode_MODIFY="02";
   
    /*
     * ���������
     */
    final static String THING_INTERIOR_VALUE="01";
    final static String THING_INTERIOR_LABEL="�ڲ�����";
    final static String THING_HUMANRESOURCE_VALUE="02";
    final static String THING_HUMANRESOURCE_LABEL="��������";
    final static String THING_ECONOMIZEREPORT_VALUE="03";
    //final static String THING_ECONOMIZEREPORT_VALUE="04";
    final static String THING_RECEIVEDFILE_VALUE="05";
    /**
     * ִ����鵥λ����-��������
     */
    //��ҵ
    final static String TJZF_PARAM_TABLETYPE_B = "B";
    //���ز�
    final static String TJZF_PARAM_TABLETYPE_X = "X";
    //����
    final static String TJZF_PARAM_TABLETYPE_A = "A";
    //����   
    final static String TJZF_PARAM_TABLETYPE_C = "C";
    //����ҵ
    final static String TJZF_PARAM_TABLETYPE_F = "F";
    //����    
    final static String TJZF_PARAM_TABLETYPE_D = "D";

    final static String TJZF_PARAM_TABLETYPE_PL = "01"; 	//����
    final static String TJZF_PARAM_TABLETYPE_CY = "02";	//����
    final static String TJZF_PARAM_TABLETYPE_FDC = "03";	//���ز�
    final static String TJZF_PARAM_TABLETYPE_FW = "04";	//����ҵ
    final static String TJZF_PARAM_TABLETYPE_GY = "05";	//��ҵ
    final static String TJZF_PARAM_TABLETYPE_JZ = "06";	//����
    /**
     * ִ��ͳ�Ƽ�鵥λ����������
     */
    final static String TJZF_PLANTYPE_02 = "02";
    /**
     * ִ��ͳ�Ƽ�鵥λ ������
     */
    final static String TJZF_PLANTYPE_01 = "01";
    
    /**
     * ִ��ͳ�Ƽ���������-Υ������
     */
    final static String TJZF_PARAM_TYPE_ILLEGAL = "01";
    /**
     * ִ��ͳ�Ƽ���������-������������
     */
    final static String TJZF_PARAM_TYPE_PUNISH = "02";
    /**
     * ִ��ͳ�Ƽ���������-��������
     */
    final static String TJZF_PARAM_TYPE_CREDIT = "03";
    
    /**
     * ִ��ͳ�Ƽ���������-�ɿ�����
     */
    final static String TJZF_PARAM_TYPE_JKLX = "04";
    /**
     * ִ��ͳ�Ƽ���������-�����������
     */
    final static String TJZF_PARAM_TYPE_CLCXLX = "05";
    
    /**
     * ִ��ͳ���±���ϸ����-Υ��
     */
    final static String TJZF_MONTHLYPAPER_TYPE_ILLEGAL = "01";

    /**
     * ִ��ͳ���±���ϸ����-�������
     */
    final static String TJZF_MONTHLYPAPER_TYPE_CLCX = "06";
    /**
     * ִ��ͳ���±���ϸ����-�������(�ᰲ)
     */
    final static String TJZF_MONTHLYPAPER_TYPE_CLCXJA = "07";
    
    /**
     * ִ��ͳ���±���ϸ����-��������
     */
    final static String TJZF_MONTHLYPAPER_TYPE_DECIDEDPUNISH="02";
    
    /**
     * ִ��ͳ���±���ϸ����-ִ�д���֮�Ƿ�����
     */
    final static String TJZF_MONTHLYPAPER_TYPE_EXECUTEDILLEGAL="03";
    
    /**
     * ִ��ͳ���±���ϸ����-ִ�д���֮��������
     */
    final static String TJZF_MONTHLYPAPER_TYPE_EXECUTEDTYPE="04";
    
    /**
     * ִ��ͳ���±���ϸ����-���ü�¼����
     */
    final static String TJZF_MONTHLYPAPER_TYPE_CREDIT = "05";
    /**
     * ִ����Ϣ-ָ��Ȩ��-��
     */
    final static String TJZF_INDEX_ACCESS_NONE = "01";
    /**
     * ִ����Ϣ-ָ��Ȩ��-ֻ��
     */
    final static String TJZF_INDEX_ACCESS_READONLY= "02";
    /**
     * ִ����Ϣ-ָ��Ȩ��-�༭
     */
    final static String TJZF_INDEX_ACCESS_EDIT= "03";
    /**
     * ִ����Ϣ������Ϣ-��λ��Ϣ
     */
    final static String TJZF_BASICINFO_TYPE_UNITINFO = "01";
    /**
     * ִ����Ϣ������Ϣ-�����Ϣ
     */
    final static String TJZF_BASICINFO_TYPE_CHECKINFO = "02";
    /**
     * ִ����Ϣ������Ϣ-������Ϣ
     */
    final static String TJZF_BASICINFO_TYPE_INQUISITINFO = "03";
    /**
     * ִ����Ϣ������Ϣ-���ڴ�����Ϣ
     */
    final static String TJZF_BASICINFO_TYPE_LATERHANDLEINFO = "04";   
    /**
     * ִ����Ϣ������Ϣ-����������Ϣ
     */
    final static String TJZF_BASICINFO_TYPE_EXECREPEATADVINFO = "05";   
    /**
     * ִ����Ϣ������Ϣ-��֤��Ϣ
     */
    final static String TJZF_BASICINFO_TYPE_AUDITINFO = "06"; 
    /**
     * ִ����Ϣ������Ϣ-ִ�з���
     */
    final static String TJZF_BASICINFO_TYPE_EXTPUNISH = "07"; 
    /**
     * ִ����Ϣ������Ϣ-�ĺ�
     */
    final static String TJZF_BASICINFO_TYPE_WH = "08"; 
    
    final static String TJZF_BASICINFO_TYPE_WHHZ = "00";    
    /**
     * ִ����Ϣ������Ϣ��ʾ����-�༭����
     */
    final static String TJZF_BASICINFO_SHOWTYPE_EDIT = "01";
    /**
     * ִ����Ϣ������Ϣ��ʾ����-��������
     */
    final static String TJZF_BASICINFO_SHOWTYPE_HIDE = "02";
    /**
     * ִ����Ϣ������Ϣ��ʾ����-ֻ������
     */
    final static String TJZF_BASICINFO_SHOWTYPE_READONLY = "03";
    
    /**
     * ���ӽ��ĵ������޲鿴Ȩ��
     */
    final static int DAGL_DZDACKQX_N=0;
    
    /**
     * ���ӽ��ĵ������в鿴Ȩ��
     */
    final static int DAGL_DZDACKQX_Y=1;

    
    /**
     * ���ӽ��ĵ������в鿴Ȩ��
     */
    final static int DAGL_DZDACKQX_BORROW=2;
    
    
    /**
     * ����״̬-δ�ύ
     */
    String DAAJ_ZT_WTJ = "01";
    
    /**
     * ����״̬-���ƽ�
     */
    String DAAJ_ZT_DYJ = "02";
    
    /**
     * ����״̬-�ƽ���
     */
    String DAAJ_ZT_YJZ = "03";
    /**
     * ����״̬-���ƽ�
     */
    String DAAJ_ZT_YYJ = "04";

    /**
     * ���뵵�����ͣ�����
     */
    final static String DAGL_ARCHIVETYPE_TEXT="01";
    /**
     * ���뵵�����ͣ�ͳ��
     */
    final static String DAGL_ARCHIVETYPE_STATISTICS="02";
    /**
     * ���뵵�����ͣ��Ƽ�
     */
    final static String DAGL_ARCHIVETYPE_TECHNOLOGY="03";
    
    
    /**
     * ���鵵����
     */
    final static String DGD_TABLE_NAME="OA_DGDWJ";
    
    /**
     * �߰����ͣ�������ʵ��
     */
    final static String CB_TYPE_LCSL = "01";
    
    /**
     * ֵ����Ա����������
     */
    final static String ZBRYGL_ZC = "01";
    /**
     * ֵ����Ա����������
     */
    final static String ZBRYGL_ZT = "02";
    
    /**
     * �߰����ͣ����ʵ��
     */
    final static String CB_TYPE_HDSL = "02";
    
    /**
     * �߰�ȼ������м�
     */
    final static int CB_LEVEL_MIDDLE = 1;
    
    /**
     * �߰�ȼ������߼�
     */
    final static int CB_LEVEL_HIGH = 2;
    
    /**
     * �߰�ȼ������ؼ�
     */
    final static int CB_LEVEL_SPECIAL = 3;
    static final String HQGL_LCMC_CZYCSQSH = "�����ó��������";
    
    
    /**
     * ֵ���Ű�ҵ������ID
     */
    String ZBPB_CALENDAR_ID ="zbpb";
    
    /**
     * �洢��ͨ������ѯ�����Ĺؼ���
     */
    String DAGL_COMMONQUERYCONDITIONS_KEY="commonquery";
    /**
     * �洢��ͨ������ѯ�����Ĺؼ���
     */
    String DAGL_FULLTEXTCONDITIONS_KEY="fulltextquery";
    
	//Ƶ���䶯�޸ı�������
    final static String D_NW_PDID="1380";//��������Ƶ�����
    final static String D_WW_PDID="1421";//��������Ƶ�����

    
    final static String FBPD_JNTZ="1000";//����֪ͨ 1000
    final static String FBPD_QXTZ="1535";//����֪ͨ 1535
    final static String FBPD_ZJXTTZ="1536";//ͳ��ϵͳ֪ͨ 1536

    final static String XXSB_YSLL_YQXX="Ҫ����Ϣ";
    final static String XXSB_YSLL_GZDT="������̬";
    final static String XXSB_YSLL_DSJ="���¼�";
    final static String XXSB_YSLL_ZWXX="������Ϣ";
    final static String XXSB_YSLL_QT="����(��Ϣ�ϱ�)";
    
    final static String DOCNUMBER_NUMBER_FLAG_EDIT ="03";    	//ִ���ĺ�ά��״̬
    final static String DOCNUMBER_NUMBER_FLAG_DELETE ="02";  	//ִ���ĺ�ɾ��
    final static String DOCNUMBER_NUMBER_FLAG_OK ="01";	 	//ִ���ĺ�ĩɾ��

    final static String DOCNUMBER_NUMBER_TYPE_01 ="01";		//ִ�����_01 ���֪ͨ���ĺ�
    final static String DOCNUMBER_NUMBER_TYPE_02 ="02";		//ִ�����_02 �����ĺ�
    final static String DOCNUMBER_NUMBER_TYPE_03 ="03";		//ִ�����_03 ���ȸ�֪�ĺ�
    final static String DOCNUMBER_NUMBER_TYPE_04 ="04";		//ִ�����    ��֤��֪�ĺ�
    final static String DOCNUMBER_NUMBER_TYPE_05 ="05";		//ִ�����    ��������ĺ�
    final static String DOCNUMBER_NUMBER_TYPE_06 ="06";		//ִ�����    ���������ĺ�
    final static String DOCNUMBER_NUMBER_TYPE_07 ="07";		//ִ�����    �᰸�����ĺ�
    final static String DOCNUMBER_NUMBER_TYPE_08 ="08";		//ִ�����    �����ĺ�
    final static String DOCNUMBER_NUMBER_TYPE_00 ="00";		//ִ�����    �ֶ������ĺ�
    
}

    

package cn.yufu.posp.queryManager.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.domain.model.CurTranLs;

public class CurTranLsReportHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements CurTranLsReportHibernateHQL {

	private static final Log log = LogFactory.getLog("query");

	@Override
	public int queryCount(CurTranLs queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("CurTranLsHibernateHQLImpl.queryCount��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from VCurTranLs where 1=1 ");

			// //��������ѯ
			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
				bufferHql.append(" and tranType = " + queryModel.getTranType() + " ");
			if (queryModel.getLocalSysDateS() != null && !queryModel.getLocalSysDateS().equals(""))
				bufferHql.append(" and localSysDate >= " + queryModel.getLocalSysDateS() + " ");
			if (queryModel.getLocalSysDateE() != null && !queryModel.getLocalSysDateE().equals(""))
				bufferHql.append(" and localSysDate <= " + queryModel.getLocalSysDateE() + " ");
			if (queryModel.getLocalSysTimeS() != null && !queryModel.getLocalSysTimeS().equals(""))
				bufferHql.append(" and localSysTime >= " + queryModel.getLocalSysTimeS() + " ");
			if (queryModel.getLocalSysTimeE() != null && !queryModel.getLocalSysTimeE().equals(""))
				bufferHql.append(" and localSysTime <= " + queryModel.getLocalSysTimeE() + " ");
			if (queryModel.getMerchantName() != null && !queryModel.getMerchantName().equals(""))
				bufferHql.append(" and merchantName like '%" + queryModel.getMerchantName() + "%'");
			if (queryModel.getTraceNo() != null && !queryModel.getTraceNo().equals(""))
				bufferHql.append(" and traceNo = " + queryModel.getTraceNo() + "");
			if (queryModel.getCardNo() != null && !queryModel.getCardNo().equals(""))
				bufferHql.append(" and cardNo = " + queryModel.getCardNo() + "");
			// if(queryModel.getId().getMerchantId()!=null &&
			// !queryModel.getId().getMerchantId().equals("") )
			// bufferHql.append(" and id.merchantId like '%"+queryModel.getId().getMerchantId()+"%'");
			//			
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("CurTranLsHibernateHQLImpl.queryCount�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsHibernateHQLImpl.queryCount����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯģ�����ʱ���쳣��");
		}
		return count;

	}

	public List query(CurTranLs queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("CurTranLsHibernateHQLImpl.query��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
			list = this.getSession().getNamedQuery("curTranLsReport").setParameter(0, queryModel.getLocalSysDateS()).setParameter(1,
					queryModel.getLocalSysDateE()).setParameter(2, queryModel.getMerchantId()).list();
			log.info("CurTranLsHibernateHQLImpl.query�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsHibernateHQLImpl.queryͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("��ѯģ��������ʱ���쳣��");
		}
		return list;
	}

	@Override
	public List queryExport(CurTranLs queryModel, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("CurTranLsHibernateHQLImpl.query��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			log.info("CurTranLsHibernateHQLImpl.query��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
			list = this.getSession().getNamedQuery("curTranLsReport").setParameter(0, queryModel.getLocalSysDateS()).setParameter(1,
					queryModel.getLocalSysDateE()).setParameter(2, queryModel.getMerchantId()).list();
			log.info("CurTranLsHibernateHQLImpl.query�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
			log.info("CurTranLsHibernateHQLImpl.query�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsHibernateHQLImpl.queryͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("��ѯģ��������ʱ���쳣��");
		}
		return list;
	}
}

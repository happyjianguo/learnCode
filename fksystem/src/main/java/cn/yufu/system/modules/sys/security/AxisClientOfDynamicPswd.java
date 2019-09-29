package cn.yufu.system.modules.sys.security;

import java.util.Iterator;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMNode;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;

public class AxisClientOfDynamicPswd {
	public static String CltCall(String method, String svcaddr,
			String namespace, String buf) throws AxisFault {
		RPCServiceClient rpcClient = new RPCServiceClient();

		Options opt = new Options();
		opt.setTo(new EndpointReference(svcaddr)); // �����ַ
		opt.setAction("urn:" + method); // ����
		rpcClient.setOptions(opt);
		OMElement element = rpcClient.invokeBlocking(new QName(namespace,
				method), new Object[] { buf });// null��ʾû�в����

		Iterator values = element.getChildrenWithName(new QName(namespace,
				"return")); // return��ʾ�з���ֵ
		StringBuilder strb = new StringBuilder();
		while (values.hasNext()) { // �������ȡ�����
			OMElement omElement = (OMElement) values.next();
			strb.append(omElement.getText());
		}

		return strb.toString();
	}

	public static String CltCall2(String method, String svcaddr,
			String namespace, String buf) throws AxisFault {
		ServiceClient serviceClient = new ServiceClient();

		Options options = new Options();

		EndpointReference targetEPR = new EndpointReference(svcaddr);
		options.setTo(targetEPR);
		options.setAction(namespace + method);
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);// �趨����Э��
		options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);// �趨SOAP�汾soap1.2
		options.setTimeOutInMilliSeconds(600000L);// 1���ӳ�ʱ
		options.setProperty(HTTPConstants.CHUNKED, "false");//
		options.setProperty(Constants.Configuration.HTTP_METHOD,
				HTTPConstants.HTTP_METHOD_POST);
		serviceClient.setOptions(options);

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(namespace, "");

		// �趨���ʵĽӿڷ���
		OMElement omMethod = fac.createOMElement(method, omNs);// Ҫ���õĽӿڷ������
		OMElement inData = fac.createOMElement("InData", omNs);// �����ĵ�һ���������

		inData.addChild(fac.createOMText(inData, buf));// �趨�����ֵ
		omMethod.addChild(inData);

		// �趨����������Բ���������������δ���?���Կ���Ϊ�������child,
		OMElement result = serviceClient.sendReceive(omMethod);// ���ýӿڷ���

		Iterator iterator = result.getChildElements();
		//List<String> list = new ArrayList<String>();
		String msg = "";
		while (iterator.hasNext()) {
			OMNode omNode = (OMNode) iterator.next();
			if (omNode.getType() == OMNode.ELEMENT_NODE) {
				OMElement omElement = (OMElement) omNode;
				if (omElement.getLocalName().equals(method+"Result")) {
					msg = omElement.getText().trim();
					System.out.println(msg);
					//list.add(temp);
					break;
				}
			}
		}

		serviceClient.cleanupTransport();// �ر���Դ
		serviceClient.cleanup();
		serviceClient = null;

		return msg;

		// /////////////////////////////////////////////////////////////////////
	}
}

package com.pay.util;

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

public class AxisClient {
	public static String CltCall(String method, String svcaddr,
			String namespace, String buf) throws AxisFault {
		RPCServiceClient rpcClient = new RPCServiceClient();

		Options opt = new Options();
		opt.setTo(new EndpointReference(svcaddr)); // 服务地址
		opt.setAction("urn:" + method); // 方法
		rpcClient.setOptions(opt);
		OMElement element = rpcClient.invokeBlocking(new QName(namespace,
				method), new Object[] { buf });// null表示没有参数传递

		Iterator values = element.getChildrenWithName(new QName(namespace,
				"return")); // return表示有返回值
		StringBuilder strb = new StringBuilder();
		while (values.hasNext()) { // 遍历出获取的数据
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
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);// 设定传输协议
		options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);// 设定SOAP版本soap1.2
		options.setTimeOutInMilliSeconds(600000L);// 1分钟超时
		options.setProperty(HTTPConstants.CHUNKED, "false");//
		options.setProperty(Constants.Configuration.HTTP_METHOD,
				HTTPConstants.HTTP_METHOD_POST);
		serviceClient.setOptions(options);

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(namespace, "");

		// 设定访问的接口方法
		OMElement omMethod = fac.createOMElement(method, omNs);// 要调用的接口方法名称
		OMElement inData = fac.createOMElement("inData", omNs);// 方法的第一个参数名称

		inData.addChild(fac.createOMText(inData, buf));// 设定参数的值
		omMethod.addChild(inData);

		// 设定其他方法参数，针对参数是数组的情况如何处理?可以考虑为参数添加child,
		OMElement result = serviceClient.sendReceive(omMethod);// 调用接口方法

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

		serviceClient.cleanupTransport();// 关闭资源
		serviceClient.cleanup();
		serviceClient = null;

		return msg;

		// /////////////////////////////////////////////////////////////////////
	}
}

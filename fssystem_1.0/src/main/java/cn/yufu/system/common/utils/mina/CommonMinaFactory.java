package cn.yufu.system.common.utils.mina;


import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class CommonMinaFactory extends ProtocolCodecFilter
{
	private static ProtocolEncoder encoder = new CommonMinaEncoder();
	private static ProtocolDecoder decoder = new CommonMinaDecoder();

	public CommonMinaFactory()
	{
		super(encoder, decoder);
	}

	@Override
	public void sessionClosed(IoFilter.NextFilter nextFilter, IoSession session) throws Exception
	{
		super.sessionClosed(nextFilter, session);
	}

}
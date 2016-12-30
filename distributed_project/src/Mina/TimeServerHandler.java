package Mina;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class TimeServerHandler implements IoHandler {

	@Override
	public void exceptionCaught(IoSession iosession, Throwable throwable) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("异常抛出>>>>>"+iosession.getId());
	}

	@Override
	public void inputClosed(IoSession iosession) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("输入关闭>>>>>"+iosession.getId());
	}

	@Override
	public void messageReceived(IoSession iosession, Object obj) throws Exception {
		// TODO Auto-generated method stub
		String msg = obj.toString();
    	System.out.println("服务器收到的消息>>>>>"+msg);

	}

	@Override
	public void messageSent(IoSession iosession, Object obj) throws Exception {
		// TODO Auto-generated method stub
		String msg = obj.toString();
    	System.out.println("服务器发送的消息>>>>>"+msg);
	}

	@Override
	public void sessionClosed(IoSession iosession) throws Exception {
		// TODO Auto-generated method stub
    	System.out.println("回话关闭>>>>>"+iosession.getId());

	}

	@Override
	public void sessionCreated(IoSession iosession) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("回话建立>>>>>"+iosession.getId());

	}

	@Override
	public void sessionIdle(IoSession iosession, IdleStatus idlestatus) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("回话闲置>>>>>"+iosession.getId());

	}

	@Override
	public void sessionOpened(IoSession iosession) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("回话打开>>>>>"+iosession.getId());
	}

}

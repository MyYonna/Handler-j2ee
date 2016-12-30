package Mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class TimeClientHandler extends IoHandlerAdapter {

    public void messageReceived(IoSession iosession, Object obj)
            throws Exception
        {
    	String msg = obj.toString();
    	System.out.println("客户端收到的消息>>>>>"+msg);
        }

        public void messageSent(IoSession iosession, Object obj)
            throws Exception
        {
        	String msg = obj.toString();
        	System.out.println("客户端发出的消息>>>>>"+msg);
        }
        
}

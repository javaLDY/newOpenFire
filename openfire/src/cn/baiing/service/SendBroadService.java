package cn.baiing.service;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class SendBroadService extends Base{

	public static void main(String[] args) {
			XMPPConnection conn = getConnection();
			try {
				conn.login("admin", "admin");
				Message message = new Message();
				message.setTo("ldy@ldy-pc");
				message.setSubject("重要通知");
				message.setBody("今天下午到1509开会!");
				message.setType(Message.Type.headline);//针对给离线用户发送信息还是给在线
				conn.sendPacket(message);
				conn.disconnect();
			} catch (XMPPException e) {
				e.printStackTrace();
			}
	}
	
}

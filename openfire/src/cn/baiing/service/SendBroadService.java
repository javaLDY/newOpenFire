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
				message.setSubject("��Ҫ֪ͨ");
				message.setBody("�������絽1509����!");
				message.setType(Message.Type.headline);//��Ը������û�������Ϣ���Ǹ�����
				conn.sendPacket(message);
				conn.disconnect();
			} catch (XMPPException e) {
				e.printStackTrace();
			}
	}
	
}

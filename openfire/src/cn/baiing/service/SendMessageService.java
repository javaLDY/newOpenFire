package cn.baiing.service;

import java.util.Scanner;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class SendMessageService extends Base{

	public static void main(String[] args) {
		ConnectionConfiguration domain = new ConnectionConfiguration(
				"localhost", 5222);
		domain.setReconnectionAllowed(true);
		domain.setSendPresence(true);
		
		XMPPConnection conn = getConnection(domain);
		try {
			conn.login("admin", "admin");
			ChatManager chatManager = conn.getChatManager();
			Chat newChat = chatManager.createChat("ldy@ldy-pc", new MessageListener() {
				
				public void processMessage(Chat chat, Message message) {
					if(message.getBody() != null){
						System.out.println("Received from ¡¾"
								+ message.getFrom()+"¡¿ message: "
										+ message.getBody()+"");
					}
					
				}
			});
			Scanner input = new Scanner(System.in);
			while(true){
				String message = input.nextLine();
				newChat.sendMessage(message);
			}
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		
	}
	
}

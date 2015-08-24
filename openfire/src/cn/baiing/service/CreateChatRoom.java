package cn.baiing.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.login.Configuration;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;

public class CreateChatRoom extends Base {

	public static void main(String[] args) {
		//createChatRoom();
		XMPPConnection connection = getConnection();
		try {
			connection.login("admin", "admin");
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addmember();
	}
	public static void createChatRoom(){
		XMPPConnection connection = getConnection();
		try {
			connection.login("admin", "admin");
			MultiUserChat muc = new MultiUserChat(connection,
					"testroom@conference.ldy-pc");
			muc.create("testroom");
			muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
			Form form = muc.getConfigurationForm();
			Form submitForm = form.createAnswerForm();
			for (Iterator fields = form.getFields(); fields.hasNext();) {
				FormField field = (FormField) fields.next();
				if (!FormField.TYPE_HIDDEN.equals(field.getType())
						&& field.getVariable() != null) {
					submitForm.setDefaultAnswer(field.getVariable());
				}
			}
			List<String> owners = new ArrayList<String>();
//			owners.add("admin@ldy-pc");
//			owners.add("ldy@ldy-pc");
//			submitForm.setAnswer("muc#roomconfig_roomowners", owners);   
			submitForm.setAnswer("muc#roomconfig_persistentroom", true);
			// 房间仅对成员开放
			submitForm.setAnswer("muc#roomconfig_membersonly", false);
			// 允许占有者邀请其他人
			submitForm.setAnswer("muc#roomconfig_allowinvites", true);
			// 能够发现占有者真实 JID 的角色
			// submitForm.setAnswer("muc#roomconfig_whois", "anyone");
			// 登录房间对话
			submitForm.setAnswer("muc#roomconfig_enablelogging", true);
			// 仅允许注册的昵称登录
			submitForm.setAnswer("x-muc#roomconfig_reservednick", true);
			// 允许使用者修改昵称
			submitForm.setAnswer("x-muc#roomconfig_canchangenick", false);
			// 允许用户注册房间
			submitForm.setAnswer("x-muc#roomconfig_registration", false);
			// 发送已完成的表单（有默认值）到服务器来配置聊天室
			muc.sendConfigurationForm(submitForm);
		} catch (XMPPException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public static void addmember(){
		XMPPConnection connection = getConnection();
		try {
			connection.login("admin", "admin");
			MultiUserChat muc = new MultiUserChat(connection, "testroom@conference.ldy-pc");
			System.out.println("邀请中........");
			muc.join("喝醉的自身");
			Message message = new  Message();
			message.setBody("这里面都是美女哦");
		//	muc.invite(message, "ldy@ldy-pc", "asaa");
			
		//	muc.invite("ldy@ldy-pc", "这里有很多的美女啊");
			muc.invite("ivan@ldy-pc", "这里有很多的美女啊");
			MultiUserChat.addInvitationListener(connection, new InvitationListener() {
				
				public void invitationReceived(Connection connection, String room, String inviter,
						String reason, String password, Message message) {
				
					System.out.println("邀请："+inviter+"，理由是:"+reason); 
					
				}
			});
			System.out.println("邀请结束........");
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}

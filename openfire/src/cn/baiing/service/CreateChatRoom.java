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
			// ������Գ�Ա����
			submitForm.setAnswer("muc#roomconfig_membersonly", false);
			// ����ռ��������������
			submitForm.setAnswer("muc#roomconfig_allowinvites", true);
			// �ܹ�����ռ������ʵ JID �Ľ�ɫ
			// submitForm.setAnswer("muc#roomconfig_whois", "anyone");
			// ��¼����Ի�
			submitForm.setAnswer("muc#roomconfig_enablelogging", true);
			// ������ע����ǳƵ�¼
			submitForm.setAnswer("x-muc#roomconfig_reservednick", true);
			// ����ʹ�����޸��ǳ�
			submitForm.setAnswer("x-muc#roomconfig_canchangenick", false);
			// �����û�ע�᷿��
			submitForm.setAnswer("x-muc#roomconfig_registration", false);
			// ��������ɵı�����Ĭ��ֵ����������������������
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
			System.out.println("������........");
			muc.join("���������");
			Message message = new  Message();
			message.setBody("�����涼����ŮŶ");
		//	muc.invite(message, "ldy@ldy-pc", "asaa");
			
		//	muc.invite("ldy@ldy-pc", "�����кܶ����Ů��");
			muc.invite("ivan@ldy-pc", "�����кܶ����Ů��");
			MultiUserChat.addInvitationListener(connection, new InvitationListener() {
				
				public void invitationReceived(Connection connection, String room, String inviter,
						String reason, String password, Message message) {
				
					System.out.println("���룺"+inviter+"��������:"+reason); 
					
				}
			});
			System.out.println("�������........");
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}

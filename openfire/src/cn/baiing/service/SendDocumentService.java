package cn.baiing.service;

import java.io.File;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.filetransfer.FileTransfer;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer;

public class SendDocumentService extends Base{

	public static void main(String[] args) {
		
		XMPPConnection conn = getConnection();
		
		try {
			conn.login("ldy", "123456", "Spark");
			Presence pre = conn.getRoster().getPresence("admin@ldy-pc");
			System.out.println(pre);
			if(pre.getType() != Presence.Type.unavailable){
				//�����ļ����������
				FileTransferManager manager = new FileTransferManager(conn);
				//����������ļ�����
				OutgoingFileTransfer transfer = manager.createOutgoingFileTransfer(
						pre.getFrom());
				//�����ļ�
				transfer.sendFile(new File("D:\\aa.jpg"), "ͼƬ");
				while( !transfer.isDone() ){
					if(transfer.getStatus() == FileTransfer.Status.in_progress){
						System.out.println(transfer.getStatus());
						System.out.println(transfer.getProgress());
						System.out.println(transfer.isDone());
					}
				}
			}
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
	
}

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
				//创建文件传输管理器
				FileTransferManager manager = new FileTransferManager(conn);
				//创建输出的文件传输
				OutgoingFileTransfer transfer = manager.createOutgoingFileTransfer(
						pre.getFrom());
				//发送文件
				transfer.sendFile(new File("D:\\aa.jpg"), "图片");
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

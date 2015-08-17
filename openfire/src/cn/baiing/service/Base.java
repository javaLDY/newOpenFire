package cn.baiing.service;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class Base {

	public static XMPPConnection getConnection(ConnectionConfiguration domain){
		XMPPConnection connection = new XMPPConnection(domain);
		try {
			connection.connect();
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
}

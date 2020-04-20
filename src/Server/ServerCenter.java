package Server;

import java.net.Socket;
import java.util.ArrayList;

import DB.DAOCenter;

public class ServerCenter {
	private ArrayList<ServerChat> sList = new ArrayList<>();
	private DAOCenter dc = DAOCenter.getInstance();
	private String[] check = null;
	private ServerChat chat = null;
	// private static ServerCenter sc;
	private Socket withServer = null;
	private Socket withServer1 = null;

	public ServerCenter(Socket withServer, Socket withServer1) {
		this.withServer = withServer;
		this.withServer1 = withServer1;

	}

//	public static ServerCenter getInstance() {
//		if (sc == null) {
//			new ServerCenter();
//		}
//		return sc;
//	}

	public void addServerChat(ServerChat s) {
		this.sList.add(s);
	}

	public void select(Object objectMember, Socket withServer) {// ServerChat chat
		// chat=chat;

		dc.whichone(objectMember, withServer);

		// }
	}

	public void goSC(String msg) {
		System.out.println("여기는 서버센터야 오바 : " + msg);
		chat = new ServerChat(withServer, withServer1, this);
		chat.send(msg); // 이제 chat(serverchat)에 send로 메세지 전달

	}

}

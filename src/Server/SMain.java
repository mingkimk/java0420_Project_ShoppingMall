package Server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Manager.Setting;

public class SMain {

	public static void main(String[] args) throws Exception {
		ServerSocket serverS = null;
		ServerSocket serverS1=null;
		
		Socket withClient = null;
		Socket withClient1=null;
		
		serverS = new ServerSocket();
		serverS1= new ServerSocket();
		
		serverS.bind(new InetSocketAddress("10.0.0.96", 7777));
		serverS1.bind(new InetSocketAddress("10.0.0.96", 7779));
		
		ArrayList<Socket> cList = new ArrayList<>();
		
		ServerCenter sc =new ServerCenter(withClient,withClient1);
		
		while (true) {
			System.out.println("���� �����");
			withClient = serverS.accept();
			cList.add(withClient);
			System.out.println(cList);
			System.out.println(withClient.getInetAddress() + "���� ������.");
			ServerChat s = new ServerChat(withClient,withClient1, sc);
			s.start();

		}
	}

}

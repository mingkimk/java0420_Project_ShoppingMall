package Client;

import java.net.Socket;


public class CMain {

	public static void main(String[] args) throws Exception {
		Socket withServer = null;
		Socket withServer1=null;
		
		withServer = new Socket("10.0.0.96", 7777);
		withServer1=new Socket("10.0.0.96", 7779);
		
		System.out.println("아이피랑 포트넘버 생성");
		new ClientChat(withServer,withServer1);
	}

}

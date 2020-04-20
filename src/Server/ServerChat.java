package Server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ServerChat extends Thread {
	private Socket withClient = null;
	private Socket withClient1=null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String id = null;
	private String[] check = null;
	private ServerCenter sc = null;
	private ServerChat ss = null;
//	private Socket with
	String msg = null;

	ServerChat(Socket withClient,Socket withClient1,ServerCenter sc) {
		this.withClient = withClient;
		this.withClient1=withClient1;
		this.sc = sc;
		try {
			sendMsg = withClient.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ss = this;
		// streamSet();
		// receive(msg);
	}

	@Override
	public void run() {
		streamSet();
		send(msg);
		// send();
	}

	public void send(String msg) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// while (true) {
					if (msg != null) {
						System.out.println("여기는 서버챗인데 받은 메세지를 확인해볼것이야 : " + msg);
						sendMsg.write(msg.getBytes());
						System.out.println("서버에서 메세지를 보냈어요");
					}
				}
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			ObjectOutputStream oos = new ObjectOutputStream(baos);
//			oos.writeObject(msg);
//
//			byte[] bowl = baos.toByteArray();
//
//			sendMsg = withClient.getOutputStream();
//
//			sendMsg.write(bowl);
//			System.out.println("보내기 완료");

				// }

				catch (Exception e) {
					// TODO: handle exception
				}
			}
		}).start();

	}
	

	public void streamSet() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {

						reMsg = withClient.getInputStream();
						byte[] reBuffer = new byte[1024];
						reMsg.read(reBuffer);

						ByteArrayInputStream bais = new ByteArrayInputStream(reBuffer);

						ObjectInputStream ois = new ObjectInputStream(bais);

						Object o = ois.readObject();

						if (o != null) {
							check = (String[]) o;
							for (int i = 0; i < check.length; i++) {
								System.out.println(check[i]);
								id = check[0];
							}
							sc.select(check,withClient);

//							String txt = "정상접속 되었습니다.";
//							sendMsg = withClient.getOutputStream();
//							sendMsg.write(txt.getBytes());
						}

//						InetAddress c_ip = withClient.getInetAddress();
//						String ip = c_ip.getHostAddress();
//
//						System.out.println(id + "님 로그인 (" + ip + ")");
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}).start();
	}
}

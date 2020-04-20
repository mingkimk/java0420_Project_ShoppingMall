package Client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {
	private Socket withServer = null;
	private Socket withServer1=null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String[] chk = null;
	String nnn = "";
	Scanner in = new Scanner(System.in);
	// Signup member = new Signup(null);
	String msg = null;
//	private static MsCenter mc=MsCenter.getInstance();
	MsCenter mc = null;

	public ClientChat(Socket withServer,Socket withServer1) {
		this.withServer = withServer;
		this.withServer1=withServer1;
		start();
		streamSet(chk);
		receive();
	}

	private void start() {
		new Login(this);
	//	new MsCenter(this, msg);
		// mc.allMsg(null);
	}

	public void receive() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// Login lg = new Login();
					System.out.println("receive start~~");

					while (true) {
//				reMsg = withServer.getInputStream();
//				byte[] reBuffer = new byte[1024];
//				reMsg.read(reBuffer);
//
//				ByteArrayInputStream bais = new ByteArrayInputStream(reBuffer);
//
//				ObjectInputStream ois = new ObjectInputStream(bais);
//
//				Object o = ois.readObject();
//
//				if (o != null) {
//					String msg = String.valueOf(o);
//					Signup member = new Signup(this);
//					member.complete(msg); 
//				}
						reMsg = withServer.getInputStream();
						byte[] reBuffer = new byte[1024];
						reMsg.read(reBuffer);
						msg = new String(reBuffer);
						if (msg != null) {
							msg = msg.trim();
							System.out.println("클라이언트에서 메세지를 받았어요." + msg);
							// MsCenter mc = new MsCenter(this,msg);
							gotoCenter(msg);
//							Signup member = new Signup(this, null);
//							member.membercheck(msg);
						}

//						System.out.println("zzz");
//						Login lg = new Login();
//						ByteArrayOutputStream baos = new ByteArrayOutputStream();
//						ObjectOutputStream oos = new ObjectOutputStream(baos);
//						oos.writeObject(check);
//						byte[] bowl = baos.toByteArray();
						// op = sc.getOutputStream();
//						op.write(bowl);

//						sendMsg = withServer.getOutputStream();
//						sendMsg.write(bowl);
//						sendMsg.write(pwd.getBytes());
					}
				} catch (Exception e) {
					System.out.println("client send end !!!");
					return;
				}
			}
		}).start();
	}

	private void gotoCenter(String msg) {
		 MsCenter mc = new MsCenter(this, msg);
	//	mc.checkMsg(msg);

	}

	public void streamSet(String[] check) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if (check != null) {

						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(baos);
						oos.writeObject(check);

						byte[] bowl = baos.toByteArray();

						sendMsg = withServer.getOutputStream();

						sendMsg.write(bowl);
						System.out.println("보내기 완료");
					}

					// 메세지 받기

//			System.out.println("서버에서 보낸 메시지 확인 :" + msg);
				} catch (Exception e) {
				}
			}
		}).start();
	}

}

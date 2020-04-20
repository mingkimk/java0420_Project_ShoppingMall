package Client;

import java.net.Socket;

public class MsCenter {
	private String msg = null;
	private Login login = null;
	private Signup join = null;
	private ClientChat ch = null;
//	private static MsCenter msgobj;
//	Socket withServer = null;

	
	public MsCenter(ClientChat ch,String msg) {
		this.ch=ch;
		checkMsg(msg);
		allMsg(null);
	}
//	public static MsCenter getInstance() {
//		if(msgobj==null) {
//			msgobj= new MsCenter(ch,msg);
//		}
//		return msgobj;
//		
//	}

	public void checkMsg(String msg) {
		if(msg.contains("member")) {
			join = new Signup(ch);
			join.membercheck(msg);
		}else if(msg.contains("login")){
			login = new Login(ch);
			login.loginresult(msg);
		}else if(msg.contains("check")) {
			join = new Signup(ch);
			join.idchk(msg);
		}
		
	}
	
	public void allMsg(String[] in) {
		//in[4]="add";
		ch.streamSet(in);
		System.out.println("¹¹°¡¹®Á¦´Ï??");
	}
	
	
}

package DB;

import java.net.Socket;

import Server.ServerChat;

public interface DAOInterface {
	
	Boolean Insert(Object DTO, String notice,Socket withServer);// ServerChat ss
	int Select(String id, String pwd,String notice,Socket withServer);
	Boolean Edit(Object DTO);
	Boolean Delete(Object DTO);
	

}


package day0905;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 * URL��˫��ģʽweb
 * Socketȫ˫��QQ
 * */
public class DemoClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket=new Socket("127.0.0.1",8888);	
		InetAddress clientAddress=socket.getInetAddress();
		SocketAddress serverAddress=socket.getRemoteSocketAddress();
		System.out.println("��������ַ��"+serverAddress);
		System.out.println("�ͻ��˵�ַ��"+clientAddress);
		InputStream in=socket.getInputStream();
		OutputStream out=socket.getOutputStream();
		out.write("���".getBytes());
		byte[] buffer=new byte[1024];
		int count;
		count=in.read(buffer);
		System.out.println(new String(buffer,0,count));
		
	}
}

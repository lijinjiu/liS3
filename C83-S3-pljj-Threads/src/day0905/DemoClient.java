package day0905;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 * URL半双工模式web
 * Socket全双工QQ
 * */
public class DemoClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket=new Socket("127.0.0.1",8888);	
		InetAddress clientAddress=socket.getInetAddress();
		SocketAddress serverAddress=socket.getRemoteSocketAddress();
		System.out.println("服务器地址："+serverAddress);
		System.out.println("客户端地址："+clientAddress);
		InputStream in=socket.getInputStream();
		OutputStream out=socket.getOutputStream();
		out.write("你好".getBytes());
		byte[] buffer=new byte[1024];
		int count;
		count=in.read(buffer);
		System.out.println(new String(buffer,0,count));
		
	}
}

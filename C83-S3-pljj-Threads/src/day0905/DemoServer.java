package day0905;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class DemoServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server=new ServerSocket(8888);
		System.out.println("服务器启动成功 监听8888端口");
		//IO操作 返回Socket对象
		Socket socket=server.accept();
		InetAddress serverAddress=socket.getInetAddress();
		SocketAddress clientAddress=socket.getRemoteSocketAddress();
		System.out.println("服务器地址："+serverAddress);
		System.out.println("客户端地址："+clientAddress);
		InputStream in=socket.getInputStream();
		OutputStream out=socket.getOutputStream();
		byte[] buffer=new byte[1024];
		int count;
	   count=in.read(buffer);
	   System.out.println(new String(buffer,0,count));
	   out.write("你好，我是服务端".getBytes());
		
	}

}

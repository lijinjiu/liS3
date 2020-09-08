package day0905;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * URL半双工模式web
 * Socket全双工QQ
 * */
public class DemoClientQQ {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket=new Socket("127.0.0.1",8888);	
		InetAddress clientAddress=socket.getInetAddress();
		SocketAddress serverAddress=socket.getRemoteSocketAddress();
		System.out.println("服务器地址："+serverAddress);
		System.out.println("客户端地址："+clientAddress);
		InputStream in=socket.getInputStream();
		OutputStream out=socket.getOutputStream();
		Scanner sc=new Scanner(System.in);
		  new Thread() {
		    	public void run() {
		    		byte[] buffer=new byte[1024];
	    			int count;
		    		while(true) {
		    		    try {
							System.out.print("我说:");
				    		out.write(sc.nextLine().getBytes());	
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
		    	}
		    }.start();
			
		    new Thread() {
		    	public void run() {
		    		byte[] buffer=new byte[1024];
	    			int count;
		    		while(true) {
		    		    try {
							count=in.read(buffer);
							System.out.print("他说:"+new String(buffer,0,count));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
		    	}
		    }.start();
	}
}

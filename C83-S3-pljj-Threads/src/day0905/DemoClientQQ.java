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
 * URL��˫��ģʽweb
 * Socketȫ˫��QQ
 * */
public class DemoClientQQ {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket=new Socket("127.0.0.1",8888);	
		InetAddress clientAddress=socket.getInetAddress();
		SocketAddress serverAddress=socket.getRemoteSocketAddress();
		System.out.println("��������ַ��"+serverAddress);
		System.out.println("�ͻ��˵�ַ��"+clientAddress);
		InputStream in=socket.getInputStream();
		OutputStream out=socket.getOutputStream();
		Scanner sc=new Scanner(System.in);
		  new Thread() {
		    	public void run() {
		    		byte[] buffer=new byte[1024];
	    			int count;
		    		while(true) {
		    		    try {
							System.out.print("��˵:");
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
							System.out.print("��˵:"+new String(buffer,0,count));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
		    	}
		    }.start();
	}
}

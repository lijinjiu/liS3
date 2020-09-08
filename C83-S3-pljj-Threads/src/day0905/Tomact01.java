package day0905;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Tomact01 {

	public static void main(String[] args) throws IOException {
		ServerSocket tomcat=new ServerSocket(8080);
		System.out.println("tomcat�����������ɹ� ����8080�˿�");
		boolean running=true;
		while(running) {
			Socket socket=tomcat.accept();
			System.out.println("���յ�����");
			new Thread() {
				public void run() {
					try {
						InputStream in=socket.getInputStream();
						OutputStream out=socket.getOutputStream();
						byte[] buffer=new byte[1024];
						int count;
						count=in.read(buffer);
						if(count >0) {
							//��ӡ������
							String requestText=new String(buffer,0,count);
							System.out.println(requestText);
							//��������������Դ�� GET /photo/404.html HTTP/1.1
							String[] lines=requestText.split("\\n");
							String[] requestLines=lines[0].split("\\s");
							String webpath=requestLines[1];
							String contentType;
							//�����
							int statusCode=200;
							//��������ļ�·��
							String path="D:/�����ĵ�/"+webpath;
							File file=new File(path);
							if(!file.exists()) {
								statusCode=404;
								path="D:/�����ĵ�/photo/404.html";
							}
							if(webpath.endsWith(".js")) {
								contentType="application/javascript charset=utf-8";
							}else if(webpath.endsWith(".css")){
								contentType="text/css; charset=utf-8";
							}else {
								//Ǳ���� ͼƬ���Է���HTML ����������Զ�ʶ��
								contentType= "text/html; charset=utf-8";
							}
							//��Ӧͷ��
							out.write(("HTTP/1.1 "+statusCode+" OK\n").getBytes());
							//��Ӧͷ��
							out.write(("contentType: "+contentType+"\n").getBytes());
							//����CRLF
							out.write("\n".getBytes());
							//ʵ��
							//out.write("���".getBytes());
							FileInputStream fis=new FileInputStream(path);
							while((count=fis.read(buffer))>0) {
								out.write(buffer,0,count);
							}
							/**
							 * ���⣺
							 *  1.ֻ�ܻظ�1��
							 *  2.�ظ���������Զ����
							 *      1.�����������е���Դ�� /photo/new.html
							 *      2.��ȡ�ļ������������ʵ����
							 * */
							fis.close();
						}
						socket.close();	
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		tomcat.close();
	}
}

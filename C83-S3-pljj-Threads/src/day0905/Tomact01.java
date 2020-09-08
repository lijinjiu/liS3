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
		System.out.println("tomcat服务器启动成功 监听8080端口");
		boolean running=true;
		while(running) {
			Socket socket=tomcat.accept();
			System.out.println("接收到请求");
			new Thread() {
				public void run() {
					try {
						InputStream in=socket.getInputStream();
						OutputStream out=socket.getOutputStream();
						byte[] buffer=new byte[1024];
						int count;
						count=in.read(buffer);
						if(count >0) {
							//打印请求报文
							String requestText=new String(buffer,0,count);
							System.out.println(requestText);
							//解析出请求报文资源名 GET /photo/404.html HTTP/1.1
							String[] lines=requestText.split("\\n");
							String[] requestLines=lines[0].split("\\s");
							String webpath=requestLines[1];
							String contentType;
							//结果码
							int statusCode=200;
							//定义磁盘文件路径
							String path="D:/下载文档/"+webpath;
							File file=new File(path);
							if(!file.exists()) {
								statusCode=404;
								path="D:/下载文档/photo/404.html";
							}
							if(webpath.endsWith(".js")) {
								contentType="application/javascript charset=utf-8";
							}else if(webpath.endsWith(".css")){
								contentType="text/css; charset=utf-8";
							}else {
								//潜规则： 图片可以返回HTML 浏览器可以自动识别
								contentType= "text/html; charset=utf-8";
							}
							//响应头行
							out.write(("HTTP/1.1 "+statusCode+" OK\n").getBytes());
							//响应头域
							out.write(("contentType: "+contentType+"\n").getBytes());
							//空行CRLF
							out.write("\n".getBytes());
							//实体
							//out.write("你好".getBytes());
							FileInputStream fis=new FileInputStream(path);
							while((count=fis.read(buffer))>0) {
								out.write(buffer,0,count);
							}
							/**
							 * 问题：
							 *  1.只能回复1次
							 *  2.回复的内容永远不变
							 *      1.解析出请求中的资源名 /photo/new.html
							 *      2.读取文件内容输出到是实体中
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

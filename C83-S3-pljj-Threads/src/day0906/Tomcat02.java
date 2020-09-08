package day0906;
/**
 * //�������� ����8080�ӿ�
		//ѭ������Socket����
		//ʹ����ɴ������������͵�����
		    //ʹ����Դ��ַ���ֶ�̬����Ͷ�̬����
		    //����������
	    //ʹ����Դ��ַ��Servelt�����л�ȡServlet����
	    //���û���ҵ���Ӧ��Servlet���� ��ô������Ϊ��̬����
	    //����̬����
	        //�ж���Դ�Ƿ���� ��������ڷ���404
 * 
 * */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream; 
import java.net.HttpRetryException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Tomcat02 {
	//Servlet����   HashMap< String, Servlet>  URL��ַ+Servlet����
	private HashMap< String, Servlet> servletMap;
	
	public void startup() throws IOException {
		//��������ʼ��Servlet����===��Map����===��URL:Servlet
		servletMap=new HashMap<>();
		servletMap.put("/photo/hello",new HelloServlet());
		//��ToIndexServlet��Ϊ��վ��Ĭ��ҳ��
		servletMap.put("/",new ToIndexServlet());
		//ע��cookie Servlet
		servletMap.put("/cookie",new CookieServlet());
		servletMap.put("/login.jsp",new LoginPageServlet());
		servletMap.put("/photo/post.do",new PostServlet());
		//�������� ����8080�ӿ�
		//ѭ������Socket����
		//ʹ����ɴ������������͵�����
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
							
							
							//����������  HttpServletRequest�������
							HttpServletRequest request=buildRequest(requestText);
							HttpServletResponse response=new HttpServletResponse(out);
							 //ʹ����Դ��ַ���ֶ�̬����Ͷ�̬����
							//ʹ����Դ��ַ��Servelt�����л�ȡServlet����
							String uri=request.getRequestURI();
							Servlet servlet=servletMap.get(uri);
							if(servlet!=null) {
								//��̬  ʹ��Servlet����̬����
								servlet.service(request, response);
							}else {
								//���û���ҵ���Ӧ��Servlet���� ��ô������Ϊ��̬����
                               processStaticRequest(request, out);								
							    //����̬����
							        //�ж���Դ�Ƿ���� ��������ڷ���404	
							}
						    
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

	public void shutdown() {
		
	}
	
	//�����������
	private HttpServletRequest buildRequest(String requestText) {
		return new HttpServletRequest(requestText);
	}
	
	public static void main(String[] args) throws IOException {
		new Tomcat02().startup();
	}
	
	public void processStaticRequest(HttpServletRequest request,OutputStream out) throws IOException {
		String webpath=request.getRequestURI();
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
		byte[] buffer=new byte[1024];
		int count;
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
}

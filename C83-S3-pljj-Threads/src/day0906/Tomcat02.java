package day0906;
/**
 * //启动服务 监听8080接口
		//循环生成Socket对象
		//使用想成处理流浪器发送的请求
		    //使用资源地址区分动态请求和动态请求
		    //解析请求报文
	    //使用资源地址到Servelt容器中获取Servlet对象
	    //如果没有找到对应的Servlet对象 那么将其视为静态请求
	    //处理静态请求
	        //判断资源是否存在 如果不存在返回404
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
	//Servlet容器   HashMap< String, Servlet>  URL地址+Servlet对象
	private HashMap< String, Servlet> servletMap;
	
	public void startup() throws IOException {
		//服务器初始化Servlet容器===》Map集合===》URL:Servlet
		servletMap=new HashMap<>();
		servletMap.put("/photo/hello",new HelloServlet());
		//让ToIndexServlet称为网站的默认页面
		servletMap.put("/",new ToIndexServlet());
		//注册cookie Servlet
		servletMap.put("/cookie",new CookieServlet());
		servletMap.put("/login.jsp",new LoginPageServlet());
		servletMap.put("/photo/post.do",new PostServlet());
		//启动服务 监听8080接口
		//循环生成Socket对象
		//使用想成处理流浪器发送的请求
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
							
							
							//解析请求报文  HttpServletRequest请求对象
							HttpServletRequest request=buildRequest(requestText);
							HttpServletResponse response=new HttpServletResponse(out);
							 //使用资源地址区分动态请求和动态请求
							//使用资源地址到Servelt容器中获取Servlet对象
							String uri=request.getRequestURI();
							Servlet servlet=servletMap.get(uri);
							if(servlet!=null) {
								//动态  使用Servlet处理动态请求
								servlet.service(request, response);
							}else {
								//如果没有找到对应的Servlet对象 那么将其视为静态请求
                               processStaticRequest(request, out);								
							    //处理静态请求
							        //判断资源是否存在 如果不存在返回404	
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
	
	//解析请求对象
	private HttpServletRequest buildRequest(String requestText) {
		return new HttpServletRequest(requestText);
	}
	
	public static void main(String[] args) throws IOException {
		new Tomcat02().startup();
	}
	
	public void processStaticRequest(HttpServletRequest request,OutputStream out) throws IOException {
		String webpath=request.getRequestURI();
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
		byte[] buffer=new byte[1024];
		int count;
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
}

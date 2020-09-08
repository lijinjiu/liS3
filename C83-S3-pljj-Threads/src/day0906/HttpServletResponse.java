package day0906;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//Entry��Map�ľ�̬�ڲ��ӿ�
import java.util.Map.Entry;

public class HttpServletResponse {
	//ͨ��socket��ȡ�������
	private OutputStream out;
	private int status;
	private String msg;
	//���ͷ���ֵ�Ե�map����
    private Map<String, String> headerMap=new HashMap<>();
    //����Cookie���͸��������Cookie
    private List<Cookie> cookieList=new ArrayList<>();
    
	public HttpServletResponse(OutputStream out) {
		this.out=out;
	}
	//�ַ���������� ��������ݱ��浽һ���ַ�����  ��Դ��
	private CharArrayWriter caw=new CharArrayWriter();
	//������  
	private PrintWriter pw=new PrintWriter(caw);
	//��ȡ��Ӧ���������ӡ����  ��ʱ����Servlet���������
	public PrintWriter getWriter() {
		return pw;
	}
	//������Ӧ�����ͽ������Ϣ
	public void setStatus(int status,String msg) {
		//����Ѿ����ý���� ��ô�Ͳ�������
		if(this.status==0) {
			this.status=status;
			this.msg=msg;	
		}
	}
	//����Ӧ�������͸������
	public void flushBuffer() throws IOException {
		out.write(("HTTP/1.1 "+status+" "+msg+"\n").getBytes());
		//��Ӧͷ��
		out.write(("contentType: text/html; charset=utf-8\n").getBytes());
		//map���ϵ�ѭ�� ��ͷ�򼯺��е�ֵд����Ӧ����
		for(Entry<String,String>entry:headerMap.entrySet()) {
			out.write((entry.getKey()+":"+entry.getValue()+"\n").getBytes());
		}
		//������ѭ��
		for (Iterator<Cookie> iterator = cookieList.iterator(); iterator.hasNext();) {
			Cookie cookie = iterator.next();
			out.write(cookie.toString().getBytes());
		}
		//����CRLF
		out.write("\n".getBytes());
		out.write(caw.toString().getBytes());
	}
	//��Ӧ�ض��򷽷� uri Ҫ��ת��ҳ��
	public void sendRedirect(String uri) {
		//д�����301 or 302
		setStatus(301, "Redirect");
		//��ͷ����д��(����)Location��Ҫ��ת��ҳ��
		headerMap.put("Location",uri);
		
	}
	public void addCookie(Cookie cookie) {
		cookieList.add(cookie);
	}	
}

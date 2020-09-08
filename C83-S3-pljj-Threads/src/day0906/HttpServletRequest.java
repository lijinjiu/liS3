package day0906;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpServletRequest {

	private String method;
	private String requestUri;
	private String Protocol;
	//���ͷ���ֵ�Ե�map����
	private Map<String, String> headerMap=new HashMap<>();
	//��Ų�����map����
	private Map<String, String> paramsMap=new HashMap<>();
	//���췽��
	public HttpServletRequest(String requestText) {
		//��ɶ������ĵĽ���
		String[] lines=requestText.split("\\n");   //���зָ�
		String[] items=lines[0].split("\\s");    //�ո�ָ�
		method=items[0];
		requestUri=items[1];
		Protocol=items[2];
		
		//��ѯ�Ƿ��в���
		int index=items[1].indexOf("?");
		if(index!=-1) {
			//��������
			requestUri=items[1].substring(0,index);
			String paramString=items[1].substring(index+1);
			String[] params=paramString.split("&");
			for(int i=0;i<params.length;i++) {
				String[] nv=params[i].split("=");
				if(nv.length==1) {
					 paramsMap.put(nv[0], "");
				}else if(nv.length>1) {
					 paramsMap.put(nv[0], nv[1]);
				}	 
			}
		}
		
		for(int i=1;i<lines.length;i++) {
			lines[i]=lines[i].trim();
			if(lines[i].isEmpty()) {
				break;
			}
			items=lines[i].split(":");
			headerMap.put(items[0],items[1].trim());
		}
		 
	}
	//��ȡ���󷽷���
	public String getMethod() {
		return method;
	}
	//��ȡ������Դ·��
	public String getRequestURI() {
		return requestUri;
	}
	//��ȡЭ��汾��
	public String getProtocol() {
		return Protocol;
	}
	//��ȡͷ��ֵ ��ֵ��  String name��
	public String getHeader(String name) {
		return headerMap.get(name);
	}
	//��ȡ�������
	public String getParameter(String name) {
		return paramsMap.get(name);
	}
	//��ȡcookie����
	public Cookie[] getCookies() {
		List<Cookie> cookieList=new ArrayList<>();
		String cookieString=headerMap.get("Cookie");
		if(cookieString==null) {
			return null;
		}else {
			String[] sCookies=cookieString.split(";\\s*");//s�ո�
			for(int i=0;i<sCookies.length;i++){
				String[] nv=sCookies[i].split("=");
				cookieList.add(new Cookie(nv[0],nv[1]));
			}
			return cookieList.toArray(new Cookie[0]);	 
		}
	}
	
}

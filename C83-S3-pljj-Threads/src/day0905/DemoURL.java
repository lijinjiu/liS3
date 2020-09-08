package day0905;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DemoURL {

	public static void main(String[] args) throws IOException {
		URL url=new URL("http://www.hyycinfo.com");
		URLConnection conn=url.openConnection();
		InputStream in=conn.getInputStream();
		byte[] buffer=new byte[1024];
		int count;
		while((count=in.read(buffer))>0) {
			System.out.println(new String(buffer,0,count));
		}
	}
}

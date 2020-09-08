package day0906;

public class Cookie {

	private String name;
	private String value;
	private int maxAge=-1;
	public Cookie(String name, String value) {
		this.name=name;
		this.value=value;
	}
	
	public String toString() {
		String  s="Set-Cookie: %s=%s;";
		s=String.format(s, name, value);
		if(maxAge>-1) {
			s+="Max-Age="+maxAge+";";
		}
		s+="\n";
		return s;
	}

	public void setMaxAge(int maxAge) {
	    this.maxAge=maxAge;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getMaxAge() {
		return maxAge;
	}
	
	
}

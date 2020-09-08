package day0905;


import java.util.ArrayList;
import java.util.List;

public class Demo4 {

	private List<String> list=new ArrayList<>();
	
	public void add(String i) {
		list.add(i);
	}
	
	public String pop() {
		return list.remove(0);
	}
	
	public int size() {
		return list.size();
	}
	
	public static void main(String[] args) {
		Demo4 d=new Demo4();
		Thread t1=new Thread("线程1") {
			public void run() {
				while(true) {
					if(d.size()<10) {
						for(int i=0;i<10;i++) {
							System.out.println(Thread.currentThread().getName()+":"+i);	
							d.add(""+i);  //转字符串
						} 
					}
				}
			}
		};
		
		Thread t2=new Thread("------线程2") {
			public void run() {
				while(true) {
					if(d.size()>0) {
						System.out.println(Thread.currentThread().getName()+":"+d.pop());
					}
				}
			}
		};
		

		Thread t3=new Thread("===========线程3") {
			public void run() {
				while(true) {
					if(d.size()>0) {
						System.out.println(Thread.currentThread().getName()+":"+d.pop());
					}
				}
			}
		};
		
		t1.start();
		t2.start();
		t3.start();
	}
}

package day0905;


//join
public class Demo3 {

	public static void main(String[] args) {
		
		//�����ڲ��ഴ���߳�
		Thread t1=new Thread("�߳�1") {
			public void run() {
				for(int i=0;i<1000;i++) {
					System.out.println(Thread.currentThread().getName()+":"+i);
				}
			}
		};
		
		Thread t2=new Thread("�߳�2") {
			public void run() {
				for(int i=0;i<1000;i++) {
					System.out.println(Thread.currentThread().getName()+":"+i);
				   //t2ִ�е�500�� join��t1��
					try {
						if(i==500) {
							t1.join();	
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		t1.start();
		t2.start();
	}
	
	
}

package day0905;

import java.util.Scanner;

public class Demo1 {

	public static void main(String[] args) {
		//ʹ�ö��߳���a��bͬʱ����
		System.out.println(" main name:"+Thread.currentThread().getName());
		System.out.println(" main name:"+Thread.currentThread().getPriority());
		System.out.println(" main name:"+Thread.currentThread().getState());
	
		A a=new A("����a()����");
		B b=new B();
		a.start();
		Thread t=new Thread(b,"����b()����");
		t.start();
	}
	
	public static void a() {
		Scanner sc=new Scanner(System.in);
		System.out.println("������:");
		System.out.println("��������ǣ�"+sc.nextLine());
	}
	
	public static void b() {
		System.out.println("����b()����");
	}
	
	//��̬�ڲ���ʵ���߳���
	//�̳�Thread�� ʵ�ֶ��߳�
	public static class A extends Thread{
		
		public A(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}

		//run��߼�ͷ �̳еķ���  ��д
		public void run() {
			a();
			System.out.println(" a() name:"+Thread.currentThread().getName());
			System.out.println(" a()  name:"+Thread.currentThread().getPriority());
			System.out.println(" a()  name:"+Thread.currentThread().getState());
		}
	}
	
	public static class B implements Runnable{
		@Override
		public void run() {
			b();
			System.out.println(" b() name:"+Thread.currentThread().getName());
			System.out.println(" b()  name:"+Thread.currentThread().getPriority());
			System.out.println(" b()  name:"+Thread.currentThread().getState());
		}
		
	}
}

package day0905;

import java.util.Scanner;
//sleep
public class Demo2 {

	public static void main(String[] args) {
		//使用多线程让a和b同时运行
		System.out.println(" main name:"+Thread.currentThread().getName());
		System.out.println(" main name:"+Thread.currentThread().getPriority());
		System.out.println(" main name:"+Thread.currentThread().getState());
	
		A a=new A("这是a()方法");
		B b=new B();
		a.start();
		Thread t=new Thread(b,"这是b()方法");
		t.start();
	}
	
	public static void a() {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入:");
		System.out.println("你输入的是："+sc.nextLine());
	}
	
	public static void b() {
		System.out.println("这是b()方法");
	}
	
	//静态内部类实现线程类
	//继承Thread类 实现多线程
	public static class A extends Thread{
		
		public A(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}

		//run左边箭头 继承的方法  重写
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
			//线程休眠10秒 调用Thread静态方法sleep
			System.out.println("b()休眠5秒");
			try {
				Thread.sleep(5*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			b();
			System.out.println(" b() name:"+Thread.currentThread().getName());
			System.out.println(" b()  name:"+Thread.currentThread().getPriority());
			System.out.println(" b()  name:"+Thread.currentThread().getState());
		}
		
	}
}

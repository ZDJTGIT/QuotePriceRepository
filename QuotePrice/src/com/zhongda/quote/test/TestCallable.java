package com.zhongda.quote.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class TestCallable implements Callable<String>{
	
	@Override
	public String call() throws Exception {
		String cString = Thread.currentThread().getName();
		for(int i = 0; i < 15 ; i++){
			System.out.println("创造的线程:"+ i + cString);
		}
		return cString;
	}

	
	public static void main(String[] args) {

		FutureTask<String> task = new FutureTask<String>(new TestCallable());
		new Thread(task, "子线程").start();
		String bString;
		try {
			bString = task.get();
			System.out.println("创造的线程返回值："+ bString);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < 10; i++){
			String mainName;
			mainName = Thread.currentThread().getName();
			System.out.println("main主线程:" + i + mainName);
		}
	

}

}
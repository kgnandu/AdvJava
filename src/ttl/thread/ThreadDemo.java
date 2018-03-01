package ttl.thread;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {

	public static void main(String[] args) throws IOException {
		new ThreadDemo().compFuture();
	}
	
	public void compFuture() {
		int numCpus = Runtime.getRuntime().availableProcessors();
		ExecutorService es = Executors.newFixedThreadPool(numCpus + 2);
		WorkerC w1 = new WorkerC();
		
		System.out.println(Thread.currentThread().getName());
		//CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> w1.call())
		CompletableFuture.supplyAsync(() -> w1.call())
				.thenAccept(System.out::println);
		
		/*
		try {
			Integer result = cf.get();
			System.out.println("result " + result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		*/
		
	}
	
	public void goESCallable() {
		int numCpus = Runtime.getRuntime().availableProcessors();
		ExecutorService es = Executors.newFixedThreadPool(numCpus + 2);

		WorkerC w1 = new WorkerC();
		// Future<Integer> f1 = es.submit(w1);
		Future<Integer> f1 = es.submit(() -> {
			int sum = 0;
			for (int i = 0; i < 1000; i++) {
				sum += i;
			}

			return sum;
		});
		

		WorkerC w2 = new WorkerC();
		Future<Integer> f2 = es.submit(w2);

		try {
			int sum1 = f1.get();
			int sum2 = f2.get();

			int finalSum = sum1 + sum2;

			// how to wait
			System.out.println(finalSum);
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		es.shutdown();

		int count = 0 ;
		while (!es.isTerminated()) {
			try {
				es.awaitTermination(2, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(count++ == 5) {
				System.out.println("Something bad is happening");
			}
		}

	}

	public void goES() throws IOException {
		int numCpus = Runtime.getRuntime().availableProcessors();
		ExecutorService es = Executors.newFixedThreadPool(numCpus + 2);

		Worker w1 = new Worker("Worker 1");
		Future<?> f1 = es.submit(w1);
		
		w1.stopGoin();

		Worker w2 = new Worker("Worker 2");
		Future<?> f2 = es.submit(w2);

		try {
			f1.get();
			f2.get();
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		

		// how to wait
		int finalSum = w1.getSum() + w2.getSum();
		System.out.println(finalSum);

		new Scanner(System.in).nextLine();

		es.shutdownNow();

		int count = 0 ;
		while (!es.isTerminated()) {
			try {
				es.awaitTermination(2, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(count++ == 5) {
				System.out.println("Something bad is happening");
			}
		}
	}

	public void go() {
		Worker w1 = new Worker("Worker 1");
		Thread th1 = new Thread(w1);

		Worker w2 = new Worker("Worker 2");
		Thread th2 = new Thread(w2);

		th1.start();
		th2.start();

		try {
			th1.join();
			th2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int finalSum = w1.getSum() + w2.getSum();

		System.out.println(finalSum);

	}

	class WorkerC implements Callable<Integer> {
		private int sum = 0;

		@Override
		public Integer call() {
			System.out.println(Thread.currentThread().getName());
			for (int i = 0; i < 1000; i++) {
				sum += i;
			}
			return sum;
		}
	}

	class Worker implements Runnable {
		private String name;
		private int sum = 0;
		private volatile boolean keepGoing = true;

		public Worker(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			//for (int i = 0; i < 1000; i++) {
			//for(;!Thread.currentThread().isInterrupted();)  {
			while(keepGoing) {
				//sum += i;
				try {
					Thread.sleep(100);
				}
				catch(InterruptedException e) {
					System.out.println("Got Interrupted");
					Thread.currentThread().interrupt();
				}
			}
		}
		
		public void stopGoin() {
			keepGoing = false;
		}

		public int getSum() {
			return sum;
		}
	}
}

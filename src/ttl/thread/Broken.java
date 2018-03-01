package ttl.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Broken {

	public void go() {
		NextId ni = new NextId();
		
		int numCpus = Runtime.getRuntime().availableProcessors();
		ExecutorService es = Executors.newFixedThreadPool(numCpus + 2);
		WorkerC w1 = new WorkerC(ni);
		WorkerC w2 = new WorkerC(ni);

		Future<Integer> f1 = es.submit(w1);
		Future<Integer> f2 = es.submit(w2);
		
		
		try {
			int sum1 = f1.get();
			int sum2 = f2.get();

			int finalSum = sum1 + sum2;

			// how to wait
			System.out.println(ni.getNextId());
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	class WorkerC implements Callable<Integer> {
		private int sum = 0;
		private NextId ni;
		public WorkerC(NextId ni) {
			this.ni = ni;
		}
		@Override
		public Integer call() {
			System.out.println(Thread.currentThread().getName());
			for (int i = 0; i < 1000; i++) {
				int nextId = ni.getNextId();
				sum += nextId;
			}
			return sum;
		}
	}
	
	public static void main(String[] args) {
		new Broken().go();
	}
}

class NextId
{
	private int nextId;
	private Object syncObject = new Object();
	
	public int getNextId() {
		synchronized(syncObject) {
			return nextId++;
		}
	}
	
	public void setNextId() {
		nextId = 24;
	}
}



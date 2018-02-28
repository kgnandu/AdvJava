package ttl.sf.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericDemo {

	public static void main(String[] args) {
		new GenericDemo().go();
	}
	
	public void go() {
		List<Number> lNumber = new ArrayList<>();
		lNumber.add(22.333);
		lNumber.add(22.34);
		lNumber.add(10);
		lNumber.add(4.890);
		
		System.out.println(sum(lNumber));
		
		List<Integer> iList = new ArrayList<>();
		iList.add(10);
		iList.add(22);
		iList.add(3);
		iList.add(45);
		
		sum(iList);
		
		Integer [] arr = { 0, 3, 6};

		addToList(iList, arr);
		
		System.out.println(iList);
		
		addToList(lNumber, arr);
	}
	
	public <T> void addToList(List<? super T> list, T [] arr) {
		for(T it : arr) {
			list.add(it);
		}
	}

	public double sum(List<? extends Number> input) {
		
		double sum = 0;
		for(Number n : input) {
			sum += n.doubleValue();
		}
		
		return sum;
	}
}

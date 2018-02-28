package ttl.sf.app;

import java.util.Arrays;
import java.util.stream.Stream;

public class RegAppFlatMap {

	public static void main(String[] args) {
		RegAppFlatMap ra = new RegAppFlatMap();
		ra.go();
	}

	public void go() {
		String [][] data = { 
				{"one", "two", "three" },
				{"four", "five", "six" },
				{"nine", "eight", "seven" },
		};
		
		//Stream.of(data)
		Stream<String[]> s1 = Arrays.stream(data);
		
		Stream<String> s2 = s1.flatMap(sarr -> Arrays.stream(sarr));

		Arrays.stream(data)
			.peek(s -> System.out.println("BeforeMap: " + s))
			.flatMap(sarr -> Arrays.stream(sarr))
			.peek(s -> System.out.println("AfterMap: " + s))
			.forEach(System.out::println);
	}
	
}


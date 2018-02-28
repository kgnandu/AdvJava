package ttl.sf.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class to count words.
 * Web site for raw data: http://www.gutenberg.org
 * @author whynot
 *
 */

public class WordCounter {

	public static void main(String[] args) throws IOException {
		// String sourceFile = "src/ttl/javastreams/WordCounter.java";
		//String sourceFile = "TaleOfTwoCities.txt";
		String sourceFile = "PrideAndPrejudice.txt";
		countWords(sourceFile);

	}

	public static void countWords(String sourceFile) throws IOException {

		Map<String, Long> result = Files.lines(Paths.get(sourceFile))
				.filter(s -> s.matches("^\\w.*")) // get rid of any empty lines
				.flatMap(s -> Arrays.stream(s.split("\\W"))) // convert all
																// streamed
																// arrays into
																// one stream
				.map(s -> s.toLowerCase())
				// now make a map, using the string itself as a key,
				// creating a new TreeMap to hold the result (will be sorted by
				// key, i.e words),
				// And send the resulting lists of words that would be the value
				// of the map
				// into the counting() collector. Which will count the number of
				// values and
				// result in a Map of <Word, Num Of Occurrences>
				.collect(
						Collectors.groupingBy(s -> s,
								TreeMap::new,
								Collectors.counting()));
		
	
		Comparator<String> comp = (left, right) -> {
			Long v1 = result.get(left);
			Long v2 = result.get(right);
			
			int i = v1.compareTo(v2);
			
			/*
			if(i == 0) {
				i = left.compareTo(right);
			}
			*/
			
			return i;
		};
		
		Map<String, Long> byValue = new TreeMap<>(comp);
		byValue.putAll(result);

		byValue.forEach((k, v) -> System.out.printf("%s: %d%n", k, v));
		
	}
}

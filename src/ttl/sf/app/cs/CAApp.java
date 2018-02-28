package ttl.sf.app.cs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class CAApp {

	public void go() {
		List<DataHolder> data = new ArrayList<>();
		data.add(new DataHolder(10, 2));
		data.add(new DataHolder(3, 25));
		data.add(new DataHolder(6, 200));
		data.add(new DataHolder(85, 49));
		data.add(new DataHolder(10, 8));
		
		Supplier<CustomStatistics> sup = CustomStatistics::new;
		
		BiConsumer<CustomStatistics, DataHolder> acc = (cs, d) -> {
			cs.xMax = Math.max(cs.xMax, d.xValue);
			cs.xMin = Math.min(cs.xMin, d.xValue);

			cs.yMax = Math.max(cs.yMax, d.yValue);
			cs.yMin = Math.min(cs.yMin, d.yValue);
			
			cs.xSum += d.xValue;
			cs.ySum += d.yValue;
			
			cs.size++;
		};

		BiConsumer<CustomStatistics, CustomStatistics> comb = (cs1, cs2) -> {
			//We will return cs1
			cs1.xMax = Math.max(cs1.xMax, cs2.xMax);
			cs1.xMin = Math.min(cs1.xMin, cs2.xMin);

			cs1.yMax = Math.max(cs1.yMax, cs2.yMax);
			cs1.yMin = Math.min(cs1.yMin, cs2.yMin);
			
			cs1.xSum += cs2.xSum;
			cs1.ySum += cs2.ySum;
			
			cs1.size += cs2.size;
		};
		
		CustomStatistics cs = data.stream().parallel()
				.collect(sup, acc, comb);
		
		System.out.println(cs);
	}
	
	public static void main(String[] args) {
		CAApp cap = new CAApp();
		cap.go();
	}
}

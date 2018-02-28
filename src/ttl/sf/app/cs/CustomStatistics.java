package ttl.sf.app.cs;

public class CustomStatistics {

	public int xSum;
	public int ySum;
	
	public int xMax = Integer.MIN_VALUE;
	public int yMax = Integer.MIN_VALUE;
	
	public int xMin = Integer.MAX_VALUE;
	public int yMin = Integer.MAX_VALUE;
	
	public int size;

	@Override
	public String toString() {
		return "CustomStatistics [xSum=" + xSum + ", ySum=" + ySum + ", xMax=" + xMax + ", yMax=" + yMax + ", xMin="
				+ xMin + ", yMin=" + yMin + ", size=" + size + "]";
	}
	
}


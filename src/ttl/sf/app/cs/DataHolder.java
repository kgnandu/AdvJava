package ttl.sf.app.cs;

public class DataHolder {
	public int xValue;
	public int yValue;
	
	public DataHolder(int x, int y) {
		xValue = x;
		yValue = y;
	}

	@Override
	public String toString() {
		return "DataHolder [xValue=" + xValue + ", yValue=" + yValue + "]";
	}
}

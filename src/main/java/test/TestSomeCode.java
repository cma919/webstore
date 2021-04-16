package test;

import com.cwpark.webstore.util.ValueFormat;

public class TestSomeCode {

	public static void main(String[] args) {
		long df = 1000l;
		String formatted = ValueFormat.format(df, ValueFormat.COMMAS);
		System.out.println(formatted);
	}

}

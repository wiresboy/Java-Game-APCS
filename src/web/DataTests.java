package web;

public class DataTests {

	public static void main(String[] args) {
		String[][] start = new String[][]
			   {{"a1","a2"},
				{"b1","b2"},
				{"c1","c2"},
				{"d1","d2"},
				{"e1","e2"}};

		System.out.println("start:");
		printArray(start);
		
		String packed = DataTransmitter.pack2DStringArray(start);
		System.out.println("\n\n\nPacked:");
		System.out.println(packed);
		System.out.println("\n\n");
		
		String[][] end = DataTransmitter.Unpack2DStringArray(packed);
		
		System.out.println("End:");
		printArray(end);
	}

	
	public static void printArray(String[][] vals)
	{
		for (String[] a : vals)
		{
			for (String b : a)
				System.out.print(b+", ");
			System.out.println();
		}
	}
}

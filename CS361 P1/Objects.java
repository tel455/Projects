public class Objects{
	private String name;
	private int value;

	Objects(String x){
		name = x;
		value = 0;
	}

	public String getName(){
		return name;
	}

	public int getValue(){
		return value;
	}

	public void updateValue(int x){
		value = x;
	}

}
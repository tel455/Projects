public class SecurityLevel{
	private String level;
	private int level_value;

	SecurityLevel(String x){
		level = x;
		if(level.equals("HIGH")){
			level_value = 1;
		}else if(level.equals("LOW")){
			level_value = 0;
		}
	}

	public int getLevel(){
		return level_value;
	}

}
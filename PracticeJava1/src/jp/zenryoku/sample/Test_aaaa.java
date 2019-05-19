package jp.zenryoku.sample;

public class Test_aaaa {
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		String[] json = new String[] {"a", "b", "c"};
		try {
			System.out.println(json[num]);
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}

      public static void yobareru() {
    	  System.out.println("Nice to meet you!");
	}
}

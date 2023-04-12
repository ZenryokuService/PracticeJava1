package jp.basic.description;

public class DoWhileDesc {
    public static void main(String[] args) {
        String[] arr = null;
        int a = arr.length;
        boolean isTrue = true;
        int count = 0;
        do {
           System.out.println("Hello");
           count++;
           if (count > 5) {
               System.out.println("Break!");
               break;
           }
        } while(isTrue);
    }
}

package jp.zenryoku.sample.algorithm;

public class BubleSort {
	public static void main(String[] args) {
		int[] hako = new int[] {6,8,7,9,5,2,3,4,1};

		int target = 0;
		int arr_size = hako.length - 1;
		for (int i = 0; i < arr_size; i++) {
			for (int j = arr_size; j > i; j--) {
				if (hako[j - 1] < hako[j]) {
					int numTemp = hako[j - 1];
					hako[j - 1] = hako[j];
					hako[j] = numTemp;;
				}
			}
		}

		for (int num : hako) {
			System.out.print(num);
		}
	}

}

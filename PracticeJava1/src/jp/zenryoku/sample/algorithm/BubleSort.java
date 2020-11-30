package jp.zenryoku.sample.algorithm;

public class BubleSort {
	public static void main(String[] args) {
		int[] hako = new int[] {9,8,7,6,5,4,3,2,1};

		int tmp = 0;
		for (int i = 0; i < hako.length; i++) {
			if (i == 0) {
				tmp = hako[i];
				continue;
			}
			if (tmp > hako[i]) {
				tmp = hako[i - 1];
				int numTemp = hako[i];
				hako[i - 1] = numTemp;
				hako[i] = tmp;
				tmp = numTemp;
			}
		}

		for (int num : hako) {
			System.out.print(num);
		}
	}

}

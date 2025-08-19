
import java.util.*;

public class MaxOfWindow {

	public int[] maxSlidingWindow(int[] nums, int k) {
		TreeSet<Integer> tset = new TreeSet((i1, i2) -> nums[(int) i1] - nums[(int) i2]);
		int[] res = new int[nums.length - k + 1];
		for (int i = 0, l = 0, j = 0; i < nums.length; i++) {
			tset.add(i);
			while (!tset.isEmpty() && tset.size() > k && l < i) {
				tset.remove(l++);
			}
			if (tset.size() == k) {
				res[j++] = nums[tset.last()];
			}
		}

		return res;
	}

	public static void main(String[] args) {
		new MaxOfWindow().maxSlidingWindow(new int[] { 1, 3, 1, 2, 0, 5 }, 3);
	}

}

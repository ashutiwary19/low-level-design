
public class RabinKarp {
	public int hash(String word) {
		int n = word.length() - 1;
		int max = Integer.MAX_VALUE;
		int hash = 0;
		for (int i = 0; i <= n; i++) {
			hash += word.charAt(i) * Math.pow(10, (n - i)) % max;
		}

		return hash % max;
	}

	public int repeatedStringMatch(String a, String b) {
		int hash = hash(b);
		int l = 0, r = 0;
		String temp = a;
		int repeat = 1;
		int currHash = 0;
		int n = b.length();
		int max = Integer.MAX_VALUE;
		while (true) {
			while (r < temp.length() && r - l < n) {
				currHash += temp.charAt(r) * Math.pow(10, n - 1 - (r % n)) % max;
				r++;
			}

			if (currHash == hash) {
				return repeat;
			}
			if (r >= temp.length()) {
				temp += a;
				repeat++;
			}

			if (l < r) {
				currHash -= temp.charAt(l) * Math.pow(10, n - 1 - (l % n)) % max;
				l++;
			}

			if (temp.length() > 2 * n) {
				return -1;
			}
		}
	}

	public static void main(String[] args) {
//		Math.abs
		new RabinKarp().repeatedStringMatch("abcd", "cdabcdab");
	}
}

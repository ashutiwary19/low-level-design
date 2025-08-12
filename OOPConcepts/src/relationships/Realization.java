package relationships;

interface Payment {
	void pay();
}

class CreditCardPayment implements Payment {
	@Override
	public void pay() {
		System.out.println("Paid using Credit Card.");
	}
}

class CashPayment implements Payment {
	@Override
	public void pay() {
		System.out.println("Paid using Cash.");
	}
}

public class Realization {
	public static void main(String[] args) {
		Payment payment1 = new CreditCardPayment();
		Payment payment2 = new CashPayment();
		payment1.pay();
		payment2.pay();
	}
}

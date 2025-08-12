package creationalDesignPattern;

// Factor method, not a design pattern but a design idiom
class PizzaFactory {
	public static Pizza createPizza(String type) {
		Pizza pizza;
		if (PepperoniPizza.class.getName().equalsIgnoreCase(type)) {
			pizza = new CheesePizza();
		} else if (PaneerPizza.class.getName().equalsIgnoreCase(type)) {
			pizza = new PaneerPizza();
		} else {
			pizza = new CheesePizza();
		}
		return pizza;
	}
}

abstract class PizzaStore {
	public abstract Pizza createPizza(String type);

	public void order(String type) {
		/*
		 * Pizza pizza; if (PepperoniPizza.class.getName().equalsIgnoreCase(type)) {
		 * pizza = new CheesePizza(); } else if
		 * (PaneerPizza.class.getName().equalsIgnoreCase(type)) { pizza = new
		 * PaneerPizza(); } else { pizza = new CheesePizza(); } return pizza;
		 */
//		Pizza pizza = PizzaFactory.createPizza(type);
		Pizza pizza = createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.pack();
		pizza.ship();
	}
}

// Factpry method pattern
class IndianPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza;
		if (IndianStylePepperoniPizza.class.getName().equalsIgnoreCase(type)) {
			pizza = new IndianStylePepperoniPizza();
		} else if (IndianStylePaneerPizza.class.getName().equalsIgnoreCase(type)) {
			pizza = new IndianStylePaneerPizza();
		} else {
			pizza = new IndianStyleCheesePizza();
		}
		return pizza;
	}

}

class NYPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String type) {
		// TODO Auto-generated method stub
		return null;
	}

}

class ItalianPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String type) {
		// TODO Auto-generated method stub
		return null;
	}

}

public class FactoryPattern {
	public static void main(String[] args) {

	}

}

package creationalDesignPattern;

public interface Pizza {
	public void prepare();

	public void bake();

	public void pack();

	public void ship();
}

interface Cheese {

}

interface Bread {

}

interface Vegies {

}

class IndianBread {

}

class NYBread {
}

class ItalianBread {

}

class CheesePizza implements Pizza {

	@Override
	public void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	public void bake() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ship() {
		// TODO Auto-generated method stub

	}

}

class IndianStyleCheesePizza extends CheesePizza {

}

class NYStyleCheesePizza extends CheesePizza {

}

class ItalianStyleCheesePizza extends CheesePizza {

}

class PepperoniPizza implements Pizza {

	@Override
	public void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	public void bake() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ship() {
		// TODO Auto-generated method stub

	}

}

class IndianStylePepperoniPizza extends PepperoniPizza {

}

class NYStylePepperoniPizza extends PepperoniPizza {

}

class ItalianStylePepperoniPizza extends PepperoniPizza {

}

class PaneerPizza implements Pizza {

	@Override
	public void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	public void bake() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ship() {
		// TODO Auto-generated method stub

	}
}

class IndianStylePaneerPizza extends PaneerPizza {

}

class NYStylePaneerPizza extends PaneerPizza {

}

class ItalianStylePaneerPizza extends PaneerPizza {

}
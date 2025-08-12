package relationships;

class Car {
	String model;

	Car(String model) {
		this.model = model;
	}

	void drive() {
		System.out.println("Driving a " + model);
	}
}

class Person {
	String name;
	// Association: A Person "has a" Car.
	Car car;

	Person(String name, Car car) {
		this.name = name;
		this.car = car;
	}

	void goForDrive() {
		System.out.println(name + " is going for a drive.");
		car.drive();
	}
}

public class SideBySideAssociation {
	public static void main(String[] args) {
		Car car = new Car("Tesla Model 3");
		Person person = new Person("Alice", car);
		person.goForDrive();
	}
}

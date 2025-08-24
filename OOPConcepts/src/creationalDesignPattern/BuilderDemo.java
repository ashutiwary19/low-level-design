package creationalDesignPattern;

public class BuilderDemo {

	private int walls;
	private int pool;
	private int bedrooms;
	private int hall;
	private int bathrooms;
	private int garage;
	private int balcony;
	private int garden;

	private BuilderDemo() {

	}

	private static BuilderDemo getInstnace(HouseBuilder builder) {
		BuilderDemo builderDemo = new BuilderDemo();
		builderDemo.walls = builder.walls;
		builderDemo.balcony = builder.balcony;
		// ....
		return builderDemo;

	}

	public int getWalls() {
		return walls;
	}

	public int getPool() {
		return pool;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public int getHall() {
		return hall;
	}

	public int getBathrooms() {
		return bathrooms;
	}

	public int getGarage() {
		return garage;
	}

	public int getBalcony() {
		return balcony;
	}

	public int getGarden() {
		return garden;
	}

	public static class HouseBuilder {
		private int walls;
		private int pool;
		private int bedrooms;
		private int hall;
		private int bathrooms;
		private int garage;
		private int balcony;
		private int garden;

		public HouseBuilder setWalls(int walls) {
			this.walls = walls;
			return this;

		}

		public HouseBuilder setPool(int pool) {
			this.pool = pool;
			return this;
		}

		public HouseBuilder setBedrooms(int bedrooms) {
			this.bedrooms = bedrooms;
			return this;
		}

		public HouseBuilder setHall(int hall) {
			this.hall = hall;
			return this;
		}

		public HouseBuilder setBathrooms(int bathrooms) {
			this.bathrooms = bathrooms;
			return this;
		}

		public HouseBuilder setGarage(int garage) {
			this.garage = garage;
			return this;
		}

		public HouseBuilder setBalcony(int balcony) {
			this.balcony = balcony;
			return this;
		}

		public HouseBuilder setGarden(int garden) {
			this.garden = garden;
			return this;
		}

		public BuilderDemo build() {
			return BuilderDemo.getInstnace(this);
		}
	}

}

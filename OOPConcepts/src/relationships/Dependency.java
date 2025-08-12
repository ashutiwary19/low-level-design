package relationships;

class Printer {
	void print(String message) {
		System.out.println("Printing: " + message);
	}
}

class Document {
	String content;

	Document(String content) {
		this.content = content;
	}

	// Dependency: Document uses Printer to print its content.
	void printDocument(Printer printer) {
		printer.print(content);
	}
}

public class Dependency {
	public class DependencyDemo {
		public static void main(String[] args) {
			Document doc = new Document("Hello, World!");
			Printer printer = new Printer();
			doc.printDocument(printer);
		}
	}
}

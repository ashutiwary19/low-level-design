package others;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class MyClassLoader extends ClassLoader {
	private final String classPath;

	public MyClassLoader(String classPath) {
		this.classPath = classPath;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String fileName = classPath + name.replace('.', '/') + ".class";
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(fileName));
			return defineClass(name, bytes, 0, bytes.length);
		} catch (IOException e) {
			throw new ClassNotFoundException("Could not load class " + name, e);
		}
	}

	public String toString() {
		return "MyClassLoader";
	}
}

public class CustomClassLoaderExample {
	public static void main(String[] args) throws Exception {
		MyClassLoader loader = new MyClassLoader("others/");
		Class<?> clazz = loader.findClass("HelloPlugin");

		Object instance = clazz.getDeclaredConstructor().newInstance();
		clazz.getMethod("greet").invoke(instance);

		System.out.println("Loaded by: " + clazz.getClassLoader());
	}
}

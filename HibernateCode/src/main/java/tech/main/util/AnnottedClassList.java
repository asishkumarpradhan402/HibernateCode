package tech.main.util;

import java.util.ArrayList;

public class AnnottedClassList {

	private static ArrayList<Class<?>> classList = new ArrayList<Class<?>>();

	private AnnottedClassList() {

	}

	public static void addClassName(Class<?> className) {
		classList.add(className);
	}

	public static ArrayList<Class<?>> getClassNames() {
		return classList;
	}
}

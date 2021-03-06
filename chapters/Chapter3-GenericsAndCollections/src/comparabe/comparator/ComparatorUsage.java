package comparabe.comparator;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class ComparatorUsage {

	static void basicTest() {
		String a = "a";
		String b = "b";
		String a2 = "a";

		System.out.println("a compare to b : " + a.compareTo(b));
		System.out.println("a compare to a : " + a.compareTo(a2));
		System.out.println("b compare to a : " + b.compareTo(a2));
	}

	static void commonErrors() {
		class NotImplementingComparable {
		}
		TreeSet<Object> o = new TreeSet<>();
//		o.add(new NotImplementingComparable()); // throws Runtime Exception, object doesn't have natural ordering
	}

	static void useDefaultOrdering() {
		Set<Student> topStudents = new TreeSet<>();
		topStudents.add(new Student("Angel", 87));
		topStudents.add(new Student("Stef", 77));
		topStudents.add(new Student("Joel", 89));
		topStudents.add(new Student("John", 85));
		AtomicInteger ctr = new AtomicInteger(0);
		System.out.println("Printing top students");
		topStudents.forEach(x -> {
			System.out.printf("#%d %s - Score:%d  %n", ctr.incrementAndGet(), x.name, x.score);
		});
	}

	static void useCustomCorderOldWay() {
		class CompareStudent implements Comparator<Student> {

			public int compare(Student s1, Student s2) {
				return s1.name.compareTo(s2.name);
			}
		}
		Set<Student> orderByName = new TreeSet<>(new CompareStudent());
		addStudents(orderByName);
		System.out.println("Printing students by name using Comparator class");
		orderByName.forEach(x -> System.out.println(x.name));
	}

	static void useCustomOrderingLambda() {
		Set<Student> orderByName = new TreeSet<>(Comparator.comparing(Student::getName));
		addStudents(orderByName);
		System.out.println("Printing students by name using lambda Comparator.comparing");
		orderByName.forEach(x -> System.out.println(x.name));
	}

	static void addStudents(Set<Student> students) {
		students.add(new Student("Angel", 87));
		students.add(new Student("Stef", 77));
		students.add(new Student("Joel", 89));
		students.add(new Student("John", 85));
	}

	public static void main(String[] args) {
		basicTest();
		commonErrors();
		useDefaultOrdering();
		useCustomCorderOldWay();
		useCustomOrderingLambda();
	}
}

package studentsAndCourses;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//All should be done in Java 8
//----------------------------
//1. Combine a list of Strings using comma separator.
//
//Input: String[] with values "Mercury", "Venus", "Mars"
//Output: Single String "Planets: Mercury, Venus, Mars"
//
//2. To give best experience to users who visit the website, the owner wants to know during which period of time load is high so that he can scale up servers.
//Given a log file having details of users visit(consider VISITID is logged) and other log messages, find out the duration(1 hour) during which load is high.
//
//Output:
//Time range during which load is high.
//
//Write a simple standalone Java application: (Use Main application with classes Student and Course.
//Input data can be list or map if DB setup is not complete
//Student should have their basic details along with course details: course name, start and end dates
//3. Get all student details based on given course input
//4. Get the average score of all students for any given course
//5. Get all available courses for the given input (next week, next month)

public class JavaDemo {

	public static void main(String[] args) {
		
		System.out.println("Demo Java 8  hands on");
		
		String [] myArray = {"Mercury", "Venus", "Mars"};
		System.out.println(mergeString(myArray));
		
		List<Student> studentList = createList();
		
		System.out.println("\n Students enrolled in "+ "Geography");
		filterByCourse(studentList, "Geography").stream().forEach(student -> System.out.println(student.getStudentName()));
		
		availableCoursesNextWeek(createCourseList()).stream().forEach(course -> System.out.println(course.getCourseName()));
		
		System.out.println(avgScore(studentList, "Geography")); 

	}
	
	
	static String mergeString(String [] strArray) {
	String myString="";
	List<String> stringList= Arrays.asList(strArray);
	
	myString = stringList.stream().reduce(myString, (string1,string2) -> string1+string2);
	
	return myString;
}

	static List<Student> createList(){
		List<Student> studentList = new ArrayList<Student>();
		
		Course course1 = new Course();
		course1.setCourseId(1);
		course1.setCourseName("History");
		course1.setStartDate(LocalDate.of(2022, 3, 21));
		course1.setEndDate(LocalDate.of(2022, 4, 21));
		
		Course course2 = new Course();
		course2.setCourseId(2);
		course2.setCourseName("Geography");
		course2.setStartDate(LocalDate.of(2022, 1, 10));
		course2.setEndDate(LocalDate.of(2022, 2, 10));
		
		Course course3 = new Course();
		course3.setCourseId(3);
		course3.setCourseName("Mathematics");
		course3.setStartDate(LocalDate.of(2022, 2, 10));
		course3.setEndDate(LocalDate.of(2022, 4, 21));
		
		
		
		
		Student student1 = new Student();
		student1.setStudenId(1);
		student1.setStudentName("Victor");
		student1.setStudentAge(25);
		student1.getCourseList().put(course3.getCourseName(),75);
		student1.getCourseList().put(course2.getCourseName(),80);
		
		Student student2 = new Student();
		student2.setStudenId(2);
		student2.setStudentName("Manuel");
		student2.setStudentAge(26);
		student2.getCourseList().put(course1.getCourseName(),78);
		student2.getCourseList().put(course2.getCourseName(),83);
		
		Student student3 = new Student();
		student3.setStudenId(3);
		student3.setStudentName("Sebastian");
		student3.setStudentAge(24);
		student3.getCourseList().put(course3.getCourseName(),91);
		student3.getCourseList().put(course1.getCourseName(),86);
		
		Student student4 = new Student();
		student4.setStudenId(4);
		student4.setStudentName("Antonio");
		student4.setStudentAge(24);
		student4.getCourseList().put(course3.getCourseName(),87);
		
		
		
		
		studentList.add(student1);
		studentList.add(student2);
		studentList.add(student3);
		studentList.add(student4);
		
		return studentList;
	}

	static List<Course> createCourseList(){
		List<Course> courseList= new ArrayList<Course>();
		
		Course course1 = new Course();
		course1.setCourseId(1);
		course1.setCourseName("History");
		course1.setStartDate(LocalDate.of(2022, 3, 21));
		course1.setEndDate(LocalDate.of(2022, 4, 21));
		
		Course course2 = new Course();
		course2.setCourseId(2);
		course2.setCourseName("Geography");
		course2.setStartDate(LocalDate.of(2022, 1, 10));
		course2.setEndDate(LocalDate.of(2022, 2, 10));
		
		Course course3 = new Course();
		course3.setCourseId(3);
		course3.setCourseName("Mathematics");
		course3.setStartDate(LocalDate.of(2022, 2, 10));
		course3.setEndDate(LocalDate.of(2022, 4, 21));
		
		courseList.add(course1);
		courseList.add(course2);
		courseList.add(course3);
		
		return courseList;
	}
	
	static List<Student> filterByCourse(List<Student> studentList, String courseName) {
		
		List<Student> filteredList = new ArrayList<Student>();
		filteredList = studentList.stream()
		.filter(student -> student.getCourseList().keySet().stream().anyMatch(course -> course.equals(courseName)))
		.collect(Collectors.toList());
		
		
		return filteredList;
	}

	static Double avgScore(List<Student> studentList,String courseName) {
		Double avgScore = 0.0;

		studentList = filterByCourse(studentList, courseName);

		avgScore = studentList.stream()
				.map(student -> (double)student.getCourseList().get(courseName))
				.reduce(0.0, (sum,score) -> sum+score)/studentList.size();

		return avgScore;
	}
	
	static List<Course> availableCoursesNextWeek(List<Course> courseList){
		
		System.out.println("\n Available courses:");
		List<Course> availableList = new ArrayList<Course>();
		
		LocalDate today = LocalDate.now();
		LocalDate endWeek = today.plusWeeks(1);

		availableList = courseList.stream()
				.filter(course ->course.getStartDate().isBefore(today) && course.getEndDate().isAfter(endWeek))
				.collect(Collectors.toList());
		
		return availableList;
	}

}

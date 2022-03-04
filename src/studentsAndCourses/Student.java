package studentsAndCourses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
	
	private Integer studenId;
	private String studentName;
	private Integer studentAge;
	private Map<String,Integer> courseList = new HashMap<String,Integer>();
	

	public Map<String, Integer> getCourseList() {
		return courseList;
	}
	public void setCourseList(Map<String, Integer> courseList) {
		this.courseList = courseList;
	}
	public Integer getStudenId() {
		return studenId;
	}
	public void setStudenId(Integer studenId) {
		this.studenId = studenId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Integer getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}

}

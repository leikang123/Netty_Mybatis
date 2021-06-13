package mybatis_01;

public class Student {
	
	// 定义一个ID号
	 private Integer id;
	// 定义一个学生的姓名
	 private String name;
	 // 定义一个所有班级
	 private Student student;
	 
	 // Student的构造方法
	public Student(Integer id, String name, Student student) {
		super();
		this.id = id;
		this.name = name;
		this.student = student;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", student=" + student + "]";
	}
	 
	 

}

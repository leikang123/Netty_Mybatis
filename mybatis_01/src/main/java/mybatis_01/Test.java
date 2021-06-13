package mybatis_01;

public class Test {
	public static void main(String[] args) {
		
		
		Test studentDAO = new MyInvocationHandler().getInstance(Test.class);
		Student stu = studentDAO.getById(1);
	}

	private Student getById(int i) {
		
		return null;
	}

}

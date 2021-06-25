package mybatis_01;

// 测试类
public class Test {
	public static void main(String[] args) {
		
		// 创建对象并获取对象
		Test studentDAO = new MyInvocationHandler().getInstance(Test.class);
		// 获取对象的ID
		Student stu = studentDAO.getById(1);
	}

	private Student getById(int i) {
		
		return null;
	}

}

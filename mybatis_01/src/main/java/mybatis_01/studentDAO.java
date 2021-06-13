package mybatis_01;
/**
 * 定义一个接口，定义接口下面的业务的方法
 * @author mac1094
 *
 */
public interface studentDAO {
	/**
	 * 查询学生的ID号
	 * @param id
	 * @return
	 */
	public Student getById(int id);
	
	/**
	 * 查询所有学生
	 * @param student
	 * @return
	 */
	public Student getByStudent(Student student);
	
	/**
	 * 查询学生的姓名
	 * @param name
	 * @return
	 */
	public Student getByName(String name);
	
	/**
	 * 查询其他班级的所有学生
	 * @param student
	 * @return
	 */
	public Student getByStudent2(Student student);

}

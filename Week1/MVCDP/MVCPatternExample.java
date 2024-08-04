package MVCDP;

// Model Class
class Student {
  private String name;
  private int id;
  private String grade;

  public Student(String name, int id, String grade) {
      this.name = name;
      this.id = id;
      this.grade = grade;
  }

  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }

  public int getId() {
      return id;
  }

  public void setId(int id) {
      this.id = id;
  }

  public String getGrade() {
      return grade;
  }

  public void setGrade(String grade) {
      this.grade = grade;
  }
}

// View Class
class StudentView {
  public void displayStudentDetails(String studentName, int studentId, String studentGrade) {
      System.out.println("Student Details:");
      System.out.println("Name: " + studentName);
      System.out.println("ID: " + studentId);
      System.out.println("Grade: " + studentGrade);
  }
}

// Controller Class
class StudentController {
  private Student student;
  private StudentView studentView;

  public StudentController(Student student, StudentView studentView) {
      this.student = student;
      this.studentView = studentView;
  }

  public void setStudentName(String name) {
      student.setName(name);
  }

  public String getStudentName() {
      return student.getName();
  }

  public void setStudentId(int id) {
      student.setId(id);
  }

  public int getStudentId() {
      return student.getId();
  }

  public void setStudentGrade(String grade) {
      student.setGrade(grade);
  }

  public String getStudentGrade() {
      return student.getGrade();
  }

  public void updateView() {
      studentView.displayStudentDetails(student.getName(), student.getId(), student.getGrade());
  }
}

// Test Class
public class MVCPatternExample {
  public static void main(String[] args) {
      // Create model (Student)
      Student student = new Student("John Doe", 1, "A");

      // Create view (StudentView)
      StudentView studentView = new StudentView();

      // Create controller (StudentController)
      StudentController studentController = new StudentController(student, studentView);

      // Display initial details
      studentController.updateView();

      // Update student details
      studentController.setStudentName("Jane Doe");
      studentController.setStudentId(2);
      studentController.setStudentGrade("B");

      // Display updated details
      studentController.updateView();
  }
}

package Resource;
import java.util.*;


public class Student {
    private static int NO = 1;
    private String studentId;
    private String studentName;
    private String status;
    // 이 학생이 수강하고 있는 과목 목록
    private final List<String> subjectList = new ArrayList<>();
        public Student(String studentName, String status) {
        studentId = "STU" + NO;
        this.studentName = studentName;
        this.status = status;
        ++NO;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }
    public String getStatus() {
        return status;
    }
    public List<String> getSubjectList() {
        return subjectList;
    }
}

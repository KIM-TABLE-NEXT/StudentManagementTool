package Data;
import Resource.Student;
import Resource.Subject;
import Resource.SubjectScore;

import java.util.*;

public class DataBase {
    // 모든 정보가 저장
    // 데이터베이스에 있는 값들로 조회를 한다.

    // 등록된 학생 목록
    private final List<Student> studentList = new ArrayList<>();

    // 모든 과목 목록
    private final Set<Subject> subjectList = new HashSet<>();

    // key : studentId + subjectName    value : 과목이름
    private final Set<String> subjectSet = new HashSet<>();


    // key : studentId + subjectName   value : 해당id의 학생의 해당 과목의 회차별 점수 목록
    private final Map<String, List<SubjectScore>> subjectScoreMap = new HashMap<>();

    // key : 상태                      value : 학생리스트
    private final Map<String, List<Student>> studentStatusMap = new HashMap<>();

    public DataBase() {
        databaseInit();
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public Set<Subject> getSubjectList() {
        return subjectList;
    }

    public Set<String> getSubjectSet() {
        return subjectSet;
    }

    public Map<String, List<SubjectScore>> getSubjectScore() {
        return subjectScoreMap;
    }

    // 학생 삭제
    public void createStudent(String name, String status, List<String> subjectNameList) {
        Student student = new Student(name, status);
        studentStatusMap.get(status).add(student);
        for (String subjectName : subjectNameList) {
            student.getSubjectList().add(subjectName);
            String key = student.getStudentId() + subjectName;
            subjectSet.add(key);
            subjectScoreMap.put(key, new ArrayList<SubjectScore>());
        }
        System.out.println("수강생이 등록되었습니다.");
        printStudent(student);
    }
    public void changeStudent(String id, String name, String status){
        for (Student student : studentList)
            if(student.getStudentId().equals(id)){
                studentStatusMap.get(student.getStatus()).remove(student);
                student.setStudentName(name);
                student.setStatus(status);
                studentStatusMap.get(status).add(student);
                printStudent(student);
                System.out.println("수정되었습니다.");
                break;
            }
    }
    public void deleteStudent (String id) {
        for (Student student : studentList)
            if(student.getStudentId().equals(id)){
            studentList.remove(student);
                studentStatusMap.get(student.getStatus()).remove(student);
            for (String subject : student.getSubjectList()) {
                String key = student.getStudentId() + subject;
                subjectSet.remove(key);
                subjectScoreMap.remove(key);
            }
            System.out.println("수강생이 삭제되었습니다.");
            break;
        }
    }






    public void inputSubjectScore(String key, List<SubjectScore> subjectScoreList) {
        for (SubjectScore subjectScore : subjectScoreList) {
            subjectScoreMap.get(key).add(subjectScore);
        }
    }

    private void databaseInit() {
        subjectList.add(new Subject("Java", true));
        subjectList.add(new Subject("객체지향", true));
        subjectList.add(new Subject("Spring", true));
        subjectList.add(new Subject("JPA", true));
        subjectList.add(new Subject("MySQL", true));
        subjectList.add(new Subject("디자인_패턴", false));
        subjectList.add(new Subject("Spring_Security", false));
        subjectList.add(new Subject("Redis", false));
        subjectList.add(new Subject("MongoDB", false));

        studentStatusMap.put("Green", new ArrayList<Student>());
        studentStatusMap.put("Yellow", new ArrayList<Student>());
        studentStatusMap.put("Red", new ArrayList<Student>());

        testInput();
    }

    private void testInput() {
        createStudent("김상엽", "Green", List.of(
                "Java", "객체지향", "Spring", "디자인_패턴", "Spring_Security"
        ));
        createStudent("이준우", "Green", List.of(
                "JPA", "객체지향", "Spring", "Redis", "Spring_Security"
        ));
        createStudent("최유라", "Yellow", List.of(
                "JPA", "MySQL", "Spring", "MongoDB", "Spring_Security"
        ));
        createStudent("김준혁", "Red", List.of(
                "Java", "MySQL", "Spring", "MongoDB", "Redis"
        ));
        // inputSubjectScore();
    }

    private  void printStudent(Student student){
        System.out.printf("ID : %-10s | 이름 : %-5s | 과목 : %-30s | 상태 : %-5s\n",student.getStudentId(), student.getStudentName(), String.join(", ",student.getSubjectList()),student.getStatus());
    }
    public void printStudentById(String id){
        for(Student student : studentList)
            if(student.getStudentId().equals(id))
                printStudent(student);
    }
    public void printStudentByStatus(String status){
        for(Student student : studentList)
            if(student.getStatus().equals(status))
                printStudent(student);
    }

    public void registerScore(String id, String subject, int score){
        String key = id + subject;
        subjectScoreMap.get(key).add(new SubjectScore(subjectScoreMap.get(key).size()+1,score, calcGrade(score,subject)));
        System.out.println("등록되었습니다.");
    }
    private char calcGrade(int score, String subject) {
        Boolean mandatory = true;
        for(Subject subjectName : subjectList)
            if(subjectName.getSubjectName().equals(subject))
                 mandatory = subjectName.isMandatory();
        if (mandatory) {
            if (score >= 95) return 'A';
            else if (score >= 90) return 'B';
            else if (score >= 80) return 'C';
            else if (score >= 70) return 'D';
            else if (score >= 60) return 'F';
            else return 'N';
        }
        else
        if (score >= 90) return 'A';
        else if (score >= 80) return 'B';
        else if (score >= 70) return 'C';
        else if (score >= 60) return 'D';
        else if (score >= 50) return 'F';
        else return 'N';
    }

    public int getRound(String key){
        return subjectScoreMap.get(key).size()+1;
    }
    private int getAverage(String key){
        int sum = 0;
        for(SubjectScore subjectScore : subjectScoreMap.get(key))
            sum += subjectScore.getScore();
        return sum/(subjectScoreMap.get(key).size()+1);
    }
    public void printScore(String id, String subject){
        for(SubjectScore subjectScore : subjectScoreMap.get(id+subject)) {
            System.out.printf("%-5s 과목 %d회차 | %d점 | %c등급\n",subject, subjectScore.getRound(), subjectScore.getScore(), subjectScore.getGrade());
        }
        System.out.printf("%-5s 과목 평균 : %d점 평균등급 : %c등급",subject,getAverage(id+subject),calcGrade(getAverage(id+subject), subject));
    }

    public void printScoreById(String id){
        for(Student student : studentList)
            if(student.getStudentId().equals(id))
                for(String subject : student.getSubjectList())
                    printScore(id, subject);

    }
    public void printScoreByStatus(String status){
        String id;
        for(Student student : studentList)
            if(student.getStatus().equals(status)){
                id = student.getStudentId();
                for(String subject : student.getSubjectList())
                    printScore(id, subject);
            }
    }

    public void changeScore(String id, String subject, int round, int score){
        for(SubjectScore subjectScore : subjectScoreMap.get(id+subject))
            if(subjectScore.getRound() == round) {
                subjectScore.setScore(score);
                subjectScore.setGrade(calcGrade(score,subject));
                System.out.println("변경되었습니다.");
            }

    }
}

package Service;
import Data.DataBase;
import Screen.ScreenStage;
import Screen.ScreenData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


// App
// "ID : %-5s 학생 | %-10d | 필수 과목 : %-20s | 선택 과목 : %-20s | 상태 : %-5s"
public class Management{
    private Scanner sc = new Scanner(System.in);
    private DataBase dataBase = new DataBase();
    private ScreenData screenData = new ScreenData();
    public void run() {
        mainMenu();
    }
    private void mainMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println(screenData.getStringData(ScreenStage.MAIN_MENU));
            String input = sc.nextLine();
            switch (inputSelect(input)) {
                case 1 -> studentMenu(); // 수강생 관리
                case 2 -> scoreMenu(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                }
            }
        }
    }
    // 수강생 관리 메뉴
    private void studentMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println(screenData.getStringData(ScreenStage.STUDENT_MAIN_MENU));
            String input = sc.nextLine();
            switch (inputSelect(input)) {
                case 1 -> registerStudentMenu(); // 수강생 관리
                case 2 -> inquireStudentMenu(); // 점수 관리
                case 3 -> changeStudentMenu();
                case 4 -> deleteStudentMenu();
                case 5 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                }
            }
        }
    }

    private void registerStudentMenu() {
        String studentSubject;
        List<String> studentsubjectList = new ArrayList<>();
        System.out.println(screenData.getStringData(ScreenStage.STUDENT_REGISTER_MENU));
        System.out.println("수강생의 이름을 입력해주세요.");
        String studentName = sc.nextLine();
        System.out.println("수강생의 상태를 입력해주세요.");
        String studentStatus = sc.nextLine();
        do {
            System.out.println("수강생의 필수과목 목록을 입력해주세요. [Java 객체지향 Spring JPA MySQL]");
            studentSubject = sc.nextLine();
        }while(studentSubject.split(" ").length<2);
        studentsubjectList.addAll(Arrays.asList(studentSubject.split(" ")));
        do {
            System.out.println("수강생의 선택과목 목록을 입력해주세요. [디자인_패턴 Spring_Security Redis MongoDB]");
            studentSubject = sc.nextLine();
        }while(studentSubject.split(" ").length<1);
        studentsubjectList.addAll(Arrays.asList(studentSubject.split(" ")));
        System.out.printf("이름 : %-5s | 과목 : %-40s | 상태 : %-5s\n", studentName, String.join(", ",studentsubjectList),studentStatus);
        System.out.println("수강생을 등록 하시겠습니까?");
        System.out.println("1. 네");
        System.out.println("2. 아니오");
        String input = sc.nextLine();
        if(inputSelect(input)==1)
            dataBase.createStudent(studentName, studentStatus, studentsubjectList);
    }
    private void inquireStudentMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println(screenData.getStringData(ScreenStage.STUDENT_INQUIRE_MENU));
            System.out.println("1. 고유번호로 조회하기");
            System.out.println("2. 상태로 조회하기");
            String input = sc.nextLine();
            switch (inputSelect(input)) {
                case 1 -> {
                    System.out.println("조회할 수강생의 고유번호를 입력해주세요");
                    input = sc.nextLine();
                    dataBase.printStudentById(input);
                }
                case 2 -> {
                    System.out.println("조회할 수강생의 상태를 입력해주세요");
                    input = sc.nextLine();
                    dataBase.printStudentByStatus(input);
                }
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                }
            }
        }
    }
    private void changeStudentMenu() {
        System.out.println(screenData.getStringData(ScreenStage.STUDENT_DELETE_MENU));
        System.out.println("수정할 수강생의 고유번호를 입력해주세요");
        String id = sc.nextLine();
        dataBase.printStudentById(id);
        System.out.println("수강생의 새로운 이름을 입력해주세요");
        String name = sc.nextLine();
        System.out.println("수강생의 새로운 상태를 입력해주세요");
        String status = sc.nextLine();
        System.out.println("수강생 정보를 변경 하시겠습니까?");
        System.out.println("1. 네");
        System.out.println("2. 아니오");
        String input = sc.nextLine();
        if(inputSelect(input)==1)
            dataBase.changeStudent(id,name,status);
    }

    private void deleteStudentMenu() {
        System.out.println(screenData.getStringData(ScreenStage.STUDENT_DELETE_MENU));
        System.out.println("삭제할 수강생의 고유번호를 입력해주세요");
        String id = sc.nextLine();
        dataBase.printStudentById(id);
        System.out.println("수강생을 삭제 하시겠습니까?");
        System.out.println("1. 네");
        System.out.println("2. 아니오");
        String input = sc.nextLine();
        if(inputSelect(input)==1)
            dataBase.deleteStudent(id);
    }
    // 수강생 관련 메뉴 끝

    // 점수 관리 메뉴
    private void scoreMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println(screenData.getStringData(ScreenStage.SCORE_MAIN_MENU));
            String input = sc.nextLine();
            switch (inputSelect(input)) {
                case 1 -> registerScoreMenu(); // 수강생 관리
                case 2 -> inquireScoreMenu(); // 점수 관리
                case 3 -> changeScoreMenu();
                case 4 -> flag = false;
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                }
            }
        }
    }
    private void registerScoreMenu() {
        System.out.println(screenData.getStringData(ScreenStage.SCORE_REGISTER_MENU));
        System.out.println("점수를 입력할 학생의 고유번호를 입력해주세요");
        String id = sc.nextLine();
        System.out.println("점수를 입력할 과목을 입력해주세요");
        String subject = sc.nextLine();
        String key = id + subject;
        System.out.printf("%d 회차 점수를 입력해주세요.", dataBase.getRound(key));
        String score = sc.nextLine();
        System.out.printf("%d 회차 점수 : %s점\n", dataBase.getRound(key), score);
        System.out.println("점수를 등록 하시겠습니까?");
        System.out.println("1. 네");
        System.out.println("2. 아니오");
        String input = sc.nextLine();
        if(inputSelect(input)==1)
        dataBase.registerScore(id, subject, inputSelect(score));
    }
    private void inquireScoreMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println(screenData.getStringData(ScreenStage.SCORE_INQUIRE_MENU));
            System.out.println("1. 고유번호로 조회하기");
            System.out.println("2. 상태로 조회하기");
            String input = sc.nextLine();
            switch (inputSelect(input)) {
                case 1 -> {
                    System.out.println("조회할 수강생의 고유번호를 입력해주세요");
                    input = sc.nextLine();
                    dataBase.printScoreById(input);
                }
                case 2 -> {
                    System.out.println("조회할 수강생의 상태를 입력해주세요");
                    input = sc.nextLine();
                    dataBase.printScoreByStatus(input);
                }
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                }
            }
        }
    }
    private void changeScoreMenu() {
        System.out.println(screenData.getStringData(ScreenStage.SCORE_CHANGE_MENU));
        System.out.println("점수를 수정할 수강생의 고유번호를 입력해주세요");
        String id = sc.nextLine();
        dataBase.printStudentById(id);
        System.out.println("점수를 수정할 과목을 입력해주세요");
        String subject = sc.nextLine();
        System.out.println("점수를 수정할 회차를 입력해주세요");
        String round = sc.nextLine();
        System.out.printf("%d 회차의 새로운 점수를 입력해주세요\n",inputSelect(round));
        String score = sc.nextLine();
        System.out.println("점수를 변경 하시겠습니까?");
        System.out.println("1. 네");
        System.out.println("2. 아니오");
        String input = sc.nextLine();
        if(inputSelect(input)==1)
            dataBase.changeScore(id,subject, inputSelect(round), inputSelect(score));
    }
    private boolean isDigit(String number){
        char[] chars = number.toCharArray();
        for (char ch : chars) {
            if (ch < '0' || ch > '9') return false;
        }
        return true;
    }

    private int inputSelect(String number){
        if(isDigit(number))
            return Integer.parseInt(number);
        else
            return 0;
    }
    // 점수 관리 메뉴 끝
}

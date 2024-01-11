package Screen;

import java.util.HashMap;
import java.util.Map;

public class ScreenData {

    private final Map<ScreenStage, String> screenMap = new HashMap<>();

    public ScreenData() {
        initScreenMap();
    }

    public String getStringData(ScreenStage screenStage) {
        return screenMap.get(screenStage);
    }

    /*
    MAIN_MENU,
    STUDENT_MAIN_MENU,
    STUDENT_REGISTER_MENU,
    STUDENT_INQUIRE_MENU,
    STUDENT_CHANGE_MENU,
    STUDENT_DELETE_MENU,
    SCORE_MAIN_MENU,
    SCORE_REGISTER_MENU,
    SCORE_INQUIRE_MENU,
    SCORE_CHANGE_MENU,
     */
    private void initScreenMap() {
        screenMap.put(ScreenStage.MAIN_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        
                        1. 수강생 관리
                        2. 점수 관리
                        3. 프로그램 종료
                        ==================================
                        """
        );
        screenMap.put(ScreenStage.STUDENT_MAIN_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        수강생 관리 페이지입니다.
                        
                        1. 수강생 등록
                        2. 수강생 조회
                        3. 수강생 수정
                        4. 수강생 삭제
                        5. 뒤로가기
                        ==================================
                        """);
        screenMap.put(ScreenStage.STUDENT_REGISTER_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        수강생 등록 페이지입니다.
                       
                        """);
        screenMap.put(ScreenStage.STUDENT_INQUIRE_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        수강생 조회 페이지입니다.
                        
                        """);
        screenMap.put(ScreenStage.STUDENT_CHANGE_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        수강생 수정 페이지입니다.
                        
                        """);
        screenMap.put(ScreenStage.STUDENT_DELETE_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        수강생 삭제 페이지입니다.
                        
                        """);

        screenMap.put(ScreenStage.SCORE_MAIN_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        점수 관리 페이지입니다
                        
                        1. 점수 입력
                        2. 점수 조회
                        3. 점수 수정
                        4. 뒤로가기
                        ==================================""");
        screenMap.put(ScreenStage.SCORE_REGISTER_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        점수 입력 페이지입니다
                        
                        """);
        screenMap.put(ScreenStage.SCORE_INQUIRE_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        점수 조회 페이지입니다
                        
                        """);
        screenMap.put(ScreenStage.SCORE_CHANGE_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        점수 수정 페이지입니다
                        
                        """);
    }
}

package Prototype;
import java.util.Scanner;

/**
 * CourseManager 클래스의 설명을 작성하세요.
 *
 * @author (작성자 이름)
 * @version (버전 번호 또는 작성한 날짜)
 */

public class CourseManager
{
    private Course[] courses;                         //과목 저장 배열
    private int courseIndex;                          //과목 저장 배열의 인덱스
    private int evaluationItemCount;                  //평가항목 개수

    public CourseManager(){
        this.courses = new Course[100];
        this.evaluationItemCount = 0;
    }

    //과목 추가
    public void addCourse(Scanner scanner) {
        //과목 정보 입력
        System.out.print("과목명>> ");
        String courseTitle = scanner.next();

        System.out.print("과목학점>> ");
        int courseCredit = scanner.nextInt();

        System.out.print("평가항목 개수>> ");
        int evaluationItemCount = scanner.nextInt();
        String[][] evaluationItems = new String[evaluationItemCount][2];

        //평가항목마다 점수 비율 차등 설정
        for (int i = 0 ; i < evaluationItemCount ; i++){
            System.out.print("평가항목 이름 입력>> ");
            evaluationItems[i][0] = scanner.next();
            System.out.print(evaluationItems[i][0] + " 점수 설정>> ");
            evaluationItems[i][1] = scanner.next();
        }

        //개설강좌 확인
        System.out.println("");
        System.out.println("----------개설된 과목----------");
        System.out.println("과목명: " + courseTitle);
        System.out.println("학점: " + courseCredit);
        System.out.println(courseTitle + " 평가 점수 비율");
        for (int i = 0; i < evaluationItemCount; i++) {
            System.out.println(evaluationItems[i][0] + " : " + evaluationItems[i][1] + "%");
        }

        courses[courseIndex] = new Course(courseTitle, courseCredit, evaluationItems);
        courseIndex++;
    }

    //과목조회
    public void findCourse(Scanner scanner){
        System.out.print("조회할 과목명을 입력하세요>> ");
        String findCourse = scanner.next();

        boolean found = false;

        for (Course course : courses) {
            //과목명 일치하는지 검사
            if (course != null) {

                String title = course.getCourseTitle();

                boolean same = true;
                
                //찾으려는 과목과 기존 과목 문자열 길이 다르면 넘어감
                if (title.length() != findCourse.length()) {
                    same = false;
                }
                //문자열 길이 같을경우 과목명 한 글자씩 비교
                else {

                    for (int i = 0; i < title.length(); i++) {
                        if (title.charAt(i) != findCourse.charAt(i)) {
                            same = false;
                            break;  
                        }
                    }
                }
                
                //일치할경우 조회된 과목정보 출력
                if (same) {
                    found = true;
                    System.out.println("과목명: " + course.getCourseTitle());

                    System.out.println("학점: " + course.getCourseCredit());

                    for (int i = 0 ; i < course.getEvaluationItem().length ; i++) {
                        System.out.println(course.getEvaluationItem()[i][0] + " : " + course.getEvaluationItem()[i][1] + "%");
                    }
                }
            }
        }
        
        //개설x 강의 안내
        if (!found) {
            System.out.println("해당 과목을 찾을 수 없습니다.");
        }
    }
}

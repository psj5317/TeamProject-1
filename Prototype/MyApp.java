package Prototype;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * MyApp 클래스의 설명을 작성하세요.
 *
 * @author (작성자 이름)
 * @version (2026.05.10)
 */
public class MyApp
{
    public static void main(String[] args){
        //변수 선언
        Scanner scanner = new Scanner(System.in); 
        //메인화면 변수
        int mainMenu = 0;                           //기본화면
        int studentMenu = 0;                        //학생메뉴
        int courseMenu = 0;                         //과목메뉴

        //학생관련 변수
        Student[] students = new Student[1000];     //학생정보(1000명 기준)
        int studentIndex = 0;                       //학생 저장 인덱스
        String[] courseStudent;                     //수강과목 배열
        String studentName;                         //학생명
        int findStudentNum;                         //학번조회

        //과목관련 변수
        int evaluationItemCount;                    //평가항목 개수
        String[] evaluationItems;                   //평가항목 배열

        while (mainMenu != 4){
            switch (mainMenu){

                    //메인화면
                    case(0):
                    System.out.print("학생메뉴: 1, 과목메뉴: 2, 성적처리: 3, 프로그램 종료: 4>>");

                    //예외처리
                    try{
                        mainMenu = scanner.nextInt();    
                    }
                    catch (InputMismatchException e){
                        System.out.println("1,2,3,4 중 입력하세요.");
                        scanner.nextLine();
                        mainMenu = 0;
                    }
                    break;

                    //학생메뉴
                    case(1):
                    while(studentMenu != 3){
                        switch (studentMenu){
                                case(0):
                                //학생메뉴 홈 탭
                                System.out.println("");
                                System.out.println("-------------학생메뉴 탭입니다.-------------");
                                System.out.println("");
                                System.out.print("학생추가: 1, 학생조회: 2, 기본메뉴로 돌아가기: 3>> ");

                                //정수 외 입력 예외처리
                                try{
                                    studentMenu = scanner.nextInt();    
                                }
                                catch (InputMismatchException e){
                                    System.out.println("1,2,3 중 입력하세요.");
                                    scanner.nextLine();
                                    studentMenu = 0;
                                }
                                break;

                                //----------------------------------------학생 추가 탭
                                case(1):

                                //변수 초기화
                                int studentNum = 0;         //학번
                                int courseCount = 0;        //수강과목 개수

                                //프롬프트
                                System.out.print("학생 이름>> ");
                                studentName = scanner.next();

                                System.out.print("학생 학번>> ");
                                studentNum = scanner.nextInt();

                                System.out.print("학생 수강과목 개수>> ");
                                try{
                                    courseCount = scanner.nextInt();
                                }
                                catch (InputMismatchException e){
                                    System.out.println("수강과목 개수는 정수로 입력해주세요 !");
                                    scanner.nextLine();
                                    break;
                                }

                                //학생 수강과목 저장할 배열 생성
                                courseStudent = new String[courseCount];

                                //수강과목명 입력
                                for (int i = 0; i < courseCount ; i++){
                                    System.out.print("수강과목 이름 입력>> ");
                                    courseStudent[i] = scanner.next();                    
                                }

                                //학생 정보 저장
                                students[studentIndex] = new Student(studentName, studentNum, courseStudent);

                                //학생 신규 추가 후 다음 학생 정보 저장을 위해 인덱스로 넘어감
                                studentIndex++;

                                //기본 학생 메뉴로 돌아가기
                                studentMenu = 0;
                                break;

                                //--------------------------------------------학생 조회 탭
                                case(2):
                                //조회 되었는지 확인
                                boolean found = false;

                                System.out.println("조회할 학생의 학번을 입력하세요>> ");
                                findStudentNum = scanner.nextInt();

                                for (Student student : students){
                                    //학생정보 인덱스중 저장되어 있는 인덱스인지 확인
                                    if (student != null){

                                        if (student.getStudentNum() == findStudentNum){
                                            found = true;
                                            System.out.println("이름: " + student.getStudentName());
                                            System.out.println("학번 " + student.getStudentNum());

                                            for(String course : student.getCourseStudent()){
                                                System.out.println("수강과목: " + course);
                                            }
                                        }    
                                    }
                                }
                                if (!found){
                                    System.out.println("해당 학번의 학생을 찾을 수 없습니다.");
                                }
                                //기본 학생 메뉴로 돌아가기
                                studentMenu = 0;
                                break;

                                //학생 탭 정수 입력 예외처리
                            default:  
                                System.out.println("메뉴 탭 1,2,3 중 입력하세요");
                                scanner.nextLine();
                                studentMenu = 0;
                        }
                    }
                    //메인메뉴로 나가기
                    studentMenu = 0;
                    mainMenu = 0;
                    break;

                    case(2):
                    while(courseMenu != 4){
                        switch (courseMenu){
                                case(0):
                                //-------------------------------------------과목메뉴 홈 탭
                                System.out.println("");
                                System.out.println("-------------과목메뉴 탭입니다.-------------");
                                System.out.println("");
                                System.out.print("과목개설: 1, 과목조회: 2, 기본메뉴로 돌아가기: 3>> ");

                                //정수 외 입력 예외처리
                                try{
                                    courseMenu = scanner.nextInt();    
                                }
                                catch (InputMismatchException e){
                                    System.out.println("1,2,3 중 입력하세요.");
                                    scanner.nextLine();
                                    courseMenu = 0;
                                }
                                break;

                                //--------------------------------------------과목개설 탭
                                case(1):
                                evaluationItemCount = 0;

                                System.out.print("과목명>> ");
                                System.out.print("과목학점>> ");
                                System.out.print("평가항목 개수>> ");
                                evaluationItems = new String[scanner.nextInt()];
                                for (int i = 0; i < evaluationItemCount ; i++){
                                    System.out.print("수강과목 이름 입력>> ");
                                    evaluationItems[i] = scanner.next();
                                    System.out.print(evaluationItems[i] + "의 점수 비율>> ");

                                }
                                
                                //과목 탭 정수 입력 예외처리
                            default:  
                                System.out.println("메뉴 탭 1,2,3 중 입력하세요");
                                scanner.nextLine();
                                studentMenu = 0;
                        }
                    }

                    //프로그램 종료
                    case(4):
                    break;

                    //메인메뉴 정수 예외처리
                default:
                    System.out.println("메뉴 탭 1,2,3,4 중 입력하세요.");
                    System.out.println("");
                    scanner.nextLine();
                    mainMenu = 0;  
            }
        }
        System.out.print("프로그램을 종료합니다.");
    }
}
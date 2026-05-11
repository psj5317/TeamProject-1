import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * CreditCalculator 클래스의 설명을 작성하세요.
 *
 * @author (작성자 이름)
 * @version (버전 번호 또는 작성한 날짜)
 */
public class CreditCalculator
{
    public static void main(String[] args){
        // 변수 선언
        Scanner scanner = new Scanner(System.in); 
        int[] studentNum = new int[10];         //학번
        int[] studentScore = new int[10];       //점수
        int findStudentNum = 0;                 //입력값 학번 비교
        int findStudentScore = 0;               //입력값 점수 비교
        int count = 0;                          //같은 점수 학생 수
        String findGrade;                       //학점 조회
        int index = 0;                          //인덱스 조회
        int menu = 0;                           //조회시스템
        int gradeA = 90;                        //학점A 기준
        int gradeB = 80;                        //학점B 기준

        // 학생정보 입력
        System.out.println("10명 학생의 학번과 점수 입력");
        for (int i = 0 ; i < 10 ; i++){
            System.out.print(i+1+ ">>");

            ;
            studentScore[i] = scanner.nextInt();
        }

        //조회시스템
        while (menu != 4){
            switch (menu){
                    case(0):
                    System.out.print("학번으로 검색: 1, 점수로 검색: 2, 등급으로 검색: 3, 끝내려면 4>>");
                    try{
                        menu = scanner.nextInt();    
                    }
                    catch (InputMismatchException e){
                        System.out.println("1,2,3 중 입력하세요.");
                        scanner.nextLine();
                        menu = 0;
                    }
                    break;

                    case(1):
                    index = 0;                              //인덱스 초기화
                    System.out.print("학번>>");
                    try{
                        findStudentNum = scanner.nextInt();     //조회할 학번 저장
                    }

                    catch (InputMismatchException e){
                        System.out.println("경고!! 정수를 입력하세요.");
                        scanner.nextLine();
                        continue;
                    }

                    for(int find : studentNum){      //조회할 학번 인덱스 추출
                        if (findStudentNum == find){
                            break;
                        }
                        else{
                            index ++;
                        }
                    }

                    try{
                        System.out.println(studentScore[index] + "점");    
                    }
                    catch(ArrayIndexOutOfBoundsException e){
                        System.out.println(findStudentNum + "의 학생은 없습니다.");
                        menu = 0;
                        break;
                    }

                    menu = 0;
                    break;

                    case(2):
                    index = 0;                              //인덱스 초기화
                    count = 0;                              //동점 명수 초기화
                    System.out.print("점수>>");

                    try{
                        findStudentScore = scanner.nextInt();   //조회할 점수 저장    
                    }

                    catch(InputMismatchException e){
                        System.out.println("경고!! 정수를 입력하세요.");
                        scanner.nextLine();
                        continue;
                    }

                    for(int find : studentScore){           //조회할 점수 인덱스 추출
                        if (findStudentScore == find){
                            if (count != 0  ){
                                System.out.print(studentNum[index]+ " ");
                                count ++;
                            }
                            else {
                                System.out.print("점수가 " + findStudentScore + "인 학생은 " + studentNum[index] + " ");
                                count ++;
                            }
                            index ++;
                        }
                        else{
                            index ++;
                        }
                    }

                    if (count != 0){
                        System.out.println("입니다.");    
                    }
                    else {
                        System.out.println("점수가 "+findStudentScore+"인 학생은 없습니다.");
                    }

                    menu = 0;
                    break;

                    case(3):
                    index = 0;                              //인덱스 초기화
                    count = 0;                              //동점 명수 초기화
                    System.out.print("검색할 학점(A,B,F)>>");

                    try{
                        findGrade = scanner.next();   //조회할 학점 저장    
                    }

                    catch(InputMismatchException e){
                        System.out.println("경고!! A,B,F 중 입력하세요.");
                        scanner.nextLine();
                        continue;
                    }

                    switch (findGrade.toUpperCase()){
                            case("A"):
                            for(int find : studentScore){           //조회할 점수 인덱스 추출
                                if (90 <= find){
                                    if (count != 0  ){
                                        System.out.print(studentNum[index]+ " ");
                                        count ++;
                                    }
                                    else {
                                        System.out.print("학점이 " + findGrade.toUpperCase() + "인 학생은 " + studentNum[index] + " ");
                                        count ++;
                                    }
                                    index ++;
                                }
                                else{
                                    index ++;
                                }
                            }
                            if (count != 0){
                                System.out.println("입니다.");    
                            }
                            else {
                                System.out.println("학점이 "+findGrade.toUpperCase() + "인 학생은 없습니다.");
                            }  

                            menu = 0;
                            break;

                            case("B"):
                            for(int find : studentScore){           //조회할 점수 인덱스 추출
                                if (80 <= find && 90 > find){
                                    if (count != 0  ){
                                        System.out.print(studentNum[index]+ " ");
                                        count ++;
                                    }
                                    else {
                                        System.out.print("학점이 " + findGrade.toUpperCase() + "인 학생은 " + studentNum[index] + " ");
                                        count ++;
                                    }
                                    index ++;
                                }
                                else{
                                    index ++;
                                }
                            }
                            if (count != 0){
                                System.out.println("입니다.");    
                            }
                            else {
                                System.out.println("학점이 "+findGrade.toUpperCase() + "인 학생은 없습니다.");
                            }  

                            menu = 0;
                            break;

                            case("F"):
                            for(int find : studentScore){           //조회할 점수 인덱스 추출
                                if (80 > find){
                                    if (count != 0  ){
                                        System.out.print(studentNum[index]+ " ");
                                        count ++;
                                    }
                                    else {
                                        System.out.print("학점이 " + findGrade.toUpperCase() + "인 학생은 " + studentNum[index] + " ");
                                        count ++;
                                    }
                                    index ++;
                                }
                                else{
                                    index ++;
                                }

                            }

                            if (count != 0){
                                System.out.println("입니다.");    
                            }
                            else {
                                System.out.println("학점이 "+findGrade.toUpperCase() + "인 학생은 없습니다.");
                            }  

                            menu = 0;
                            break;

                        default:
                            System.out.println("경고!! A,B,F 중 입력하세요.");
                            scanner.nextLine();
                            break;
                    }
            }
        }
        System.out.print("프로그램을 종료합니다.");
    }
}
package Prototype;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * MyApp 클래스의 설명을 작성하세요.
 *
 * @author (2023320006 정준영)
 * @version (2026.05.13)
 */

public class MyApp{ 
    //과목정보 입력 메서드
    /**
     * 성적처리 할 과목의 정보를 입력받는 메서드
     *
     * @param   scanner : 메인 메서드와 연동되어 입력받는 파라미터
     *          studentDB : 학생정보가 저장되어 있는 데이터베이스
     *          courseDB : 과목정보가 저장되어있는 데이터베이스
     *          totalCourse : 현재까지 입력받은 과목개수
     *          
     * @return  totalCourse : 과목정보가 입력되어 수정된 총 과목개수 값 반환
     */
    public static int addCourse(Scanner scanner,Student[] studentDB, Course[] courseDB, int totalCourse) {
        //과목 정보 입력
        System.out.print("과목명>> ");
        String courseTitle = scanner.next();

        System.out.print("과목학점>> ");
        int courseCredit = scanner.nextInt();

        //개설강좌 확인
        System.out.println("");
        System.out.println("----------입력된 과목----------");
        System.out.println("과목명: " + courseTitle);
        System.out.println("학점: " + courseCredit);

        //과목DB에 과목 추가
        courseDB[totalCourse] = new Course(courseTitle, courseCredit);

        totalCourse++; //과목이 정상적으로 추가되었으므로 데이터베이스에 저장된 학생 수 +1        

        //추가된 총 과목 수 메인메서드로 리턴
        return totalCourse;
    }
    //
    // 학생 정보 입력
    /**
     * 학생의 이름, 학번, 수강과목 각각 점수를 입력받는 메서드
     *
     * @param   scanner : 메인 메서드와 연동되어 입력받는 파라미터
     *          studentDB : 학생정보가 저장되어 있는 데이터베이스
     *          courseDB : 과목정보가 저장되어있는 데이터베이스
     *          totalStudent : 현재까지 입력받은 학생수
     *          totalCourse : 현재까지 입력받은 과목개수
     *          
     * @return  totalStudent : 학생정보가 입력되어 수정된 총학생수 값 반환
     */
    public static int addStudent(Scanner scanner,Student[] studentDB, Course[] courseDB, int totalStudent, int totalCourse) {      
        System.out.print("학생 이름>> ");
        String studentName = scanner.next();
        System.out.print("학생 학번>> ");
        int studentNum = scanner.nextInt();
        System.out.print("학생 수강과목 개수>> ");
        int courseCount = 0;

        //수강과목 수 입력받기
        while (true) {
            try {
                courseCount = scanner.nextInt();
                if (courseCount > totalCourse) {
                    System.out.println("현재까지 입력된 과목 수는 " + totalCourse + "개 입니다. 과목 정보를 먼저 입력한 뒤, 학생 정보를 입력해주세요.");
                    System.out.print("학생 수강과목 개수>> ");
                } else {
                    break;  // 유효한 값이면 끝내기
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("수강과목 개수는 정수로 입력해주세요!");
                scanner.nextLine();
            }
        }

        String[] studentCourse = new String[courseCount];      //입력받은 과목들을 저장할 배열 생성
        int[] score = new int[courseCount];                    //과목별 성적 입력받을 배열 생성

        //수강과목 학생점수 입력받기
        for (int i = 0 ; i < courseCount ; i++) {
            System.out.println("");;
            System.out.print("수강과목명 입력>> ");
            String inputCourse = scanner.next();   //과목이 데이터베이스에 입력되어 있는지 확인하기 위해 입력값을 저장해둘 변수

            //수강과목이 현재 과목DB에 입력되어 있는지 확인하고 학생정보 입력받기
            boolean found = false;  //입력받은 과목이 현재 데이터베이스에 있는지 구분하기 위한 변수

            //현재 과목DB에 저장되어있는 과목들을 검사하므로 입력된 과목개수만큼 반복
            for (int j = 0; j < totalCourse; j++) {

                //과목DB에서 현재 입력받은 과목명이 있는지 확인(equals()는 예제 3-6에서 나왔었음)
                if (courseDB[j].getCourseName().equals(inputCourse)) {

                    //수강과목 배열과 점수배열을 같은 인덱스로 위치시켜서 연동
                    studentCourse[i] = inputCourse;
                    System.out.print(inputCourse + "의 총점을 입력해주세요. >> ");    
                    score[i] = scanner.nextInt(); 

                    //검색한 과목 찾았으니 found를 true로 변경
                    found = true;
                    break;
                }
            }

            // 없는 과목이면 다시 입력
            if(!found){
                System.out.println(inputCourse + "과목은 입력되지 않은 과목입니다.");
                i--;
            }
        }

        //성적입력받기 전 이므로 빈 성적표 배열 만들기
        String[] grade = new String[courseCount];

        //DB에 학생 추가
        studentDB[totalStudent] = new Student(studentName, studentNum, studentCourse, score, grade);
        System.out.println(studentName + "학생 정보가 입력되었습니다.");

        totalStudent++; //학생이 정상적으로 추가되었으므로 데이터베이스에 저장된 학생 수 +1

        //추가된 총 학생 수 메인메서드로 리턴
        return totalStudent;
    }

    //과목별 성적 계산
    /**
     * 입력받은 과목에서 수강학생의 점수를 정렬하고 점수표를 출력한 뒤, 
     * 교수님께서 성적컷을 입력하면 선문대 학칙에 맞게 자동으로 학생들에게 성적을 부여하는 메서드
     * 
     *
     * @param   scanner : 메인 메서드와 연동되어 입력받는 파라미터
     *          courseDB : 과목정보가 저장되어있는 데이터베이스
     *          studentDB : 학생정보가 저장되어 있는 데이터베이스
     *          totalStudent : 현재까지 입력받은 학생수
     *          totalCourse : 현재까지 입력받은 과목개수
     */
    public static void courseCalculate(Scanner scanner , Course[] courseDB ,Student[] studentDB, int totalStudent, int totalCourse){
        System.out.print("성적조회할 과목명을 입력하세요 >> ");
        String findCourse = scanner.next(); //조회할 과목명

        Student[] rankStudents = new Student[totalStudent]; //수강학생 점수 순위를 넣을 배열
        int[] totals = new int[totalStudent];   //수강자의 점수를 저장할 배열
        int count = 0;  //수강자수

        // 해당 과목 수강 학생 찾기
        for(Student student : studentDB){
            //DB가 앞에서부터 채워지고 수정 기능이 없으므로 처음 빈 곳을 발견하면 검색 끝내기
            if(student == null){
                break;
            }

            String[] courses = student.getStudentCourse();  //학생의 수강과목 불러오기
            int[] scores = student.getScore();  //학생의 점수 불러오기

            for(int i = 0; i < courses.length; i++){
                // 해당과목 수강하는지 수강명으로 확인
                if(courses[i].equals(findCourse)){
                    rankStudents[count] = student;  //점수표에 학생 넣기
                    totals[count] = scores[i];  //점수 입력
                    count++;    //수강자 1명 추가
                    break;
                }
            }
        }

        // 수강 학생이 없는 경우
        if(count == 0){
            System.out.println("해당 과목 수강 학생이 없습니다.");
            return;
        }

        // 점수 내림차순 정렬
        for(int i = 0; i < count - 1; i++){ //정렬되지 않은 배열에서 앞 사람 인덱스를 뜻하는 i
            for(int j = i + 1; j < count; j++){ //정렬되지 않은 배열에서 뒷 사람의 인덱스를 뜻하는 j
                if(totals[i] < totals[j]){  //정렬되지 않은 배열에서 앞 사람 점수와 뒷사람 점수 비교

                    int tempScore = totals[i];  //정렬되지 않은 배열에서 앞 사람 점수를 임시저장
                    totals[i] = totals[j];  //배열의 앞 사람 자리에 뒷사람 점수를 저장
                    totals[j] = tempScore;  //배열의 뒷 사람 자리에 앞사람 점수를 저장

                    Student tempStudent = rankStudents[i];  //학생 정보도 위와 같은 방법으로 연동시킴
                    rankStudents[i] = rankStudents[j];      //점수와 학생배열의 인덱스가 같이 움직여야하기 때문에 우에 사용한 i와 j 인덱스를 그대로 사용
                    rankStudents[j] = tempStudent;
                }
            }
        }

        // 등급 나누기 전 순위표 출력
        System.out.println("");;
        System.out.println("[" + findCourse + " 성적현황]");

        for(int i = 0; i < count; i++){
            System.out.println(
                (i + 1) + "등 " + rankStudents[i].getStudentName() + " " + rankStudents[i].getStudentNum() + " " + totals[i] + "점");
        }

        // 등급컷 입력
        System.out.print("A 등급 최소 점수 >> ");
        int aCut = scanner.nextInt();

        System.out.print("B 등급 최소 점수 >> ");
        int bCut = scanner.nextInt();

        System.out.print("C 등급 최소 점수 >> ");
        int cCut = scanner.nextInt();

        //선문대학교의 학사정보에 의하면 소수점처리는 올림으로 한다
        //선문대학교 A이상 비율 35%
        double aValue = count * 0.35;
        int aLimit = (int)aValue;
        //소수점이 있다면 올림처리
        if(aValue > aLimit){
            aLimit++;
        }
        //선문대학교 B이상 비율 70%
        double bValue = count * 0.70;
        int bLimit = (int)bValue;

        if(bValue > bLimit){
            bLimit++;
        }

        // 최종 성적 출력
        System.out.println("");;
        System.out.println("[" + findCourse + " 최종 성적]");

        for(int i = 0; i < count; i++){
            String grade;   //학점 저장
            //만약 35% 이상이 A 이상을 받게된다면 자동으로 성적순으로 끊어 35%이후 학생은 B+학점 부여
            if(i < aLimit && totals[i] >= aCut){
                grade = "A";
            }
            else if(i < bLimit && totals[i] >= bCut){
                grade = "B";
            }
            else if(totals[i] >= cCut){
                grade = "C";
            }
            else{
                grade = "F";
            }

            String[] grade = rankStudents[i].getGrades();   //받은 성적 저장
            String[] course = rankStudents[i].getStudentCourse();   //어느 과목의 성적을 저장할지 정함

            // 현재 처리중인 과목 위치 찾아서 성적배열의 같은 인덱스에 저장
            for(int k = 0; k < courses.length; k++){
                if(courses[k].equals(findCourse)){
                    grades[k] = grade;
                    break;
                }
            }
            System.out.println((i + 1) + "등 " + rankStudents[i].getStudentName() + " " + totals[i] + "점 " + grade);
        }
    }

    //학생별 성적 계산
    /**
     * 학번을 입력받으면 해당 학생의 성적을 계산하여 학생 성적표를 출력하는 메서드
     *
     * @param   scanner : 메인 메서드와 연동되어 입력받는 파라미터
     *          studentDB : 학생정보가 저장되어 있는 데이터베이스
     *          courseDB : 과목정보가 저장되어있는 데이터베이스
     *          totalStudent : 현재까지 입력받은 학생수
     *          totalCourse : 현재까지 입력받은 과목개수
     */
    public static void studentScoreCalculate(Scanner scanner ,Student[] studentDB ,Course[] courseDB ,int totalStudent ,int totalCourse){
        System.out.print("성적처리할 학생의 학번을 입력하세요. >> ");
        int findStudentNum = scanner.nextInt(); //조회학생 학번

        //검색할 학생정보 저장
        Student targit = null;

        //학생을 찾았는가 확인
        boolean found = false;

        //학생 DB에서 학번으로 학생 조회
        for(int i = 0; i < totalStudent; i++){
            if(studentDB[i].getStudentNum() == findStudentNum){
                targit = studentDB[i];  //찾은학생정보 저장
                found = true;
                break;
            }
        }

        //학생 DB에 해당 학생 없을경우 안내문구 출력
        if (!found) {
            System.out.println("");;
            System.out.println("해당 학번의 학생을 찾을 수 없습니다.");
            return;
        }

        System.out.println("");
        System.out.println("이름 : " + targit.getStudentName());
        System.out.println("학번 : " + targit.getStudentNum());

        String[] course = targit.getStudentCourse();    //수강과목 불러오기
        int[] score = targit.getScore();    //과목별 총점수 불러오기
        String[] grade = targit.getGrade();    //과목별 성적 불러오기
        int applyCredit = 0;      // 신청학점
        int completeCredit = 0;   // 이수학점
        double totalPoint = 0;    // 평점합계
        double avg = 0;           // 평점평균
        double percent;           // 백분위
        double point = 0.0;       // 과목 평점 저장할 변수

        //과목별 성적표 출력
        for(int i = 0; i < course.length; i++){
            String courseName = course[i];
            int credit = 0; //현재 과목학점

            // 과목학점 불러오기
            for(int j = 0; j < totalCourse; j++){
                if(courseDB[j].getCourseName().equals(courseName)){
                    credit = courseDB[j].getCourseCredit();
                    break;
                }
            }

            applyCredit += credit; //신청학점 합산

            //성적별 평점부여
            if(grade[i].equals("A")){
                point = 4.0;
                completeCredit += credit;
            }
            else if(grade[i].equals("B")){
                point = 3.0;
                completeCredit += credit;
            }
            else if(grade[i].equals("C")){
                point = 2.0;
                completeCredit += credit;
            }
            else{
                point = 0.0;
            }

            totalPoint += point * credit;   //평점 * 과목학점 = 평점합계
            System.out.println(courseName + " : " + credit + "학점 / " + grade[i]);
        }
        
        // 평점평균 계산 (평점 총합 / 이수학점)
        avg = totalPoint / completeCredit;

        // 백분위 계산
        if(avg >= 4.4){
            percent = (avg * 20) + 10;
        }
        else{
            percent = (avg * 10) + 54;
        }

        // 석차 계산
        int rank = 1;

        for(int i = 0; i < totalStudent; i++){

            Student other = studentDB[i];

            if(other == null){
                break;
            }

            String[] otherGrades = other.getGrades();
            String[] otherCourses = other.getStudentCourse();

            int otherComplete = 0;
            double otherTotal = 0;

            for(int j = 0; j < otherCourses.length; j++){

                int credit = 0;

                for(int k = 0; k < totalCourse; k++){
                    if(courseDB[k].getCourseName()
                    .equals(otherCourses[j])){

                        credit = courseDB[k].getCourseCredit();
                        break;
                    }
                }

                double point = 0;

                if(otherGrades[j].equals("A")){
                    point = 4.0;
                    otherComplete += credit;
                }
                else if(otherGrades[j].equals("B")){
                    point = 3.0;
                    otherComplete += credit;
                }
                else if(otherGrades[j].equals("C")){
                    point = 2.0;
                    otherComplete += credit;
                }

                otherTotal += point * credit;
            }

            double otherAvg = 0;

            if(otherComplete != 0){
                otherAvg = otherTotal / otherComplete;
            }

            if(otherAvg > avg){
                rank++;
            }

            System.out.println("");
            System.out.println("신청학점 : " + applyCredit);
            System.out.println("이수학점 : " + completeCredit);
            System.out.println("평점합계 : " + totalPoint);
            System.out.println("평점평균 : " + avg);
            System.out.println("백분위점수 : " + percent);
            System.out.println("석차 : " + rank + "/" + totalStudent);
        }
    }

    //메인 메서드
    public static void main(String[] args){
        //변수 선언
        Scanner scanner = new Scanner(System.in);
        Student[] studentDB = new Student[1000];     //학생정보를 저장할 데이터베이스
        Course[] courseDB = new Course[100];         //과목을 저장할 데이터베이스

        //메인화면 변수
        int mainMenu = 0;                           //기본화면
        int studentMenu = 0;                        //학생메뉴
        int courseMenu = 0;                         //과목메뉴
        int creditMenu = 0;                         //성적처리메뉴

        //학생관련 변수
        int totalStudent = 0;                        //입력받은 총 학생 수

        //과목관련 변수
        int totalCourse = 0;                        //입력받은 총 과목 수

        while (mainMenu != 4){
            switch (mainMenu){
                    //메인화면
                case 0:
                    System.out.println("");
                    System.out.println("-------------성적처리 홈화면입니다.-------------");
                    System.out.println("");
                    System.out.print("과목명 입력: 1, 학생정보 입력: 2, 성적처리: 3, 프로그램 종료: 4>> ");

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

                    //과목명 입력
                case 1:
                    System.out.println("");
                    totalCourse = addCourse(scanner,studentDB,courseDB,totalCourse);
                    mainMenu = 0;
                    break;

                    //학생 메뉴
                case 2:
                    totalStudent = addStudent(scanner, studentDB, courseDB, totalStudent, totalCourse);       
                    mainMenu = 0;
                    break;

                    //성적처리 메뉴
                case 3:
                    while(creditMenu != 3){
                        mainMenu = 0;
                        switch (creditMenu){
                            case 0:
                                //성적처리메뉴 홈 탭
                                System.out.println("");
                                System.out.println("-------------성적처리 탭입니다.-------------");
                                System.out.println("");
                                System.out.print("과목별 성적조회: 1, 학생별 성적조회: 2, 기본메뉴로 돌아가기: 3>> ");

                                //정수 외 입력 예외처리
                                try{
                                    creditMenu = scanner.nextInt();    
                                }
                                catch (InputMismatchException e){
                                    System.out.println("1,2,3 중 입력하세요.");
                                    scanner.nextLine();
                                    studentMenu = 0;
                                }
                                break;

                                //----------------------------------------과목별 성적조회 탭
                            case 1:
                                courseCalculate(scanner , courseDB , studentDB, totalStudent, totalCourse);
                                creditMenu = 0;
                                break;

                                //--------------------------------------------학생별 성적조회 탭
                            case 2:
                                studentScoreCalculate(scanner , studentDB , courseDB , totalStudent , totalCourse);
                                creditMenu = 0;
                                break;

                            case 3:
                                mainMenu = 0;
                                break;

                                //성적처리 탭 정수 입력 예외처리
                            default:  
                                System.out.println("메뉴 탭 1,2,3 중 입력하세요");
                                scanner.nextLine();
                                studentMenu = 0;
                        }
                    }
                    creditMenu = 0;
                    mainMenu = 0;
                    break;

                    //프로그램 종료
                case 4:
                    break;

                    //메인메뉴 정수 예외처리
                default:
                    System.out.println("메뉴 탭 1,2,3,4 중 입력하세요.");
                    System.out.println("");
                    scanner.nextLine();
                    mainMenu = 0;  
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }
}
package Prototype;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * 과목정보와 학생정보를 입력받고 선문대학교 학칙에 맞춰 성적처리를 하는 프로그램
 *
 * @author (2023320010 박성준, 2023320012 강성하, 2023320006 정준영, 2023320029 정지후)
 * @version (2026.05.15)
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

        int courseCredit;    
        while(true){
            try{
                System.out.print("과목학점>> ");
                courseCredit = scanner.nextInt();
                break;
            }
            catch(java.util.InputMismatchException e){
                System.out.println("과목학점은 정수로 입력해주세요!");
                scanner.nextLine();
            }
        }

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
        int studentNum;
        while(true){
            try{
                System.out.print("학생 학번>> ");
                studentNum= scanner.nextInt();
                break;
            }
            catch(java.util.InputMismatchException e){
                System.out.println("학번을 다시 입력해주세요!");
                scanner.nextLine();
            }
        }
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

            } 
            catch (java.util.InputMismatchException e) {
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

                    //제대로 입력될 때까지 반복
                    while(true){
                        try {
                            System.out.print(inputCourse + "의 총점을 입력해주세요. >> ");
                            score[i] = scanner.nextInt();
                            break;
                        }
                        catch (java.util.InputMismatchException e) {
                            System.out.println("총점은 정수로 입력해주세요!");
                            scanner.nextLine();
                        }
                    }
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
     * 교수님께서 성적컷을 입력하면 선문대 학칙에 맞게 자동으로 성적을 부여하는 메서드
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
        int aCut; 
        while(true){
            try{
                System.out.print("A 등급 최소 점수 >> ");
                aCut = scanner.nextInt();
                break;
            }
            catch(java.util.InputMismatchException e){
                System.out.println("정수로 입력해주세요!");
                scanner.nextLine();
            }
        }

        int bCut;
        while(true){
            try{
                System.out.print("B 등급 최소 점수 >> ");
                bCut = scanner.nextInt();
                break;
            }
            catch(java.util.InputMismatchException e){
                System.out.println("정수로 입력해주세요!");
                scanner.nextLine();
            }
        }

        int cCut;
        while(true){
            try{
                System.out.print("C 등급 최소 점수 >> ");
                cCut = scanner.nextInt();
                break;
            }
            catch(java.util.InputMismatchException e){
                System.out.println("정수로 입력해주세요!");
                scanner.nextLine();
            }
        }

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

            //받은 성적 저장
            String[] courses = rankStudents[i].getStudentCourse();

            for(int k = 0; k < courses.length; k++){
                //성적계산 받은 과목의 인덱스 찾기
                if(courses[k].equals(findCourse)){
                    rankStudents[i].setGrade(k, grade); //해당 과목과 같은 인덱스에 성적 저장하기
                    break;
                }
            }
            System.out.println((i + 1) + "등 " + rankStudents[i].getStudentName() + " " + totals[i] + "점 / 성적 : " + grade);
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
        int findStudentNum;  //조회학생 학번
        while(true){
            try{
                System.out.print("성적처리할 학생의 학번을 입력하세요. >> ");
                findStudentNum = scanner.nextInt();
                break;
            }
            catch(java.util.InputMismatchException e){
                System.out.println("학번은 정수로 입력해주세요!");
                scanner.nextLine();
            }
        }

        //검색할 학생정보 저장
        Student target = null;

        //학생을 찾았는가 확인
        boolean found = false;

        //학생 DB에서 학번으로 학생 조회
        for(int i = 0; i < totalStudent; i++){
            if(studentDB[i].getStudentNum() == findStudentNum){
                target = studentDB[i];  //찾은학생정보 저장
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
        System.out.println("이름 : " + target.getStudentName());
        System.out.println("학번 : " + target.getStudentNum());

        String[] course = target.getStudentCourse();    //수강과목 불러오기
        String[] grade = target.getGrade();    //과목별 성적 불러오기
        int applyCredit = 0;      // 신청학점
        int completeCredit = 0;   // 이수학점
        double totalPoint = 0;    // 평점합계
        double point = 0.0;       // 과목 평점

        // 이수과목 성적처리 완료 여부 확인
        for(int i = 0; i < grade.length; i++){
            //만약 아직 성적이 계산되지 않았다면 실행
            if(grade[i] == null){
                System.out.println("");
                System.out.println(course[i] + " 과목의 성적처리가 아직 완료되지 않았습니다.");
                System.out.println("과목별 성적처리를 먼저 진행해주세요.");
                return;
            }
        }

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

        // 평점평균 계산 (평점평균 = 평점합계 / 이수학점)
        double avg = 0;
        avg = totalPoint / completeCredit;
        avg = ((int)(avg * 100 + 0.5)) / 100.0; //평점평균 산출은 다음과 같이 하며 소수점 셋째자리에서 반올림하여 둘째자리까지 표기한다.#선문대학교 학칙 (6-1)교무규정 36조

        // 백분위 계산  
        //평점평균 4.4 이상일때 (평점평균*20)+10
        //평점평균 4.4 미만일때는 (평점평균*10)+54
        double percent;
        if(avg >= 4.4){
            percent = (avg * 20) + 10;
        }
        else{
            percent = (avg * 10) + 54;
        }

        // 석차 계산
        int rank = 1;

        for(int i = 0; i < totalStudent; i++){
            Student other = studentDB[i];   //조회학생 외 학생정보 불러오기

            if(other == null){  //DB에서 처음으로 빈 곳 발견하면 그 뒤로도 입력된 학생이 없으므로 불러오기 종료
                break;
            }

            String[] otherGrade = other.getGrade();     //나머지 학생 성적 불러오기
            String[] otherCourse = other.getStudentCourse();    //나머지 학생 수강과목 불러오기

            int otherComplete = 0;  //나머지 학생의 이수학점 
            double otherTotal = 0;  //나머지 학생의 평점합계

            // 나머지 학생들 이수과목 성적처리 완료 여부 확인
            for(int l = 0; l < otherGrade.length; l++){
                //만약 아직 성적이 계산되지 않았다면 실행
                if(otherGrade[i] == null){
                    System.out.println("");
                    System.out.println(course[l] + " 과목의 성적처리가 아직 완료되지 않았습니다.");
                    System.out.println("과목별 성적처리를 먼저 진행해주세요.");
                    return;
                }
            }

            for(int j = 0; j < otherCourse.length; j++){
                int credit = 0;     //과목학점 저장

                for(int k = 0; k < totalCourse; k++){
                    if(courseDB[k].getCourseName().equals(otherCourse[j])){
                        credit = courseDB[k].getCourseCredit();
                        break;
                    }
                }

                double otherPoint = 0;  //나머지 학생의 과목평점

                if(otherGrade[j].equals("A")){
                    otherPoint = 4.0;
                    otherComplete += credit;
                }
                else if(otherGrade[j].equals("B")){
                    otherPoint = 3.0;
                    otherComplete += credit;
                }
                else if(otherGrade[j].equals("C")){
                    otherPoint = 2.0;
                    otherComplete += credit;
                }

                otherTotal += otherPoint * credit;
            }

            double otherAvg = 0;    //나머지 학생의 백분위
            otherAvg = otherTotal / otherComplete;
            otherAvg = ((int)(otherAvg * 100 + 0.5)) / 100.0;

            //백분위 점수 기준으로 석차 계산
            if(otherAvg > avg){
                rank++;
            }

        }
        System.out.println("");
        System.out.println("신청학점 : " + applyCredit);
        System.out.println("이수학점 : " + completeCredit);
        System.out.println("평점합계 : " + totalPoint);
        System.out.println("평점평균 : " + avg);
        System.out.println("백분위점수 : " + percent);
        System.out.println("석차 : " + rank + "/" + totalStudent);
    }

    //메인 메서드
    public static void main(String[] args){
        //변수 선언
        Scanner scanner = new Scanner(System.in);
        Student[] studentDB = new Student[1000];     //학생정보를 저장할 데이터베이스
        Course[] courseDB = new Course[100];         //과목을 저장할 데이터베이스

        //메인화면 변수
        int mainMenu = 0;                           //기본화면

        //학생관련 변수
        int totalStudent = 0;                        //입력받은 총 학생 수

        //과목관련 변수
        int totalCourse = 0;                        //입력받은 총 과목 수

        //사용법 출력
        System.out.println("성적처리 프로그램을 실행합니다.");
        System.out.println("사용법은 아래와 같습니다.");
        System.out.println("1. 원하는 항목의 숫자를 키보드로 입력후 엔터를 눌러주세요. ");
        System.out.println("2. 성적처리 도중, 학생 또는 과목이 추가될 경우 해당 정보를 입력한 뒤 3번 항목(과목별 성적계산)부터 다시 실행해주세요.");
        System.out.println("3. 숫자 5를 입력하면 프로그램이 종료됩니다.");
        System.out.println("4. 이 프로그램은 선문대학교 학칙, 학사팀 문의내용을 기준으로 만들어졌습니다.");
        while (mainMenu != 5){
            switch (mainMenu){
                    //메인화면
                case 0:
                    System.out.println("");
                    System.out.println("-------------성적처리 홈화면입니다.-------------");
                    System.out.println("");
                    System.out.print("과목정보 입력: 1, 학생정보 입력: 2, 과목별 성적계산: 3, 학생별 성적조회: 4, 프로그램 종료: 5>> ");

                    //예외처리
                    try{
                        mainMenu = scanner.nextInt();
                    }
                    catch (java.util.InputMismatchException e){
                        System.out.println("1,2,3,4,5 중 입력하세요.");
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

                    //과목별 성적계산
                case 3:
                    courseCalculate(scanner , courseDB , studentDB, totalStudent, totalCourse);
                    mainMenu = 0;
                    break;

                case 4:
                    studentScoreCalculate(scanner , studentDB , courseDB , totalStudent , totalCourse);
                    mainMenu = 0;
                    break;

                    //프로그램 종료
                case 5:
                    break;

                    //메인메뉴 정수 예외처리
                default:
                    System.out.println("메뉴 탭 1,2,3,4,5 중 입력하세요.");
                    System.out.println("");
                    scanner.nextLine();
                    mainMenu = 0;  
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }
}
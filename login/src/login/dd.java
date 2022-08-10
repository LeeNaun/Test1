package login;

import java.util.Scanner;

public class dd {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Check check = new Check();
		String id = null, pw;
		
		String dateno;
		String title;
		String contents;
		
		int flag=0;
		System.out.println("*-*-*-*-*-*-*WELCOME!!*-*-*-*-*-*-*");
		System.out.println("로그인은(1), 회원가입은(2), 회원삭제는(3)을 입력하세요.");
		for(;;) {
			
			int user=sc.nextInt();
			sc.nextLine(); //숫자 입력 후 엔터값 없애기
			if(user==1) {
				System.out.println("아이디를 입력해 주세요.");
				id=sc.nextLine();
				System.out.println("패스워드를 입력해 주세요.");
				pw=sc.nextLine();
				
				
				//객체(Check)의 메소드(login(string id, string pw):int(1:아이디가 틀렸다.
				//2: 패스워드 틀렸다. 3:로그인성공)를 통해서 로그인 여부 확인
				
				flag = check.login(id,pw);
				
				if(flag==1)
					System.out.println("아이디가 틀렸다.");
				if(flag==2)
					System.out.println("패스워드가 틀렸다.");
				if(flag==3) {
					System.out.println("로그인 성공");
					System.out.println("글을 작성하려면(1), 일기장 목록으로 가려면(2)를 입력하세요. ");
					int a=sc.nextInt();
					sc.nextLine();
					if(a==1) {
						System.out.println("오늘의 날짜(22/00/00)를 입력해 주세요.");
						dateno=sc.nextLine();
						System.out.println("일기장 제목을 입력해 주세요.");
						title=sc.nextLine();
						System.out.println("오늘의 일기를 작성해 주세요.");
						contents=sc.nextLine();
						System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
						System.out.println("게시글 작성이 완료되었습니다.");
						System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
						
					}else {
						
					}
				}
					
				if(flag==0)
					System.out.println("로그인 실패");
			}
		}
	}
}


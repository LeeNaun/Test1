package login;

import java.util.List;
import java.util.Scanner;

import login2.User;

public class naunDiary {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Check check = new Check();
		String id = null, pw;
		
		String dateno;
		String title;
		String contents;
		
	
		int flag=0;
		System.out.println("*-*-*-*-*-*-*WELCOME!!*-*-*-*-*-*-*");
		
		for(;;) {
			System.out.println("로그인은(1), 회원가입은(2), 회원삭제는(3), 일기장 목록은(4)를 입력하세요.");
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
				if(flag==3) 
					System.out.println("로그인 성공");
					
				if(flag==0)
					System.out.println("로그인 실패");
			
				
				
			}if(user==2){
				System.out.println("회원가입을 시작합니다.");
				System.out.println("아이디를 입력해 주세요.");
				id=sc.nextLine();
				System.out.println("패스워드를 입력해 주세요.");
				pw=sc.nextLine();
				check.signUp(id,pw);
				System.out.println("*-*-*-*-*-*-*");
				System.out.println("회원가입 완료");
				System.out.println("*-*-*-*-*-*-*");
				
			}if(user==3){	
				System.out.println("회원 삭제를 진행하겠습니다.");
				System.out.println("삭제를 진행하려면 (1), 취소를 원하시면 (2)를 입력해 주세요.");
				int a=sc.nextInt();
				sc.nextLine(); //엔터값 삭제
				if(a==1) {
				
					do {
						System.out.println("회원 삭제를 위해 아이디를 입력해 주세요.");
						id=sc.nextLine();
						
						flag=check.login(id,"1"); //1:아이디가 없다. 2:아이디가 있다.
						int count=0;
						if(flag==2){
							do{
								System.out.println("회원 삭제를 위해 패스워드를 입력해 주세요.");
								pw=sc.nextLine();
									 
								count=check.delete(id, pw); 
									
							}while(count==0);
							System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
							System.out.println("회원 삭제가 완료되었습니다. 감사합니다.");
							System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
						}	
					}while(flag==1);
				}else {
					System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*");
					System.out.println("취소하였습니다. 감사합니다.");
					System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*");
				}
				
				}
			if(user==4) {//목록을 보려면 로그인을 해야 보임
					System.out.println("목록을 확인하려면 로그인을 하세요.");
					System.out.println("아이디를 입력해 주세요.");
					id=sc.nextLine();
					System.out.println("패스워드를 입력해 주세요.");
					pw=sc.nextLine();
					if(flag==1)
						System.out.println("아이디가 틀렸다.");
					if(flag==2)
						System.out.println("패스워드가 틀렸다.");
					if(flag==3) {
						System.out.println("로그인 성공");
						 List list = check.list();
					        for (User temp : list) {
					          System.out.print(temp.id + "   ");
					          System.out.println(temp.pw);
					        }
					}
					if(flag==0)
						System.out.println("로그인 실패");
			}
			
		}//for
	

	}

}


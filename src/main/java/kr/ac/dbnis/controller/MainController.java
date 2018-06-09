package kr.ac.dbnis.controller;

import java.util.Scanner;

import kr.ac.dbnis.article.ArticleVO;
import kr.ac.dbnis.user.UserVO;

public class MainController {
	public static void main(String[] args) {
		UserVO vo = new UserVO();
		UserController usercont = new UserController();
		// List<UserVO> userList = userDAO.getUserList(vo);

		Scanner key = new Scanner(System.in);

		int count = 0;
		boolean flag = true;
		while (true) {

			// login and create account
			while (flag) {
				if (count == 3) {
					System.out.println("unable to log-in to Simple BBS after 3 attempts");
					return;
				}
				System.out.println("CS360 \t Simple BBS");
				System.out.println("please type 'new' to create a new account.");
				System.out.print("user name: ");
				String userid = key.next();
				if (userid.equals("new")) {
					boolean flag2 = true;
					while (flag2) {
						System.out.print("ID: ");
						String newID = key.next();
						if (usercont.judgeSameID(vo, newID)) {
							System.out.println("same ID exists.");
						} else {
							System.out.print("PASSWORD: ");
							String newPW = key.next();
							vo.setUserid(newID);
							vo.setPasswd(newPW);
							usercont.createAccount(vo);
							System.out.println("new account " + newID + " is created");
							flag2 = false;

						}
					}

				} else {
					System.out.print("password: ");
					String passwd = key.next();
					vo.setUserid(userid);
					vo.setPasswd(passwd);
					if (!usercont.judgeLogin(vo)) {
						System.out.println("invalid username/password");
						count++;
						continue;
					} else {
						System.out.println("");
						System.out.println("");
						flag = false;
					}
				}
			} // finish while(flag) statement

			System.out.print("command: ");
			String command = key.next();
			ArticleVO articleVO = new ArticleVO();
			ArticleController articlecont = new ArticleController();
			key.nextLine();
			switch (command) {
			// 2-2 BBS program exits
			case "q":
				System.out.println("Bye bye~");
				return;
			// 2-1 show the catalog of user articles in the order of article number(no)
			case "s":
				articlecont.showArticle();
				System.out.println("");
				System.out.println("");
				break;
			// 3 show the title and content of the selected article and increase count
			case "r":
				System.out.println("Read Mode>>");
				System.out.print("no.: ");
				int articleNO = key.nextInt();
				articlecont.showDetail(articleNO);
				System.out.println("");
				System.out.println("");
				break;
			// 4 note that entering content ends with the carriage return
			case "w":
				System.out.println("Write Mode>>");
				System.out.print("title: ");
				String title = key.nextLine();
				System.out.print("content: ");
				String content = key.nextLine();

				articleVO.setWriter(vo.getUserid());
				articleVO.setTitle(title);
				articleVO.setContent(content);

				articlecont.insertArticle(articleVO);

				System.out.println("");
				System.out.println("");
				break;
			// 5 note that a user cannot delete the others' articles
			case "d":
				System.out.println("Delete Mode>>");
				System.out.print("no.: ");
				int no = key.nextInt();
				String articleWriter = articlecont.getArticle(no);

				if (articleWriter.equals(vo.getUserid())) {
					articlecont.deleteArticle(no);
				} else {
					System.out.println("you can't delete.");
				}
				System.out.println("");
				System.out.println("");
				break;

			}	// finish switch statement

		} // finish while(true) statement

	} // finish main() method

} // finish MainController class

package kr.ac.dbnis.controller;

import java.util.List;

import kr.ac.dbnis.article.ArticleVO;
import kr.ac.dbnis.article.impl.ArticleDAO;

public class ArticleController {
	ArticleDAO articleDAO = new ArticleDAO();
	ArticleVO vo = new ArticleVO();
	
	// 2-1 show the catalog of user articles in the order of article number(no) 
	void showArticle() {
		List<ArticleVO> articleList = articleDAO.getArticleList();
		System.out.println("CS360 \t Simple BBS");
		System.out.println("--------------------------------------");
		System.out.println("No.\t" + "writer\t" + "count\t" + "title");
		System.out.println("--------------------------------------");
		for (ArticleVO article : articleList) {
			System.out.println(article.showList());
		}
	}	// finish showArticle()
	
	// 3 show the title and content of the selected article and increase count
	void showDetail(int no) {
		ArticleVO article = articleDAO.getArticle(no);
		System.out.println("title: "+ article.getTitle());
		System.out.println("content: "+ article.getContent());
		articleDAO.updateCount(no);	// 게시글 조회수 증가
		
	}	// finish showDetail()
	
	
	// 4 note that entering content ends with the carriage return
	void insertArticle(ArticleVO vo) {
		articleDAO.insertArticle(vo);
	}
	
	// 5 judge able to delete writer
	String getArticle(int no) {
		ArticleVO article = articleDAO.getArticle(no);
		return article.getWriter();
	}
	
	void deleteArticle(int no) {
		articleDAO.deleteArticle(no);
		articleDAO.updateNO(no);
	}
		
}

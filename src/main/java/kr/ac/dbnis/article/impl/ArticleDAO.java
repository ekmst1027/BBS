package kr.ac.dbnis.article.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.ac.dbnis.article.ArticleVO;
import kr.ac.dbnis.common.JDBCUtil;

// DAO(Data Access Object)
@Repository("articleDAO")
public class ArticleDAO {
	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	// SQL 명렁어들
	private final String ARTICLE_INSERT = "insert into articles(no, writer, title, content)"
			+ "values((select nvl(max(no), 0)+1 from articles), ?, ?, ?)";
	private final String ARTICLE_UPDATE = "update articles set title=?, content=? where no=?";
	private final String ARTICLE_COUNT = "update articles "
			+ "set count=(select nvl(COUNT,0)+1 FROM articles where no=?) "
			+ "where no=?";
	private final String ARTICLE_DELETE = "delete articles where no=?";
	private final String ARTICLE_GET = "select * from articles where no=?";
	private final String ARTICLE_LIST = "select no, writer, count, title from articles order by no asc";
	private final String UPDATE_NO = "update articles set no = no - 1 where no > ?";
	
	// CRUD 기능의 메소드 구현
	// 글 등록
	public void insertArticle(ArticleVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ARTICLE_INSERT);
			stmt.setString(1, vo.getWriter());
			stmt.setString(2, vo.getTitle());
			stmt.setString(3, vo.getContent());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}	// finish insertArticle() method
	
	// 글 수정
	public void updateArticle(ArticleVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ARTICLE_UPDATE);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getNo());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}	// finish updateArticle() method
	
	// 글 삭제
	public void deleteArticle(int no) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ARTICLE_DELETE);
			stmt.setInt(1, no);
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}	// finish deleteArticle() method
	
	// 글 상세 조회
	public ArticleVO getArticle(int no) {
		ArticleVO article = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ARTICLE_GET);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			if(rs.next()) {
				article = new ArticleVO();
				article.setWriter(rs.getString("WRITER"));
				article.setTitle(rs.getString("TITLE"));
				article.setContent(rs.getString("CONTENT"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return article;
	}	// finish getArticle() method
	
	// 게시글 조회수 증가
	public void updateCount(int no) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ARTICLE_COUNT);
			stmt.setInt(1, no);
			stmt.setInt(2, no);
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}	// finish updateArticle() method
	
	// 글 목록 조회
	public List<ArticleVO> getArticleList() {
		List<ArticleVO> articleList = new ArrayList<ArticleVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ARTICLE_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				ArticleVO article = new ArticleVO();
				article.setNo(rs.getInt("NO"));
				article.setWriter(rs.getString("WRITER"));
				article.setCount(rs.getInt("COUNT"));
				article.setTitle(rs.getString("TITLE"));
				articleList.add(article);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return articleList;
	}	// finish getArticleList() method
	
	// 인덱스 1씩 줄이기
	public void updateNO(int no) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(UPDATE_NO);
			stmt.setInt(1, no);
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}	// finish updateArticle() method
}	// finish BoardDAO class


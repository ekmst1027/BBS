package kr.ac.dbnis.article;

// VO(Value Object)
public class ArticleVO {
	private int no;
	private String writer;
	private int count;
	private String title;
	private String content;
	
	// declare Getters and Setters
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	// declare showList() method
	public String showList() {
		return no + "\t" + writer + "\t" + count + "\t" + title;
	}
	
} // finish the ArticleVO class

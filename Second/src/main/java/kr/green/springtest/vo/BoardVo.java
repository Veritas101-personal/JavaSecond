package kr.green.springtest.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVo {

	private int num;
	private String writer;
	private String title;
	private String content;
	private Date date;
	private int watchNum;
	private int commentNum;
	private char isDel;
	private int up;
	private String file;
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return transFormat.format(date);
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public void setDate(String date) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.date = transFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public int getWatchNum() {
		return watchNum;
	}
	public void setWatchNum(int watchNum) {
		this.watchNum = watchNum;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public char getIsDel() {
		return isDel;
	}
	public void setIsDel(char isDel) {
		this.isDel = isDel;
	}
	
	public int getUp() {
		return up;
	}
	public void setUp(int up) {
		this.up = up;
	}
	
	@Override
	public String toString() {
		return "BoardVo [num=" + num + ", writer=" + writer + ", title=" + title + ", content=" + content + ", date="
				+ date + ", watchNum=" + watchNum + ", commentNum=" + commentNum + ", isDel=" + isDel + ", up=" + up
				+ ", file=" + file + "]";
	}
	/* DB에 저장된 file 이름은 /년도/월/일/uuid_파일명.확장자로 되어있는데,
	 * 사용자는 파일명.확장자만 보여줘야 하기 때문에 getOriFile을 통해 원본 파일명을 보여줌.
	 */
	public String getOriFile() {
		int index = file.indexOf("_");
		return file.substring(index+1);
	}
	
}

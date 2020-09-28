package kr.green.springtest.pagenation;

public class Criteria {

	private int page;
	private int perPageNum;
	private String search;
	private int type;
	
	public Criteria(){
		page = 1;
		perPageNum = 10;
		search = "";
		type = 0; // 이건 원래 생략 가능, int는 초기값이 0으로 자동고정되기 때문 
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page <= 0) {
			this.page = 0;
		}
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if (perPageNum < 10) {
			this.perPageNum =10;
		} else {
			this.perPageNum = perPageNum;
		}
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		if(type < 0 || type >3) {
			this.type = 1;
		} else {
			this.type = type;
		}
	}
	
	public int getStartPage() {
		return (page - 1)*perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", search=" + search + ", type=" + type + "]";
	}
	
}

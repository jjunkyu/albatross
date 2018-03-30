package kosta.albatross.dao;

public class PagingBean {

	private int nowPage = 1;
	private int postCountPerPage = 10;
	private int pageCountPerPageGroup = 5;
	private int totalPostCount;

	public PagingBean() {
	}

	public PagingBean(int totalPostCount) {
		this.totalPostCount = totalPostCount;
	}

	public PagingBean(int totalPostCount, int nowPage) {
		this.totalPostCount = totalPostCount;
		this.nowPage = nowPage;
	}

	public int getNowPage() {
		return nowPage;
	}

	public int getStartRowNumber() {

		return ((nowPage - 1) * postCountPerPage) + 1;
	}

	public int getEndRowNumber() {
		int endRowNumber = nowPage * postCountPerPage;
		if (endRowNumber > totalPostCount)
			endRowNumber = totalPostCount;

		return endRowNumber;
	}

	private int getTotalPage() {
		int totalPage = totalPostCount % postCountPerPage;
		if (totalPage == 0)
			return totalPostCount / postCountPerPage;
		else
			return totalPostCount / postCountPerPage + 1;
	}

	private int getTotalPageGroup() {
		int totalPageGroup = getTotalPage() % pageCountPerPageGroup;
		if (totalPageGroup == 0)
			return getTotalPage() / pageCountPerPageGroup;
		else
			return getTotalPage() / pageCountPerPageGroup + 1;
	}

	private int getNowPageGroup() {
		int nowPageGroup = nowPage % pageCountPerPageGroup;
		if (nowPageGroup == 0)
			return nowPage / pageCountPerPageGroup;
		else
			return nowPage / pageCountPerPageGroup + 1;
	}

	public int getStartPageOfPageGroup() {
		return pageCountPerPageGroup * (getNowPageGroup() - 1) + 1;
	}

	public int getEndPageOfPageGroup() {
		int num = getNowPageGroup() * pageCountPerPageGroup;
		if (getTotalPage() < num)
			num = getTotalPage();

		return num;
	}

	public boolean isPreviousPageGroup() {
		boolean flag = false;
		if (this.getNowPageGroup() > 1) {
			flag = true;
		}
		return flag;
	}

	public boolean isNextPageGroup() {
		boolean flag = false;
		if (getNowPageGroup() < getTotalPageGroup())
			flag = true;

		return flag;
	}

}

package com.prowo.db.mysql.interceptor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * @Description 分页
 * @author FQ
 * @date 2014年9月15日 下午4:44:58
 * @version 1.0
 */

public class Page<T> implements Serializable {

	public static final int MAX_PAGE_SIZE = 20000;// 每页最大记录数限制

	private int pageNumber = 1;// 当前页码
	private int pageSize = 20;// 每页记录数
	private int totalCount = 0;// 总记录数
	private int pageCount = 0;// 总页数
	private Long begDate; // 开始时间
	private Long endDate; // 接收时间

	private List<T> resultsContent = new ArrayList<T>();// 对应的当前页记录


	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		if (pageNumber < 1)
			this.pageNumber = 1;
		else
			this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else if (pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {

		this.totalCount = totalCount;

		// 设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
		int pageCount = totalCount % pageSize == 0 ? totalCount / pageSize
				: totalCount / pageSize + 1;
		this.setPageCount(pageCount);
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getResultsContent() {
		return resultsContent;
	}

	public void setResultsContent(List<T> resultsContent) {
		this.resultsContent = resultsContent;
	}

	public Long getBegDate() {
		return begDate;
	}

	public void setBegDate(Long begDate) {
		this.begDate = begDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageNumber=").append(pageNumber)
				.append(", pageSize=").append(pageSize)
				.append(", resultsContent=").append(JSON.toJSONString(resultsContent))
				.append(", pageCount=").append(pageCount)
				.append(", totalCount=").append(totalCount).append("]");
		return builder.toString();
	}
}



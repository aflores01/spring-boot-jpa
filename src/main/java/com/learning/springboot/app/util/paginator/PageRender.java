package com.learning.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;
	private Integer totalPages;
	private Integer numItemsPPage;
	private int currentPage;
	
	private List<PageItem> pages;

	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.pages = new ArrayList<PageItem>();
		numItemsPPage = page.getSize();
		totalPages = page.getTotalPages();
		currentPage = page.getNumber() + 1;
		int desde, hasta;
		
		if (totalPages <= numItemsPPage) {
			desde = 1;
			hasta = totalPages;

		} else {
			if (currentPage <= numItemsPPage / 2) {
				desde = 1;
				hasta = numItemsPPage;
			} else if (currentPage >= totalPages - numItemsPPage / 2) {
				desde = totalPages - numItemsPPage + 1;
				hasta = numItemsPPage;
			} else {
				desde = currentPage - numItemsPPage / 2;
				hasta = numItemsPPage;
			}
		}
		
		for(int i=0; i < hasta; i++ ) {
			pages.add(new PageItem(desde + i, currentPage == desde + i));
		}
		
		
	}

	public String getUrl() {
		return url;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<PageItem> getPages() {
		return pages;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}

	public boolean isHasPrevious() {
		return page.hasPrevious();
	}

}

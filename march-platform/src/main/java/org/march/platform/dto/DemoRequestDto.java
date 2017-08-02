package org.march.platform.dto;

import java.util.List;

import org.march.persistence.entity.Pagination;

public class DemoRequestDto {
	
	private Pagination pagination;
	
	private List<Long> ids;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	
}

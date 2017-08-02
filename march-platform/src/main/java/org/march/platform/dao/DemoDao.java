package org.march.platform.dao;

import java.util.List;
import java.util.Map;

import org.march.persistence.dto.PaginationParameterDto;
import org.march.persistence.entity.Pagination;
import org.march.platform.entity.Demo;

public interface DemoDao {
	
	void add(Demo demo);
	
	void delete(Long id);
	
	void update(Demo demo);
	
	Demo getById(Long id);
	
	List<Demo> getAll();
	
	List<Demo> getListByPagination(PaginationParameterDto paginationParameterDto);
	
}

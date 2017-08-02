package org.march.platform.dao.impl;

import java.util.List;
import java.util.Map;

import org.march.persistence.dto.PaginationParameterDto;
import org.march.persistence.entity.Pagination;
import org.march.platform.dao.DemoDao;
import org.march.platform.entity.Demo;
import org.march.platform.persistence.BaseDao;
import org.springframework.stereotype.Repository;

@Repository("demoDao")
public class DemoDaoImpl extends BaseDao<Demo> implements DemoDao {
	
	@Override
	public void add(Demo demo) {
		super.save(demo);
	}

	@Override
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	public void update(Demo demo) {
		super.update(demo);
	}

	@Override
	public Demo getById(Long id) {
		return super.findById(id);
	}

	@Override
	public List<Demo> getAll() {
		return super.findAll();
	}

	@Override
	public List<Demo> getListByPagination(PaginationParameterDto paginationParameterDto) {
		return (List<Demo>) super.getPaginationList(paginationParameterDto);
	}
	
}

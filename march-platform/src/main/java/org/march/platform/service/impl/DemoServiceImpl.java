package org.march.platform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.march.persistence.constant.QueryType;
import org.march.persistence.dto.PaginationParameterDto;
import org.march.platform.common.Utils;
import org.march.platform.dao.DemoDao;
import org.march.platform.dto.DemoDto;
import org.march.platform.dto.DemoRequestDto;
import org.march.platform.entity.Demo;
import org.march.platform.service.DemoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("demoService")
public class DemoServiceImpl implements DemoService {
	
	@Resource
	private DemoDao demoDao;
	
	@Override
	@Transactional
	public void add(Demo demo) {
		demoDao.add(demo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		demoDao.delete(id);
	}

	@Override
	@Transactional
	public void update(Demo demo) {
		demoDao.update(demo);
	}

	@Override
	public DemoDto getById(Long id) {
		return new DemoDto(demoDao.getById(id));
	}

	@Override
	public List<DemoDto> getAll() {
		List<Demo> list = demoDao.getAll();
		List<DemoDto> returnList = new ArrayList<>();
		for (Demo demo: Utils.ifNullReturnEmpty(list)) {
			returnList.add(new DemoDto(demo));
		}
		return returnList;
	}

	@Override
	public List<DemoDto> getListByPagination(DemoRequestDto demoRequestDto) {
		try {
			Map<String, Object> paramMap = new HashMap<>(); 
			paramMap.put("ids", demoRequestDto.getIds());
			PaginationParameterDto paginationParameterDto = new PaginationParameterDto();
			paginationParameterDto.setCriteria(paramMap);
			paginationParameterDto.setPagination(demoRequestDto.getPagination());
			paginationParameterDto.setQueryString("select d from Demo d where id in (:ids)");
			paginationParameterDto.setQueryType(QueryType.HQL);
			return Utils.convertListToTarget(demoDao.getListByPagination(paginationParameterDto), new ArrayList<DemoDto>(), DemoDto.class);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}

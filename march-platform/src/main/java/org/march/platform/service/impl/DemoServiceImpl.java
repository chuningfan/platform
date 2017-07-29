package org.march.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.march.platform.common.Utils;
import org.march.platform.dao.DemoDao;
import org.march.platform.dto.DemoDto;
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

}

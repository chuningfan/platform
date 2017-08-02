package org.march.platform.service;

import java.util.List;

import org.march.platform.dto.DemoDto;
import org.march.platform.dto.DemoRequestDto;
import org.march.platform.entity.Demo;

public interface DemoService {
	
	void add(Demo demo);
	
	void delete(Long id);
	
	void update(Demo demo);
	
	DemoDto getById(Long id);
	
	List<DemoDto> getAll();
	
	List<DemoDto> getListByPagination(DemoRequestDto demoRequestDto);
}

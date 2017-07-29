package org.march.platform.dao;

import java.util.List;

import org.march.platform.entity.Demo;

public interface DemoDao {
	
	void add(Demo demo);
	
	void delete(Long id);
	
	void update(Demo demo);
	
	Demo getById(Long id);
	
	List<Demo> getAll();
	
}

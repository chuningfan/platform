package org.march.platform.persistence;

import org.hibernate.SessionFactory;
import org.march.persistence.dao.HibernateBaseDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao<T> extends HibernateBaseDAO<T> {
	
	@Autowired
	public void setSessionFactoryForBase(SessionFactory sessionFactory){  
	 super.setSessionFactory(sessionFactory);  
	} 
	
}

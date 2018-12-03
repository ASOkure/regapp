package uk.ac.nesc.idsd.model;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("DsdNeoLongDao")
@Transactional
public class DsdNeoLongDaoImpl extends AbstractDao<DsdNeoLong> implements DsdNeoLongDao {
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	
	}
	
	
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	@Transactional(readOnly=true)
	public List<DsdNeoLong> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from DsdNeolong c").list();
	}

	
	public  void save( DsdNeoLong dsdneolong) {
		sessionFactory.getCurrentSession().save(dsdneolong);

	
}

}
	
	
	
	



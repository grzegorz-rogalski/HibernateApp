package javahive.domain.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javahive.domain.RepozytoriumStudent;
import javahive.domain.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;


@Component
public class RepozytoriumStudentImpl implements RepozytoriumStudent {
	private static final String QUERY_STUDENT_LASTNAME = "SELECT s FROM Student s " +
															"WHERE LOWER(s.nazwisko) = :nazwisko"; 
    @PersistenceContext
    private EntityManager entityManager;

		    private static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
		        List<T> r = new ArrayList<T>(c.size());
		        for(Object o: c)
		          r.add(clazz.cast(o));
		        return r;
		    }
    
    @Override
    public List<Student> getStudenciPoNazwisku_HQL(String nazwisko) {
        Session session=entityManager.unwrap(Session.class);
        org.hibernate.Query query = session.createQuery(QUERY_STUDENT_LASTNAME);
        query.setParameter("nazwisko", nazwisko);
      
        return castList(Student.class, query.list()); //session close?
    }

	@Override
	public List<Student> getStudenciPoNazwisku_JPQL(String nazwisko) {
		 javax.persistence.Query query = entityManager.createQuery(QUERY_STUDENT_LASTNAME);
		 query.setParameter("nazwisko", nazwisko); // jak sprwdzić LOWER?
		 
		return castList(Student.class, query.getResultList());
	}

	@Override
	public List<Student> getStudenciPoNazwisku_CRITERIA(String nazwisko) {
		Session session=entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Student.class);
		criteria.add(Restrictions.like("nazwisko",nazwisko));
		//return session.createCriteria(nu).uniqueResult();
		return castList(Student.class, criteria.list());
	}
	
    @Override
    public List<Student> getStudenciPoNazwiskuZaczynajacymSieOdLiter(String nazwisko) {
        return null;
    }
}

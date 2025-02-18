package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Mentor> criteriaQuery = criteriaBuilder.createQuery(Mentor.class);
            Root<Mentor> root = criteriaQuery.from(Mentor.class);
            Predicate agePredicate = criteriaBuilder.greaterThan(root.get("age"), age);
            criteriaQuery.select(root).where(agePredicate);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new IllegalStateException("Cant find by gt: " + age, e);
        }
    }
}

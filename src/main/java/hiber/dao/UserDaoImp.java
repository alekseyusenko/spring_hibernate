package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(int series, String model) {
//      String sql = "select name, last_name from users inner join cars" +
//              " on cars.series = users.car_id where car_id = 1;";
      String hql = "from User user left join fetch user.car " +
              "where user.car.series=:series and user.car.model=:model";
      Session session = null;

      try {
         session = sessionFactory.openSession();
         Query query = session.createQuery(hql);
         query.setParameter("series", series);
         query.setParameter("model", model);
         return (User) query.getResultList().get(0);

      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }

      finally {
         if (session != null)
         try {
             session.close();
         } catch (Exception ignore) {}
      }
   }
}

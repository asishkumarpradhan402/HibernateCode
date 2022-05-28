package tech.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tech.main.model.User;
import tech.main.util.AnnottedClassList;
import tech.main.util.HibernateUtil;

public class App 
{
    public static void main( String[] args )
    {
        Session session = null;
		try {
//			https://www.javatpoint.com/hibernate-configuration
			AnnottedClassList.addClassName(User.class);
			session = HibernateUtil.getSession(AnnottedClassList.getClassNames());
			System.out.println(session.getClass());
			User user1 = new User();
			user1.setUserName("Asish");
			User user2 = new User();
			user2.setUserName("Litu");
			Transaction transaction = session.beginTransaction();
			session.save(user1);
			session.save(user2);
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
    }
}

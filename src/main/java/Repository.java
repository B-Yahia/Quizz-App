import dto.Answers;
import dto.Question;
import dto.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Repository {

    public static void addUserToDB(Users user) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Users.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
    }

    public static List<Question> getListOfQuestions() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Question.class)
                .addAnnotatedClass(Answers.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Question> questionList = session.createQuery("FROM Question",Question.class).getResultList();
        transaction.commit();
        return questionList;
    }

    public static void updateUserDetails(Users userLogged) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Users.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(userLogged);
        transaction.commit();
    }

    public static void addQuestionToDB(Question question) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Question.class)
                .addAnnotatedClass(Answers.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(question.getTheAnswers());
        session.persist(question);
        transaction.commit();
    }

    public static List<Users> getListOfUsers() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Users.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Users> usersList = session.createQuery("FROM Users",Users.class).getResultList();
        transaction.commit();
        return usersList;
    }

    public static void deleteQuestion(Question question) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Question.class)
                .addAnnotatedClass(Answers.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(question);
        session.remove(question.getTheAnswers());
        transaction.commit();
    }
}

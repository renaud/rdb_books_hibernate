package ch.hevs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello Hibenate!");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e){
            System.out.println("woooops: Echec de chargement du pilote Postgres!");
            e.printStackTrace();
            System.exit(17);
        }

        Configuration conf = new Configuration().configure();
        conf.setProperty(Environment.DEFAULT_SCHEMA, "public");
        SessionFactory sessionFactory = conf.buildSessionFactory();


        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String id = "A01";  // primary key value of the first author
        Author a = (Author) session.load(Author.class, id);
        tx.commit();
        session.close();

        System.out.println("Author first name is:" + a.getAuFname());
    }
}
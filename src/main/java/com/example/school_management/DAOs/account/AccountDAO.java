package com.example.school_management.DAOs.account;

import com.example.school_management.models.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class AccountDAO implements IAccountDAO {
    private final SessionFactory sessionFactory;

    public AccountDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean login(Account account) {
        try (Session session = sessionFactory.openSession()) {
            Query<Account> query = session.createQuery(
                    "SELECT a FROM Account a WHERE a.email = :email AND a.password = :password",
                    Account.class
            );
            query.setParameter("email", account.getEmail());
            query.setParameter("password", account.getPassword());
            Account accountCurrent = query.uniqueResult();
            if (accountCurrent != null) {
                return true; // Đăng nhập thành công
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Đăng nhập thất bại
    }

    @Override
    public boolean register(Account account) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Query<Account> query = session.createQuery(
                    "SELECT a FROM Account a WHERE a.email = :email",
                    Account.class
            );
            query.setParameter("email", account.getEmail());
            Account accountCurrent = query.uniqueResult();
            if (accountCurrent != null) {
                throw new Exception("Account already exists");
            } else {
                session.beginTransaction();
                session.save(account);
                session.getTransaction().commit();
                return true; // Đăng ký thành công
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Account already exists");
        }
//        return false; // Đăng ký thất bại
    }
}
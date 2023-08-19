package com.example.school_management.DAOs.account;

import com.example.school_management.DAOs.IGeneralDAO;
import com.example.school_management.models.Account;

public interface IAccountDAO{
    boolean login(Account account);
    boolean register(Account account) throws Exception;
}

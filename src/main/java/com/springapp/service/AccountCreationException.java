package com.springapp.service;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 */
public class AccountCreationException extends Exception {

    public AccountCreationException(String message) {
        super(message);

    }

    public AccountCreationException(Exception cause,String message) {
        super(message,cause);
    }
}

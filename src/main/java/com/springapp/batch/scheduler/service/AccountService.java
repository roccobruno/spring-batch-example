package com.springapp.batch.scheduler.service;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 09:13
 * To change this template use File | Settings | File Templates.
 */
public class AccountService implements IAccountService {


    /**
     * Step da eseguire:
     *
     * 1) read all the records with flag 'WithAccount' false
     * 2) For each record :
     *
     *           2.1) create account invoking rest service
     *           2.2) set the flag 'WithAccount' to true for the record
     *
     *
     *
     *
     *
     */




    @Override
    public void process() {
        System.out.println("CIAO");
    }
}

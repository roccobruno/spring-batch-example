package com.springapp.domain;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 18:46
 * To change this template use File | Settings | File Templates.
 */
public enum ACCOUNT_FLAG_VALUES {
    WITH_ACCOUNT("s"),
    WITHOUT_ACCOUNT("n");

    private String id;


    ACCOUNT_FLAG_VALUES(String n) {
        this.id = n;
    }
    public String getId() {
        return id;
    }
}

package com.kennsyu.li;

import com.sun.jdi.connect.Connector;

/*
アカウントクラス
 */
public class Account {

    private String cardId;
    private String userName;
    private String passWard;
    private double Money;
    private double quotaMoney;

    public Account() {
    }

    public Account(String cardId, String userName, String passWard, double money, double quotaMoney) {
        this.cardId = cardId;
        this.userName = userName;
        this.passWard = passWard;
        Money = money;
        this.quotaMoney = quotaMoney;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWard() {
        return passWard;
    }

    public void setPassWard(String passWard) {
        this.passWard = passWard;
    }

    public double getMoney() {
        return Money;
    }

    public void setMoney(double money) {
        Money = money;
    }

    public double getQuotaMoney() {
        return quotaMoney;
    }

    public void setQuotaMoney(double quotaMoney) {
        this.quotaMoney = quotaMoney;
    }



}

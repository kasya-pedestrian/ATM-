package com.kennsyu.li;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATMSystem {
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();
        Show(accounts);
    }
    public static void Show(ArrayList<Account> accounts){
        System.out.println("================liのATM===============");

        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println("選択してください");
            System.out.println("1.ログイン");
            System.out.println("2.口座開設");
            System.out.println("数字を入力してください:");
            int commend = sc.nextInt();
            switch (commend) {
                case 1:
                    //登録
                    login(accounts);
                    break;
                case 2:
                        //開設
                    register(accounts);
                    break;
                default:
                    System.out.println("入力内容が間違えました");
                }
            }
        }
    //アカウントを開設する
    private static void register(ArrayList<Account> accounts) {
        String UserName ="";
        String password ="";
        String okPassword ="";
        double quotaMoney=0;
        while (true) {
            System.out.println("================新規登録===============");
            System.out.println("お客さまの名前を入力してください");
            Scanner sc = new Scanner(System.in);
            UserName = sc.next();
            System.out.println("暗証番号を入力してください");
            password = sc.next();
            System.out.println("暗証番号をもう一度確認してください");
            okPassword = sc.next();
            System.out.println("1日あたりのカード利用限度額を設定してください");
            quotaMoney = sc.nextDouble();
            if (password.equals(okPassword)){

                System.out.println("口座を成功に開設しました");
                createCardID(accounts);

                break;

            }else{
                System.out.println("二つの入力パスワードが違います");
                System.out.println("入力直してください");
            }
        }
        String cardID =createCardID(accounts);
        Account account=new Account(cardID,UserName,password,0.0,quotaMoney);
        accounts.add(account);
        System.out.println("口座の開設が成功しました、口座の番号は"+account.getCardId());

    }
    public static  String createCardID(ArrayList<Account> accounts){
        while (true){
            String CardID="";
            Random r=new Random();
            for (int i=0;i<8;i++){
                CardID +=r.nextInt(10);
            }
            Account acc=account_Card(CardID,accounts);
            if (acc==null){
                return CardID;
            }
        }

    }
    public static Account account_Card(String CardID,ArrayList<Account> accounts){
        for (int i =0;i<accounts.size();i++){
            Account acc=accounts.get(i);
            if (acc.getCardId().equals(CardID)){
                return  acc;
            }
        }
        return null;
    }
    public static void login(ArrayList<Account> accounts){
        if(accounts.size()==0){
            System.out.println(("システムの中でア口座がないです、開設をお願いします"));
            return;
        }
        while(true) {
            System.out.println("口座番号を入力してください");
            Scanner sc = new Scanner(System.in);
            String CardId = sc.next();
            Account acc =account_Card(CardId, accounts);
            if (acc!=null){
                while(true){
                System.out.println("暗証番号を入力してください");
                String password= sc.next();
                if (acc.getPassWard().equals(password)){
                    //パスワード
                    //登録画面
                    System.out.println("登録成功しました、お客様："+acc.getUserName()+">>口座番号:"+acc.getCardId());
                    ShowUserCommand(acc,accounts);
                    return;
                }else{
                    System.out.println("暗証番号が間違えました、もう一度入力してください");
                }
                }

            }else{
                System.out.println("口座の番号が開設していない、もう一度入力してください");
            }
        }

    }
    public static void ShowUserCommand(Account acc,ArrayList<Account> accounts) {
        System.out.println("================機能一覧===============");

        while (true) {
            System.out.println("1.残高照会");
            System.out.println("2.お預入れ");
            System.out.println("3.お引出し");
            System.out.println("4.振り込み");
            System.out.println("5.暗証番号の変更");
            System.out.println("6.退出");
            System.out.println("7.利用停止");
            System.out.println("操作を選択してください");
            Scanner sc=new Scanner(System.in);
            int Command =sc.nextInt();
            switch (Command){
                case 1:
                    //残高照会
                    ShowAccount(acc);
                    break;
                case 2:
                    //お預入れ
                    depositMoney(acc);
                    break;
                case 3:
                    //お引出し
                    Withdrawal(acc);
                    break;
                case 4:
                    //振り込み
                    TransferMoney(acc,accounts);
                    break;
                case 5:
                    //暗証番号の変更
                    updatePassword(acc);
                    return;
                case 6:
                    //退出
                    System.out.println("退出しています");
                    return;
                case 7:
                    //利用停止
                    accounts.remove(acc);
                    System.out.println("アカウント解約しました");
                    return;
                default:
                    System.out.println("入力が間違えました、再入力してください");
            }
        }
    }

    private static void updatePassword(Account acc) {
        System.out.println("================暗証番号の変更==============");
        while (true) {
            System.out.println("暗証番号を入力してください");
            Scanner sc=new Scanner(System.in);
            String ok_Password =sc.next();
            if (acc.getPassWard().equals(ok_Password)){
                while (true) {
                    System.out.println("新しい暗証番号を入力してください：");
                    String new_Password=sc.next();
                    System.out.println("暗証番号を確認してください：");
                    String newOk_Password=sc.next();
                    if (new_Password.equals(newOk_Password)){
                        acc.setPassWard(new_Password);
                        System.out.println("暗証番号変更成功しました、ログイン直してください");
                        return;
                    }else{
                        System.out.println("二つの入力する暗証番号が違います");
                    }
                }

            }else{
                System.out.println("暗証番号が違います");
            }
        }


    }

    private static void TransferMoney(Account acc, ArrayList<Account> accounts) {
        if(accounts.size()<2){
            System.out.println("システムの中で一つの口座だけがあります、振り込みできないです");
            return;
        }
        if (acc.getMoney()==0){
            System.out.println("残高が0です、まずお預か入れしてください");
        }
        //振り込みします
        while (true) {
            System.out.println("お受取人の口座番号を入力してください:");
            Scanner sc=new Scanner(System.in);
            String cardID=sc.next();
            Account account =account_Card(cardID,accounts);
            if(account!=null){
                //名前の確認
                String name="*"+account.getUserName().substring(1);
                System.out.println("["+name+"]の名前を補足してください");
                String PreName=sc.next();
                if (account.getUserName().startsWith(PreName)){
                    //本番の振り込みを初めます
                    System.out.println("振り込みの金額を入力してください");
                    double money = sc.nextDouble();
                    if (money>acc.getMoney()){
                        System.out.println("残高が足りない、振り込み失敗");
                    }
                    else {
                        acc.setMoney(acc.getMoney()-money);
                        account.setMoney(account.getMoney()+money);
                        System.out.println("振り込み成功しました、"+account.getUserName()+"の口座に"+money+"円振り込みしました");
                        ShowAccount(acc);
                        return;
                    }
                }else{
                    System.out.println("名前が違います＝＝＝＝");
                }


            }else{
                System.out.println("口座番号がございません、もう一度入力してください");
            }
        }


    }

    private static void Withdrawal(Account acc) {
        System.out.println("================引き出し===============");
        if(acc.getMoney()>1000){
            while (true) {
                System.out.println("引き出し金額を入力してください");
                Scanner sc=new Scanner(System.in);
                double DrawlMoney=sc.nextDouble();
                if (DrawlMoney<= acc.getQuotaMoney()){
                    if (DrawlMoney<=acc.getMoney()){
                    acc.setMoney(acc.getMoney()-DrawlMoney);
                    System.out.println("引き出し成功しました、残高:"+acc.getMoney());
                    return;
                    }else {
                        System.out.println("残高が足りないです");
                    }
                }else{
                    System.out.println("引き出し金額が利用限度額を超えました、引き出し失敗しました");
                }
            }
        }else{
            System.out.println("残高で残りが１千円以下で、引き出し出来ないです");
        }

    }


    private static void depositMoney(Account acc) {
        System.out.println("================お預入れ===============");
        System.out.println("お預かり入れの金額を入力してください");
        Scanner sc=new Scanner(System.in);
        double money =sc.nextDouble();
        acc.setMoney(acc.getMoney()+money);
        System.out.println("お預入れ成功しました");
        ShowAccount(acc);
    }

    private static void ShowAccount(Account acc) {
        System.out.println("================残高照会===============");
        System.out.println("お客様:"+acc.getUserName());
        System.out.println("口座番号:"+acc.getCardId());
        System.out.println("残高:"+acc.getMoney());
        System.out.println("今の限度額:"+acc.getQuotaMoney());

    }
}

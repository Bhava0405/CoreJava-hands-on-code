package controller;

import dto.Wallet;
import exception.WalletException;
import service.WalletService;
import service.WalletServiceImpl;

public class Controller {
    public static void main(String[] args) {
        WalletService walletService = new WalletServiceImpl();
        try {
            //registerWallet
            Wallet wallet1 = walletService.registerWallet(new Wallet(1,"priya",45000.0,"1234"));
            Wallet wallet2 = walletService.registerWallet(new Wallet(2,"seetha",35000.0,"AbCd"));
            Wallet wallet3 = walletService.registerWallet(new Wallet(3,"lakshmi",54000.0,"zAyB"));
            walletService.registerWallet(new Wallet(4,"ramu",25000.0,"bBaA"));
            System.out.println(wallet1);
            System.out.println(wallet2);
            System.out.println(wallet3);

            //login
            walletService.login(1,"1234");

            //addFundsToWallet
            walletService.addFundsToWallet(1,20000.0);

            //showWalletBalance
            double wallet4 = walletService.showWalletBalance(1);
            System.out.println(wallet4);

            //fundTransfer
            walletService.fundTransfer(2,3,20000.0);
            double wallet5 = walletService.showWalletBalance(3);
            System.out.println("After transfer new balance " + wallet5);
            double wallet6 = walletService.showWalletBalance(2);
            System.out.println(wallet6);

            //unregister wallet
            walletService.unRegisterWallet(4,"bBaA");
            System.out.println(walletService.showWalletBalance(4));

        } catch (WalletException e) {
            System.out.println(e.getMessage());
        }
    }
}

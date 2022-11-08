package service;

import dto.Wallet;
import exception.WalletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletServiceTest {

    WalletService walletService = new WalletServiceImpl();

    @BeforeEach
    void setUp() {
        System.out.println("create all test data");
    }

    @AfterEach
    void tearDown() {
        System.out.println("clear all test data");
    }

    @Test
    void registerWallet() {
        try {
            Wallet wallet = walletService.registerWallet(new Wallet(10,"bala",10000.0,"1234"));
            assertNotNull(wallet);
            assertEquals(wallet,walletService.registerWallet(wallet));
        } catch (WalletException e) {
            e.printStackTrace();
        }
    }

    @Test
    void login() {
        try {
            Wallet wallet = walletService.registerWallet(new Wallet(10,"abcd",2000.0,"2345"));
            assertNotNull(wallet);
            Boolean wallet1 = walletService.login(10,"2345");
            assertTrue(wallet1);
        } catch (WalletException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addFundsToWallet() {
        try {
            Wallet wallet = walletService.registerWallet(new Wallet(10,"xyz",10000.0,"4545"));
            assertNotNull(wallet);
            walletService.addFundsToWallet(10,1000.0);
            assertEquals(11000.0,wallet.getBalance());
        } catch (WalletException e) {
            e.printStackTrace();
        }
    }

    @Test
    void showWalletBalance() {
        try {
            Wallet wallet = walletService.registerWallet(new Wallet(10,"xyz",10000.0,"1234"));
            assertNotNull(wallet);
            Double wallet1 = walletService.showWalletBalance(10);
            assertEquals(wallet.getBalance(),wallet1);
        } catch (WalletException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showExceptionTest() {
        try {
            Wallet wallet = walletService.registerWallet(new Wallet(10,"abc",10000.0,"1234"));
            assertThrows(WalletException.class,()->walletService.login(11,"123"));
            assertThrows(WalletException.class,()-> walletService.showWalletBalance(200));
            assertThrows(WalletException.class,()->walletService.addFundsToWallet(11,1000.0));
            assertThrows(WalletException.class,()->walletService.fundTransfer(11,12,1000.0));
            assertThrows(WalletException.class,()->walletService.unRegisterWallet(12,"123"));
        } catch (WalletException e) {
            e.printStackTrace();
        }
    }

    @Test
    void fundTransfer() {
        try {
            Wallet wallet = walletService.registerWallet(new Wallet(10,"abc",20000.0,"1234"));
            Wallet wallet1 = walletService.registerWallet(new Wallet(11,"xyz",35000.0,"9876"));
            assertNotNull(wallet);
            assertNotNull(wallet1);
            Boolean wallet2 = walletService.fundTransfer(10,11,10000.0);
            assertTrue(wallet2);
            assertEquals(45000.0,wallet1.getBalance());
        } catch (WalletException e) {
            e.printStackTrace();
        }
    }

    @Test
    void unRegisterWallet() {
        try {
            Wallet wallet = walletService.registerWallet(new Wallet(10,"abc",10000.0,"1234"));
            assertNotNull(wallet);
            walletService.unRegisterWallet(10,"1234");
        } catch (WalletException e) {
            e.printStackTrace();
        }
    }

}
package dao;

import dto.Wallet;
import exception.WalletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletDaoTest {

    WalletDao walletDao = new WalletDaoImpl();

    @BeforeEach
    void setUp() {
        System.out.println("create all test data");
    }

    @AfterEach
    void tearDown() {
        System.out.println("clear all test data");
    }

    @Test
    void addWallet() {
        try {
            Wallet wallet = walletDao.addWallet(new Wallet(10,"abc",10000.0,"1234"));
            assertNotNull(wallet);
            assertEquals(10,wallet.getId());
        } catch (WalletException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getWalletById() {
        try {
            Wallet wallet = walletDao.addWallet(new Wallet(10,"abc",10000.0,"1234"));
            assertNotNull(wallet);
            Wallet wallet1 = walletDao.getWalletById(10);
            assertEquals(10,wallet1.getId());
        } catch (WalletException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateWallet() {
        try {
            Wallet wallet = walletDao.addWallet(new Wallet(10,"abc",2000.0,"9876"));
            assertNotNull(wallet);
            Wallet wallet1 = walletDao.updateWallet(new Wallet(10,"xyz",11000.0,"9876"));
            assertEquals("xyz",wallet1.getName());
        } catch (WalletException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteWalletById() {
        try {
            Wallet wallet = walletDao.addWallet(new Wallet(10,"xyz",20000.0,"3456"));
            assertNotNull(wallet);
            Wallet wallet1 = walletDao.deleteWalletById(10);
            assertNotNull(wallet1);
        } catch (WalletException e) {
            e.printStackTrace();
        }

    }
}
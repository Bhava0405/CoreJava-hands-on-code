package service;

import dto.Wallet;
import exception.WalletException;
import dao.WalletDao;
import dao.WalletDaoImpl;

public class WalletServiceImpl implements WalletService {

    WalletDao walletRepository = new WalletDaoImpl();

    @Override
    public Wallet registerWallet(Wallet newWallet) throws WalletException {
        return this.walletRepository.addWallet(newWallet);
    }

    @Override
    public Boolean login(Integer walletId, String password) throws WalletException {
        Wallet foundWallet = this.walletRepository.getWalletById(walletId);
        if (foundWallet == null)
            throw new WalletException("Wallet Id does not exists to login. Enter the correct login credentials!!!");
        if(!foundWallet.getPassword().equals(password))
            throw new WalletException("Password does not match to login to your account");
        return true;
    }

    @Override
    public Double addFundsToWallet(Integer walletId, Double amount) throws WalletException {
        Wallet foundWallet = this.walletRepository.getWalletById(walletId);
        if (foundWallet == null)
            throw new WalletException("The " + walletId + " wallet does not exists for adding funds to wallet:");
        foundWallet.setBalance(foundWallet.getBalance() + amount);
        Wallet finalUpdateBalance = this.walletRepository.updateWallet(foundWallet);
        return finalUpdateBalance.getBalance();
    }

    @Override
    public Double showWalletBalance(Integer walletId) throws WalletException {
        Wallet foundWallet = this.walletRepository.getWalletById(walletId);
        if (foundWallet == null)
            throw new WalletException("Wallet does not exists for id:" + walletId);
        return foundWallet.getBalance();
    }

    @Override
    public Boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException {
        Wallet fromWallet = this.walletRepository.getWalletById(fromId);
        Wallet toWallet = this.walletRepository.getWalletById(toId);
        if (fromWallet == null)
            throw new WalletException("Wallet does not exists for id: " + fromWallet);
        if (toWallet == null)
            throw new WalletException("Wallet does not exists for id : " + toWallet);
        double fromIdBalance = fromWallet.getBalance();
        double toIdBalance = toWallet.getBalance();
        if (fromIdBalance < amount)
            throw new WalletException("Balance is insufficient to transfer fund");
        fromIdBalance = fromIdBalance - amount;
        fromWallet.setBalance(fromIdBalance);
        this.walletRepository.updateWallet(fromWallet);
        toIdBalance = toIdBalance + amount;
        toWallet.setBalance(toIdBalance);
        this.walletRepository.updateWallet(toWallet);
        return true;
    }

    public Wallet unRegisterWallet(Integer walletId, String password) throws WalletException {

        Wallet foundWallet = this.walletRepository.getWalletById(walletId);
        if(foundWallet == null)
            throw new WalletException("Wallet not found to unregister");

        if(!foundWallet.getPassword().equals(password))
            throw new WalletException("Password doesn't match to unregister your account.");

        Wallet deletedWallet;
        try {
            deletedWallet = this.walletRepository.deleteWalletById(walletId);
        } catch (WalletException e) {

            throw new WalletException(e.getMessage());
        }
        return deletedWallet;
    }
}

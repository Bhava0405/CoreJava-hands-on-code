package dao;

import dto.Wallet;
import exception.WalletException;

import java.util.HashMap;
import java.util.Map;

public class WalletDaoImpl implements WalletDao{
    Map<Integer,Wallet> wallets = new HashMap<>();
    @Override
    public Wallet addWallet(Wallet newWallet) throws WalletException {
        this.wallets.put(newWallet.getId(),newWallet);
        return this.wallets.get(newWallet.getId());
    }

    @Override
    public Wallet getWalletById(Integer walletId) throws WalletException {
        return this.wallets.get(walletId);
    }

    @Override
    public Wallet updateWallet(Wallet updateWallet) throws WalletException {
        this.wallets.replace(updateWallet.getId(), updateWallet);
        return this.wallets.get(updateWallet.getId());
    }

    @Override
    public Wallet deleteWalletById(Integer walletID) throws WalletException {
        if (!this.wallets.containsKey(walletID))
            throw new WalletException("Wallet Id does not exists to delete");
        return this.wallets.remove(walletID);
    }
}

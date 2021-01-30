package com.jblur.acme_client.manager;

import org.shredzone.acme4j.Account;
import org.shredzone.acme4j.AccountBuilder;
import org.shredzone.acme4j.Login;
import org.shredzone.acme4j.Session;
import org.shredzone.acme4j.exception.AcmeException;

import javax.crypto.SecretKey;
import java.net.URI;
import java.net.URL;
import java.security.KeyPair;

public class AccountManager {
    private Account account;
    private Login login;

    public AccountManager(Login login) {
        this.account = login.getAccount();
        this.login = login;
    }

    public AccountManager(KeyPair keyPair, Session session, boolean agreeToTermsOfService)
            throws AcmeException {
        AccountBuilder accountBuilder = new AccountBuilder().useKeyPair(keyPair);
        if(agreeToTermsOfService){
            accountBuilder = accountBuilder.agreeToTermsOfService();
        }
        this.login = accountBuilder.createLogin(session);
        this.account = this.login.getAccount();
    }

    public AccountManager(KeyPair keyPair, Session session, String keyIdentifier,
                          SecretKey macKey, boolean agreeToTermsOfService)
            throws AcmeException {
        AccountBuilder accountBuilder = new AccountBuilder()
                .withKeyIdentifier(keyIdentifier, macKey).useKeyPair(keyPair);
        if(agreeToTermsOfService){
            accountBuilder = accountBuilder.agreeToTermsOfService();
        }
        this.login = accountBuilder.createLogin(session);
        this.account = this.login.getAccount();
    }

    public AccountManager(KeyPair keyPair, Session session, String keyIdentifier,
                          String macKey, boolean agreeToTermsOfService)
            throws AcmeException {
        AccountBuilder accountBuilder = new AccountBuilder()
                .withKeyIdentifier(keyIdentifier, macKey).useKeyPair(keyPair);
        if(agreeToTermsOfService){
            accountBuilder = accountBuilder.agreeToTermsOfService();
        }
        this.login = accountBuilder.createLogin(session);
        this.account = this.login.getAccount();
    }

    public AccountManager(KeyPair keyPair, Session session, URL accountLocationUrl) {
        this.login = session.login(accountLocationUrl, keyPair);
        this.account = this.login.getAccount();
    }

    public Account getAccount() {
        return this.account;
    }

    public Login getLogin(){
        return this.login;
    }

    public void addContact(URI contactURI) throws AcmeException {
        this.account.modify()
                .addContact(contactURI)
                .commit();
    }

    public void changeAccountKeyPair(KeyPair keyPair) throws AcmeException {
        this.account.changeKey(keyPair);
    }

    public void deactivateAccount() throws AcmeException {
        try {
            this.account.deactivate();
        } catch (AcmeException e) {
            if (!e.getMessage().equals("HTTP 202: Accepted")) {
                throw e;
            }
        }
    }

}

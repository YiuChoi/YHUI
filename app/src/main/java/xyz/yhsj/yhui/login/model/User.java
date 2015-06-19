package xyz.yhsj.yhui.login.model;

/**
 * Created by LOVE on 2015/6/19 019.
 */
public class User {

    private String isPass;

    private String info;

    private Account account;

    public String getIsPass() {
        return this.isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

package org.jarivm.relationGraph.services;

/**
 * @author Jari Van Melckebeke
 * @since 08.10.16
 */
public class AuthToken {
    private String loginName;
    private String loginPass;

    public AuthToken() {
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }
}

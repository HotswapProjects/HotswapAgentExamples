package org.hotswap.agent.example.deltaspike.enums;

public enum UserRole {

    ADMIN("A", "userRole.ADMIN"),
    USER("U", "userRole.USER");

    public static UserRole getByCode(String code) {
        if (code != null) {
            for (UserRole en : UserRole.values()) {
                if (code.equals(en.getCode())) {
                    return en;
                }
            }
        }

        return null;
    }

    final String code;
    final String rbKey;

    UserRole(String code, String rbKey) {
        this.code = code;
        this.rbKey = rbKey;
    }

    public String getCode() {
        return code;
    }

    public String getRbKey() {
        return rbKey;
    }

}

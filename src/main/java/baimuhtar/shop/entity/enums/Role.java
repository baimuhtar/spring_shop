package baimuhtar.shop.entity.enums;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("admin", "Администратор"),
    USER("user", "Пользователь");

    private final String serviceName;
    private final String viewName;

    Role(String serviceName, String viewName) {
        this.serviceName = serviceName;
        this.viewName = viewName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getViewName() {
        return viewName;
    }
}

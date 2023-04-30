package baimuhtar.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Table(name = "roles")
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

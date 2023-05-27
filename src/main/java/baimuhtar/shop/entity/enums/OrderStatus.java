package baimuhtar.shop.entity.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;


public enum OrderStatus {
    AVAILABLE("ДОСТУПЕН"),
    SEND("ОТПРАВЛЕН"),
    SOLD("ПРОДАН"),
    CANCELED("ОТМЕНЕН");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}

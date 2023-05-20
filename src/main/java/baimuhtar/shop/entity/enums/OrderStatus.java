package baimuhtar.shop.entity.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

@Getter
public enum OrderStatus {
    AVAILABLE("Доступен"),
    SEND("Отправлен"),
    SOLD("Продан"),
    CANCELED("Отменен");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }
}

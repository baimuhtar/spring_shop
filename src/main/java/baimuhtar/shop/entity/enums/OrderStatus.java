package baimuhtar.shop.entity.enums;

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

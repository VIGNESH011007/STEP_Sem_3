package Week8_Class;
import java.util.*;
public class Problem5{

interface PaymentMethod {
    String getMethodName();
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public String getMethodName() { return "Credit Card"; }
    @Override
    public boolean processPayment(double amount) { return true; } // succeeds
}

class PayPalPayment implements PaymentMethod {
    private final boolean willSucceed;

    public PayPalPayment() { this(false); }
    public PayPalPayment(boolean willSucceed) { this.willSucceed = willSucceed; }

    @Override
    public String getMethodName() { return "PayPal"; }
    @Override
    public boolean processPayment(double amount) { return willSucceed; }
}

class BankTransferPayment implements PaymentMethod {
    @Override
    public String getMethodName() { return "Bank Transfer"; }
    @Override
    public boolean processPayment(double amount) { return true; }
}

class Product {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() { return price; }
}

enum OrderStatus {
    PENDING,
    PAID
}

class Order {
    private final String orderId;
    private final Map<Product, Integer> items = new HashMap<>();
    private OrderStatus status = OrderStatus.PENDING;

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public void addProduct(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public double calculateTotal() {
        double sum = 0;
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            sum += e.getKey().getPrice() * e.getValue();
        }
        return sum;
    }

    public void processPayment(PaymentMethod method) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }
        System.out.println("Payment initiated via " + method.getMethodName() + " for Order " + orderId + ".");
        boolean success = method.processPayment(calculateTotal());
        if (success) {
            this.status = OrderStatus.PAID;
            System.out.println("Payment for Order " + orderId + " successful. Order status: Paid.");
        } else {
            System.out.println("Payment for Order " + orderId + " failed. Order status: Pending.");
        }
    }
}
}

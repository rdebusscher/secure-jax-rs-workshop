package be.rubus.jaxrs.secure.workshop.order;

public class Order {

    private Long userId;
    private long[] products;
    private String address;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long[] getProducts() {
        return products;
    }

    public void setProducts(long[] products) {
        this.products = products;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

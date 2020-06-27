package vn.edu.ntu.dinhtuyen.model;

public class Customer {

    private String customerName, customerPhone;
    private boolean friendly;

    public Customer(String customerName, String customerPhone, boolean friendly) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.friendly = friendly;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public boolean isFriendly() {
        return friendly;
    }

    public void setFriendly(boolean friendly) {
        this.friendly = friendly;
    }
}

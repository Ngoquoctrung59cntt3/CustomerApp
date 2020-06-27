package vn.edu.ntu.dinhtuyen.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customer")
public class CustomerEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "customerName")
    private String customerName;

    @ColumnInfo(name = "customerPhone")
    private String customerPhone;

    @ColumnInfo(name = "friendly")
    private boolean friendly;

    public CustomerEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

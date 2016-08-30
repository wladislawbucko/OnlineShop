package by.expertsoft.butko.phone;

import java.math.BigDecimal;

/**
 * Created by wladek on 28.08.16.
 */
public class PersonalInfo {
    private String firstName;
    private String lastName;
    private String number;
    private BigDecimal deliveryPrice = new BigDecimal(5);
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }
}
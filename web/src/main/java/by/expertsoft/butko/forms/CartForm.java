package by.expertsoft.butko.forms;

import by.expertsoft.butko.forms.CartItemForm;

import java.util.List;

/**
 * Created by wladek on 05.09.16.
 */
public class CartForm {
    private List<CartItemForm> cartItemFormList;

    public List<CartItemForm> getCartItemFormList() {
        return cartItemFormList;
    }

    public void setCartItemFormList(List<CartItemForm> cartItemFormList) {
        this.cartItemFormList = cartItemFormList;
    }
}
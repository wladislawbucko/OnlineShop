package by.expertsoft.butko.web;


import by.expertsoft.butko.cart.AbstractCartItem;
import by.expertsoft.butko.cart.PersonalInfo;
import by.expertsoft.butko.exception.CartNotFoundException;
import by.expertsoft.butko.service.CartService;
import by.expertsoft.butko.service.OrderInformationService;
import by.expertsoft.butko.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by wladek on 26.08.16.
 * session Attribute
 */
@Controller
@RequestMapping("/order")
@SessionAttributes("personalInfo")
public class OrderController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderInformationService orderInformationService;

    @ModelAttribute
    public PersonalInfo populatePersonalInfo(){
        return new PersonalInfo();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ExceptionHandler({CartNotFoundException.class})
    public String getCartList(Map<String, Object> model){
        if(cartService.getCart().getCartSize() == 0){
            throw new CartNotFoundException("Your cart is empty.");
        }
        model.put("cart", cartService.getCart());
        List cartItemNames = orderInformationService.getCartItemNamesList(cartService.getCart());
        model.put("cartItemNames", cartItemNames);
        model.put("deliveryPrice", orderInformationService.getDeliveryPrice(cartService.getCart()));
        return "orderInformationPage";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ExceptionHandler({CartNotFoundException.class})
    public String placeOrder(
            @Valid @ModelAttribute("personalInfo")PersonalInfo personalInfo,
            BindingResult resultPersonalInfo,
            Map<String, Object> model,
            final RedirectAttributes redirectAttributes
    ){
        if(resultPersonalInfo.hasErrors()) {
            model.put("cart", cartService.getCart());
            return "orderInformationPage";
        }else{
            if(cartService.getCart().getCartSize() == 0){
                throw new CartNotFoundException("Your cart is empty.");
            }
            String orderId = orderService.placeOrder(personalInfo);
            cartService.clearCart();
            redirectAttributes.addFlashAttribute("message", "Thank you for order. It was added to the list!");
            return "redirect:/order/confirmation/"+orderId;
        }
    }
}

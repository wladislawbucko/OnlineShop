<%@ page import="by.expertsoft.butko.model.Mobile" %><%--
  Created by IntelliJ IDEA.
  User: wladek
  Date: 12.08.16
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $(".add_shop").on('click', function () {
                var mobile = $(this).parent('.mobile_shop');

                var amount = mobile.find("[name=amount]").val();
                var productId = mobile.find("[name=productId]").val();
                var cost = mobile.find("[name=cost]").val();
                var name = mobile.find("[name=name]").val();

                $.ajax({
                    type: "POST",
                    url: "/web/cart",
                    data: "amount=" + amount + "&productId=" + productId + "&cost=" + cost + "&name=" + name,
                    success: function (response) {
                        $('#headerTotal').html("${sessionScope.cart.getTotalAmount()} :Amount; ${sessionScope.cart.getTotal()}: Total");
                        mobile.find("[name=info]").html(response);
                    },
                    error: function (e) {
                        alert('Error: ' + e);
                    }
                });
            });
        });
    </script>
        <div class="container-fluid">
            <c:forEach var="product" items="${productList}" varStatus="count">
                <div class="list-group-item">
                    <div class="row">
                        <div class="col-lg-5 col-md-5">
                            <c:out value="${count.index+1}  " />
                            <a href="<s:url value="/mobilephones/model?id=${product.getId()}"/>"> ${product.getName()} </a>
                        </div>
                        <div class="col-lg-4 col-lg-offset-3 col-md-5 col-md-offset-2 mobile_shop">
                            <!--<sf:form method="post" action="cart" class="mobile_shop" modelAttribute="cartItem">
                                <input name="amount" size="10" placeholder="amount" type="number" min="1">
                                <button class="add">add to cart</button>
                                <input type="hidden" name="productId" value="${product.getId()}">
                                <input type="hidden" name="name" value="${product.getName()}">
                                <input type="hidden" name="cost" value="${product.getCost()}">
                                <div id="info" style="color: green;"></div>
                            </sf:form>-->
                            <input name="amount" size="10" placeholder="amount" type="number" min="1">
                            <sf:errors path="amount" cssClass="error" />
                            <button class="add_shop">add to cart</button>
                            <input type="hidden" name="productId" value="${product.getId()}">
                            <input type="hidden" name="name" value="${product.getName()}">
                            <div name="info" style="color: green;"></div>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
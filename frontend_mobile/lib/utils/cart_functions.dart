import 'package:flutter/material.dart';
import 'package:frontend_mobile/dto/cart.dart';
import 'package:frontend_mobile/dto/cart_item.dart';
import 'package:frontend_mobile/dto/order.dart';
import 'package:frontend_mobile/dto/order_item.dart';
import 'package:frontend_mobile/dto/product.dart';
import 'package:frontend_mobile/dto/user.dart';
import 'cart_functions.dart';
import 'package:frontend_mobile/pages/login_page.dart';
import 'package:frontend_mobile/pages/register_page.dart';
import 'package:frontend_mobile/pages/catalog_page.dart';

class CartFunctions extends ChangeNotifier {
  static String url_host = "http://10.0.2.2/api/cart";

  void addToCart(Product product) {

  }

  void buyNow(Product product) {

  } 
  Cart getCartDetails(Customer customer) {
    //belom
    return Cart(
      id: customer.cartId,
      user: customer,
      cartItems: [],
    );
  }
}

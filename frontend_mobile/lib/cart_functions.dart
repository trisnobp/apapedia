import 'cart.dart';
import 'product.dart';
import 'package:flutter/material.dart';
import 'package:frontend_mobile/cart.dart';
import 'package:frontend_mobile/cart_item.dart';
import 'package:frontend_mobile/order.dart';
import 'package:frontend_mobile/order_item.dart';
import 'package:frontend_mobile/product.dart';
import 'package:frontend_mobile/user.dart';
import 'cart_functions.dart';
import 'package:frontend_mobile/login_page.dart';
import 'package:frontend_mobile/register_page.dart';
import 'package:frontend_mobile/catalog_page.dart';

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
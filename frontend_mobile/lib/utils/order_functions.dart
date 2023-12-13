import 'package:flutter/material.dart';
import 'package:frontend_mobile/dto/order.dart';
import 'package:frontend_mobile/dto/user.dart';

class OrderFunctions extends ChangeNotifier {
  static String url_host = "http://10.0.2.2/api/order";
  
  List<Order> getOrderListForCustomer(Customer customer) {
    //belom
    return [];
  }
}

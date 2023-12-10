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
import 'order_functions.dart';

class OrderHistoryPage extends StatefulWidget {
  final Customer customer;

  OrderHistoryPage({Key? key, required this.customer}) : super(key: key);

  @override
  _OrderHistoryPageState createState() => _OrderHistoryPageState();
}

class _OrderHistoryPageState extends State<OrderHistoryPage> {
  List<Order> orderList = [];

  @override
  void initState() {
    super.initState();
    _getOrders();
  }

  void _getOrders() {
    // orderList = getOrderListForCustomer(widget.customer);
  }

  void _updateOrderStatus(Order order) {
    // setState(() => order.status = newStatus);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Order History'),
      ),
      body: ListView.builder(
        itemCount: orderList.length,
        itemBuilder: (context, index) {
          Order order = orderList[index];
          return Card(
            child: ListTile(
              title: Text('Order #${order.id}'),
              subtitle: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text('Created At: ${order.createdAt.toString()}'),
                  Text('Status: ${order.status.toString().split('.').last}'),
                  Text('Total: \$${order.total.toStringAsFixed(2)}'),
                ],
              ),
              trailing: ElevatedButton(
                onPressed: () => _updateOrderStatus(order),
                child: Text('Update Status'),
              ),
            ),
          );
        },
      ),
    );
  }
}
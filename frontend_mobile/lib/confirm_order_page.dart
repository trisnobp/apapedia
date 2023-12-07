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
import 'package:frontend_mobile/order_history_page.dart';
import 'package:frontend_mobile/cart_page.dart';

class ConfirmOrderPage extends StatefulWidget {
  final Customer customer;
  final Order order;

  ConfirmOrderPage({Key? key, required this.customer, required this.order}) : super(key: key);

  @override
  _ConfirmOrderPageState createState() => _ConfirmOrderPageState();
}

class _ConfirmOrderPageState extends State<ConfirmOrderPage> {
  late double total;

  @override
  void initState() {
    super.initState();
    total = calculateTotal(widget.order.orderItems);
  }

  double calculateTotal(List<OrderItem> orderItems) {
    return orderItems.fold(0, (sum, item) => sum + item.price * item.quantity);
  }

  void _confirmOrderAndNavigate() {
    // tambahin order ke db etc
    Navigator.pushReplacement(context, // Use pushReplacement to prevent going back to confirm page
      MaterialPageRoute(builder: (context) => OrderHistoryPage(customer: widget.customer)),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Confirm Order Page'),
      ),
      body: Column(
        children: <Widget>[
          Expanded(
            child: ListView.builder(
              itemCount: widget.order.orderItems.length,
              itemBuilder: (context, index) {
                var item = widget.order.orderItems[index];
                return ListTile(
                  title: Text(item.name),
                  subtitle: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text('Price: ${item.price}'),
                      Text('Quantity: ${item.quantity}'),
                      Text('Total: ${item.price * item.quantity}'),
                    ],
                  ),
                );
              },
            ),
          ),
          Padding(
            padding: EdgeInsets.all(16.0),
            child: Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    Text('Total Price:'),
                    Text('$total'),
                  ],
                ),
                SizedBox(height: 20),
                ElevatedButton(
                  onPressed: _confirmOrderAndNavigate,
                  child: Text('Confirm Order'),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

import 'package:flutter/material.dart';
import 'package:frontend_mobile/dto/cart.dart';
import 'package:frontend_mobile/dto/cart_item.dart';
import 'package:frontend_mobile/dto/order.dart';
import 'package:frontend_mobile/dto/order_item.dart';
import 'package:frontend_mobile/dto/product.dart';
import 'package:frontend_mobile/dto/user.dart';
import 'package:frontend_mobile/pages/login_page.dart';
import 'package:frontend_mobile/pages/register_page.dart';
import 'package:frontend_mobile/pages/catalog_page.dart';
import 'package:frontend_mobile/pages/order_history_page.dart';
import 'package:frontend_mobile/pages/cart_page.dart';

class ConfirmOrderPage extends StatefulWidget {

  ConfirmOrderPage({Key? key}) : super(key: key);

  @override
  _ConfirmOrderPageState createState() => _ConfirmOrderPageState();
}

class _ConfirmOrderPageState extends State<ConfirmOrderPage> {
  late double total;

  @override
  void initState() {
    super.initState();
    //total = widget.order.calculateAndUpdateTotal();
  }

  void _confirmOrderAndNavigate() {
    // tambahin order ke db etc
    Navigator.pushReplacement(context,
      MaterialPageRoute(builder: (context) => OrderHistoryPage()),
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
              //itemCount: widget.order.orderItems.length,
              itemBuilder: (context, index) {
                //var item = widget.order.orderItems[index];
                return ListTile(
                  title: Text("Barang"),
                  subtitle: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text('Price: Rp50000'),
                      Text('Quantity: 30'),
                      Text('Total: 40'),
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

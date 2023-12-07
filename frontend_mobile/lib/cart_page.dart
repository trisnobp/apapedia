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
import 'confirm_order_page.dart';

class CartPage extends StatefulWidget {
  final Customer customer;

  CartPage({Key? key, required this.customer}) : super(key: key);

  @override
  _CartPageState createState() => _CartPageState();
}

class _CartPageState extends State<CartPage> {
  late Cart cart;

  @override
  void initState() {
    super.initState();
    //ambil cart dia skrg dr cart_functions
    cart = getCartDetails(widget.customer);
  }

  void _updateQuantity(int cartItemId, int newQuantity) { //buat update
    setState(() {
      CartItem cartItem = cart.cartItems.firstWhere((item) => item.id == cartItemId);
      cartItem.quantity = newQuantity;
    });
  }

  void _navigateToConfirmOrder() {
    List<OrderItem> orderItems = cart.cartItems.map((cartItem) {
      return OrderItem(
        id: cartItem.product.id,
        name: cartItem.product.name,
        quantity: cartItem.quantity,
        price: cartItem.product.price,
      );
    }).toList();


    Order order = Order(
      id: DateTime.now().millisecondsSinceEpoch,
      customer: widget.customer,
      seller: Seller(),
      status: OrderStatus.Pending,
      createdAt: DateTime.now(),
      updatedAt: DateTime.now(),
      orderItems: orderItems,
    );

    Navigator.push(context, MaterialPageRoute(builder: (context) => ConfirmOrderPage(customer: widget.customer, order: order)));
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Your Cart'),
      ),
      body: ListView.builder(
        itemCount: cart.cartItems.length,
        itemBuilder: (context, index) {
          CartItem item = cart.cartItems[index];
          return ListTile(
            title: Text(item.product.name),
            subtitle: Text('Quantity: ${item.quantity}'),
            trailing: Row(
              mainAxisSize: MainAxisSize.min,
              children: [
                IconButton(
                  icon: Icon(Icons.remove),
                  onPressed: () => _updateQuantity(item.id, item.quantity - 1), //kalo kurang
                ),
                IconButton(
                  icon: Icon(Icons.add),
                  onPressed: () => _updateQuantity(item.id, item.quantity + 1), //kalo nambah
                ),
              ],
            ),
          );
        },
      ),
      bottomNavigationBar: BottomAppBar(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.end,
          children: <Widget>[
            ElevatedButton(
              onPressed: _navigateToConfirmOrder,
              child: Text('Order Now'),
            ),
          ],
        ),
      ),
    );
  }
}



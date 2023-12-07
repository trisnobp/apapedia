import 'package:flutter/material.dart';
import 'package:frontend_mobile/cart.dart';
import 'package:frontend_mobile/cart_item.dart';
import 'package:frontend_mobile/order.dart';
import 'package:frontend_mobile/order_item.dart';
import 'package:frontend_mobile/product.dart';
import 'package:frontend_mobile/profile_page.dart';
import 'package:frontend_mobile/user.dart';
import 'cart_functions.dart';
import 'package:frontend_mobile/login_page.dart';
import 'package:frontend_mobile/register_page.dart';
import 'package:frontend_mobile/catalog_page.dart';
import 'package:frontend_mobile/product_detail_page.dart';
import 'package:frontend_mobile/cart_page.dart';
import 'confirm_order_page.dart';
import 'package:frontend_mobile/order_history_page.dart';
import 'edit_profile_page.dart';

//global variables
bool isLoggedIn = false;
bool isRegistered = false;

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'APAPEDIA',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
      ),
      initialRoute: '/catalog',
      routes: {
        '/login': (context) => LoginPage(),
        '/register': (context) => RegisterPage(),
        '/detail': (context) => ProductDetailPage(
          product: product // INI PRODUCT GANGERTI
        ),
        '/catalog': (context) => CatalogPage(
          products: []
        ),
        '/order': (context) => OrderHistoryPage(customer: null),
        '/cart': (context) => CartPage(customer: null),
        '/confirm': (context) => ConfirmOrderPage(customer: null, order: null),
        '/profile' : (context) => ProfilePage(customer: null),
        '/edit' : (context) => EditProfilePage(customer: null),
      },
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.blue,
        title: Text('Home'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headline6,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}

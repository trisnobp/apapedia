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
import 'package:frontend_mobile/product_detail_page.dart';
import 'package:frontend_mobile/cart_page.dart';

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
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
      ),
      initialRoute: '/catalog',
      routes: {
        '/login': (context) => LoginPage(),
        '/register': (context) => RegisterPage(),
        '/homePage': (context) => MyHomePage(),
        '/productDetail': (context) => ProductDetailPage(
          product: product
        ),
        '/catalog': (context) => CatalogPage(
          products: []
        ),
        '/orderHistory': (context) => OrderHistoryPage(),
        '/cart': (context) => CartPage(),
        '/confirmOrder': (context) => ConfirmOrderPage(),
      },
    );
  }
}

// class ConfirmOrderPage extends StatelessWidget {
//   final List<Product> selectedProducts; // List produk yang ingin dipesan
//   final double totalCost; // Total biaya pesanan
//   final double userBalance; // Saldo pengguna
//
//   ConfirmOrderPage({
//     required this.selectedProducts,
//     required this.totalCost,
//     required this.userBalance,
//   });
//
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       appBar: AppBar(
//         title: Text('Confirm Order'),
//       ),
//       body: Padding(
//         padding: const EdgeInsets.all(16.0),
//         child: Column(
//           crossAxisAlignment: CrossAxisAlignment.start,
//           children: [
//             Text(
//               'Ringkasan Pesanan:',
//               style: TextStyle(
//                 fontSize: 18,
//                 fontWeight: FontWeight.bold,
//               ),
//             ),
//             SizedBox(height: 8),
//             // Menampilkan ringkasan pesanan
//             _buildOrderSummary(),
//             SizedBox(height: 16),
//             Text(
//               'Total Biaya: \$${totalCost.toStringAsFixed(2)}',
//               style: TextStyle(
//                 fontSize: 16,
//                 fontWeight: FontWeight.bold,
//               ),
//             ),
//
//             SizedBox(height: 16),
//             // Tombol "Confirm Order"
//             ElevatedButton(
//               onPressed: () {
//                 // Implementasikan logika untuk mengkonfirmasi pesanan
//                 if (totalCost <= userBalance) {
//                   _confirmOrder(context);
//                 } else {
//                   // Saldo tidak mencukupi
//                   _showInsufficientBalanceDialog(context);
//                 }
//               },
//               child: Text('Confirm Order'),
//             ),
//           ],
//         ),
//       ),
//     );
//   }
//
//   // Fungsi untuk menampilkan ringkasan pesanan
//   Widget _buildOrderSummary() {
//     return Column(
//       crossAxisAlignment: CrossAxisAlignment.start,
//       children: selectedProducts.map((product) {
//         return Text(
//           '${product.name} - \$${product.price.toStringAsFixed(2)}',
//           style: TextStyle(fontSize: 16),
//         );
//       }).toList(),
//     );
//   }
//
//   // Fungsi untuk bayar
//   void _confirmOrder(BuildContext context) {
//     // Belom BE
//
//     Navigator.of(context).pushReplacementNamed('/orderHistory');
//   }
//
//
//   void _showInsufficientBalanceDialog(BuildContext context) {
//     showDialog(
//       context: context,
//       builder: (BuildContext context) {
//         return AlertDialog(
//           title: Text('Saldo Tidak Mencukupi'),
//           content: Text('Saldo Anda tidak mencukupi untuk melakukan pesanan ini.'),
//           actions: <Widget>[
//             TextButton(
//               onPressed: () {
//                 Navigator.of(context).pop();
//               },
//               child: Text('OK'),
//             ),
//           ],
//         );
//       },
//     );
//   }
// }

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

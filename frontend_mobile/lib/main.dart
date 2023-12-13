import 'package:flutter/material.dart';
import 'package:frontend_mobile/dto/cart.dart';
import 'package:frontend_mobile/dto/cart_item.dart';
import 'package:frontend_mobile/dto/order.dart';
import 'package:frontend_mobile/dto/order_item.dart';
import 'package:frontend_mobile/dto/product.dart';
import 'package:frontend_mobile/pages/customer_page.dart';
import 'package:frontend_mobile/pages/profile_page.dart';
import 'package:frontend_mobile/dto/user.dart';
import 'package:frontend_mobile/pages/login_page.dart';
import 'package:frontend_mobile/pages/register_page.dart';
import 'package:frontend_mobile/pages/catalog_page.dart';
import 'package:frontend_mobile/pages/product_detail_page.dart';
import 'package:frontend_mobile/pages/cart_page.dart';
import 'package:frontend_mobile/utils/cart_functions.dart';
import 'package:frontend_mobile/utils/catalog_functions.dart';
import 'package:frontend_mobile/utils/order_functions.dart';
import 'package:frontend_mobile/utils/user_functions.dart';
import 'pages/confirm_order_page.dart';
import 'package:frontend_mobile/pages/order_history_page.dart';
import 'pages/edit_profile_page.dart';
import 'package:provider/provider.dart';

//global variables
bool isLoggedIn = false;
bool isRegistered = false;
const primaryColor = Colors.grey;

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
          ChangeNotifierProvider(create: (_) => UserFunctions()),
          ChangeNotifierProvider(create: (_) => OrderFunctions()),
          ChangeNotifierProvider(create: (_) => CatalogFunctions()),
          ChangeNotifierProvider(create: (_) => CartFunctions()),
        ],
      child: SafeArea(
        child: MaterialApp(
          title: 'APAPEDIA',
          theme: ThemeData(
            primaryColor: primaryColor,
            useMaterial3: true,
          ),
          initialRoute: '/customer',
          routes: {
            '/login': (context) => LoginPage(),
            '/register': (context) => RegisterPage(),
            '/detail': (context) => ProductDetailPage(),
            '/customer': (context) => const CustomerPage(idx: 0),
            '/confirm': (context) => ConfirmOrderPage(),
            '/edit': (context) => EditProfilePage(),
          },
        ),
      ),
    );
  }
}

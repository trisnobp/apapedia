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

class ProductDetailPage extends StatelessWidget {
  //final Product product;

  ProductDetailPage({Key? key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Product Detail'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            SizedBox(height: 20),
            Text('ID: 1'),
            Text('Name: Baju'),
            Text('Description: Bagus'),
            Text('Price: '),
            Text('Category: Pakaian dan Aksesoris'),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                ;
              },
              child: Text('Add To Cart'),
            ),
            ElevatedButton(
              onPressed: () {
                ;
              },
              child: Text('Buy Now'),
            ),
          ],
        ),
      ),
    );
  }
}

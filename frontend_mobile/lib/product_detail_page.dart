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

class ProductDetailPage extends StatelessWidget {
  final Product product;

  ProductDetailPage({required this.product});

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
            Image.asset(
              product.image,
              width: 200,
              height: 200,
              fit: BoxFit.cover,
            ),
            SizedBox(height: 20),
            Text('ID: ${product.id}'),
            Text('Name: ${product.name}'),
            Text('Description: ${product.description}'),
            Text('Price: \$${product.price.toStringAsFixed(2)}'),
            Text('Category: ${product.category.toString().split('.').last}'),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                addToCart(product);
              },
              child: Text('Add To Cart'),
            ),
            ElevatedButton(
              onPressed: () {
                buyNow(product);
              },
              child: Text('Buy Now'),
            ),
          ],
        ),
      ),
    );
  }
}

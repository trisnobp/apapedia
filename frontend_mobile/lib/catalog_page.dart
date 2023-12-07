import 'package:flutter/material.dart';
import 'main.dart';
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


class CatalogPage extends StatelessWidget {
  final List<Product> products;

  CatalogPage({required this.products});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('APAPEDIA Catalog'),
      ),
      body: ListView.builder(
        itemCount: products.length,
        itemBuilder: (context, index) {
          var product = products[index];
          return Card(
            child: ListTile(
              title: Text(product.name),
              subtitle: Text('\$${product.price}'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => ProductDetailPage(product: product),
                  ),
                );
              },
              trailing: _buildButtons(product),
              leading: Image.asset(
                product.image,
                width: 80,
                height: 80,
                fit: BoxFit.cover,
              ),
            ),
          );
        },
      ),
    );
  }

  Widget _buildButtons(Product product) {
    if (!isLoggedIn) {
      return Container();
    }

    return Row(
      mainAxisSize: MainAxisSize.min,
      children: [
        IconButton(
          icon: Icon(Icons.add_shopping_cart),
          onPressed: () {
            addToCart(product);
          },
        ),
        IconButton(
          icon: Icon(Icons.payment),
          onPressed: () {
            buyNow(product);
          },
        ),
      ],
    );
  }
}
import 'user.dart';class Cart {
  final int id;
  final User user;
  final List<CartItem> cartItems;

  Cart({
    required this.id,
    required this.user,
    required this.cartItems,
  });
}

import 'cart_item.dart';


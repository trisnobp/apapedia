import 'user.dart';
import 'order_item.dart';

class Order {
  final int id;
  final User customer;
  final User seller;
  final OrderStatus status;
  final DateTime createdAt;
  final DateTime updatedAt;
  final List<OrderItem> orderItems;

  Order({
    required this.id,
    required this.customer,
    required this.seller,
    required this.status,
    required this.createdAt,
    required this.updatedAt,
    required this.orderItems,
  });
}

enum OrderStatus {
  Pending,
  Shipped,
  Delivered,
  Cancelled,
}

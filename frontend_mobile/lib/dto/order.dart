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
  double total;

  Order({
    required this.id,
    required this.customer,
    required this.seller,
    required this.status,
    required this.createdAt,
    required this.updatedAt,
    required this.orderItems,
    this.total = 0.0,
  });

  double calculateAndUpdateTotal() {
    total = orderItems.fold(0.0, (sum, item) => sum + item.price * item.quantity);
    return total;
  }

}

enum OrderStatus {
  BarangDiterima,
  Selesai,
}

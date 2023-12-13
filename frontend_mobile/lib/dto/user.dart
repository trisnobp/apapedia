class User {
  final int id;
  final String name;
  final String username;
  final String password;
  final String email;
  double balance;
  final String address;
  final DateTime createdAt;
  final DateTime updatedAt;

  User({
    required this.id,
    required this.name,
    required this.username,
    required this.password,
    required this.email,
    required this.address,
    required this.createdAt,
    required this.updatedAt,
    this.balance = 0.0,
  });
}

enum SellerCategory {
  Biasa,
  OfficialStore,
}

class Seller extends User {
  final SellerCategory category;

  Seller({
    required int id,
    required String name,
    required String username,
    required String password,
    required String email,
    required String address,
    required DateTime createdAt,
    required DateTime updatedAt,
    required this.category,

  }) : super(
    id: id,
    name: name,
    username: username,
    password: password,
    email: email,
    address: address,
    createdAt: createdAt,
    updatedAt: updatedAt,
  );
}

class Customer extends User {
  final int cartId;

  Customer({
    required int id,
    required String name,
    required String username,
    required String password,
    required String email,
    required String address,
    required DateTime createdAt,
    required DateTime updatedAt,
    required this.cartId,
  }) : super(
    id: id,
    name: name,
    username: username,
    password: password,
    email: email,
    address: address,
    createdAt: createdAt,
    updatedAt: updatedAt,
  );
}

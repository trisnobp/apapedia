class LoginResponse{
  LoginResponse({
    required this.status,
    required this.message,
    required this.token
  });

  bool? status;
  String? message;
  String? token;

  factory LoginResponse.fromJson(Map<String, dynamic> json) => LoginResponse(
    status: json["status"],
    message: json["message"],
    token: json["token"]
  );
}
class RegisterResponse{
  RegisterResponse({
    required this.status,
    required this.message
  });

  bool? status;
  String? message;

  factory RegisterResponse.fromJson(Map<String, dynamic> json) => RegisterResponse(
    status: json["status"],
    message: json["message"]
  );
}
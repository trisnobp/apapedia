import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:frontend_mobile/dto/response/login_response.dart';
import 'package:frontend_mobile/dto/response/register_response.dart';
import 'package:http/http.dart' as http;

class UserFunctions extends ChangeNotifier {
  static String url_host_auth = "http://10.0.2.2:8080/api/account";
  static String url_host_user = "http://10.0.2.2:8080/api/user";

  Future<bool> isLoggedIn() async {
    final storage = FlutterSecureStorage();
    String? token = await storage.read(key: "token");
    return token != null;
  }

  Future<String?> getToken() async {
    final storage = FlutterSecureStorage();
    return await storage.read(key: "token");
  }

  Future<void> setToken(String token) async {
    final storage = FlutterSecureStorage();
    await storage.write(key: "token", value: token);
  }

  Future<RegisterResponse> register(dynamic data) async {
    var response = await http.post(Uri.parse(url_host_auth + "/register"),
        headers: {
          "Accept": "application/json",
          "Content-Type": "application/json"
        },
        body: jsonEncode(data));

    return RegisterResponse.fromJson(jsonDecode(response.body));
  }

  Future<LoginResponse> login(dynamic data) async {
    var response = await http.post(Uri.parse(url_host_auth + "/customerLogin"),
        headers: {
          "Accept": "application/json",
          "Content-Type": "application/json"
        },
        body: jsonEncode(data));

    var mappedData = LoginResponse.fromJson(jsonDecode(response.body));
    // check if the token is not null (succcessful)
    if (mappedData.token != null) {
      setToken(mappedData.token!);
    }

    return mappedData;
  }
}

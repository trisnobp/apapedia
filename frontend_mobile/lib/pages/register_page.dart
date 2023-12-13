import 'package:another_flushbar/flushbar.dart';
import 'package:flutter/material.dart';
import 'package:frontend_mobile/utils/user_functions.dart';
import 'package:provider/provider.dart';
import '../main.dart';

class RegisterPage extends StatefulWidget {
  @override
  _RegisterPageState createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  TextEditingController _usernameController = TextEditingController();
  TextEditingController _passwordController = TextEditingController();
  TextEditingController _nameController = TextEditingController();
  TextEditingController _emailController = TextEditingController();
  TextEditingController _addressController = TextEditingController();
  GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey();

  showLoading(BuildContext context) {
    return showDialog(
        context: context,
        barrierDismissible: false,
        builder: (BuildContext context) {
          return const Center(
            child: CircularProgressIndicator(),
          );
        });
  }

  @override
  Widget build(BuildContext context) {
    final request = Provider.of<UserFunctions>(context);
    return SafeArea(
      child: Scaffold(
        key: _scaffoldKey,
        appBar: AppBar(),
        body: Padding(
          padding: EdgeInsets.all(16.0),
          child: SingleChildScrollView(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text(
                  'APAPEDIA',
                  style: TextStyle(
                    color: Colors.orange,
                    fontFamily: 'Poppins',
                    fontSize: 48,
                    fontWeight: FontWeight.w700,
                  ),
                ),
                SizedBox(height: 30),
                Text(
                  'Sign Up',
                  style: TextStyle(
                    color: Colors.black,
                    fontFamily: 'Poppins',
                    fontSize: 36,
                    fontWeight: FontWeight.w500,
                  ),
                ),
                SizedBox(height: 20),
                Container(
                  padding: EdgeInsets.symmetric(horizontal: 48.0),
                  width: double.infinity, // Expand the container to full width
                  height: 60, // Fixed height for text fields
                  child: TextField(
                    keyboardType: TextInputType.emailAddress,
                    controller: _nameController,
                    decoration: InputDecoration(
                      labelText: 'Nama',
                    ),
                  ),
                ),
                SizedBox(height: 20),
                Container(
                  padding: EdgeInsets.symmetric(horizontal: 48.0),
                  width: double.infinity, // Expand the container to full width
                  height: 60, // Fixed height for text fields
                  child: TextField(
                    controller: _usernameController,
                    decoration: InputDecoration(
                      labelText: 'Username',
                    ),
                    style: TextStyle(
                      fontFamily: 'Poppins',
                      fontSize: 16,
                      fontWeight: FontWeight.w400,
                    ),
                  ),
                ),
                SizedBox(height: 20),
                Container(
                  padding: EdgeInsets.symmetric(horizontal: 48.0),
                  width: double.infinity, // Expand the container to full width
                  height: 60, // Fixed height for text fields
                  child: TextField(
                    keyboardType: TextInputType.emailAddress,
                    controller: _emailController,
                    decoration: InputDecoration(
                      labelText: 'Email address',
                      hintText: 'name@gmail.com',
                    ),
                  ),
                ),
                SizedBox(height: 20),
                Container(
                  padding: EdgeInsets.symmetric(horizontal: 48.0),
                  width: double.infinity, // Expand the container to full width
                  height: 60, // Fixed height for text fields
                  child: TextField(
                    controller: _passwordController,
                    obscureText: true,
                    decoration: InputDecoration(
                      labelText: 'Password',
                    ),
                    style: TextStyle(
                      fontFamily: 'Poppins',
                      fontSize: 16,
                      fontWeight: FontWeight.w400,
                    ),
                  ),
                ),
                SizedBox(height: 20),
                Container(
                  padding: EdgeInsets.symmetric(horizontal: 48.0),
                  width: double.infinity, // Expand the container to full width
                  height: 60, // Fixed height for text fields
                  child: TextField(
                    controller: _addressController,
                    decoration: InputDecoration(
                      labelText: 'Address',
                    ),
                    style: TextStyle(
                      fontFamily: 'Poppins',
                      fontSize: 16,
                      fontWeight: FontWeight.w400,
                    ),
                  ),
                ),
                SizedBox(height: 20),
                Container(
                  padding: EdgeInsets.symmetric(horizontal: 48.0),
                  width: double.infinity, // Expand the  to full width
                  height: 50, // Fixed height for the button
                  child: ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      primary: Colors.black,
                      onPrimary: Colors.white,
                    ),
                    onPressed: () async {
                      showLoading(context);

                      final response = await request.register({
                        "name": _nameController.text,
                        "username": _usernameController.text,
                        "email": _emailController.text,
                        "password": _passwordController.text,
                        "address": _addressController.text
                      });

                       Navigator.pop(context);

                      if (response.status == true) {
                        Navigator.pushNamed(context, "/login");
                        Flushbar(
                          backgroundColor:
                              const Color.fromARGB(255, 29, 167, 86),
                          flushbarPosition: FlushbarPosition.TOP,
                          title: "Berhasil",
                          duration: const Duration(seconds: 3),
                          message: response.message,
                        ).show(context);
                      } else {
                        Navigator.pushNamed(context, "/register");
                        Flushbar(
                          backgroundColor:
                              const Color.fromARGB(255, 244, 105, 77),
                          flushbarPosition: FlushbarPosition.TOP,
                          title: "Gagal",
                          duration: const Duration(seconds: 3),
                          message: response.message,
                        ).show(context);
                      }
                    },
                    child: Text('Sign up'),
                  ),
                ),
                SizedBox(height: 20),
                TextButton(
                  onPressed: () {
                    // Navigate to the login page when the link is pressed
                    Navigator.pushNamed(context, "/login");
                  },
                  child: const Text(
                    'Already have an account? Log in',
                    style: TextStyle(
                      color: Colors.orange,
                      fontFamily: 'Poppins',
                      fontSize: 12,
                      fontWeight: FontWeight.w400,
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

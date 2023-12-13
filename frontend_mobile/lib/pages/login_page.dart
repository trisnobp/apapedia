import 'package:flutter/material.dart';
import 'package:frontend_mobile/utils/user_functions.dart';
import 'package:provider/provider.dart';
import '../main.dart';
import 'package:another_flushbar/flushbar.dart';

class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  TextEditingController _usernameOrEmailController = TextEditingController();
  TextEditingController _passwordController = TextEditingController();
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

    return Scaffold(
      key: _scaffoldKey,
      appBar: AppBar(),
      body: Padding(
        padding: EdgeInsets.all(16.0),
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
            SizedBox(height: 50),
            Text(
              'Log In',
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
                controller: _usernameOrEmailController,
                decoration: InputDecoration(
                  labelText: 'Username or Email',
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
              height: 50, // Fixed height for the button
              child: ElevatedButton(
                style: ElevatedButton.styleFrom(
                  primary: Colors.black,
                  onPrimary: Colors.white,
                ),
                onPressed: () async {
                  showLoading(context);

                  final response = await request.login({
                    "usernameOrEmail": _usernameOrEmailController.text,
                    "password": _passwordController.text
                  });

                  Navigator.pop(context);

                  if (response.status == true) {
                    Navigator.pushNamed(context, "/customer");
                    Flushbar(
                      backgroundColor: const Color.fromARGB(255, 29, 167, 86),
                      flushbarPosition: FlushbarPosition.TOP,
                      title: "Berhasil",
                      duration: const Duration(seconds: 3),
                      message: response.message,
                    ).show(context);
                  } else {
                    Navigator.pushNamed(context, "/login");
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
                child: Text('Sign in'),
              ),
            ),
            SizedBox(height: 20),
            TextButton(
              onPressed: () {
                // Navigate to the registration page when the link is pressed
                Navigator.pushNamed(context, '/register');
              },
              child: Text(
                'Don\'t have an account? Sign up',
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
    );
  }
}

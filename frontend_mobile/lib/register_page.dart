import 'package:flutter/material.dart';
import 'main.dart';

class RegisterPage extends StatefulWidget {
  @override
  _RegisterPageState createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  TextEditingController _usernameController = TextEditingController();
  TextEditingController _passwordController = TextEditingController();
  GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey();

  void _register() {
    String username = _usernameController.text;
    String password = _passwordController.text;

    // Perform registration logic here
    isRegistered = true;

    // Assuming registration is successful, navigate to login
    Navigator.pushReplacementNamed(context, '/login');
  }

  @override
  Widget build(BuildContext context) {
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
                color: Colors.blue,
                fontFamily: 'Poppins',
                fontSize: 48,
                fontWeight: FontWeight.w700,
              ),
            ),
            SizedBox(height: 50),
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
                controller: _usernameController,
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
              width: double.infinity, // Expand the  to full width
              height: 50, // Fixed height for the button
              child: ElevatedButton(
                style: ElevatedButton.styleFrom(
                  primary: Colors.yellow,
                  onPrimary: Colors.black,
                ),
                onPressed: _register,
                child: Text('Sign up'),
              ),
            ),
            SizedBox(height: 20),
            TextButton(
              onPressed: () {
                // Navigate to the login page when the link is pressed
                Navigator.pushNamed(context, '/loginPage');
              },
              child: Text(
                'Already have an account? Log in',
                style: TextStyle(
                  color: Colors.blue,
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
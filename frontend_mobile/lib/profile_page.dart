import 'package:flutter/material.dart';
import 'package:frontend_mobile/user.dart';

class ProfilePage extends StatefulWidget {
  final Customer customer;

  ProfilePage({Key? key, required this.customer}) : super(key: key);

  @override
  _ProfilePageState createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {

  void _editProfile() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => EditProfilePage(customer: widget.customer))
    );
  }

  void _withdrawBalance() {
    Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => TopUpPage(customer: widget.customer))
    );
  }

  void _signOut() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text("Sign Out"),
          content: Text("Are you sure you want to sign out?"),
          actions: <Widget>[
            TextButton(
              child: Text("Cancel"),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
            TextButton(
              child: Text("Sign Out"),
              onPressed: () {
                Navigator.of(context).pushReplacementNamed('/login');
              },
            ),
          ],
        );
      },
    );
  }

  void _deleteAccount() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text("Delete Account"),
          content: Text("Are you sure you want to delete your account? This action cannot be undone."),
          actions: <Widget>[
            TextButton(
              child: Text("Cancel"),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
            TextButton(
              child: Text("Delete"),
              onPressed: () {
                Navigator.of(context).pushReplacementNamed('/register');
              },
            ),
          ],
        );
      },
    );
  }

  Widget _balanceCard(Customer customer) {
    return Card(
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            Text('Balance', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
            SizedBox(height: 8),
            Text('\$${customer.balance.toStringAsFixed(2)}', style: TextStyle(fontSize: 24)),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: _withdrawBalance,
              child: Text('Withdraw Balance'),
              style: ElevatedButton.styleFrom(
                primary: Colors.orange, // Button color
                onPrimary: Colors.white, // Text color
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(30),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Profile'),
      ),
      body: SingleChildScrollView(
        child: Column(
          children: <Widget>[
            Card(
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(30),
              ),
              child: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  children: <Widget>[
                    CircleAvatar(
                      radius: 75,
                      backgroundImage: NetworkImage("https://via.placeholder.com/150"),
                    ),
                    SizedBox(height: 10),
                    Text(widget.customer.name, style: TextStyle(fontSize: 24)),
                    Text('Customer', style: TextStyle(color: Colors.grey)),
                    _balanceCard(widget.customer),
                  ],
                ),
              ),
            ),
            Card(
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(20),
              ),
              child: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  children: <Widget>[
                    _buildDetailRow('Username', widget.customer.username),
                    _buildDetailRow('Full Name', widget.customer.name),
                    _buildDetailRow('Email', widget.customer.email),
                    _buildDetailRow('Address', widget.customer.address),
                    ElevatedButton(
                      onPressed: _editProfile,
                      child: Text('Edit User Data'),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.orange, // background (button) color
                        onPrimary: Colors.white, // foreground (text) color
                      ),
                    ),
                  ],
                ),
              ),
            ),
            ElevatedButton(
              onPressed: _signOut,
              child: Text('Sign Out'),
              style: ElevatedButton.styleFrom(
                primary: Colors.red,
              ),
            ),
            ElevatedButton(
              onPressed: _deleteAccount,
              child: Text('Delete Account'),
              style: ElevatedButton.styleFrom(
                primary: Colors.red,
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildDetailRow(String title, String value) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 8.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          Text(title, style: TextStyle(fontWeight: FontWeight.bold)),
          Text(value),
        ],
      ),
    );
  }
}

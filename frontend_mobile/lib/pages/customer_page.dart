import 'package:flutter/material.dart';
import 'package:frontend_mobile/pages/cart_page.dart';
import 'package:frontend_mobile/pages/catalog_page.dart';
import 'package:frontend_mobile/pages/login_page.dart';
import 'package:frontend_mobile/pages/order_history_page.dart';
import 'package:frontend_mobile/pages/profile_page.dart';
import 'package:frontend_mobile/pages/register_page.dart';
import 'package:provider/provider.dart';
import 'package:frontend_mobile/utils/user_functions.dart';

class CustomerPage extends StatefulWidget {
  const CustomerPage({super.key, required this.idx});
  final int idx;

  @override
  State<CustomerPage> createState() => _CustomerPageState(idx);
}

class _CustomerPageState extends State<CustomerPage> {
  _CustomerPageState(this._selectedIndex);
  int _selectedIndex;
  bool? isLoggedIn = true;

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  setIsLoggedIn() async {
    isLoggedIn = await UserFunctions().isLoggedIn();
  }

  @override
  Widget build(BuildContext context) {
    //setIsLoggedIn();

    List<Widget> _widgetOptions = <Widget>[
      CatalogPage(),
      OrderHistoryPage(),
      CartPage(),
      ProfilePage(),
    ];

    final userRequest = Provider.of<UserFunctions>(context);

    return SafeArea(
      child: Scaffold(
        backgroundColor: const Color.fromARGB(255, 245, 245, 245),
        appBar: AppBar(
          backgroundColor: Colors.white,
          elevation: 0,
          iconTheme: const IconThemeData(color: Colors.black),
          centerTitle: true,
          title: Image.asset(
            "lib/images/logo.png",
            width: 50,
            height: 50,
          ),
        ),
        body: Center(
          child: _widgetOptions.elementAt(_selectedIndex),
        ),
        bottomNavigationBar: isLoggedIn!
            ? BottomNavigationBar(
                backgroundColor: const Color.fromARGB(255, 245, 245, 245),
                items: const <BottomNavigationBarItem>[
                  BottomNavigationBarItem(
                    icon: Icon(Icons.shop_2_rounded),
                    label: 'Home',
                  ),
                  BottomNavigationBarItem(
                    icon: Icon(Icons.view_list_rounded),
                    label: 'Order History',
                  ),
                  BottomNavigationBarItem(
                    icon: Icon(Icons.shopping_cart_rounded),
                    label: 'Cart',
                  ),
                  BottomNavigationBarItem(
                    icon: Icon(Icons.account_box_rounded),
                    label: 'Profile',
                  ),
                ],
                currentIndex: _selectedIndex,
                unselectedItemColor: Colors.black,
                selectedItemColor: const Color.fromARGB(255, 5, 89, 91),
                onTap: _onItemTapped,
              )
            : null,
        floatingActionButton: !isLoggedIn!
            ? Padding(
                padding: const EdgeInsets.symmetric(horizontal: 10),
                child: Container(
                  height: 60,
                  decoration: BoxDecoration(
                    // border: Border.all(color: Colors.black),
                    color: Colors.transparent,
                    borderRadius: BorderRadius.circular(18),
                  ),
                  child: Row(
                    children: [
                      Expanded(
                        child: Container(
                          height: 60,
                          width: double.infinity,
                          decoration: BoxDecoration(
                            color: Colors.orange,
                            borderRadius: BorderRadius.circular(18),
                          ),
                          child: TextButton(
                              onPressed: () {
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) => RegisterPage()));
                              },
                              child: const Text(
                                "Register",
                                style: TextStyle(
                                    fontSize: 16,
                                    color: Colors.white,
                                    fontWeight: FontWeight.bold),
                              )),
                        ),
                      ),
                      Expanded(
                        child: Container(
                          height: 60,
                          width: double.infinity,
                          decoration: BoxDecoration(
                            color: Colors.white,
                            borderRadius: BorderRadius.circular(18),
                          ),
                          child: TextButton(
                            onPressed: () {
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) => LoginPage()));
                            },
                            child: const Text("Sign In",
                                style: TextStyle(
                                    fontSize: 16,
                                    color: Colors.orange,
                                    fontWeight: FontWeight.bold)),
                          ),
                        ),
                      )
                    ],
                  ),
                ),
              )
            : null,
        floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
      ),
    );
  }
}

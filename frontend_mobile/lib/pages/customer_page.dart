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
  bool? isLoggedIn = false;

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
    double screenWidth = MediaQuery.of(context).size.width;

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
          backgroundColor:  const Color.fromARGB(255, 245, 245, 245),
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
                backgroundColor: Colors.black,
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
                selectedItemColor: Colors.orange[900],
                onTap: _onItemTapped,
              )
            : null,
        floatingActionButton: !isLoggedIn!
            ? Padding(
                padding: const EdgeInsets.symmetric(horizontal: 10),
                child: Container(
                  height: screenWidth*0.2,
                  decoration: BoxDecoration(
                    // border: Border.all(color: Colors.black),
                    color: Colors.transparent,
                    borderRadius: BorderRadius.circular(18),
                  ),
                  child: Row(
                    children: [
                      Padding(
                        padding: EdgeInsets.all(8.0),
                      ),
                      Expanded(
                        child: Container(
                          height: screenWidth*0.125,
                          width: double.infinity,
                          decoration: BoxDecoration(
                            color: Colors.black,
                            borderRadius: BorderRadius.circular(18),
                          ),
                          child: TextButton(
                              onPressed: () {
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) => RegisterPage()));
                              },
                              child: Text(
                                "Register",
                                style: TextStyle(
                                    fontFamily: 'Poppins',
                                    fontSize: screenWidth*0.03,
                                    color: Colors.white,
                                    fontWeight: FontWeight.w600,)
                              )),
                        ),
                      ),
                      SizedBox(width: 16,),
                      Expanded(
                        child: Container(
                            height: screenWidth*0.125,
                          width: double.infinity,
                          decoration: BoxDecoration(
                            color: Colors.orange,
                            borderRadius: BorderRadius.circular(18),
                          ),
                            child: DecoratedBox(
                              decoration: BoxDecoration(
                                border: Border.all(
                                  color: Colors.orange,//e Set border color
                                  width: 2.0, // Set border width
                                ),
                                borderRadius: BorderRadius.circular(18.0), // Set border radius (optional)
                              ),
                              child: TextButton(
                                onPressed: () {
                                  Navigator.push(
                                    context,
                                    MaterialPageRoute(builder: (context) => LoginPage()),
                                  );
                                },
                                child: Text(
                                  "Sign In",
                                  style: TextStyle(
                                    fontFamily: 'Poppins',
                                    fontSize: screenWidth*0.03,
                                    color: Colors.white,
                                    fontWeight: FontWeight.w600,

                                  ),
                                ),
                              ),
                            )

                        ),

                        ),Padding(
                        padding: EdgeInsets.all(8.0),
                      ),

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

import 'package:flutter/material.dart';

//global variables
bool isLoggedIn = false;
bool isRegistered = false;

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
      ),
      initialRoute: '/catalog', // Set the initial route to the login page
      routes: {
        '/login': (context) => LoginPage(),
        '/register': (context) => RegisterPage(),
        '/homePage': (context) => MyHomePage(),
        '/catalog' : (context) => CatalogPage(
          products: [], // produk yang ditunjukkin disini
          isLoggedIn: isLoggedIn, // Pass the value of your global isLoggedIn variable
          onAddToCart: (product) {
                // Implement your addToCart logic here
          },
          onBuyNow: (product) {
                List<Product> purchasedProducts = [product];

                // Navigate to the OrderHistoryPage and pass the purchased products
                Navigator.pushNamed(
                  context,
                  '/orderHistory',
                  arguments: purchasedProducts,
                );
          },
          ),
        '/productDetail' : (context) => ProductDetailPage(
          product : product //produk to show
        ),
        '/orderHistory' : (context) => OrderHistoryPage(),
        '/cart' : (context) => CartPage(),
            },

          );
  }
}

class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  TextEditingController _usernameController = TextEditingController();
  TextEditingController _passwordController = TextEditingController();
  GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey();

  void _login() {
    String username = _usernameController.text;
    String password = _passwordController.text;

    // Perform login logic here
      isLoggedIn = true;
    //

    // Assuming login is successful, navigate to the home page
    Navigator.pushReplacementNamed(context, '/homePage');
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
              width: double.infinity, // Expand the container to full width
              height: 50, // Fixed height for the button
              child: ElevatedButton(
                style: ElevatedButton.styleFrom(
                  primary: Colors.yellow,
                  onPrimary: Colors.black,
                ),
                onPressed: _login,
                child: Text('Sign in'),
              ),
            ),
            SizedBox(height: 20),
            TextButton(
              onPressed: () {
                // Navigate to the registration page when the link is pressed
                Navigator.pushNamed(context, '/registerPage');
              },
              child: Text(
                'Don\'t have an account? Sign up',
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

    // Assuming registration is successful, navigate to the home page
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

// class Catalogue

// product category

enum ProductCategory {
  NoCategory,
  AksesorisFashion,
  BukuAlatTulis,
  Elektronik,
  FashionBayiAnak,
  FashionMuslim,
  Fotografi,
  HobiKoleksi,
  JamTangan,
  PerawatanKecantikan,
  MakananMinuman,
  Otomotif,
  PerlengkapanRumah,
  SouvenirPartySupplies,
}

class Product {
  final String id;
  final String name;
  final String description;
  final double price;
  final String image; // Add image attribute
  final ProductCategory category; // Add category attribute

  Product({
    required this.id,
    required this.name,
    required this.description,
    required this.price,
    required this.image,
    required this.category,
  });
}

class CatalogPage extends StatelessWidget {
  final List<Product> products;
  final bool isLoggedIn;
  final Function(Product) onAddToCart;
  final Function(Product) onBuyNow;

  CatalogPage({
    required this.products,
    required this.isLoggedIn,
    required this.onAddToCart,
    required this.onBuyNow,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('APAPEDIA Catalog'),
      ),
      body: ListView.builder(
        itemCount: products.length,
        itemBuilder: (context, index) {
          var product = products[index];
          return Card(
            child: ListTile(
              title: Text(product.name),
              subtitle: Text('\$${product.price}'),
              onTap: (){
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => ProductDetailPage(product: product),
                  ),
                );
              },
              trailing: isLoggedIn ? _buildButtons(product) : null,
              leading: Image.asset(
                product.image, // Load the image from the asset path
                width: 80, // Set the desired width
                height: 80, // Set the desired height
                fit: BoxFit.cover, // Adjust the fit as needed
              ),
            ),
          );
        },
      ),
    );
  }
}

  Widget _buildButtons(Product product) {
    return Row(
      mainAxisSize: MainAxisSize.min,
      children: [
        IconButton(
          icon: Icon(Icons.add_shopping_cart),
          onPressed: () => onAddToCart(product),
        ),
        IconButton(
          icon: Icon(Icons.payment),
          onPressed: () => onBuyNow(product),
        ),
      ],
    );
  }
}

class ProductDetailPage extends StatelessWidget {
  final Product product;

  ProductDetailPage({required this.product});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Product Detail'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Image.asset(
              product.image,
              width: 200,
              height: 200,
              fit: BoxFit.cover,
            ),
            SizedBox(height: 20),
            Text('ID: ${product.id}'),
            Text('Name: ${product.name}'),
            Text('Description: ${product.description}'),
            Text('Price: \$${product.price.toStringAsFixed(2)}'),
            Text('Category: ${product.category.toString().split('.').last}'),

            SizedBox(height: 20),

            ElevatedButton(
              onPressed: () {
                // Implement your addToCart logic here
                addToCart(product);
              },
              child: Text('Add To Cart'),
            ),

            ElevatedButton(
              onPressed: () {
                // Implement your buyNow logic here
                buyNow(product);
              },
              child: Text('Buy Now'),
            ),
          ],
        ),
      ),
    );
  }


  void addToCart(Product product) {
    // Implement addToCart logic here
    // You can add the product to the cart or perform any other necessary actions.
  }

  void buyNow(Product product) {
    // Implement logic here
    // You can handle the purchase process, navigate to order history, etc.
  }
}

// class OrderHistoryPage extends StatelessWidget {
//   @override
//   Widget build(BuildContext context) {
//     // Receive the purchased products from the route arguments
//     final List<Product> purchasedProducts = ModalRoute
//         .of(context)!
//         .settings
//         .arguments as List<Product>;
//   }

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.blue,
        title: Text('Home'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headline6,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}

import 'package:flutter/material.dart';
import 'package:frontend_mobile/pages/order_history_page.dart';
import 'package:frontend_mobile/pages/profile_page.dart';
import 'dart:convert';
import 'dart:typed_data';

class CatalogueCard extends StatelessWidget {
  const CatalogueCard({super.key, required this.isLoggedIn});

  final bool isLoggedIn;

  Widget getImagenBase64(String imagen) {
    Uint8List _bytesImage = Base64Decoder().convert(imagen.split(',').last);
    return Image.memory(
      _bytesImage,
      gaplessPlayback: true,
      fit: BoxFit.fitWidth,
    );
  }

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.of(context).size.width;
    double screenHeight = MediaQuery.of(context).size.height;
    double cardWidth = screenWidth * 0.45;
    double cardHeight = screenWidth * 0.7;
    double baseTextSize = screenWidth * 0.03;

    return InkWell(
      onTap: () {},
      child: Container(
        margin: EdgeInsets.all(screenWidth*0.015),
        width: cardWidth,
        height: cardHeight,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.all(Radius.circular(30)),
          color: Colors.white,
          boxShadow: [
            BoxShadow(
              color: Colors.grey.withOpacity(0.5),
              spreadRadius: 3,
              blurRadius: 7,
            ),
          ],
        ),
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: SingleChildScrollView(
            child: Container(// Set the desired height for the Column
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  SizedBox(height: screenWidth * 0.04),
                  Center(
                    child: Image.asset(
                      'lib/images/logo.png',
                      width: cardWidth * 0.4,
                      fit: BoxFit.fitWidth,
                    ),
                  ),
                  SizedBox(height: screenWidth * 0.03),
                  Text(
                    "Norwegian Wood",
                    style: TextStyle(
                      fontFamily: 'Poppins',
                      fontSize: baseTextSize,
                      fontWeight: FontWeight.w400,
                      color: Colors.black,
                    ),
                  ),
                  Text(
                    "Rp200.000,-",
                    style: TextStyle(
                      fontFamily: 'Poppins',
                      fontSize: baseTextSize * 1.14,
                      fontWeight: FontWeight.w600,
                      color: Colors.red,
                    ),
                  ),
                  SizedBox(height: screenWidth * 0.03),
                  if (isLoggedIn) buildButtons(context, cardWidth),
                ],
              ),
            )

          ),
        ),
      ),
    );
  }

  Widget buildButtons(BuildContext context, double cardWidth) {
    double buttonWidth = cardWidth * 0.4;
    double buttonHeight = cardWidth * 0.175; // Fixed height for buttons

    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: [
        SizedBox(
          width: buttonWidth,
          height: buttonHeight,
          child: TextButton(
            style: ButtonStyle(
              backgroundColor: MaterialStateProperty.all(Colors.white),
              shape: MaterialStateProperty.all(
                RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(12),
                ),
              ),
            ),
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => ProfilePage()),
              );
            },
            child: Text(
              "Add to Cart",
              style: TextStyle(
                fontFamily: 'Poppins',
                fontSize: 12,
                fontWeight: FontWeight.w400,
                color: Colors.grey,
              ),
            ),
          ),
        ),
        SizedBox(
          width: buttonWidth,
          height: buttonHeight,
          child: TextButton(
            style: ButtonStyle(
              backgroundColor: MaterialStateProperty.all(Colors.black),
              shape: MaterialStateProperty.all(
                RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(12),
                ),
              ),
            ),
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => OrderHistoryPage()),
              );
            },
            child: const Text(
              "Order",
              style: TextStyle(
                fontFamily: 'Poppins',
                fontSize: 12,
                fontWeight: FontWeight.w400,
                color: Colors.white,
              ),
            ),
          ),
        ),
      ],
    );
  }
}

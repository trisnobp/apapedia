import 'package:flutter/material.dart';

class CategoryList extends StatelessWidget {
  const CategoryList({Key? key});

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.of(context).size.width;

    return ExpansionTile(
      title: Text(
        "Product Category",
        style: TextStyle(
          fontFamily: 'Poppins',
          fontSize: screenWidth * 0.03,
          fontWeight: FontWeight.w400,
          color: Colors.black,
        ),
      ),
      children: [
        buildCategoryCard(
          icon: Icons.shopping_bag_rounded,
          label: "Aksesoris Fashion",
        ),
        buildCategoryCard(
          icon: Icons.menu_book_rounded,
          label: "Buku & Alat Tulis",
        ),
        buildCategoryCard(
          icon: Icons.phone_android_rounded,
          label: "Elektronik",
        ),
        buildCategoryCard(
          icon: Icons.baby_changing_station_rounded,
          label: "Fashion Bayi & Anak",
        ),
        buildCategoryCard(
          icon: Icons.abc_rounded,
          label: "Fashion Muslim",
        ),
        buildCategoryCard(
          icon: Icons.camera_alt_rounded,
          label: "Fotografi",
        ),
        buildCategoryCard(
          icon: Icons.collections_bookmark_rounded,
          label: "Hobi & Koleksi",
        ),
        buildCategoryCard(
          icon: Icons.watch,
          label: "Jam Tangan",
        ),
        buildCategoryCard(
          icon: Icons.abc_rounded,
          label: "Perawatan & Kecantikan",
        ),
        buildCategoryCard(
          icon: Icons.food_bank_rounded,
          label: "Makanan & Minuman",
        ),
        buildCategoryCard(
          icon: Icons.car_repair_rounded,
          label: "Otomotif",
        ),
        buildCategoryCard(
          icon: Icons.house_rounded,
          label: "Perlengkapan Rumah",
        ),
        buildCategoryCard(
          icon: Icons.party_mode_rounded,
          label: "Souvenir & Party Supplies",
        ),
        const SizedBox(
          height: 8,
        ),
      ],
    );
  }

  Widget buildCategoryCard({required IconData icon, required String label}) {
    return Container(
      margin: EdgeInsets.symmetric(vertical: 4),
      child: Card(
        color: Colors.white, // Set card color to transparent
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(8),
        ),
        child: Padding(
          padding: EdgeInsets.all(12),
          child: Row(
            children: [
              Icon(
                icon,
                size: 28,
              ),
              SizedBox(
                width: 12,
              ),
              Text(
                label,
                style: TextStyle(
                  fontFamily: 'Poppins', // Set the font family to Poppins
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

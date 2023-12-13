import 'package:flutter/material.dart';

class CategoryList extends StatelessWidget {
  const CategoryList({super.key});

  @override
  Widget build(BuildContext context) {
    return ExpansionTile(
      title: const Text("Product Category"),
      children: [
        Card(
                        elevation: 4,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(18)),
                        color: const Color(0xfff5f5dc),
                        child: const Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Row(
                              children: [
                                Icon(
                                  Icons.shopping_bag_rounded,
                                  size: 40,
                                ),
                                SizedBox(
                                  width: 10,
                                ),
                                Text("Aksesoris Fashion")
                              ],
                            ),
                        ),
                        ),
                      Card(
                        elevation: 4,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(18)),
                        color: const Color(0xfff5f5dc),
                        child: const Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Row(
                              children: [
                                Icon(
                                  Icons.menu_book_rounded,
                                  size: 40,
                                ),
                                SizedBox(
                                  width: 10,
                                ),
                                Text("Buku & Alat Tulis")
                              ],
                            ),
                        ),
                        ),
                      Card(
                        elevation: 4,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(18)),
                        color: const Color(0xfff5f5dc),
                        child: const Padding(
                          padding: EdgeInsets.all(8),
                          child: Row(
                              children:  [
                                Icon(
                                  Icons.phone_android_rounded,
                                  size: 40,
                                ),
                                SizedBox(
                                  width: 10,
                                ),
                                Text("Elektronik")
                              ],
                            ),
                        ),
                        ),
                        Card(
                        elevation: 4,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(18)),
                        color: const Color(0xfff5f5dc),
                        child: const Padding(
                          padding: EdgeInsets.all(8),
                          child: Row(
                              children:  [
                                Icon(
                                  Icons.baby_changing_station_rounded,
                                  size: 40,
                                ),
                                SizedBox(
                                  width: 10,
                                ),
                                Text("Fashion Bayi & Anak")
                              ],
                            ),
                        ),
                        ),
                        Card(
                        elevation: 4,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(18)),
                        color: const Color(0xfff5f5dc),
                        child: const Padding(
                          padding: EdgeInsets.all(8),
                          child: Row(
                              children:  [
                                Icon(
                                  Icons.abc_rounded,
                                  size: 40,
                                ),
                                SizedBox(
                                  width: 10,
                                ),
                                Text("Fashion Muslim")
                              ],
                            ),
                        ),
                        ),
                        Card(
                        elevation: 4,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(18)),
                        color: const Color(0xfff5f5dc),
                        child: const Padding(
                          padding: EdgeInsets.all(8),
                          child: Row(
                              children:  [
                                Icon(
                                  Icons.camera_alt_rounded,
                                  size: 40,
                                ),
                                SizedBox(
                                  width: 10,
                                ),
                                Text("Fotografi")
                              ],
                            ),
                        ),
                        ),
                        Card(
                        elevation: 4,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(18)),
                        color: const Color(0xfff5f5dc),
                        child: const Padding(
                          padding: EdgeInsets.all(8),
                          child: Row(
                              children:  [
                                Icon(
                                  Icons.collections_bookmark_rounded,
                                  size: 40,
                                ),
                                SizedBox(
                                  width: 10,
                                ),
                                Text("Hobi & Koleksi")
                              ],
                            ),
                        ),
                        ),
                        Card(
                        elevation: 4,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(18)),
                        color: const Color(0xfff5f5dc),
                        child: const Padding(
                          padding: EdgeInsets.all(8),
                          child: Row(
                              children:  [
                                Icon(
                                  Icons.watch,
                                  size: 40,
                                ),
                                SizedBox(
                                  width: 10,
                                ),
                                Text("Jam Tangan")
                              ],
                            ),
                        ),
                        ),
                        Card(
                        elevation: 4,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(18)),
                        color: const Color(0xfff5f5dc),
                        child: const Padding(
                          padding: EdgeInsets.all(8),
                          child: Row(
                              children:  [
                                Icon(
                                  Icons.abc_rounded,
                                  size: 40,
                                ),
                                SizedBox(
                                  width: 10,
                                ),
                                Text("Perawatan & Kecantikan")
                              ],
                            ),
                        ),
                        ),
                        Card(
                        elevation: 4,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(18)),
                        color: const Color(0xfff5f5dc),
                        child: const Padding(
                          padding: EdgeInsets.all(8),
                          child: Row(
                              children:  [
                                Icon(
                                  Icons.food_bank_rounded,
                                  size: 40,
                                ),
                                SizedBox(
                                  width: 10,
                                ),
                                Text("Makanan & Minuman")
                              ],
                            ),
                        ),
                        ),
                        Card(
                        elevation: 4,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(18)),
                        color: const Color(0xfff5f5dc),
                        child: const Padding(
                          padding: EdgeInsets.all(8),
                          child: Row(
                              children:  [
                                Icon(
                                  Icons.car_repair_rounded,
                                  size: 40,
                                ),
                                SizedBox(
                                  width: 10,
                                ),
                                Text("Otomotif")
                              ],
                            ),
                        ),
                        ),
                        Card(
                        elevation: 4,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(18)),
                        color: const Color(0xfff5f5dc),
                        child: const Padding(
                          padding: EdgeInsets.all(8),
                          child: Row(
                              children:  [
                                Icon(
                                  Icons.house_rounded,
                                  size: 40,
                                ),
                                SizedBox(
                                  width: 10,
                                ),
                                Text("Perlengkapan Rumah")
                              ],
                            ),
                        ),
                        ),
                        Card(
                        elevation: 4,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(18)),
                        color: const Color(0xfff5f5dc),
                        child: const Padding(
                          padding: EdgeInsets.all(8),
                          child: Row(
                              children:  [
                                Icon(
                                  Icons.party_mode_rounded,
                                  size: 40,
                                ),
                                SizedBox(
                                  width: 10,
                                ),
                                Text("Souvenir & Party Supplies")
                              ],
                            ),
                        ),
                        ),
                      const SizedBox(
                        height: 20,
                      ),
      ],
    );
  }
}
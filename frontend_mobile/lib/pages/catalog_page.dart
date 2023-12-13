import 'package:flutter/material.dart';
import 'package:frontend_mobile/components/catalogue_card.dart';
import 'package:frontend_mobile/components/category_list.dart';
import 'package:frontend_mobile/utils/user_functions.dart';

class CatalogPage extends StatelessWidget {
  CatalogPage({Key? key});
  
  int filter_order = 0;

  bool? isLoggedIn = true;

  setIsLoggedIn() async {
    isLoggedIn = await UserFunctions().isLoggedIn();
  }

  @override
  Widget build(BuildContext context) {
    setIsLoggedIn();

    return SafeArea(
      child: Scaffold(
        body: NestedScrollView(
          headerSliverBuilder: (BuildContext context, bool innerBoxIsScrolled) {
            return const [
              SliverToBoxAdapter(
                child: Padding(
                  padding: EdgeInsets.all(8),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      CategoryList(),
                      SizedBox(
                        height: 50,
                      ),
                      Center(
                        child: Text(
                          "All Catalogues",
                          style: TextStyle(
                              fontSize: 20, fontWeight: FontWeight.w500),
                        ),
                      ),
                      SizedBox(
                        height: 25,
                      ),
                    ],
                  ),
                ),
              ),
            ];
          },
          body: GridView.count(
            crossAxisCount: 2,
            children: List.generate(10, (index) {
              return Row(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  CatalogueCard(isLoggedIn: isLoggedIn!,),
                ],
              );
            }),
          ),
        ),
      ),
    );
  }

  // ... rest of your code
}

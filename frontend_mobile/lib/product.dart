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

Map<ProductCategory, int> categoryIds = {
  ProductCategory.NoCategory: 0,
  ProductCategory.AksesorisFashion: 1,
  ProductCategory.BukuAlatTulis: 2,
  ProductCategory.Elektronik: 3,
  ProductCategory.FashionBayiAnak: 4,
  ProductCategory.FashionMuslim: 5,
  ProductCategory.Fotografi: 6,
  ProductCategory.HobiKoleksi: 7,
  ProductCategory.JamTangan: 8,
  ProductCategory.PerawatanKecantikan: 9,
  ProductCategory.MakananMinuman: 10,
  ProductCategory.Otomotif: 11,
  ProductCategory.PerlengkapanRumah: 12,
  ProductCategory.SouvenirPartySupplies: 13,
};

class Product {
  final int id;
  final int sellerId;
  final String name;
  final String description;
  final double price;
  final int categoryId;
  final int stock;
  final String image;

  Product({
    required this.id,
    required this.sellerId,
    required this.name,
    required this.description,
    required this.price,
    required this.image,
    required this.categoryId,
    required this.stock,
  });

  ProductCategory get category {
    return categoryIds.entries
        .firstWhere((entry) => entry.value == categoryId, orElse: () => MapEntry(ProductCategory.NoCategory, 0))
        .key;
  }
}

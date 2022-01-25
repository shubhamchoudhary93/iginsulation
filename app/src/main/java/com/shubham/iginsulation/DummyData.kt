package com.shubham.iginsulation

import com.shubham.iginsulation.database.customer.Customer
import com.shubham.iginsulation.database.customer.CustomerDatabaseDao
import com.shubham.iginsulation.database.sale.SaleDatabaseDao
import com.shubham.iginsulation.database.saledetails.SaleDetailsDatabaseDao
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransactionDatabaseDao
import com.shubham.iginsulation.database.shopstock.ShopStock
import com.shubham.iginsulation.database.shopstock.ShopStockDatabaseDao
import com.shubham.iginsulation.database.stock.Stock
import com.shubham.iginsulation.database.stock.StockDatabaseDao
import com.shubham.iginsulation.database.transaction.TransactionDatabaseDao

object DummyData {

    fun populate(
        customerDatabase: CustomerDatabaseDao,
        stockDatabase: StockDatabaseDao,
        shopStockDatabase: ShopStockDatabaseDao,
        saleDatabase: SaleDatabaseDao,
        saleDetailsDatabase: SaleDetailsDatabaseDao,
        shopStockTransactionDatabase: ShopStockTransactionDatabaseDao,
        transactionDatabase: TransactionDatabaseDao
    ) {

        customerDatabase.clear()
        stockDatabase.clear()
        shopStockDatabase.clear()
        saleDatabase.clear()
        saleDetailsDatabase.clear()
        shopStockTransactionDatabase.clear()
        transactionDatabase.clear()


        customerDatabase.insert(Customer(0, "Akash", 9999999999, "Kashipur", 0F, 0F))

        stockDatabase.insert(Stock(0, "F class 1mm", "Sleeve", "F class", 98F, 1.65F))
        stockDatabase.insert(Stock(0, "F class 1.5mm", "Sleeve", "F class", 128F, 1.65F))
        stockDatabase.insert(Stock(0, "F class 2mm", "Sleeve", "F class", 138F, 1.65F))
        stockDatabase.insert(Stock(0, "F class 3mm", "Sleeve", "F class", 177F, 1.65F))
        stockDatabase.insert(Stock(0, "F class 4mm", "Sleeve", "F class", 222F, 1.65F))
        stockDatabase.insert(Stock(0, "F class 5mm", "Sleeve", "F class", 274F, 1.65F))
        stockDatabase.insert(Stock(0, "F class 6mm", "Sleeve", "F class", 325F, 1.65F))
        stockDatabase.insert(Stock(0, "F class 8mm", "Sleeve", "F class", 485F, 1.65F))
        stockDatabase.insert(Stock(0, "F class 10mm", "Sleeve", "F class", 625F, 1.65F))
        stockDatabase.insert(Stock(0, "F class 12mm", "Sleeve", "F class", 800F, 1.65F))
        stockDatabase.insert(Stock(0, "F class 16mm", "Sleeve", "F class", 1390F, 1.65F))
        stockDatabase.insert(Stock(0, "B class 1mm", "Sleeve", "B class", 98F, 1.3F))
        stockDatabase.insert(Stock(0, "B class 1.5mm", "Sleeve", "B class", 128F, 1.3F))
        stockDatabase.insert(Stock(0, "B class 2mm", "Sleeve", "B class", 138F, 1.3F))
        stockDatabase.insert(Stock(0, "B class 3mm", "Sleeve", "B class", 177F, 1.3F))
        stockDatabase.insert(Stock(0, "B class 4mm", "Sleeve", "B class", 222F, 1.3F))
        stockDatabase.insert(Stock(0, "B class 5mm", "Sleeve", "B class", 274F, 1.3F))
        stockDatabase.insert(Stock(0, "B class 6mm", "Sleeve", "B class", 325F, 1.3F))
        stockDatabase.insert(Stock(0, "B class 8mm", "Sleeve", "B class", 485F, 1.3F))
        stockDatabase.insert(Stock(0, "B class 10mm", "Sleeve", "B class", 625F, 1.3F))
        stockDatabase.insert(Stock(0, "B class 12mm", "Sleeve", "B class", 800F, 1.3F))
        stockDatabase.insert(Stock(0, "B class 16mm", "Sleeve", "B class", 1390F, 1.3F))
        stockDatabase.insert(Stock(0, "B class 20mm", "Sleeve", "B class", 1890F, 1.3F))
        stockDatabase.insert(Stock(0, "B class 25mm", "Sleeve", "B class", 2390F, 1.3F))
        stockDatabase.insert(Stock(0, "A class 1mm", "Sleeve", "A class", 98F, 1.2F))
        stockDatabase.insert(Stock(0, "A class 2mm", "Sleeve", "A class", 128F, 1.2F))
        stockDatabase.insert(Stock(0, "A class 3mm", "Sleeve", "A class", 138F, 1.2F))
        stockDatabase.insert(Stock(0, "A class 4mm", "Sleeve", "A class", 177F, 1.2F))
        stockDatabase.insert(Stock(0, "A class 5mm", "Sleeve", "A class", 222F, 1.2F))
        stockDatabase.insert(Stock(0, "A class 6mm", "Sleeve", "A class", 274F, 1.2F))
        stockDatabase.insert(Stock(0, "A class 8mm", "Sleeve", "A class", 325F, 1.2F))
        stockDatabase.insert(Stock(0, "A class 10mm", "Sleeve", "A class", 485F, 1.2F))
        stockDatabase.insert(Stock(0, "A class 12mm", "Sleeve", "A class", 625F, 1.2F))
        stockDatabase.insert(Stock(0, "H class 1mm", "Sleeve", "H class", 98F, 1.65F))
        stockDatabase.insert(Stock(0, "H class 1.5mm", "Sleeve", "H class", 128F, 1.8F))
        stockDatabase.insert(Stock(0, "H class 2mm", "Sleeve", "H class", 138F, 1.7F))
        stockDatabase.insert(Stock(0, "H class 3mm", "Sleeve", "H class", 177F, 2F))
        stockDatabase.insert(Stock(0, "H class 4mm", "Sleeve", "H class", 222F, 2F))
        stockDatabase.insert(Stock(0, "H class 5mm", "Sleeve", "H class", 274F, 2.2F))
        stockDatabase.insert(Stock(0, "H class 6mm", "Sleeve", "H class", 325F, 2.2F))
        stockDatabase.insert(Stock(0, "H class 8mm", "Sleeve", "H class", 485F, 2.2F))
        stockDatabase.insert(Stock(0, "H class 10mm", "Sleeve", "H class", 625F, 2.3F))
        stockDatabase.insert(Stock(0, "H class 12mm", "Sleeve", "H class", 800F, 2.3F))
        stockDatabase.insert(Stock(0, "H class 14mm", "Sleeve", "H class", 1390F, 1F))
        stockDatabase.insert(Stock(0, "H class 16mm", "Sleeve", "H class", 1890F, 1F))
        stockDatabase.insert(Stock(0, "H class 18mm", "Sleeve", "H class", 2390F, 1F))
        stockDatabase.insert(Stock(0, "H class 20mm", "Sleeve", "H class", 150F, 1F))
        stockDatabase.insert(Stock(0, "H class 25mm", "Sleeve", "H class", 200F, 1F))
        stockDatabase.insert(Stock(0, "China 1mm", "Sleeve", "China", 150F, 0.67F))
        stockDatabase.insert(Stock(0, "China 1.5mm", "Sleeve", "China", 200F, 0.67F))
        stockDatabase.insert(Stock(0, "China 2mm", "Sleeve", "China", 200F, 0.67F))
        stockDatabase.insert(Stock(0, "China 3mm", "Sleeve", "China", 300F, 0.67F))
        stockDatabase.insert(Stock(0, "China 4mm", "Sleeve", "China", 400F, 0.67F))
        stockDatabase.insert(Stock(0, "China 5mm", "Sleeve", "China", 500F, 0.67F))
        stockDatabase.insert(Stock(0, "China 6mm", "Sleeve", "China", 600F, 0.67F))
        stockDatabase.insert(Stock(0, "China 8mm", "Sleeve", "China", 800F, 0.77F))
        stockDatabase.insert(Stock(0, "China 10mm", "Sleeve", "China", 1000F, 0.84F))
        stockDatabase.insert(Stock(0, "China 12mm", "Sleeve", "China", 1200F, 0.84F))
        stockDatabase.insert(Stock(0, "China 14mm", "Sleeve", "China", 1400F, 1.4F))
        stockDatabase.insert(Stock(0, "China 16mm", "Sleeve", "China", 1600F, 1.4F))
        stockDatabase.insert(Stock(0, "China 18mm", "Sleeve", "China", 1800F, 1.4F))
        stockDatabase.insert(Stock(0, "China 20mm", "Sleeve", "China", 2000F, 1.4F))
        stockDatabase.insert(Stock(0, "China 25mm", "Sleeve", "China", 2500F, 1.4F))
        stockDatabase.insert(Stock(0, "Empire 1mm", "Sleeve", "Empire", 30F, 1F))
        stockDatabase.insert(Stock(0, "Empire 2mm", "Sleeve", "Empire", 40F, 1F))
        stockDatabase.insert(Stock(0, "Empire 3mm", "Sleeve", "Empire", 60F, 1F))
        stockDatabase.insert(Stock(0, "Empire 4mm", "Sleeve", "Empire", 90F, 1F))
        stockDatabase.insert(Stock(0, "Empire 5mm", "Sleeve", "Empire", 120F, 1F))
        stockDatabase.insert(Stock(0, "Empire 6mm", "Sleeve", "Empire", 130F, 1F))
        stockDatabase.insert(Stock(0, "Empire 8mm", "Sleeve", "Empire", 90F, 1F))
        stockDatabase.insert(Stock(0, "Empire 10mm", "Sleeve", "Empire", 140F, 1F))
        stockDatabase.insert(Stock(0, "China 1mm Yellow ", "Sleeve", "China", 150F, 0.8F))
        stockDatabase.insert(Stock(0, "China 1mm Green ", "Sleeve", "China", 150F, 0.8F))
        stockDatabase.insert(Stock(0, "China 1mm Blue ", "Sleeve", "China", 150F, 0.8F))
        stockDatabase.insert(Stock(0, "China 1mm Black ", "Sleeve", "China", 150F, 0.8F))
        stockDatabase.insert(Stock(0, "China 1.5mm Yellow ", "Sleeve", "China", 200F, 0.8F))
        stockDatabase.insert(Stock(0, "China 1.5mm Green ", "Sleeve", "China", 200F, 0.8F))
        stockDatabase.insert(Stock(0, "China 1.5mm Blue ", "Sleeve", "China", 200F, 0.8F))
        stockDatabase.insert(Stock(0, "China 1.5mm Black ", "Sleeve", "China", 200F, 0.8F))
        stockDatabase.insert(Stock(0, "China 2mm Yellow ", "Sleeve", "China", 200F, 0.8F))
        stockDatabase.insert(Stock(0, "China 2mm Green ", "Sleeve", "China", 200F, 0.8F))
        stockDatabase.insert(Stock(0, "China 2mm Blue ", "Sleeve", "China", 200F, 0.8F))
        stockDatabase.insert(Stock(0, "China 2mm Black ", "Sleeve", "China", 200F, 0.8F))
        stockDatabase.insert(Stock(0, "China 3mm Yellow ", "Sleeve", "China", 300F, 0.8F))
        stockDatabase.insert(Stock(0, "China 3mm Green ", "Sleeve", "China", 300F, 0.8F))
        stockDatabase.insert(Stock(0, "China 3mm Blue ", "Sleeve", "China", 300F, 0.8F))
        stockDatabase.insert(Stock(0, "China 3mm Black ", "Sleeve", "China", 300F, 0.8F))
        stockDatabase.insert(Stock(0, "China 4mm Yellow ", "Sleeve", "China", 400F, 0.8F))
        stockDatabase.insert(Stock(0, "China 4mm Green ", "Sleeve", "China", 400F, 0.8F))
        stockDatabase.insert(Stock(0, "China 4mm Blue ", "Sleeve", "China", 400F, 0.8F))
        stockDatabase.insert(Stock(0, "China 4mm Black ", "Sleeve", "China", 400F, 0.8F))
        stockDatabase.insert(Stock(0, "H class 1mm Black", "Sleeve", "H class", 98F, 1.8F))
        stockDatabase.insert(Stock(0, "H class 2mm Black", "Sleeve", "H class", 138F, 1.8F))
        stockDatabase.insert(Stock(0, "H class 3mm Black", "Sleeve", "H class", 138F, 2.6F))
        stockDatabase.insert(Stock(0, "H class 4mm Black", "Sleeve", "H class", 177F, 2.6F))
        stockDatabase.insert(Stock(0, "H class 5mm Black", "Sleeve", "H class", 222F, 2.6F))
        stockDatabase.insert(Stock(0, "H class 6mm Black", "Sleeve", "H class", 274F, 2.6F))
        stockDatabase.insert(Stock(0, "H class 8mm Black", "Sleeve", "H class", 325F, 2.6F))
        stockDatabase.insert(Stock(0, "H class 4mm Yellow ", "Sleeve", "H class", 485F, 2.6F))
        stockDatabase.insert(Stock(0, "H class 4mm Green ", "Sleeve", "H class", 222F, 2.6F))
        stockDatabase.insert(Stock(0, "H class 4mm Blue ", "Sleeve", "H class", 222F, 2.6F))
        stockDatabase.insert(Stock(0, "LeadWire 0.5", "LeadWire", "LeadWire", 6F, 1F))
        stockDatabase.insert(Stock(0, "LeadWire 0.75", "LeadWire", "LeadWire", 8.5F, 1F))
        stockDatabase.insert(Stock(0, "LeadWire 1", "LeadWire", "LeadWire", 5.6F, 2.1F))
        stockDatabase.insert(Stock(0, "LeadWire 1.5", "LeadWire", "LeadWire", 7.2F, 2.1F))
        stockDatabase.insert(Stock(0, "LeadWire 2.5", "LeadWire", "LeadWire", 9.95F, 2.1F))
        stockDatabase.insert(Stock(0, "LeadWire 4", "LeadWire", "LeadWire", 15.2F, 2.1F))
        stockDatabase.insert(Stock(0, "LeadWire 6", "LeadWire", "LeadWire", 21F, 2.1F))
        stockDatabase.insert(Stock(0, "LeadWire 10", "LeadWire", "LeadWire", 34.5F, 2.1F))
        stockDatabase.insert(Stock(0, "LeadWire 16", "LeadWire", "LeadWire", 56F, 2.1F))
        stockDatabase.insert(Stock(0, "Heat 1", "Sleeve", "Heat", 2.1F, 0.75F))
        stockDatabase.insert(Stock(0, "Heat 2", "Sleeve", "Heat", 2.9F, 0.75F))
        stockDatabase.insert(Stock(0, "Heat 3", "Sleeve", "Heat", 4.25F, 0.75F))
        stockDatabase.insert(Stock(0, "Heat 4", "Sleeve", "Heat", 5.5F, 0.75F))
        stockDatabase.insert(Stock(0, "Heat 5", "Sleeve", "Heat", 7.3F, 0.75F))
        stockDatabase.insert(Stock(0, "Heat 6", "Sleeve", "Heat", 8.6F, 0.75F))
        stockDatabase.insert(Stock(0, "Heat 7", "Sleeve", "Heat", 7.85F, 0.75F))
        stockDatabase.insert(Stock(0, "Heat 8", "Sleeve", "Heat", 8.5F, 0.75F))
        stockDatabase.insert(Stock(0, "Heat 9", "Sleeve", "Heat", 9.3F, 0.75F))
        stockDatabase.insert(Stock(0, "Heat 10", "Sleeve", "Heat", 10.2F, 0.75F))
        stockDatabase.insert(Stock(0, "Heat 3 Color", "Sleeve", "Heat", 4.25F, 0.85F))
        stockDatabase.insert(Stock(0, "FDR 5", "Paper", "Sheet", 305F, 1F))
        stockDatabase.insert(Stock(0, "FDR 7", "Paper", "Sheet", 295F, 1F))
        stockDatabase.insert(Stock(0, "FDR 10", "Paper", "Sheet", 285F, 1F))
        stockDatabase.insert(Stock(0, "FDR W 5", "Paper", "Nomex", 305F, 1F))
        stockDatabase.insert(Stock(0, "FDR W 7", "Paper", "Nomex", 295F, 1F))
        stockDatabase.insert(Stock(0, "FDR W 10", "Paper", "Nomex", 285F, 1F))
        stockDatabase.insert(Stock(0, "FDR D 5", "Paper", "Sheet", 325F, 1F))
        stockDatabase.insert(Stock(0, "FDR D 7", "Paper", "Sheet", 305F, 1F))
        stockDatabase.insert(Stock(0, "FDR D 10", "Paper", "Sheet", 295F, 1F))
        stockDatabase.insert(Stock(0, "GP10 7", "Paper", "Sheet", 250F, 1F))
        stockDatabase.insert(Stock(0, "GP10 10", "Paper", "Sheet", 240F, 1F))
        stockDatabase.insert(Stock(0, "GP100 7", "Paper", "Sheet", 360F, 1F))
        stockDatabase.insert(Stock(0, "GP100 10", "Paper", "Sheet", 340F, 1F))
        stockDatabase.insert(Stock(0, "CDR 7", "Paper", "Sheet", 180F, 1F))
        stockDatabase.insert(Stock(0, "CDR 10", "Paper", "Sheet", 170F, 1F))
        stockDatabase.insert(Stock(0, "BongFoge 7", "Paper", "Sheet", 250F, 1F))
        stockDatabase.insert(Stock(0, "BongFoge 10", "Paper", "Sheet", 240F, 1F))
        stockDatabase.insert(Stock(0, "Neoflex W 7", "Paper", "Nomex", 295F, 1F))
        stockDatabase.insert(Stock(0, "Neoflex W 10", "Paper", "Nomex", 285F, 1F))
        stockDatabase.insert(Stock(0, "Neoflex 10", "Paper", "Sheet", 260F, 1F))
        stockDatabase.insert(Stock(0, "OK 7", "Paper", "Sheet", 650F, 1F))
        stockDatabase.insert(Stock(0, "OK 10", "Paper", "Sheet", 550F, 1F))
        stockDatabase.insert(Stock(0, "ULM 7", "Paper", "Sheet", 425F, 1F))
        stockDatabase.insert(Stock(0, "ULM 10", "Paper", "Sheet", 405F, 1F))
        stockDatabase.insert(Stock(0, "INK 7", "Paper", "Sheet", 420F, 1F))
        stockDatabase.insert(Stock(0, "INK 10", "Paper", "Sheet", 400F, 1F))
        stockDatabase.insert(Stock(0, "UD 7", "Paper", "Sheet", 345F, 1F))
        stockDatabase.insert(Stock(0, "UD 10", "Paper", "Sheet", 325F, 1F))
        stockDatabase.insert(Stock(0, "DF 7", "Paper", "Sheet", 285F, 1F))
        stockDatabase.insert(Stock(0, "DF 10", "Paper", "Sheet", 270F, 1F))
        stockDatabase.insert(Stock(0, "China Nomex 7", "Paper", "Nomex", 240F, 1F))
        stockDatabase.insert(Stock(0, "China Nomex 10", "Paper", "Nomex", 240F, 1F))
        stockDatabase.insert(Stock(0, "China Nomex 5", "Paper", "Nomex", 250F, 1F))
        stockDatabase.insert(Stock(0, "Nomex 5", "Paper", "Nomex", 1260F, 1F))
        stockDatabase.insert(Stock(0, "Nomex 7", "Paper", "Nomex", 890F, 1F))
        stockDatabase.insert(Stock(0, "Nomex 10", "Paper", "Nomex", 790F, 1F))
        stockDatabase.insert(Stock(0, "B Nomex 5", "Paper", "Nomex", 1200F, 1F))
        stockDatabase.insert(Stock(0, "B Nomex 7", "Paper", "Nomex", 850F, 1F))
        stockDatabase.insert(Stock(0, "B Nomex 10", "Paper", "Nomex", 750F, 1F))
        stockDatabase.insert(Stock(0, "Beicolam 5", "Paper", "Nomex", 380F, 1F))
        stockDatabase.insert(Stock(0, "Beicolam 7", "Paper", "Nomex", 380F, 1F))
        stockDatabase.insert(Stock(0, "Beicolam 10", "Paper", "Nomex", 380F, 1F))
        stockDatabase.insert(Stock(0, "Garware 5", "Paper", "Film", 210F, 1F))
        stockDatabase.insert(Stock(0, "Garware 7", "Paper", "Film", 210F, 1F))
        stockDatabase.insert(Stock(0, "Garware 10", "Paper", "Film", 210F, 1F))
        stockDatabase.insert(Stock(0, "Jindal 5", "Paper", "Film", 195F, 1F))
        stockDatabase.insert(Stock(0, "Jindal 7", "Paper", "Film", 195F, 1F))
        stockDatabase.insert(Stock(0, "Jindal 10", "Paper", "Film", 195F, 1F))
        stockDatabase.insert(Stock(0, "Uncle Film", "Paper", "Film", 180F, 1F))
        stockDatabase.insert(Stock(0, "Pio 500ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Pio 1L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Pio 5L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Pio 20L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Mac 200ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Mac 500ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Mac 1L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Mac 5L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Mac 20L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Ray 200ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Ray 500ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Ray 1L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Ray 5L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Ray 20L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Bak  200ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Bak  500ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Bak  1L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Bak  5L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Bak  20L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Beck 200ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Beck 500ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Beck 1L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Beck 5L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Beck 20L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Dia 200ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Dia 500ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Dia 1L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Dia 5L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Dia 20L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Wil  200ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Wil  500ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Wil  1L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Wil  5L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Wil  20L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "A1 200ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "A1 500ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "A1 1L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "A1 5L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "A1 20L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "A1 W 200ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "A1 W 500ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "A1 W 1L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "A1 W 5L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "A1 W 20L", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Ray 100ml", "Chemical", "Varnish", 1F, 1F))
        stockDatabase.insert(Stock(0, "Epoxy 520", "Chemical", "Epoxy", 1F, 1F))
        stockDatabase.insert(Stock(0, "Hardener 758", "Chemical", "Hardener", 1F, 1F))
        stockDatabase.insert(Stock(0, "Thinner 205", "Chemical", "Thinner", 1F, 1F))
        stockDatabase.insert(Stock(0, "Ram 1", "Cotton Tape", "Cotton Tape", 210F, 1F))
        stockDatabase.insert(Stock(0, "Ram 3/4", "Cotton Tape", "Cotton Tape", 200F, 1F))
        stockDatabase.insert(Stock(0, "Ram 1/2", "Cotton Tape", "Cotton Tape", 190F, 1F))
        stockDatabase.insert(Stock(0, "Roja 1", "Cotton Tape", "Cotton Tape", 280F, 1F))
        stockDatabase.insert(Stock(0, "Roja 1/2", "Cotton Tape", "Cotton Tape", 260F, 1F))
        stockDatabase.insert(Stock(0, "Roja 3/4", "Cotton Tape", "Cotton Tape", 270F, 1F))
        stockDatabase.insert(Stock(0, "Kumar 25", "Cotton Tape", "Cotton Tape", 230F, 1F))
        stockDatabase.insert(Stock(0, "Kumar 40", "Cotton Tape", "Cotton Tape", 350F, 1F))
        stockDatabase.insert(Stock(0, "303", "Cotton Tape", "Cotton Tape", 160F, 1F))
        stockDatabase.insert(Stock(0, "303 Poly", "Cotton Tape", "Cotton Tape", 220F, 1F))
        stockDatabase.insert(Stock(0, "301 Poly", "Cotton Tape", "Cotton Tape", 220F, 1F))
        stockDatabase.insert(Stock(0, "Lotus 40 1", "Cotton Tape", "Cotton Tape", 350F, 1F))
        stockDatabase.insert(Stock(0, "Lotus 40 3/4", "Cotton Tape", "Cotton Tape", 340F, 1F))
        stockDatabase.insert(Stock(0, "Lotus 40 1/2", "Cotton Tape", "Cotton Tape", 330F, 1F))
        stockDatabase.insert(Stock(0, "Roja Poly fine", "Cotton Tape", "Cotton Tape", 220F, 1F))
        stockDatabase.insert(Stock(0, "Roja Poly", "Cotton Tape", "Cotton Tape", 220F, 1F))
        stockDatabase.insert(Stock(0, "Rubber DS", "Rubber", "Rubber", 150F, 1F))
        stockDatabase.insert(Stock(0, "Rubber SS", "Rubber", "Rubber", 130F, 1F))
        stockDatabase.insert(Stock(0, "Empire 1", "Empire Tape", "Empire Tape", 160F, 1F))
        stockDatabase.insert(Stock(0, "Empire 3/4", "Empire Tape", "Empire Tape", 150F, 1F))
        stockDatabase.insert(Stock(0, "Esol sp", "Esol", "Esol", 1F, 1F))
        stockDatabase.insert(Stock(0, "Esol local", "Esol", "Esol", 1F, 1F))
        stockDatabase.insert(Stock(0, "Esol 500", "Esol", "Esol", 1F, 1F))
        stockDatabase.insert(Stock(0, "Esol 1L", "Esol", "Esol", 1F, 1F))
        stockDatabase.insert(
            Stock(
                0,
                "SSP 1",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                920F,
                1F
            )
        )
        stockDatabase.insert(
            Stock(
                0,
                "SSP 1/2",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                920F,
                1F
            )
        )
        stockDatabase.insert(
            Stock(
                0,
                "SSP 3/4",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                960F,
                1F
            )
        )
        stockDatabase.insert(
            Stock(
                0,
                "SSP chotta",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                260F,
                1F
            )
        )
        stockDatabase.insert(
            Stock(
                0,
                "SSP Pink",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                1000F,
                1F
            )
        )
        stockDatabase.insert(
            Stock(
                0,
                "Wonder 1",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                1260F,
                1F
            )
        )
        stockDatabase.insert(
            Stock(
                0,
                "Wonder 3/4",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                1260F,
                1F
            )
        )
        stockDatabase.insert(
            Stock(
                0,
                "Wonder 1/2",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                1260F,
                1F
            )
        )
        stockDatabase.insert(
            Stock(
                0,
                "Wonder Pink",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                1380F,
                1F
            )
        )
        stockDatabase.insert(
            Stock(
                0,
                "Wonder chotta",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                260F,
                1F
            )
        )
        stockDatabase.insert(
            Stock(
                0,
                "Trinity",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                1480F,
                1F
            )
        )
        stockDatabase.insert(Stock(0, "JS Reel 3mm", "Cotton Tape", "Cotton Tape", 550F, 1F))
        stockDatabase.insert(Stock(0, "JS Reel 4mm", "Cotton Tape", "Cotton Tape", 550F, 1F))
        stockDatabase.insert(Stock(0, "JS Reel 6mm", "Cotton Tape", "Cotton Tape", 600F, 1F))
        stockDatabase.insert(Stock(0, "JS Reel 8mm", "Cotton Tape", "Cotton Tape", 650F, 1F))
        stockDatabase.insert(Stock(0, "JS Cotton", "Cotton Tape", "Cotton Tape", 300F, 1F))
        stockDatabase.insert(
            Stock(
                0,
                "Glass Cloth VS 7mil",
                "Glass Cloth",
                "Glass Cloth",
                1080F,
                1F
            )
        )
        stockDatabase.insert(
            Stock(
                0,
                "Glass Cloth VS 5mil",
                "Glass Cloth",
                "Glass Cloth",
                1150F,
                1F
            )
        )
        stockDatabase.insert(
            Stock(
                0,
                "Glass Cloth VS 10mil",
                "Glass Cloth",
                "Glass Cloth",
                1100F,
                1F
            )
        )
        stockDatabase.insert(
            Stock(
                0,
                "Glass Cloth JS 7mil",
                "Glass Cloth",
                "Glass Cloth",
                1300F,
                1F
            )
        )
        stockDatabase.insert(
            Stock(
                0,
                "Glass Cloth JS 10mil",
                "Glass Cloth",
                "Glass Cloth",
                1330F,
                1F
            )
        )

        shopStockDatabase.insert(
            ShopStock(
                0,
                "F class 1mm",
                "Sleeve",
                "F class",
                0,
                100,
                50,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "F class 1.5mm",
                "Sleeve",
                "F class",
                0,
                20,
                2,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "F class 2mm",
                "Sleeve",
                "F class",
                0,
                80,
                35,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "F class 3mm",
                "Sleeve",
                "F class",
                0,
                90,
                30,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "F class 4mm",
                "Sleeve",
                "F class",
                0,
                50,
                25,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "F class 5mm",
                "Sleeve",
                "F class",
                0,
                40,
                20,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "F class 6mm",
                "Sleeve",
                "F class",
                0,
                30,
                15,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "F class 8mm",
                "Sleeve",
                "F class",
                0,
                40,
                20,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "F class 10mm",
                "Sleeve",
                "F class",
                0,
                20,
                5,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "F class 12mm",
                "Sleeve",
                "F class",
                0,
                20,
                5,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "F class 16mm",
                "Sleeve",
                "F class",
                0,
                20,
                2,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "B class 1mm",
                "Sleeve",
                "B class",
                0,
                100,
                50,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "B class 1.5mm",
                "Sleeve",
                "B class",
                0,
                20,
                2,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "B class 2mm",
                "Sleeve",
                "B class",
                0,
                80,
                35,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "B class 3mm",
                "Sleeve",
                "B class",
                0,
                90,
                30,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "B class 4mm",
                "Sleeve",
                "B class",
                0,
                50,
                25,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "B class 5mm",
                "Sleeve",
                "B class",
                0,
                40,
                20,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "B class 6mm",
                "Sleeve",
                "B class",
                0,
                30,
                15,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "B class 8mm",
                "Sleeve",
                "B class",
                0,
                40,
                20,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "B class 10mm",
                "Sleeve",
                "B class",
                0,
                20,
                5,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "B class 12mm",
                "Sleeve",
                "B class",
                0,
                20,
                5,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "B class 16mm",
                "Sleeve",
                "B class",
                0,
                20,
                2,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "B class 20mm",
                "Sleeve",
                "B class",
                0,
                20,
                2,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "B class 25mm",
                "Sleeve",
                "B class",
                0,
                20,
                2,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "A class 1mm",
                "Sleeve",
                "A class",
                0,
                50,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "A class 2mm",
                "Sleeve",
                "A class",
                0,
                50,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "A class 3mm",
                "Sleeve",
                "A class",
                0,
                50,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "A class 4mm",
                "Sleeve",
                "A class",
                0,
                50,
                10,
                0F
            )
        )
        shopStockDatabase.insert(ShopStock(0, "A class 5mm", "Sleeve", "A class", 0, 20, 5, 0F))
        shopStockDatabase.insert(ShopStock(0, "A class 6mm", "Sleeve", "A class", 0, 20, 5, 0F))
        shopStockDatabase.insert(ShopStock(0, "A class 8mm", "Sleeve", "A class", 0, 20, 5, 0F))
        shopStockDatabase.insert(
            ShopStock(
                0,
                "A class 10mm",
                "Sleeve",
                "A class",
                0,
                20,
                5,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "A class 12mm",
                "Sleeve",
                "A class",
                0,
                20,
                5,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 1mm",
                "Sleeve",
                "H class",
                0,
                80,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 1.5mm",
                "Sleeve",
                "H class",
                0,
                10,
                2,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 2mm",
                "Sleeve",
                "H class",
                0,
                35,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 3mm",
                "Sleeve",
                "H class",
                0,
                42,
                10,
                0F
            )
        )
        shopStockDatabase.insert(ShopStock(0, "H class 4mm", "Sleeve", "H class", 0, 30, 5, 0F))
        shopStockDatabase.insert(ShopStock(0, "H class 5mm", "Sleeve", "H class", 0, 20, 5, 0F))
        shopStockDatabase.insert(ShopStock(0, "H class 6mm", "Sleeve", "H class", 0, 16, 3, 0F))
        shopStockDatabase.insert(ShopStock(0, "H class 8mm", "Sleeve", "H class", 0, 21, 3, 0F))
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 10mm",
                "Sleeve",
                "H class",
                0,
                20,
                2,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 12mm",
                "Sleeve",
                "H class",
                0,
                20,
                1,
                0F
            )
        )
        shopStockDatabase.insert(ShopStock(0, "H class 14mm", "Sleeve", "H class", 0, 0, 0, 0F))
        shopStockDatabase.insert(ShopStock(0, "H class 16mm", "Sleeve", "H class", 0, 0, 0, 0F))
        shopStockDatabase.insert(ShopStock(0, "H class 18mm", "Sleeve", "H class", 0, 0, 0, 0F))
        shopStockDatabase.insert(ShopStock(0, "H class 20mm", "Sleeve", "H class", 0, 0, 0, 0F))
        shopStockDatabase.insert(ShopStock(0, "H class 25mm", "Sleeve", "H class", 0, 0, 0, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 1mm", "Sleeve", "China", 0, 3, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 1.5mm", "Sleeve", "China", 0, 10, 5, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 2mm", "Sleeve", "China", 0, 5, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 3mm", "Sleeve", "China", 0, 5, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 4mm", "Sleeve", "China", 0, 5, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 5mm", "Sleeve", "China", 0, 42, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 6mm", "Sleeve", "China", 0, 5, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 8mm", "Sleeve", "China", 0, 5, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 10mm", "Sleeve", "China", 0, 20, 2, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 12mm", "Sleeve", "China", 0, 20, 2, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 14mm", "Sleeve", "China", 0, 0, 0, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 16mm", "Sleeve", "China", 0, 0, 0, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 18mm", "Sleeve", "China", 0, 0, 0, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 20mm", "Sleeve", "China", 0, 0, 0, 0F))
        shopStockDatabase.insert(ShopStock(0, "China 25mm", "Sleeve", "China", 0, 0, 0, 0F))
        shopStockDatabase.insert(ShopStock(0, "Empire 1mm", "Sleeve", "Empire", 0, 100, 5, 0F))
        shopStockDatabase.insert(ShopStock(0, "Empire 2mm", "Sleeve", "Empire", 0, 50, 5, 0F))
        shopStockDatabase.insert(ShopStock(0, "Empire 3mm", "Sleeve", "Empire", 0, 50, 5, 0F))
        shopStockDatabase.insert(ShopStock(0, "Empire 4mm", "Sleeve", "Empire", 0, 50, 5, 0F))
        shopStockDatabase.insert(ShopStock(0, "Empire 5mm", "Sleeve", "Empire", 0, 50, 5, 0F))
        shopStockDatabase.insert(ShopStock(0, "Empire 6mm", "Sleeve", "Empire", 0, 50, 5, 0F))
        shopStockDatabase.insert(ShopStock(0, "Empire 8mm", "Sleeve", "Empire", 0, 20, 5, 0F))
        shopStockDatabase.insert(ShopStock(0, "Empire 10mm", "Sleeve", "Empire", 0, 20, 5, 0F))
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 1mm Yellow ",
                "Sleeve",
                "China",
                0,
                20,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 1mm Green ",
                "Sleeve",
                "China",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 1mm Blue ",
                "Sleeve",
                "China",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 1mm Black ",
                "Sleeve",
                "China",
                0,
                20,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 1.5mm Yellow ",
                "Sleeve",
                "China",
                0,
                20,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 1.5mm Green ",
                "Sleeve",
                "China",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 1.5mm Blue ",
                "Sleeve",
                "China",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 1.5mm Black ",
                "Sleeve",
                "China",
                0,
                20,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 2mm Yellow ",
                "Sleeve",
                "China",
                0,
                20,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 2mm Green ",
                "Sleeve",
                "China",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 2mm Blue ",
                "Sleeve",
                "China",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 2mm Black ",
                "Sleeve",
                "China",
                0,
                20,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 3mm Yellow ",
                "Sleeve",
                "China",
                0,
                20,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 3mm Green ",
                "Sleeve",
                "China",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 3mm Blue ",
                "Sleeve",
                "China",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 3mm Black ",
                "Sleeve",
                "China",
                0,
                20,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 4mm Yellow ",
                "Sleeve",
                "China",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 4mm Green ",
                "Sleeve",
                "China",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 4mm Blue ",
                "Sleeve",
                "China",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "China 4mm Black ",
                "Sleeve",
                "China",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 1mm Black",
                "Sleeve",
                "H class",
                0,
                10,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 2mm Black",
                "Sleeve",
                "H class",
                0,
                10,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 3mm Black",
                "Sleeve",
                "H class",
                0,
                10,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 4mm Black",
                "Sleeve",
                "H class",
                0,
                10,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 5mm Black",
                "Sleeve",
                "H class",
                0,
                10,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 6mm Black",
                "Sleeve",
                "H class",
                0,
                10,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 8mm Black",
                "Sleeve",
                "H class",
                0,
                10,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 4mm Yellow ",
                "Sleeve",
                "H class",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 4mm Green ",
                "Sleeve",
                "H class",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "H class 4mm Blue ",
                "Sleeve",
                "H class",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "LeadWire 0.5",
                "LeadWire",
                "LeadWire",
                0,
                10,
                5,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "LeadWire 0.75",
                "LeadWire",
                "LeadWire",
                0,
                10,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "LeadWire 1",
                "LeadWire",
                "LeadWire",
                0,
                10,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "LeadWire 1.5",
                "LeadWire",
                "LeadWire",
                0,
                10,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "LeadWire 2.5",
                "LeadWire",
                "LeadWire",
                0,
                10,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "LeadWire 4",
                "LeadWire",
                "LeadWire",
                0,
                5,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "LeadWire 6",
                "LeadWire",
                "LeadWire",
                0,
                2,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "LeadWire 10",
                "LeadWire",
                "LeadWire",
                0,
                1,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "LeadWire 16",
                "LeadWire",
                "LeadWire",
                0,
                1,
                1,
                0F
            )
        )
        shopStockDatabase.insert(ShopStock(0, "Heat 1", "Sleeve", "Heat", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Heat 2", "Sleeve", "Heat", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Heat 3", "Sleeve", "Heat", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Heat 4", "Sleeve", "Heat", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Heat 5", "Sleeve", "Heat", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Heat 6", "Sleeve", "Heat", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Heat 7", "Sleeve", "Heat", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Heat 8", "Sleeve", "Heat", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Heat 9", "Sleeve", "Heat", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Heat 10", "Sleeve", "Heat", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Heat 3 Color", "Sleeve", "Heat", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "FDR 5", "Paper", "Sheet", 0, 0, 50, 0F))
        shopStockDatabase.insert(ShopStock(0, "FDR 7", "Paper", "Sheet", 0, 0, 50, 0F))
        shopStockDatabase.insert(ShopStock(0, "FDR 10", "Paper", "Sheet", 0, 0, 50, 0F))
        shopStockDatabase.insert(ShopStock(0, "FDR W 5", "Paper", "Nomex", 0, 0, 10, 0F))
        shopStockDatabase.insert(ShopStock(0, "FDR W 7", "Paper", "Nomex", 0, 0, 10, 0F))
        shopStockDatabase.insert(ShopStock(0, "FDR W 10", "Paper", "Nomex", 0, 0, 10, 0F))
        shopStockDatabase.insert(ShopStock(0, "FDR D 5", "Paper", "Sheet", 0, 0, 10, 0F))
        shopStockDatabase.insert(ShopStock(0, "FDR D 7", "Paper", "Sheet", 0, 0, 10, 0F))
        shopStockDatabase.insert(ShopStock(0, "FDR D 10", "Paper", "Sheet", 0, 0, 10, 0F))
        shopStockDatabase.insert(ShopStock(0, "GP10 7", "Paper", "Sheet", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "GP10 10", "Paper", "Sheet", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "GP100 7", "Paper", "Sheet", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "GP100 10", "Paper", "Sheet", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "CDR 7", "Paper", "Sheet", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "CDR 10", "Paper", "Sheet", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "BongFoge 7", "Paper", "Sheet", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "BongFoge 10", "Paper", "Sheet", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "Neoflex W 7", "Paper", "Nomex", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "Neoflex W 10", "Paper", "Nomex", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "Neoflex 10", "Paper", "Sheet", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "OK 7", "Paper", "Sheet", 0, 50, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "OK 10", "Paper", "Sheet", 0, 50, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "ULM 7", "Paper", "Sheet", 0, 50, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "ULM 10", "Paper", "Sheet", 0, 50, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "INK 7", "Paper", "Sheet", 0, 50, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "INK 10", "Paper", "Sheet", 0, 50, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "UD 7", "Paper", "Sheet", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "UD 10", "Paper", "Sheet", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "DF 7", "Paper", "Sheet", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "DF 10", "Paper", "Sheet", 0, 0, 25, 0F))
        shopStockDatabase.insert(ShopStock(0, "China Nomex 7", "Paper", "Nomex", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "China Nomex 10", "Paper", "Nomex", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "China Nomex 5", "Paper", "Nomex", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Nomex 5", "Paper", "Nomex", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Nomex 7", "Paper", "Nomex", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Nomex 10", "Paper", "Nomex", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "B Nomex 5", "Paper", "Nomex", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "B Nomex 7", "Paper", "Nomex", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "B Nomex 10", "Paper", "Nomex", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Beicolam 5", "Paper", "Nomex", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Beicolam 7", "Paper", "Nomex", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Beicolam 10", "Paper", "Nomex", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Garware 5", "Paper", "Film", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Garware 7", "Paper", "Film", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Garware 10", "Paper", "Film", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Jindal 5", "Paper", "Film", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Jindal 7", "Paper", "Film", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Jindal 10", "Paper", "Film", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Uncle Film", "Paper", "Film", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Pio 500ml", "Chemical", "Varnish", 0, 5, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Pio 1L", "Chemical", "Varnish", 0, 5, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Pio 5L", "Chemical", "Varnish", 0, 2, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Pio 20L", "Chemical", "Varnish", 0, 1, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Mac 200ml", "Chemical", "Varnish", 0, 5, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Mac 500ml", "Chemical", "Varnish", 0, 10, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Mac 1L", "Chemical", "Varnish", 0, 10, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Mac 5L", "Chemical", "Varnish", 0, 2, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Mac 20L", "Chemical", "Varnish", 0, 2, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Ray 200ml", "Chemical", "Varnish", 0, 2, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Ray 500ml", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Ray 1L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Ray 5L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Ray 20L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Bak  200ml", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Bak  500ml", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Bak  1L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Bak  5L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Bak  20L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Beck 200ml", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Beck 500ml", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Beck 1L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Beck 5L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Beck 20L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Dia 200ml", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Dia 500ml", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Dia 1L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Dia 5L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Dia 20L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Wil  200ml", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Wil  500ml", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Wil  1L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Wil  5L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Wil  20L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "A1 200ml", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "A1 500ml", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "A1 1L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "A1 5L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "A1 20L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "A1 W 200ml", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "A1 W 500ml", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "A1 W 1L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "A1 W 5L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "A1 W 20L", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Ray 100ml", "Chemical", "Varnish", 0, 0, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Epoxy 520", "Chemical", "Epoxy", 0, 0, 1, 0F))
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Hardener 758",
                "Chemical",
                "Hardener",
                0,
                0,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Thinner 205",
                "Chemical",
                "Thinner",
                0,
                0,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Ram 1",
                "Cotton Tape",
                "Cotton Tape",
                0,
                100,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Ram 3/4",
                "Cotton Tape",
                "Cotton Tape",
                0,
                20,
                5,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Ram 1/2",
                "Cotton Tape",
                "Cotton Tape",
                0,
                20,
                5,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Roja 1",
                "Cotton Tape",
                "Cotton Tape",
                0,
                100,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Roja 1/2",
                "Cotton Tape",
                "Cotton Tape",
                0,
                20,
                5,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Roja 3/4",
                "Cotton Tape",
                "Cotton Tape",
                0,
                20,
                5,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Kumar 25",
                "Cotton Tape",
                "Cotton Tape",
                0,
                100,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Kumar 40",
                "Cotton Tape",
                "Cotton Tape",
                0,
                50,
                5,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "303",
                "Cotton Tape",
                "Cotton Tape",
                0,
                100,
                20,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "303 Poly",
                "Cotton Tape",
                "Cotton Tape",
                0,
                50,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "301 Poly",
                "Cotton Tape",
                "Cotton Tape",
                0,
                50,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Lotus 40 1",
                "Cotton Tape",
                "Cotton Tape",
                0,
                0,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Lotus 40 3/4",
                "Cotton Tape",
                "Cotton Tape",
                0,
                0,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Lotus 40 1/2",
                "Cotton Tape",
                "Cotton Tape",
                0,
                0,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Roja Poly fine",
                "Cotton Tape",
                "Cotton Tape",
                0,
                50,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Roja Poly",
                "Cotton Tape",
                "Cotton Tape",
                0,
                0,
                10,
                0F
            )
        )
        shopStockDatabase.insert(ShopStock(0, "Rubber DS", "Rubber", "Rubber", 0, 50, 10, 0F))
        shopStockDatabase.insert(ShopStock(0, "Rubber SS", "Rubber", "Rubber", 0, 50, 10, 0F))
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Empire 1",
                "Empire Tape",
                "Empire Tape",
                0,
                50,
                10,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Empire 3/4",
                "Empire Tape",
                "Empire Tape",
                0,
                50,
                10,
                0F
            )
        )
        shopStockDatabase.insert(ShopStock(0, "Esol sp", "Esol", "Esol", 0, 1, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Esol local", "Esol", "Esol", 0, 2, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Esol 500", "Esol", "Esol", 0, 2, 1, 0F))
        shopStockDatabase.insert(ShopStock(0, "Esol 1L", "Esol", "Esol", 0, 1, 1, 0F))
        shopStockDatabase.insert(
            ShopStock(
                0,
                "SSP 1",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                0,
                2,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "SSP 1/2",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                0,
                6,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "SSP 3/4",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                0,
                6,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "SSP chotta",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                0,
                2,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "SSP Pink",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                0,
                6,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Wonder 1",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                0,
                2,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Wonder 3/4",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                0,
                6,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Wonder 1/2",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                0,
                6,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Wonder Pink",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                0,
                6,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Wonder chotta",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                0,
                0,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Trinity",
                "Adhv Cotton Tape",
                "Adhv Cotton Tape",
                0,
                0,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "JS Reel 3mm",
                "Cotton Tape",
                "Cotton Tape",
                0,
                5,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "JS Reel 4mm",
                "Cotton Tape",
                "Cotton Tape",
                0,
                5,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "JS Reel 6mm",
                "Cotton Tape",
                "Cotton Tape",
                0,
                5,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "JS Reel 8mm",
                "Cotton Tape",
                "Cotton Tape",
                0,
                5,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "JS Cotton",
                "Cotton Tape",
                "Cotton Tape",
                0,
                5,
                1,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Glass Cloth VS 7mil",
                "Glass Cloth",
                "Glass Cloth",
                0,
                10,
                2,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Glass Cloth VS 5mil",
                "Glass Cloth",
                "Glass Cloth",
                0,
                2,
                2,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Glass Cloth VS 10mil",
                "Glass Cloth",
                "Glass Cloth",
                0,
                10,
                2,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Glass Cloth JS 7mil",
                "Glass Cloth",
                "Glass Cloth",
                0,
                10,
                2,
                0F
            )
        )
        shopStockDatabase.insert(
            ShopStock(
                0,
                "Glass Cloth JS 10mil",
                "Glass Cloth",
                "Glass Cloth",
                0,
                10,
                2,
                0F
            )
        )
    }
}
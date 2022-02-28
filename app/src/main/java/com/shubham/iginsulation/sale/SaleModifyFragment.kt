package com.shubham.iginsulation.sale

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.BackupRestore
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.customer.CustomerDatabase
import com.shubham.iginsulation.database.customer.CustomerDatabaseDao
import com.shubham.iginsulation.database.sale.Sale
import com.shubham.iginsulation.database.sale.SaleDatabase
import com.shubham.iginsulation.database.sale.SaleDatabaseDao
import com.shubham.iginsulation.database.saledetails.SaleDetails
import com.shubham.iginsulation.database.saledetails.SaleDetailsDatabase
import com.shubham.iginsulation.database.saledetails.SaleDetailsDatabaseDao
import com.shubham.iginsulation.database.stock.StockDatabase
import com.shubham.iginsulation.database.stock.StockDatabaseDao
import com.shubham.iginsulation.databinding.FragmentSaleModifyBinding
import java.util.*
import kotlin.math.roundToInt

class SaleModifyFragment : Fragment() {

    private lateinit var binding: FragmentSaleModifyBinding
    private lateinit var saleDatabase: SaleDatabaseDao
    private lateinit var saleDetailsDatabase: SaleDetailsDatabaseDao
    private lateinit var customerDatabase: CustomerDatabaseDao
    private lateinit var customers: List<String>
    private lateinit var stockDatabase: StockDatabaseDao
    private lateinit var stocks: List<String>

    private var id = 0L
    private var name = ""
    private var prevTotal = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sale_modify, container, false
        )

        saleDatabase =
            SaleDatabase.getInstance(requireContext()).saleDatabaseDao

        saleDetailsDatabase =
            SaleDetailsDatabase.getInstance(requireContext()).saleDetailsDatabaseDao

        customerDatabase = CustomerDatabase.getInstance(requireContext()).customerDatabaseDao

        stockDatabase = StockDatabase.getInstance(requireContext()).stockDatabaseDao

        customers = customerDatabase.getCustomerNames()
        val adapterCustomer: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            customers
        )
        binding.saleModifyName.threshold = 1
        binding.saleModifyName.setAdapter(adapterCustomer)

        stocks = stockDatabase.getStockNames()
        val adapterStock: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            stocks
        )

        binding.saleModifyOneName.threshold = 1
        binding.saleModifyOneName.setAdapter(adapterStock)

        binding.saleModifyTwoName.threshold = 1
        binding.saleModifyTwoName.setAdapter(adapterStock)

        binding.saleModifyThreeName.threshold = 1
        binding.saleModifyThreeName.setAdapter(adapterStock)

        binding.saleModifyFourName.threshold = 1
        binding.saleModifyFourName.setAdapter(adapterStock)

        binding.saleModifyFiveName.threshold = 1
        binding.saleModifyFiveName.setAdapter(adapterStock)

        binding.saleModifySixName.threshold = 1
        binding.saleModifySixName.setAdapter(adapterStock)

        binding.saleModifySevenName.threshold = 1
        binding.saleModifySevenName.setAdapter(adapterStock)

        binding.saleModifyEightName.threshold = 1
        binding.saleModifyEightName.setAdapter(adapterStock)

        binding.saleModifyNineName.threshold = 1
        binding.saleModifyNineName.setAdapter(adapterStock)

        binding.saleModifyTenName.threshold = 1
        binding.saleModifyTenName.setAdapter(adapterStock)

        binding.saleModifyElevenName.threshold = 1
        binding.saleModifyElevenName.setAdapter(adapterStock)

        binding.saleModifyTwelveName.threshold = 1
        binding.saleModifyTwelveName.setAdapter(adapterStock)

        binding.saleModifyThirteenName.threshold = 1
        binding.saleModifyThirteenName.setAdapter(adapterStock)

        binding.saleModifyFourteenName.threshold = 1
        binding.saleModifyFourteenName.setAdapter(adapterStock)

        binding.saleModifyFifteenName.threshold = 1
        binding.saleModifyFifteenName.setAdapter(adapterStock)

        binding.saleModifySixteenName.threshold = 1
        binding.saleModifySixteenName.setAdapter(adapterStock)

        binding.saleModifySeventeenName.threshold = 1
        binding.saleModifySeventeenName.setAdapter(adapterStock)

        binding.saleModifyEighteenName.threshold = 1
        binding.saleModifyEighteenName.setAdapter(adapterStock)

        binding.saleModifyNineteenName.threshold = 1
        binding.saleModifyNineteenName.setAdapter(adapterStock)

        binding.saleModifyTwentyName.threshold = 1
        binding.saleModifyTwentyName.setAdapter(adapterStock)

        val args = SaleDetailFragmentArgs.fromBundle(requireArguments())
        id = args.id

        val saleDetails = fetchSale(id)
        binding.saleModifyModify.setOnClickListener {
            if (binding.saleModifyName.text.toString() != "" && customers.contains(binding.saleModifyName.text.toString())) {
                val sale = Sale()
                sale.id = id
                sale.cash = binding.cash.isChecked
                if (binding.saleModifyName.text.toString() != "")
                    sale.name = binding.saleModifyName.text.toString()

                if (binding.saleModifyDate.text.toString() != "")
                    sale.date = binding.saleModifyDate.text.toString()

                if (binding.saleModifyTransport.text.toString() != "")
                    sale.transport =
                        if (binding.saleModifyTransport.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyTransport.text.toString().toFloat()

                if (binding.saleModifyOther.text.toString() != "")
                    sale.otherCharges =
                        if (binding.saleModifyOther.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyOther.text.toString().toFloat()

                var total = 0

                if (binding.saleModifyOneName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[0].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyOneName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyOneQuantity.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyOneQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyOneRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyOneRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyOnePercentage.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyOnePercentage.text.toString().toFloat()

                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyTwoName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[1].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyTwoName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyTwoQuantity.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyTwoQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyTwoRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyTwoRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyTwoPercentage.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyTwoPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyThreeName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[2].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyThreeName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyThreeQuantity.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyThreeQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyThreeRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyThreeRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyThreePercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyThreePercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyFourName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[3].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyFourName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyFourQuantity.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyFourQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyFourRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyFourRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyFourPercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyFourPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyFiveName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[4].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyFiveName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyFiveQuantity.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyFiveQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyFiveRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyFiveRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyFivePercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyFivePercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifySixName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[5].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifySixName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifySixQuantity.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifySixQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifySixRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifySixRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifySixPercentage.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifySixPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifySevenName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[6].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifySevenName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifySevenQuantity.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifySevenQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifySevenRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifySevenRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifySevenPercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifySevenPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyEightName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[7].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyEightName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyEightQuantity.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyEightQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyEightRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyEightRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyEightPercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyEightPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyNineName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[8].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyNineName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyNineQuantity.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyNineQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyNineRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyNineRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyNinePercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyNinePercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyTenName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[9].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyTenName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyTenQuantity.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyTenQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyTenRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyTenRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyTenPercentage.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyTenPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyElevenName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[10].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyElevenName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyElevenQuantity.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyElevenQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyElevenRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyElevenRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyElevenPercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyElevenPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyTwelveName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[11].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyTwelveName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyTwelveQuantity.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyTwelveQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyTwelveRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyTwelveRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyTwelvePercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyTwelvePercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyThirteenName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[12].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyThirteenName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyThirteenQuantity.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyThirteenQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyThirteenRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyThirteenRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyThirteenPercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyThirteenPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyFourteenName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[13].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyFourteenName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyFourteenQuantity.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyFourteenQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyFourteenRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyFourteenRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyFourteenPercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyFourteenPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyFifteenName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[14].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyFifteenName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyFifteenQuantity.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyFifteenQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyFifteenRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyFifteenRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyFifteenPercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyFifteenPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifySixteenName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[15].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifySixteenName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifySixteenQuantity.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifySixteenQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifySixteenRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifySixteenRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifySixteenPercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifySixteenPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifySeventeenName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[16].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifySeventeenName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifySeventeenQuantity.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifySeventeenQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifySeventeenRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifySeventeenRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifySeventeenPercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifySeventeenPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyEighteenName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[17].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyEighteenName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyEighteenQuantity.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyEighteenQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyEighteenRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyEighteenRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyEighteenPercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyEighteenPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyNineteenName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[18].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyNineteenName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyNineteenQuantity.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyNineteenQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyNineteenRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyNineteenRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyNineteenPercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyNineteenPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                if (binding.saleModifyTwentyName.text.toString() != "") {
                    val saleModify = SaleDetails()
                    saleModify.id = saleDetails[19].id
                    saleModify.customer = sale.name
                    saleModify.saleId = id
                    saleModify.item = binding.saleModifyTwentyName.text.toString()
                    saleModify.quantity =
                        if (binding.saleModifyTwentyQuantity.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyTwentyQuantity.text.toString().toFloat()

                    saleModify.rate =
                        if (binding.saleModifyTwentyRate.text.toString().toFloatOrNull() == null)
                            0F
                        else binding.saleModifyTwentyRate.text.toString().toFloat()

                    saleModify.percentage =
                        if (binding.saleModifyTwentyPercentage.text.toString()
                                .toFloatOrNull() == null
                        )
                            0F
                        else binding.saleModifyTwentyPercentage.text.toString().toFloat()
                    saleModify.total =
                        (saleModify.quantity * saleModify.rate * saleModify.percentage).roundToInt()
                    total += saleModify.total
                    saleDetailsDatabase.update(saleModify)
                }

                total = (total + sale.transport + sale.otherCharges).toInt()
                sale.amount = total
                saleDatabase.update(sale)
                var balance = customerDatabase.getCustomerCurrentBalance(name)
                if (name == sale.name) {
                    balance = balance - prevTotal + total
                    customerDatabase.setCustomerCurrentBalance(balance, sale.name)
                } else {
                    customerDatabase.setCustomerCurrentBalance(balance - prevTotal, name)
                    val balance2 = customerDatabase.getCustomerCurrentBalance(sale.name)
                    customerDatabase.setCustomerCurrentBalance(balance2 + total, sale.name)
                }
                BackupRestore.backup(context, "sale")
                BackupRestore.backup(context, "sale_details")
                BackupRestore.backup(context, "customer")
                view?.findNavController()?.navigate(
                    SaleModifyFragmentDirections.actionSaleModifyFragmentToSaleDetailFragment(id)
                )

            } else
                Toast.makeText(context, "Customer not present ", Toast.LENGTH_SHORT).show()
        }

        binding.saleModifyDate.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this.requireContext(),
                { _, yearPick, monthOfYear, dayOfMonth ->
                    val text = dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + yearPick
                    binding.saleModifyDate.setText(text)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        return binding.root
    }

    private fun fetchSale(id: Long): List<SaleDetails> {
        val sale = if (id == 0L) {
            try {
                saleDatabase.getLastSale()
            } catch (e: Exception) {
                e.printStackTrace()
                Sale()
            }
        } else {
            try {
                saleDatabase.get(id)!!
            } catch (e: Exception) {
                e.printStackTrace()
                Sale()
            }
        }
        val saleDetails = try {
            saleDetailsDatabase.getSaleDetails1(sale.id)
        } catch (e: Exception) {
            e.printStackTrace()
            listOf()
        }
        setSaleData(sale, saleDetails)
        name = sale.name
        prevTotal = sale.amount
        return saleDetails
    }

    private fun setSaleData(
        sale: Sale, saleDetails: List<SaleDetails>
    ) {
        binding.cash.text = sale.cash.toString()
        binding.cash.isEnabled = false
        binding.saleModifyName.setText(sale.name)
        binding.saleModifyDate.setText(sale.date)
        binding.saleModifyTransport.setText(sale.transport.toString())
        binding.saleModifyOther.setText(sale.otherCharges.toString())
        binding.saleModifyTotal.setText(sale.amount.toString())

        val numberOfItems = saleDetails.size

        if (numberOfItems > 0) {
            binding.saleModifyOneName.setText(saleDetails[0].item)
            binding.saleModifyOneQuantity.setText(saleDetails[0].quantity.toString())
            binding.saleModifyOneRate.setText(saleDetails[0].rate.toString())
            binding.saleModifyOnePercentage.setText(saleDetails[0].percentage.toString())
        }

        if (numberOfItems > 1) {
            binding.saleModifyTwoName.setText(saleDetails[1].item)
            binding.saleModifyTwoQuantity.setText(saleDetails[1].quantity.toString())
            binding.saleModifyTwoRate.setText(saleDetails[1].rate.toString())
            binding.saleModifyTwoPercentage.setText(saleDetails[1].total.toString())
        }

        if (numberOfItems > 2) {
            binding.saleModifyThreeName.setText(saleDetails[2].item)
            binding.saleModifyThreeQuantity.setText(saleDetails[2].quantity.toString())
            binding.saleModifyThreeRate.setText(saleDetails[2].rate.toString())
            binding.saleModifyThreePercentage.setText(saleDetails[2].total.toString())
        }

        if (numberOfItems > 3) {
            binding.saleModifyFourName.setText(saleDetails[3].item)
            binding.saleModifyFourQuantity.setText(saleDetails[3].quantity.toString())
            binding.saleModifyFourRate.setText(saleDetails[3].rate.toString())
            binding.saleModifyFourPercentage.setText(saleDetails[3].total.toString())
        }

        if (numberOfItems > 4) {
            binding.saleModifyFiveName.setText(saleDetails[4].item)
            binding.saleModifyFiveQuantity.setText(saleDetails[4].quantity.toString())
            binding.saleModifyFiveRate.setText(saleDetails[4].rate.toString())
            binding.saleModifyFivePercentage.setText(saleDetails[4].total.toString())
        }

        if (numberOfItems > 5) {
            binding.saleModifySixName.setText(saleDetails[5].item)
            binding.saleModifySixQuantity.setText(saleDetails[5].quantity.toString())
            binding.saleModifySixRate.setText(saleDetails[5].rate.toString())
            binding.saleModifySixPercentage.setText(saleDetails[5].total.toString())
        }

        if (numberOfItems > 6) {
            binding.saleModifySevenName.setText(saleDetails[6].item)
            binding.saleModifySevenQuantity.setText(saleDetails[6].quantity.toString())
            binding.saleModifySevenRate.setText(saleDetails[6].rate.toString())
            binding.saleModifySevenPercentage.setText(saleDetails[6].total.toString())
        }

        if (numberOfItems > 7) {
            binding.saleModifyEightName.setText(saleDetails[7].item)
            binding.saleModifyEightQuantity.setText(saleDetails[7].quantity.toString())
            binding.saleModifyEightRate.setText(saleDetails[7].rate.toString())
            binding.saleModifyEightPercentage.setText(saleDetails[7].total.toString())
        }

        if (numberOfItems > 8) {
            binding.saleModifyNineName.setText(saleDetails[8].item)
            binding.saleModifyNineQuantity.setText(saleDetails[8].quantity.toString())
            binding.saleModifyNineRate.setText(saleDetails[8].rate.toString())
            binding.saleModifyNinePercentage.setText(saleDetails[8].total.toString())
        }

        if (numberOfItems > 9) {
            binding.saleModifyTenName.setText(saleDetails[9].item)
            binding.saleModifyTenQuantity.setText(saleDetails[9].quantity.toString())
            binding.saleModifyTenRate.setText(saleDetails[9].rate.toString())
            binding.saleModifyTenPercentage.setText(saleDetails[9].total.toString())
        }

        if (numberOfItems > 10) {
            binding.saleModifyElevenName.setText(saleDetails[10].item)
            binding.saleModifyElevenQuantity.setText(saleDetails[10].quantity.toString())
            binding.saleModifyElevenRate.setText(saleDetails[10].rate.toString())
            binding.saleModifyElevenPercentage.setText(saleDetails[10].total.toString())
        }

        if (numberOfItems > 11) {
            binding.saleModifyTwelveName.setText(saleDetails[11].item)
            binding.saleModifyTwelveQuantity.setText(saleDetails[11].quantity.toString())
            binding.saleModifyTwelveRate.setText(saleDetails[11].rate.toString())
            binding.saleModifyTwelvePercentage.setText(saleDetails[11].total.toString())
        }

        if (numberOfItems > 12) {
            binding.saleModifyThirteenName.setText(saleDetails[12].item)
            binding.saleModifyThirteenQuantity.setText(saleDetails[12].quantity.toString())
            binding.saleModifyThirteenRate.setText(saleDetails[12].rate.toString())
            binding.saleModifyThirteenPercentage.setText(saleDetails[12].total.toString())
        }

        if (numberOfItems > 13) {
            binding.saleModifyFourteenName.setText(saleDetails[13].item)
            binding.saleModifyFourteenQuantity.setText(saleDetails[13].quantity.toString())
            binding.saleModifyFourteenRate.setText(saleDetails[13].rate.toString())
            binding.saleModifyFourteenPercentage.setText(saleDetails[13].total.toString())
        }

        if (numberOfItems > 14) {
            binding.saleModifyFifteenName.setText(saleDetails[14].item)
            binding.saleModifyFifteenQuantity.setText(saleDetails[14].quantity.toString())
            binding.saleModifyFifteenRate.setText(saleDetails[14].rate.toString())
            binding.saleModifyFifteenPercentage.setText(saleDetails[14].total.toString())
        }

        if (numberOfItems > 15) {
            binding.saleModifySixteenName.setText(saleDetails[15].item)
            binding.saleModifySixteenQuantity.setText(saleDetails[15].quantity.toString())
            binding.saleModifySixteenRate.setText(saleDetails[15].rate.toString())
            binding.saleModifySixteenPercentage.setText(saleDetails[15].total.toString())
        }

        if (numberOfItems > 16) {
            binding.saleModifySeventeenName.setText(saleDetails[16].item)
            binding.saleModifySeventeenQuantity.setText(saleDetails[16].quantity.toString())
            binding.saleModifySeventeenRate.setText(saleDetails[16].rate.toString())
            binding.saleModifySeventeenPercentage.setText(saleDetails[16].total.toString())
        }

        if (numberOfItems > 17) {
            binding.saleModifyEighteenName.setText(saleDetails[17].item)
            binding.saleModifyEighteenQuantity.setText(saleDetails[17].quantity.toString())
            binding.saleModifyEighteenRate.setText(saleDetails[17].rate.toString())
            binding.saleModifyEighteenPercentage.setText(saleDetails[17].total.toString())
        }

        if (numberOfItems > 18) {
            binding.saleModifyNineteenName.setText(saleDetails[18].item)
            binding.saleModifyNineteenQuantity.setText(saleDetails[18].quantity.toString())
            binding.saleModifyNineteenRate.setText(saleDetails[18].rate.toString())
            binding.saleModifyNineteenPercentage.setText(saleDetails[18].total.toString())
        }

        if (numberOfItems > 19) {
            binding.saleModifyTwentyName.setText(saleDetails[19].item)
            binding.saleModifyTwentyQuantity.setText(saleDetails[19].quantity.toString())
            binding.saleModifyTwentyRate.setText(saleDetails[19].rate.toString())
            binding.saleModifyTwentyPercentage.setText(saleDetails[19].total.toString())
        }
    }
}
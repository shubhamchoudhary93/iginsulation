package com.shubham.iginsulation.sale

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.BackupRestore
import com.shubham.iginsulation.IDAssign
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.customer.CustomerDatabase
import com.shubham.iginsulation.database.customer.CustomerDatabaseDao
import com.shubham.iginsulation.database.sale.Sale
import com.shubham.iginsulation.database.sale.SaleDatabase
import com.shubham.iginsulation.database.sale.SaleDatabaseDao
import com.shubham.iginsulation.database.saledetails.SaleDetails
import com.shubham.iginsulation.database.saledetails.SaleDetailsDatabase
import com.shubham.iginsulation.database.saledetails.SaleDetailsDatabaseDao
import com.shubham.iginsulation.database.transaction.Transaction
import com.shubham.iginsulation.database.transaction.TransactionDatabase
import com.shubham.iginsulation.database.transaction.TransactionDatabaseDao
import com.shubham.iginsulation.databinding.FragmentSaleDetailBinding
import java.io.OutputStream
import kotlin.math.roundToInt

class SaleDetailFragment : Fragment() {

    private lateinit var binding: FragmentSaleDetailBinding
    private lateinit var saleDatabase: SaleDatabaseDao
    private lateinit var customerDatabase: CustomerDatabaseDao
    private lateinit var saleDetailsDatabase: SaleDetailsDatabaseDao
    private lateinit var transactionDatabase: TransactionDatabaseDao

    private var id = 0L
    private var name = ""
    private var date = ""
    private var total = 0
    private var receipt = false

    private var oldBalance = 0
    private var transaction1 = 0
    private var transaction2 = 0
    private var saleTotal = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sale_detail, container, false
        )

        saleDatabase = SaleDatabase.getInstance(requireContext()).saleDatabaseDao
        customerDatabase = CustomerDatabase.getInstance(requireContext()).customerDatabaseDao
        saleDetailsDatabase = SaleDetailsDatabase.getInstance(requireContext()).saleDetailsDatabaseDao
        transactionDatabase = TransactionDatabase.getInstance(requireContext()).transactionDatabaseDao

        val args = SaleDetailFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchSale(id)

        binding.saleDetailDelete.setOnClickListener {
            saleDatabase.delete(id)
            BackupRestore.backup(context, "sale")

            if (!receipt) {
                var balance = customerDatabase.getCustomerCurrentBalance(name)
                balance -= total
                customerDatabase.setCustomerCurrentBalance(balance, name)
                BackupRestore.backup(context, "customer")
            } else {
                transactionDatabase.deleteCashTransaction(date, total, name)
                BackupRestore.backup(context, "transaction")
            }

            saleDetailsDatabase.deleteSaleId(id)
            BackupRestore.backup(context, "sale_details")
            view?.findNavController()?.navigate(
                SaleDetailFragmentDirections.actionSaleDetailFragmentToSaleListFragment()
            )
        }

        binding.saleDetailModify.setOnClickListener {
            view?.findNavController()?.navigate(
                SaleDetailFragmentDirections.actionSaleDetailFragmentToSaleModifyFragment(id)
            )
        }

        binding.saleDetailShare.setOnClickListener {
            binding.saleDetailShare.visibility = View.GONE
            binding.saleDetailModify.visibility = View.GONE
            binding.saleDetailDelete.visibility = View.GONE

            if (binding.oldBalanceCheck.isChecked) {
                binding.oldBalanceCheck.visibility = View.GONE
                if (binding.pastOneCheck.isChecked) {
                    binding.pastOneCheck.visibility = View.GONE
                    if (binding.pastTwoCheck.isChecked)
                        binding.pastTwoCheck.visibility = View.GONE
                    else {
                        binding.oldBalance.text = (oldBalance - transaction1).toString()
                        binding.saleDetailsPastTwoLayout.visibility = View.GONE
                    }
                } else {
                    binding.oldBalance.text = oldBalance.toString()
                    binding.saleDetailsPastOneLayout.visibility = View.GONE
                    binding.saleDetailsPastTwoLayout.visibility = View.GONE
                }
            } else {
                binding.saleDetailsOldBalanceLayout.visibility = View.GONE
                binding.saleDetailsPastOneLayout.visibility = View.GONE
                binding.saleDetailsPastTwoLayout.visibility = View.GONE
            }

            val bitmap = getScreenShot(binding.screenshotLayout)
            val uriScreenShot = insertImage(
                context?.contentResolver!!,
                bitmap,
                binding.saleDetailName.text.toString(),
                "Bill"
            )
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(uriScreenShot, "image/*")
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            startActivity(Intent.createChooser(intent, "View Bill"))

            binding.saleDetailShare.visibility = View.VISIBLE
            binding.saleDetailModify.visibility = View.VISIBLE
            binding.saleDetailDelete.visibility = View.VISIBLE
            binding.saleDetailsOldBalanceLayout.visibility = View.VISIBLE
            binding.saleDetailsPastOneLayout.visibility = View.VISIBLE
            binding.saleDetailsPastTwoLayout.visibility = View.VISIBLE
            binding.oldBalanceCheck.visibility = View.VISIBLE
            binding.pastOneCheck.visibility = View.VISIBLE
            binding.pastTwoCheck.visibility = View.VISIBLE
            fetchSale(id)
        }
        return binding.root
    }

    private fun fetchSale(id: Long) {
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

        val pastTransaction = try {
            transactionDatabase.getLastTwoTransaction(sale.name)
        } catch (e: Exception) {
            e.printStackTrace()
            listOf()
        }

        setSaleData(sale, saleDetails, pastTransaction)
        name = sale.name
        total = sale.amount
        date = sale.date


    }

    private fun setSaleData(sale: Sale, saleDetails: List<SaleDetails>, pastTransaction: List<Transaction>) {
        IDAssign.assign(binding)

        if (sale.cash)
            binding.cash.text = sale.cash.toString()
        receipt = sale.cash
        binding.saleDetailName.text = sale.name.uppercase()
        binding.saleDetailDate.text = sale.date
        binding.saleDetailTransport.text = sale.transport.roundToInt().toString()
        binding.saleDetailOther.text = sale.otherCharges.roundToInt().toString()
        binding.saleDetailTotal.text = sale.amount.toString()
        val currentBalance = customerDatabase.getCustomerCurrentBalance(sale.name).roundToInt()
        binding.saleDetailBalance.text = currentBalance.toString()

        val numberOfItems = saleDetails.size

        if (numberOfItems > 0) {
            for (i in 1..numberOfItems) {
                val s = 30000 + i
                val j = s + 100
                val k = j + 100
                val m = k + 100
                val n = m + 100
                println("i $i s $s j $j k $k m $m n $n")
                binding.root.findViewById<LinearLayout>(m).visibility = View.VISIBLE
                binding.root.findViewById<TextView>(s).text = saleDetails[i - 1].item
                binding.root.findViewById<TextView>(k).text = saleDetails[i - 1].quantity.toString()
                binding.root.findViewById<TextView>(j).text = (saleDetails[i - 1].percentage * saleDetails[i - 1].rate).toString()
                binding.root.findViewById<TextView>(n).text = saleDetails[i - 1].total.toString()
            }
        }
        for (i in (30300 + numberOfItems + 1)..30320)
            binding.root.findViewById<LinearLayout>(i).visibility = View.GONE

        saleTotal = sale.amount
        oldBalance = currentBalance - saleTotal
        if (oldBalance != 0) {
            binding.saleDetailsOldBalanceLayout.visibility = View.VISIBLE
            binding.oldBalance.text = oldBalance.toString()
        } else
            binding.saleDetailsOldBalanceLayout.visibility = View.GONE

        if (pastTransaction.isNotEmpty()) {
            binding.saleDetailsPastOneLayout.visibility = View.VISIBLE
            binding.pastOneDate.text = pastTransaction[0].date
            transaction1 = if (pastTransaction[0].receipt) (0 - pastTransaction[0].amount) else pastTransaction[0].amount
            binding.pastOneAmount.text = transaction1.toString()
            binding.oldBalance.text = (oldBalance - transaction1).toString()
            if (pastTransaction.size > 1) {
                binding.saleDetailsPastTwoLayout.visibility = View.VISIBLE
                binding.pastTwoDate.text = pastTransaction[1].date
                transaction2 = if (pastTransaction[1].receipt) (0 - pastTransaction[1].amount) else pastTransaction[1].amount
                binding.pastTwoAmount.text = transaction2.toString()
                binding.oldBalance.text = (oldBalance - transaction1 - transaction2).toString()
            }
        } else {
            binding.saleDetailsPastOneLayout.visibility = View.GONE
            binding.saleDetailsPastTwoLayout.visibility = View.GONE
        }
    }

    private fun insertImage(
        cr: ContentResolver,
        source: Bitmap?,
        title: String?,
        description: String?
    ): Uri? {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, title)
        values.put(MediaStore.Images.Media.DISPLAY_NAME, title)
        values.put(MediaStore.Images.Media.DESCRIPTION, description)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis())
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
        var url: Uri? = null

        try {
            url = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            if (source != null) {
                val imageOut: OutputStream? = url?.let { cr.openOutputStream(it) }
                imageOut.use { imageOut2 ->
                    source.compress(Bitmap.CompressFormat.JPEG, 100, imageOut2)
                }
            } else {
                if (url != null) {
                    cr.delete(url, null, null)
                }
                url = null
            }
        } catch (e: Exception) {
            if (url != null) {
                cr.delete(url, null, null)
                url = null
            }
        }

        return url
    }

    private fun getScreenShot(view: View): Bitmap {

        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas)
        else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }
}
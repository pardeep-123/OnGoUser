package com.ongouser.Adapter

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.home.activity.payment.PaymentActivity
import com.ongouser.pojo.GetAddedCardListResponse
import com.ongouser.R
import com.ongouser.home.activity.payment.AddCardDetailActivity
import com.ongouser.utils.others.GlobalVariable
import kotlinx.android.synthetic.main.saved_payment_card.view.*
import java.util.ArrayList


class SavedCardsAdapter(
        val mContext: Context?,
        internal var savedCardLists: ArrayList<GetAddedCardListResponse.Body>,
        internal var paymentActivity: PaymentActivity
) : RecyclerView.Adapter<SavedCardsAdapter.SavedCardsHolder>() {

    var selectedpos = -1
    var cvvtext = ""
    var cardid = ""
    override fun onBindViewHolder(holder: SavedCardsHolder, position: Int) {
        holder.bindItems(savedCardLists[position], position)
        if (savedCardLists[position].isSelected.equals("true"))
        {
            holder.cvv.visibility = View.VISIBLE
            holder.ivSelectCard.visibility = View.VISIBLE
            holder.tvSelectCard.visibility = View.GONE


        } else {
            holder.ivSelectCard.visibility = View.GONE
            holder.cvv.visibility = View.GONE
            holder.tvSelectCard.visibility = View.VISIBLE
        }

     /*   if (selectedpos == position)
        {
            holder.cvv.visibility = View.VISIBLE
            holder.ivSelectCard.visibility = View.VISIBLE
            holder.tvSelectCard.visibility = View.GONE


        } else {
            holder.ivSelectCard.visibility = View.GONE
            holder.cvv.visibility = View.GONE
            holder.tvSelectCard.visibility = View.VISIBLE
        }*/

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedCardsHolder {
        return SavedCardsHolder(LayoutInflater.from(mContext).inflate(R.layout.saved_payment_card, parent, false))
    }

    override fun getItemCount(): Int {
        return savedCardLists.size
    }


    inner class SavedCardsHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvCardNumber = itemView.findViewById(R.id.tv_card_number) as TextView
        val cvv = itemView.findViewById(R.id.cvvedt) as EditText
        val tvCardName = itemView.findViewById(R.id.tv_name) as TextView
        val tvEditCard = itemView.findViewById(R.id.tv_edit_card) as TextView
        val tvCardExpiryDate = itemView.findViewById(R.id.card_expiry_date) as TextView
        val ivDeleteCard = itemView.findViewById(R.id.iv_delete_card) as ImageView
        val ivSelectCard = itemView.findViewById(R.id.iv_select_card) as ImageView
        val tvSelectCard = itemView.findViewById(R.id.tv_select_card) as TextView

        fun bindItems(savedCardList: GetAddedCardListResponse.Body, position: Int) {

            //  val radioBtn = itemView.findViewById(R.id.radio_btn) as RadioButton
            cvv.setText("")
            tvCardNumber.text = savedCardList.cardNumber
            tvCardName.text = savedCardList.name
            var month=savedCardList.month.toString()
            if (savedCardList.month.toString().length==1){
                month="0"+month
            }
            tvCardExpiryDate.text = month + "/" + savedCardList.year.toString()

            ivDeleteCard.setOnClickListener {

                GlobalVariable.globalyesno_btndialog(mContext!!,"Are you sure to delete this card?",object :GlobalVariable.OnOkselectforlocation{
                    override fun yesselect() {
                        paymentActivity.deleteAPIMethod(position, savedCardList.id.toString())
                    }

                    override fun noselect() {

                    }

                })

            }

            tvEditCard.setOnClickListener {
                var intent =  Intent(mContext, AddCardDetailActivity::class.java)
                intent.putExtra("id", savedCardList.id.toString())
                intent.putExtra("cardNumber", savedCardList.cardNumber)
                intent.putExtra("name", savedCardList.name)
                intent.putExtra("month", savedCardList.month.toString())
                intent.putExtra("year", savedCardList.year.toString())
                intent.putExtra("cardType", savedCardList.cardType.toString())


                mContext!!.startActivity(intent)
            }
            cvv.addTextChangedListener(object :TextWatcher{
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    cvvtext  = p0!!.toString()
                }

            })

            tvSelectCard.setOnClickListener {
                cvvtext=""
                selectedpos = position
                for (i in 0 until savedCardLists.size){
                    savedCardLists.get(i).isSelected="false"
                }
                savedCardLists.get(position).isSelected="true"
                cardid = savedCardList.id.toString()
                notifyDataSetChanged()
            }

        }



        init {
            itemView.setOnClickListener {

            }
        }
    }
    fun getselectedcvv():String{
        return cvvtext
    }
    fun getselectedcardid():String{
        return cardid
    }
}
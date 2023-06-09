package com.example.foodorder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorder.R
import com.example.foodorder.adapter.ContactAdapter.ContactViewHolder
import com.example.foodorder.constant.GlobalFuntion
import com.example.foodorder.databinding.ItemContactBinding
import com.example.foodorder.model.Contact

class ContactAdapter(private var context: Context?, private var listContact: MutableList<Contact>,
                     private val iCallPhone: ICallPhone?) : RecyclerView.Adapter<ContactViewHolder>() {

    interface ICallPhone {
        fun onClickCallPhone()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemContactBinding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(itemContactBinding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = listContact[position]
        holder.mItemContactBinding.imgContact.setImageResource(contact.getImage())
        if (context == null) {
            return
        }
        when (contact.getId()) {
            Contact.FACEBOOK -> holder.mItemContactBinding.tvContact.text = context!!.getString(R.string.label_facebook)
            Contact.HOTLINE -> holder.mItemContactBinding.tvContact.text = context!!.getString(R.string.label_call)
            Contact.GMAIL -> holder.mItemContactBinding.tvContact.text = context!!.getString(R.string.label_gmail)
            Contact.SKYPE -> holder.mItemContactBinding.tvContact.text = context!!.getString(R.string.label_skype)
            Contact.YOUTUBE -> holder.mItemContactBinding.tvContact.text = context!!.getString(R.string.label_youtube)
            Contact.ZALO -> holder.mItemContactBinding.tvContact.text = context!!.getString(R.string.label_zalo)
        }
        holder.mItemContactBinding.layoutItem.setOnClickListener {
            when (contact.getId()) {
                Contact.FACEBOOK -> GlobalFuntion.onClickOpenFacebook(context!!)
                Contact.HOTLINE -> iCallPhone?.onClickCallPhone()
                Contact.GMAIL -> GlobalFuntion.onClickOpenGmail(context!!)
                Contact.SKYPE -> GlobalFuntion.onClickOpenSkype(context!!)
                Contact.YOUTUBE -> GlobalFuntion.onClickOpenYoutubeChannel(context!!)
                Contact.ZALO -> GlobalFuntion.onClickOpenZalo(context!!)
            }
        }
    }

    override fun getItemCount(): Int {
        return listContact.size
    }

    fun release() {
        context = null
    }

    class ContactViewHolder(val mItemContactBinding: ItemContactBinding) : RecyclerView.ViewHolder(mItemContactBinding.root)
}
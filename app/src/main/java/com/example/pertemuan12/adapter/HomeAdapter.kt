package com.example.pertemuan12.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan12.R
import com.example.pertemuan12.databinding.UserListBinding
import com.example.pertemuan12.model.Mahasiswa
import com.google.firebase.database.FirebaseDatabase

class HomeAdapter (private val dataMhs: ArrayList<Mahasiswa>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
        class ViewHolder(val binding: UserListBinding) :
                RecyclerView.ViewHolder(binding.root)

    private val databaseRef =
        FirebaseDatabase.getInstance().getReference("Mahasiswa")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            UserListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataMhs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtNim.text = dataMhs[position].nim
        holder.binding.txtNama.text = dataMhs[position].nama
        holder.binding.txtTelepon.text = dataMhs[position].telepon

        holder.binding.cardView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id", dataMhs[position].id)
            bundle.putString("nim", dataMhs[position].nim)
            bundle.putString("nama", dataMhs[position].nama)
            bundle.putString("telepon", dataMhs[position].telepon)

            findNavController(it).navigate(R.id.action_homeFragment_to_detailFragment2,bundle)
        }

        holder.binding.btnEdit.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id", dataMhs[position].id)
            bundle.putString("nim", dataMhs[position].nim)
            bundle.putString("nama", dataMhs[position].nama)
            bundle.putString("telepon", dataMhs[position].telepon)

            findNavController(it).navigate(R.id.action_homeFragment_to_updateFragment)
        }
    }

    fun setData(newData: ArrayList<Mahasiswa>) {
        dataMhs.clear()
        dataMhs.addAll(newData)
        notifyDataSetChanged()
    }
}
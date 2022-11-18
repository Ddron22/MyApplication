package com.example.myapplication.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TeachersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeachersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var database = FirebaseDatabase.getInstance().getReference("Teachers")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teachers, container, false)
    }

    override fun onStart() {
        super.onStart()
        var getdata = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for(i in p0.children){
                    var id = i.child("id").getValue().toString().toInt()
                    var firstName = i.child("firstName").getValue().toString()
                    var lastName = i.child("lastName").getValue().toString()
                    var surName = i.child("surName").getValue().toString()
                    (activity as MainActivity?)?.newDataThird(id as Int,
                        firstName as String,
                        lastName as String,
                        surName as String
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        database.addValueEventListener(getdata)
        //database.addListenerForSingleValueEvent(getdata)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TeachersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TeachersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
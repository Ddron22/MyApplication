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
 * Use the [ClubFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClubFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var database = FirebaseDatabase.getInstance().getReference("Events")

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
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onStart() {
        super.onStart()
        var getdata = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for(i in p0.children){
                    var id = i.child("id").getValue().toString().toInt()
                    var name = i.child("name").getValue().toString()
                    var manager = i.child("manager").getValue().toString()
                    var room = i.child("room").getValue().toString()
                    var member_count = i.child("member_count").getValue().toString().toInt()
                    (activity as MainActivity?)?.newDataFour(id as Int,
                        name as String,
                        manager as String,
                        room as String,
                        member_count as Int
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
         * @return A new instance of fragment ClubFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ClubFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
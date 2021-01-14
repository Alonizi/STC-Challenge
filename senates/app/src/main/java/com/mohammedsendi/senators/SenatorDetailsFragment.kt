package com.mohammedsendi.senators

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mohammedsendi.senators.model.Senator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "BIOID"

/**
 * A simple [Fragment] subclass.
 * Use the [SenatorDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SenatorDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private lateinit var senate : Senator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        }

        val vm = SenatorsViewModel()
        senate = param1?.let { vm.getSenatesByBioId(it) }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_senator_details, container, false)
        val senateLastName = view.findViewById<TextView>(R.id.senate_lastname)
        val senateAddress = view.findViewById<TextView>(R.id.senate_address)
        val senateCity = view.findViewById<TextView>(R.id.senate_city)
        val senateZip = view.findViewById<TextView>(R.id.senate_zippcode)
        val senateCountry = view.findViewById<TextView>(R.id.senate_country)

        senateLastName.text = "Senate LastName : ${senate.lastName}"
        senateAddress.text = "Senate Address : ${senate.address}"

//        senateCountry.text = TODO()
//        senateZip.text = TODO()
        // Inflate the layout for this fragment
        return view

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SenatorDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(bioID: String) =
            SenatorDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, bioID)
                   // putString(ARG_PARAM2, param2)
                }
            }
    }
}
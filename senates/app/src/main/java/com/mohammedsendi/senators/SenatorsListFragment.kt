package com.mohammedsendi.senators

import android.content.Context
import android.os.Bundle
import android.os.ParcelFileDescriptor.open
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.mohammedsendi.senators.model.Senator
import com.mohammedsendi.senators.model.SenatorDeserializer
import com.mohammedsendi.senators.model.Senators
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "SENATORS"

/**
 * A simple [Fragment] subclass.
 * Use the [SenatorsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SenatorsListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView : RecyclerView
    private lateinit var senatesList : List<Senator>
    private lateinit var senators : Senators


//    init {
////        val jsonData = getJsonDataFromAsset(activity?.applicationContext!!,"us_senators.json")
////        if (jsonData != null) {
////            Log.i(TAG,jsonData)
////        }
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val jsonString = getJsonDataFromAsset(activity?.applicationContext!!,"us_senators.json")

        val gSon = GsonBuilder().registerTypeAdapter(Senators::class.java, SenatorDeserializer()).create()
         senators = gSon.fromJson<Senators>(jsonString, Senators::class.java)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_senators_list, container, false)
        recyclerView = view.findViewById(R.id.senators_list)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = CardAdapter(senators.senatesList)

        return view

    }

    private inner class CardAdapter(
        private var senatesList: List<Senator>
    ) :
        RecyclerView.Adapter<CardHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
            //Log.i("BUSINESS", businessList.first().name)
            return CardHolder(
                layoutInflater.inflate(
                    R.layout.senator_card_view,
                    parent,
                    false
                ) as MaterialCardView
            )
        }

        override fun onBindViewHolder(holder: CardHolder, position: Int) {
            holder.onBind(senatesList[position])
        }

        override fun getItemCount(): Int {
            return senatesList.size
        }
    }

    private inner class CardHolder(val cardView: MaterialCardView) :
        RecyclerView.ViewHolder(cardView) {
        val senateImage = cardView.findViewById<ImageView>(R.id.senate_image)
        val senateName = cardView.findViewById<TextView>(R.id.senate_name)
        val senateDescription = cardView.findViewById<TextView>(R.id.senate_description)
        val senateParty = cardView.findViewById<TextView>(R.id.senate_party)


        fun onBind(senate: Senator) {
            senateName.text = senate.lastName + ", " + senate.firstName
            senateParty.text = senate.party
            senateDescription.text = senate.description

            cardView.setOnClickListener {

                TODO()
                true
            }

        }
    }


    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SenatorsListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SenatorsListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
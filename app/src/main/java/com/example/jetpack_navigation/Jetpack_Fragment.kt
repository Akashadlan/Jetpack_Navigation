package com.example.jetpack_navigation

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.jetpack_navigation.databinding.FragmentJetpackBinding
import java.text.SimpleDateFormat
import java.util.Date

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Jetpack_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Jetpack_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentJetpackBinding? = null
    var mainActivity : MainActivity? = null
    var simpleDateFormat = SimpleDateFormat("dd/MMM/yyyy")
    var timeFormat = SimpleDateFormat("hh:mm aa")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
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
        binding = FragmentJetpackBinding.inflate(layoutInflater)
        return binding?.root
        // return inflater.inflate(R.layout.fragment_jetpack, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding?.btnFristFragment?.setOnClickListener {
           findNavController().navigate(R.id.secondFragment)
       }
        binding?.btndate?.setOnClickListener {
            var calendar = Calendar.getInstance()
           // calendar.timeZone.inDaylightTime(Date(24))
            DatePickerDialog(
                requireContext(), R.style.Base_Theme_Jetpack_Navigation,
                { _, year, month, date ->
                    Log.e(TAG, "year $year month $month date $date")
                    var calendar = Calendar.getInstance()
                    calendar.set(year, month, date)
                    var formattedDate = simpleDateFormat.format(calendar.time)
                    binding?.btndate?.setText(formattedDate)
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DATE),
            ).show()
        }
        binding?.btntime?.setOnClickListener {
            var calendar = Calendar.getInstance()
           // calendar.timeZone.inDaylightTime(Date(24))
            if (calendar.timeInMillis<9 && calendar.timeInMillis>6){
                Toast.makeText(requireContext(),"Your Time Is Remove",Toast.LENGTH_LONG).show()
            }
            TimePickerDialog(requireContext(),R.style.Theme_Jetpack_Navigation,
                {_, hour, minute ->
                    Log.e(TAG, "hour $hour minute $minute")
                    var calendar = Calendar.getInstance()
                    calendar.set(Calendar.HOUR_OF_DAY, hour)
                    calendar.set(Calendar.MINUTE, minute)
                    binding?.btntime?.setText(timeFormat.format(calendar.time))
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                false
            ).show()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Jetpack_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Jetpack_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
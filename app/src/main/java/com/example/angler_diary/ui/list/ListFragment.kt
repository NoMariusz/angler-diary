package com.example.angler_diary.ui.list


import android.R.attr.value
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.FormActivity
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentListBinding
import com.example.angler_diary.logic.FormModes


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private lateinit var databaseViewModel: DatabaseViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        databaseViewModel = ViewModelProvider(this)[DatabaseViewModel::class.java]

        loadTitle()
        loadRecyclerView()
        connectAddObject()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadRecyclerView() {
        val fishingObject = getFishingObject()
        val listView = FishingObjectListViewFactory.create(databaseViewModel, fishingObject)
        listView.prepareRecyclerView(binding.recyclerView, requireContext(), viewLifecycleOwner)
    }

    private fun loadTitle(){
        val textView: TextView = binding.titleObjectName
        val fishingObject = getFishingObject()
        textView.text = fishingObject.name
    }

    private fun getFishingObject(): FishingObjects {
        return (arguments?.get("fishingObject")
            ?: throw Exception("No fishing object provided to ListFragment")) as FishingObjects
    }

    private fun connectAddObject(){
        binding.fab.setOnClickListener {
            navToAddObjectForm()
        }
    }

    private fun navToAddObjectForm(){
        val myIntent = Intent(
            requireContext(),
            FormActivity::class.java
        )
        myIntent.putExtra("fishingObject", getFishingObject())
        myIntent.putExtra("mode", FormModes.Adding)
        this.startActivity(myIntent)
    }
}
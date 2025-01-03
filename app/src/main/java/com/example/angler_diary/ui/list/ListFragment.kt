package com.example.angler_diary.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val listViewModel =
            ViewModelProvider(this)[ListViewModel::class.java]

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        listViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val fishingObject = arguments?.get("fishingObject") as FishingObjects?
        listViewModel.updateText(fishingObject?.toString() ?: "There is no FishingObject")

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
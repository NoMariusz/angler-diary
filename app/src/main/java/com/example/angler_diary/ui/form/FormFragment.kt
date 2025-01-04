package com.example.angler_diary.ui.form

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.logic.FormModes

class FormFragment : Fragment() {

    private var _binding: FragmentFormBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadTitle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadTitle() {
        val mode = getMode()
        val fishingObject = getFishingObject()
        val id = getIntentExtra<Int?>("id") ?: ""

        binding.titleText.text = "${mode.name} ${fishingObject.name} ${id}"
    }

    private fun getMode(): FormModes {
        return getIntentExtra<FormModes>("mode") ?: throw IllegalArgumentException("No mode provided to FormFragment")
    }

    private fun getFishingObject(): FishingObjects {
        return getIntentExtra<FishingObjects>("fishingObject") ?: throw IllegalArgumentException("No fishingObject provided to FormFragment")
    }

    private inline fun <reified T> getIntentExtra(key: String): T? {
        val intent = activity?.intent ?: throw IllegalStateException("Activity or intent is null")

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(key, T::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(key)
        }
    }
}
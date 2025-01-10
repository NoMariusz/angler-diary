package com.example.angler_diary.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var databaseViewModel: DatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        databaseViewModel = ViewModelProvider(this)[DatabaseViewModel::class.java]

        binding.textHome.text = "Score: loading..."
        lifecycleScope.launch {
            val actualScoreData = withContext(Dispatchers.IO) {
                databaseViewModel.getNewestScoreHistory()
            }

            binding.textHome.text = "Score: ${actualScoreData?.score ?: 0}"
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
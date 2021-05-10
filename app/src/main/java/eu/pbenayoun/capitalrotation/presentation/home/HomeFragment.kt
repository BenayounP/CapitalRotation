package eu.pbenayoun.capitalrotation.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import eu.pbenayoun.capitalrotation.R
import eu.pbenayoun.capitalrotation.databinding.FragmentHomeBinding


class HomeFragment() : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? =null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        setObservers()
        setViews()
        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setObservers(){

    }


    private fun setViews() {

    }


    private fun snackIt(snackText: String){
        Snackbar.make(binding.root,snackText, Snackbar.LENGTH_LONG).show()

    }

}
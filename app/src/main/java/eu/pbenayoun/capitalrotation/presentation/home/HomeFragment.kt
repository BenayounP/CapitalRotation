package eu.pbenayoun.capitalrotation.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.adapters.TextViewBindingAdapter.setText
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

    override fun onResume() {
        super.onResume()
        setTexts()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setObservers(){

    }

    private fun setTexts(){
        binding.homeTitle.text=getString(viewModel.stepTextIds.title)
        binding.homeEditSearch.setText(viewModel.currentQueryText)
        binding.homeEditSearch.hint=getString(viewModel.stepTextIds.editTextHint)
        binding.homeButton.text=getString(viewModel.stepTextIds.buttonText)
    }


    private fun setViews() {
        binding.homeEditSearch.doAfterTextChanged {
            viewModel.setCurrentQuery(it.toString())
        }

        binding.homeButton.setOnClickListener { view ->
            onButtonClick()
        }
    }

    fun onButtonClick(){
        when(viewModel.checkQuery()){
             CheckResult.OK-> setTexts()
            CheckResult.KO ->{
                binding.homeEditSearch.setText(viewModel.currentQueryText)
                snackIt(getString(viewModel.stepTextIds.snackText))
            }

        }
    }


    private fun snackIt(snackText: String){
        Snackbar.make(binding.root,snackText, Snackbar.LENGTH_LONG).show()

    }

}
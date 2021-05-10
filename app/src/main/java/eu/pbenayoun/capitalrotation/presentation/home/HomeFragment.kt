package eu.pbenayoun.capitalrotation.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
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


    private fun setViews() {
        binding.homeEditSearch.doAfterTextChanged {
            viewModel.setCurrentQuery(it.toString())
        }

        binding.homeEditSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onTextValidation()
                false
            } else {
                false
            }
        }

        binding.homeButton.setOnClickListener { view ->
            onTextValidation()
        }
    }

    private fun setTexts(){
        binding.homeTitle.text=getString(viewModel.stepTextIds.title)
        binding.homeEditSearch.setText(viewModel.currentQueryText)
        binding.homeEditSearch.hint=getString(viewModel.stepTextIds.editTextHint)
        binding.homeButton.text=getString(viewModel.stepTextIds.buttonText)
    }

    fun onTextValidation(){
        hideKeyboard()
        val result = viewModel.checkQuery()
        when(result){
            CheckResult.OK-> onOkResult()
            CheckResult.KO -> onKOResult()
        }
    }


    fun onOkResult(){
        when(viewModel.currentStep){
            Step.CAPITAL_LETTER_CHECK -> setRotationStep()
            Step.ROTATION_ANGLE_CHECK -> goToRotationFragment()
        }
    }

    fun setRotationStep(){
        viewModel.setStepRotation()
        setTexts()
    }

    fun onKOResult(){
        binding.homeEditSearch.setText(viewModel.currentQueryText)
        snackIt(getString(viewModel.stepTextIds.snackText))
    }

    private fun goToRotationFragment(){
        Navigation.findNavController(binding.root).navigate(R.id.action_home_to_rotation)
    }

    private fun hideKeyboard(){
        (binding.homeEditSearch.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager).hideSoftInputFromWindow(binding.homeEditSearch.windowToken, 0)
    }

    private fun snackIt(snackText: String){
        Snackbar.make(binding.root,snackText, Snackbar.LENGTH_LONG).show()
    }

}
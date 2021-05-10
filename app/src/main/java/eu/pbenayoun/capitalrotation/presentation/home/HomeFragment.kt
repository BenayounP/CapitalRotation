package eu.pbenayoun.capitalrotation.presentation.home

import android.content.Context
import android.os.Bundle
import android.text.InputType
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


/** Home Fragment that displays Home page with the two form-field:
 *  - one for Capital Field
 *  - one for rotation angle
 */

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
        setViewsContent()
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

    private fun setViewsContent(){
        binding.homeTitle.text=getString(viewModel.stepTextIds.title)
        binding.homeEditSearch.setText(viewModel.currentQueryText)
        binding.homeEditSearch.hint=getString(viewModel.stepTextIds.editTextHint)
        binding.homeButton.text=getString(viewModel.stepTextIds.buttonText)
        if (viewModel.currentStep==Step.ROTATION_ANGLE_CHECK) binding.homeEditSearch.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL)
    }

    fun onTextValidation(){
        hideKeyboard()
        val checkResponse = viewModel.checkQuery()
        if (checkResponse.result==CheckResult.OK) onSuccess(checkResponse)
        else onFail()
    }


    fun onSuccess(checkResponse: CheckResponse){
        when(viewModel.currentStep){
            Step.CAPITAL_LETTER_CHECK -> setRotationStep()
            Step.ROTATION_ANGLE_CHECK -> goToRotationFragment(checkResponse.rotationAngle)
        }
    }

    fun setRotationStep(){
        viewModel.setStepRotation()
        setViewsContent()
    }

    fun onFail(){
        binding.homeEditSearch.setText(viewModel.currentQueryText)
        snackIt(getString(viewModel.stepTextIds.snackText))
    }

    private fun goToRotationFragment(rotationAngle: Float){
        val action = HomeFragmentDirections.actionHomeToRotation(rotationAngle)
        Navigation.findNavController(binding.root).navigate(action)
    }

    private fun hideKeyboard(){
        (binding.homeEditSearch.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager).hideSoftInputFromWindow(binding.homeEditSearch.windowToken, 0)
    }

    private fun snackIt(snackText: String){
        Snackbar.make(binding.root,snackText, Snackbar.LENGTH_LONG).show()
    }

}
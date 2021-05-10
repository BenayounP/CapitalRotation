package eu.pbenayoun.capitalrotation.presentation.rotation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import eu.pbenayoun.capitalrotation.R
import eu.pbenayoun.capitalrotation.databinding.FragmentRotationBinding


// simple fragment to display rotated image
class RotationFragment() : Fragment(R.layout.fragment_rotation) {

    private var _binding: FragmentRotationBinding? =null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var rotationAngle : Float = 0f
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRotationBinding.inflate(inflater, container, false)
        val view = binding.root
        // we get here the angle given by other fragment
        rotationAngle=arguments?.getFloat("rotationAngle")?:-1f
        setViews(rotationAngle)
        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setViews(rotationAngle: Float) {

        if (rotationAngle>0){
            binding.rotateImageview.rotation=rotationAngle
        }

        // go back to previous fragment on BackPressed
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Navigation.findNavController(binding.root).popBackStack()
                }
            })
    }
}
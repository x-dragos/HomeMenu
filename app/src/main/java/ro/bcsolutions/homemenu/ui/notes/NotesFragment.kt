package ro.bcsolutions.homemenu.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ro.bcsolutions.homemenu.R
import ro.bcsolutions.homemenu.Utils
import ro.bcsolutions.homemenu.databinding.NotesFragmentBinding

class NotesFragment : Fragment() {

    private lateinit var notesViewModel: NotesViewModel

    private lateinit var binding : NotesFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.notes_fragment, container, false)

        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        binding.notesViewModel = notesViewModel

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        this.context?.let { notesViewModel.saveNotes(it) }
        Utils.hideKeyboard(this.requireActivity())
    }

    override fun onResume() {
        super.onResume()
        this.context?.let { notesViewModel.loadNotes(it) }
    }
}

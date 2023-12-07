package com.example.notes.mynotesapplication.ui.notes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.notes.R
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.mynotesapplication.ui.addnote.AddNoteFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.fragment_notes) {
    private var binding: FragmentNotesBinding? = null
    private val viewModel: NotesViewModel by viewModels()

    private val adapter = NotesAdapter {
        openNote(it.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotesBinding.bind(view)

        viewModel.allNotes
            .flowWithLifecycle(lifecycle)
            .onEach { notes ->
                println(notes)
                adapter.submitData(notes)
            }
            .launchIn(lifecycleScope)


        binding?.fabAddNote?.setOnClickListener {
            openNote(null)
        }
        binding?.rvNotes?.adapter = adapter
    }

    private fun openNote(id: Int?) {
        parentFragmentManager.commit {
            replace(R.id.container, AddNoteFragment.newInstance(id))
            addToBackStack(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance() = NotesFragment()
    }

}
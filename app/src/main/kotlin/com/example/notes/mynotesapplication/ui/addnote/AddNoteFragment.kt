package com.example.notes.mynotesapplication.ui.addnote

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNoteBinding
import com.example.notes.mynotesapplication.data.entity.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AddNoteFragment : Fragment(R.layout.fragment_add_note) {
    private var binding: FragmentAddNoteBinding? = null
    private val viewModel: AddNoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddNoteBinding.bind(view)
        arguments?.getInt(ARG_NOT_ID)?.let { noteId ->
            viewModel.getNote(noteId)
        }

        viewModel.note
            .flowWithLifecycle(lifecycle)
            .filterNotNull()
            .onEach { note ->
                binding?.run {
                    etTitle.setText(note.title)
                    etContent.setText(note.content)
                }
            }
            .launchIn(lifecycleScope)

        binding?.run {
            btnAdd.setOnClickListener {
                val note = Note(
                    title = etTitle.text?.toString().orEmpty(),
                    content = etContent.text?.toString().orEmpty(),
                    color = 0
                )
                viewModel.saveOrUpdateNote(note)
                parentFragmentManager.popBackStack()
            }
            fabDelete.setOnClickListener {
                viewModel.deleteNote()
                parentFragmentManager.popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val ARG_NOT_ID = "ARG_NOT_ID"

        fun newInstance(id: Int?) = AddNoteFragment().apply {
            arguments = bundleOf(ARG_NOT_ID to id)
        }
    }

}
package com.example.notes.mynotesapplication.ui.notes


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notes.databinding.ItemNoteBinding
import com.example.notes.mynotesapplication.data.entity.Note

class NotesAdapter(
    private val onClick: (Note) -> Unit
) : PagingDataAdapter<Note, NotesAdapter.NotesViewHolder>(
    NotesDiffUtilItemCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }

    class NotesViewHolder(
        private val viewBinding: ItemNoteBinding,
        private val onClick: (Note) -> Unit
    ) : ViewHolder(viewBinding.root) {
        fun bind(item: Note) = with(viewBinding) {
            root.setOnClickListener { onClick(item) }
            tvTitle.text = item.title
            tvContent.text = item.content

        }
    }

}
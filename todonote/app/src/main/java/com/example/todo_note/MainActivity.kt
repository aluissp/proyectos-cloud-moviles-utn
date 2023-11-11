package com.example.todo_note

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.todo_note.databinding.ActivityMainBinding
import com.example.todo_note.models.Task
import com.example.todo_note.utils.Status
import com.example.todo_note.utils.clearEditText
import com.example.todo_note.utils.longToastShow
import com.example.todo_note.utils.setupDialog
import com.example.todo_note.utils.validateEditText
import com.example.todo_note.view.TaskViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Date
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private val mainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val addTaskDialog: Dialog by lazy {
        Dialog(this).apply {
            setupDialog(R.layout.add_task_dialog)
        }
    }

    private val updateTaskDialog: Dialog by lazy {
        Dialog(this, R.style.DialogCustomTheme).apply {
            setupDialog(R.layout.update_task_dialog)
        }
    }

    private val loadingDialog: Dialog by lazy {
        Dialog(this).apply {
            setupDialog(R.layout.loading_dialog)
        }
    }

    private val taskViewModel: TaskViewModel by lazy {
        ViewModelProvider(this)[TaskViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        // Add task start
        val addCloseImg = addTaskDialog.findViewById<ImageView>(R.id.closeImg)

        addCloseImg.setOnClickListener { addTaskDialog.dismiss() }

        val addTaskTitle = addTaskDialog.findViewById<TextInputEditText>(R.id.edTaskTitle)
        val addTaskTitleL = addTaskDialog.findViewById<TextInputLayout>(R.id.edTaskTitleL)

        addTaskTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditText(addTaskTitle, addTaskTitleL)
            }
        })

        val addTaskDescr = addTaskDialog.findViewById<TextInputEditText>(R.id.edTaskDescr)
        val addTaskDescrL = addTaskDialog.findViewById<TextInputLayout>(R.id.edTaskDescrL)

        addTaskDescr.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditText(addTaskDescr, addTaskDescrL)
            }
        })

        mainBinding.addTaskFloatBtn.setOnClickListener {
            clearEditText(addTaskTitle, addTaskTitleL)
            clearEditText(addTaskDescr, addTaskDescrL)
            addTaskDialog.show()
        }

        val saveTaskBtn = addTaskDialog.findViewById<Button>(R.id.saveTaskBtn)
        saveTaskBtn.setOnClickListener {
            if (validateEditText(addTaskTitle, addTaskTitleL)
                && validateEditText(addTaskDescr, addTaskDescrL)
            ) {
                addTaskDialog.dismiss()
                val newTask = Task(
                    UUID.randomUUID().toString(),
                    addTaskTitle.text.toString().trim(),
                    addTaskDescr.text.toString().trim(),
                    Date()
                )

                taskViewModel.insertTask(newTask).observe(this) {
                    when (it.status) {
                        Status.LOADING -> {
                            loadingDialog.show()
                        }

                        Status.SUCCESS -> {
                            loadingDialog.dismiss()
                            if (it.data?.toInt() != -1) {
                                longToastShow("Task added successfully!")
                            }
                        }

                        Status.ERROR -> {
                            loadingDialog.dismiss()
                            it.message?.let { msg -> longToastShow(msg) }
                        }

                    }
                }
            }

        }
        // Add task end

        // Update task start
        val updateCloseImg = updateTaskDialog.findViewById<ImageView>(R.id.closeImg)
        updateCloseImg.setOnClickListener { updateTaskDialog.dismiss() }

        val updateTaskTitle = updateTaskDialog.findViewById<TextInputEditText>(R.id.upTaskTitle)
        val updateTaskTitleL = updateTaskDialog.findViewById<TextInputLayout>(R.id.upTaskTitleL)

        updateTaskTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditText(updateTaskTitle, updateTaskTitleL)
            }
        })

        val updateTaskDescr = updateTaskDialog.findViewById<TextInputEditText>(R.id.upTaskDescr)
        val updateTaskDescrL = updateTaskDialog.findViewById<TextInputLayout>(R.id.upTaskDescrL)

        updateTaskDescr.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditText(updateTaskDescr, updateTaskDescrL)
            }
        })

        val updateTaskBtn = updateTaskDialog.findViewById<Button>(R.id.updateTaskBtn)
        updateTaskBtn.setOnClickListener {
            if (validateEditText(updateTaskTitle, updateTaskTitleL)
                && validateEditText(updateTaskDescr, updateTaskDescrL)
            ) {
                updateTaskDialog.dismiss()
                Toast.makeText(this, "Validated!", Toast.LENGTH_LONG).show()
                loadingDialog.show()
            }
        }
        // Update task end


    }
}
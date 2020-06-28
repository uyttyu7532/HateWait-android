package com.example.hatewait

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.twilio.rest.monitor.v1.Alert

class NameCheckDialogFragment : DialogFragment() {

    internal lateinit var nameChecklistener : NameCheckListener

    interface NameCheckListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }
//    롤리팝 이하버전은 지원 X
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //                .setPositiveButtonIcon(R.drawable)
        return activity?.let {
            val builder = AlertDialog.Builder(it)
//            val inflater = requireActivity().layoutInflater
//            builder.setView(inflater.inflate(R.layout.activity_name_check_dialog, null))
            builder.setTitle("회원 이름 확인")
                .setIcon(resources.getDrawable(R.drawable.main_logo, context!!.theme))
                .setMessage("문X훈 회원님 맞나요?")
                .setPositiveButton("", DialogInterface.OnClickListener { dialog, _ ->
                    nameChecklistener.onDialogPositiveClick(this)
                })
                .setPositiveButtonIcon(resources.getDrawable(R.drawable.yes_button_vector, context!!.theme))
                .setNegativeButton("", DialogInterface.OnClickListener { dialog, _ ->
                    nameChecklistener.onDialogNegativeClick(this)
                })
                .setNegativeButtonIcon(resources.getDrawable(R.drawable.no_button_vector, context!!.theme))
            val dialog = builder.create()

//            여기서부터 커스터마이징
            //        Button Size Customzation
//        https://stackoverflow.com/questions/34555107/how-to-customize-alertdialog-so-that-buttons-fit-in-the-alertdialog
            val negativeButton: Button =
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
            val positiveButton: Button =
                dialog.getButton(DialogInterface.BUTTON_POSITIVE)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                2f
            )
            negativeButton.layoutParams = params
            positiveButton.layoutParams = params

            negativeButton.invalidate()
            positiveButton.invalidate()

//            커스터마이징 끝 return은 커스터마이징 된 dialog
            dialog
//           if builder fails to create Activity ->  Elvis Operator null check
        } ?: throw IllegalStateException("Activity Can't be null")
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            nameChecklistener = context as NameCheckListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() + "must implement NameCheckListener"))
        }
    }
}
package org.bs.views

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.widget.EditText
import org.bs.R
import kotlin.reflect.KFunction1

class DialogHelper {
    companion object Dialog{
        fun showDialog(ctx: Context, title: String, functionIfPositive: KFunction1<String?, Unit>): Unit {
            val builder = AlertDialog.Builder(ctx)
            val inp = EditText(ctx)
            inp.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(inp)
                .setTitle(title)
                .setPositiveButton(ctx.getString(R.string.ok)) { _: DialogInterface, _: Int ->
                    @Override
                    fun onClick() {
                        functionIfPositive(inp.text.toString())
                    }
                }.setNegativeButton(
                    ctx.getString(R.string.cancel)
                ) { dialog: DialogInterface, _: Int ->
                    @Override
                    fun onClick() {
                        dialog.cancel()
                    }
                }
                .create()
                .show()
        }

    }
}
package id.autodhil.koramvvm.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import id.autodhil.koramvvm.R
import com.google.firebase.auth.FirebaseAuth

class PasswordResetDialog : DialogFragment() {
    //widgets
    private var mEmail: EditText? = null

    //vars
    private var mContext: Context? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(R.layout.dialog_resetpassword, container, false)
        mEmail = view.findViewById<View>(R.id.email_password_reset) as EditText
        mContext = activity
        val confirmDialog =
            view.findViewById<View>(R.id.dialogConfirm) as TextView
        confirmDialog.setOnClickListener {
            if (!isEmpty(mEmail!!.text.toString())) {
                Log.d(
                    TAG,
                    "onClick: attempting to send reset link to: " + mEmail!!.text.toString()
                )
                sendPasswordResetEmail(mEmail!!.text.toString())
                dialog!!.dismiss()
            }
        }
        return view
    }

    /**
     * Send a password reset link to the email provided
     * @param email
     */
    fun sendPasswordResetEmail(email: String?) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(
                        TAG,
                        "onComplete: Password Reset Email sent."
                    )
                    Toast.makeText(
                        mContext, "Link Password Berhasil Dikirim, Cek Email Kamu!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.d(
                        TAG,
                        "onComplete: No user associated with that email."
                    )
                    Toast.makeText(
                        mContext, "E-mail tidak dikenali",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    /**
     * Return true if the @param is null
     * @param string
     * @return
     */
    private fun isEmpty(string: String): Boolean {
        return string == ""
    }

    companion object {
        private const val TAG = "PasswordResetDialog"
    }
}

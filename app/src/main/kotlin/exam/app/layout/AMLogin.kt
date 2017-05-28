package exam.app.layout

import android.os.Bundle
import android.support.v4.app.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import exam.app.ActivityMain
import exam.app.R
import kotlinx.android.synthetic.main.fragment_am_login.view.*
import org.jetbrains.anko.onClick
import java.util.regex.Matcher
import java.util.regex.Pattern


class AMLogin : Fragment() {
    val TAG = "AMLogin"

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?):
            View? {

        /*
        Inflate the layout for this fragment
            Takes the XML code and makes the view.
            ! All buttons should be with fragment
            EX. fragment.BUTTONNAME
         */
        val fragment = inflater.inflate(R.layout.fragment_am_login, container, false)

        /**
         *  Login button
         *  Gets Username, email, Phonenumber and Password.
         *  Connects to firebase and vaildates the user.
         *  Shows the overview.
         */

        fragment.login_button.onClick {
            //Gets Username
            val displayName = fragment.username_field.text.toString()
            //Gets E-mail
            val email = fragment.email_field.text.toString()
            //Gets Phonenumber
            val phonenumber = fragment.phone_number_field.text.toString()
            //Gets Password
            val password = fragment.password_field.text.toString()


            /**
             * Change view
             * Make sure to call the activity like this when changing view.
             * (activity as ActivityMain).FunctionName
             */

            //TODO: Make methode to only create user
            //(activity as ActivityMain).sendMessageToUser("testuser", "Hiiiiiiiiiiii", email, password)
            //(activity as ActivityMain).showOverview(username, email, phonenumber);
            (activity as ActivityMain).showNewMessagetest()

            if(displayName.trim().equals("")){
                fragment.username_field.setError("Please enter a Username")
            } else if (!validateEmail(email)){
                fragment.email_field.setError("Please enter a valied email")
            } else if (!validatePhonenumber(phonenumber)){
                fragment.phone_number_field.setError("Please enter a Phonenumber")
            } else if (password.trim().equals("")) {
                fragment.password_field.setError("Please enter a Password")
            } else {
                (activity as ActivityMain).firebaseLogin(displayName, email, password, phonenumber)
            }

        }

        /**
         * This is that last thing that should happen in the fragment.
         * This where it actually returns the view
         */
        return fragment

    }

    fun validateEmail(email : String) : Boolean {
        val regex : String = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern : Pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        val matcher : Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun validatePhonenumber(phonenumber : String) : Boolean {
        val regex : String = "^\\d{8}$"
        val pattern : Pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        val matcher : Matcher = pattern.matcher(phonenumber)
        return matcher.matches()
    }
}



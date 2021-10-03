package com.example.roomloginapp.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.roomloginapp.R
import com.example.roomloginapp.data.SharedPreferencesManager
import com.example.roomloginapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executor

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: AppCompatButton
    private lateinit var fingerprintAuthButton: ImageButton
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setBiometricAuthentication()

        sharedPreferencesManager = SharedPreferencesManager(requireContext())

        userViewModel.user()?.observe(viewLifecycleOwner) { user ->
            // if user is registered
            if (sharedPreferencesManager.checkRegistered()) {
                // set default value for username
                usernameEditText.setText(user.username)
            }
        }

        // sign in button on click
        signInButton.setOnClickListener {
            signIn()
        }

        // fingerprint button on click
        fingerprintAuthButton.setOnClickListener {
            // if user is registered
            if (sharedPreferencesManager.checkRegistered()) {
                initBiometricAuthentication()
            } else {
                Toast.makeText(context, "User is not registered!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // init fragment views
    private fun initViews(view: View) {
        usernameEditText = view.findViewById(R.id.username_edit_text)
        passwordEditText = view.findViewById(R.id.password_edit_text)
        signInButton = view.findViewById(R.id.sign_in_button)
        fingerprintAuthButton = view.findViewById(R.id.fingerprint_button)
    }

    // sign in user
    private fun signIn() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        // sign in user
        userViewModel.signInUser(username, password)
    }

    // set biometric authentication details
    @RequiresApi(Build.VERSION_CODES.P)
    private fun setBiometricAuthentication() {
        executor = requireContext().mainExecutor

        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(context, "Error: $errString", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)

                    passwordEditText.setText(userViewModel.user()?.value?.password)
                    signIn()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        )

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Login")
            .setSubtitle("Use biometric credential")
            .setNegativeButtonText("Use account password")
            .build()
    }

    // init biometric authentication
    private fun initBiometricAuthentication() {
        val biometricManager: BiometricManager = BiometricManager.from(requireContext())

        if (biometricManager.canAuthenticate() != BiometricManager.BIOMETRIC_SUCCESS) {
            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
            return
        }

        biometricPrompt.authenticate(promptInfo)
    }

}
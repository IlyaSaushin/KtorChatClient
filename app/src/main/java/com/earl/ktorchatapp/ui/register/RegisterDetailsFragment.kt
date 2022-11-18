package com.earl.ktorchatapp.ui.register

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.earl.ktorchatapp.KtorChatApp
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.core.Keys
import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.core.SharedPreferenceManager
import com.earl.ktorchatapp.databinding.FragmentUserDetailsBinding
import com.earl.ktorchatapp.ui.NavigationContract
import com.earl.ktorchatapp.ui.ViewModelFactory
import com.earl.ktorchatapp.ui.models.UiRegisterDto
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import javax.inject.Inject

class RegisterDetailsFragment @Inject constructor(
    private val email: String,
    private val password: String
) : BaseFragment<FragmentUserDetailsBinding>(), OperationResultListener {

    private lateinit var navigator: NavigationContract
    private lateinit var preferenceManager: SharedPreferenceManager
    @Inject
    lateinit var viewModel: RegisterViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var validation: UserDetailsFormValidation
    var encodedImage: String? = null

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUserDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as KtorChatApp).appComponent.injectRegisterDetailsFragment(this)
        navigator = requireActivity() as NavigationContract
        preferenceManager = SharedPreferenceManager(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[RegisterViewModel::class.java]
        binding.addAvatar.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            pickImage.launch(intent)
        }
        binding.addDetailsButton.setOnClickListener { register() }
        binding.iconBack.setOnClickListener { navigator.back() }
    }

    private fun isValidate(): Boolean {
        return validation.validate(
            encodedImage!!,
            binding.nameEd,
            binding.nickEd,
            binding.userBio
        )
    }

    private fun register() {
        if (isValidate()) {
            navigator.showProgressBar()

            val dto = UiRegisterDto.Base(
                email,
                binding.nameEd.text.toString(),
                password,
                binding.userBio.text.toString(),
                encodedImage ?: "",
            )

            viewModel.register(dto, this)
        }
    }

    private fun encodeImage(bitmap: Bitmap): String {
        val previewWidth = 150
        val previewHeight = bitmap.height * previewWidth / bitmap.width
        val previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false)
        val byteArrayOutputStream = ByteArrayOutputStream()
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
        val bytes = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    private val pickImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data != null) {
                val imageUri = result.data!!.data
                try {
                    val inputStream =
                        requireContext().contentResolver.openInputStream(
                            imageUri!!
                        )
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    binding.accAvatar.setImageBitmap(bitmap)
                    binding.addAvatar.visibility = View.GONE
                    encodedImage = encodeImage(bitmap)
                } catch (exception: FileNotFoundException) {
                    exception.printStackTrace()
                }
            }
        }
    }

    override fun <T> success(success: T) {
        preferenceManager.putString(Keys.KEY_IMAGE, encodedImage)
        preferenceManager.putString(Keys.KEY_NAME, binding.nameEd.text.toString().trim())
        preferenceManager.putString(Keys.KEY_NICK_NAME, binding.nickEd.text.toString().trim())
        preferenceManager.putString(Keys.KEY_USER_BIO, binding.userBio.text.toString().trim())
        preferenceManager.putBoolean(Keys.KEY_IS_SIGNED_UP, true)
        preferenceManager.putString(Keys.KEY_TOKEN, success.toString())
        navigator.hideProgressBar()
        navigator.chatBaseFragment()
    }

    override fun fail(exception: Exception?) {
        Toast.makeText(
            requireContext(),
            "Registration failed, exception - " + exception + "Try to login, or register again",
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {

        fun newInstance(email: String, password: String) = RegisterDetailsFragment(email, password)
    }
}
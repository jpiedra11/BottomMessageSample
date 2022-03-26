package mx.aztek.bottommessagesample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private const val ARG_TITLE = "title"
private const val ARG_MESSAGE = "message"
private const val ARG_FIRST_BUTTON_TEXT = "first_button_text"
private const val ARG_SECOND_BUTTON_TEXT = "second_button_text"

/**
 * A simple [Fragment] subclass.
 * Use the [BottomDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BottomDialogFragment : BottomSheetDialogFragment() {

    private lateinit var title: String
    private lateinit var message: String
    private lateinit var firstButtonText: String
    private lateinit var secondButtonText: String
    private var image: Int? = null
    private var onFirstButtonClick: (() -> Unit?)? = null
    private var onSecondButtonClick: (() -> Unit?)? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //To have transparent style
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)

        arguments?.let {
            title = it.getString(ARG_TITLE, "Title")
            message = it.getString(ARG_MESSAGE, "Message")
            firstButtonText = it.getString(ARG_FIRST_BUTTON_TEXT, "FIRST BUTTON")
            secondButtonText = it.getString(ARG_SECOND_BUTTON_TEXT, "SECOND BUTTON")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_dialog, container, false)

        if (onFirstButtonClick != null) {
            val firstButton = view?.findViewById<Button>(R.id.firstButton)
            firstButton?.setOnClickListener(View.OnClickListener {
                onFirstButtonClick?.invoke()
                dialog?.dismiss()
            })
            firstButton?.text = firstButtonText
            firstButton?.visibility = View.VISIBLE
        }

        if (onSecondButtonClick != null) {
            val secondButton = view?.findViewById<Button>(R.id.secondButton)
            secondButton?.setOnClickListener(View.OnClickListener {
                onSecondButtonClick?.invoke()
                dialog?.dismiss()
            })
            secondButton?.text = secondButtonText
            secondButton?.visibility = View.VISIBLE
        }

        val closeButton = view?.findViewById<Button>(R.id.closeButton)
        closeButton?.setOnClickListener(View.OnClickListener {
            this.dialog?.dismiss()
        })

        val titleTextView = view?.findViewById<TextView>(R.id.titleTextView)
        titleTextView?.text = title

        val messageTextView = view?.findViewById<TextView>(R.id.messageTextView)
        messageTextView?.text = message

        if (image != null) {
            val helpImageView = view?.findViewById<ImageView>(R.id.helpImageView)
            helpImageView?.setImageDrawable(resources.getDrawable(image!!, requireContext().theme))
        }

        return view
    }

    class Builder {
        private val arguments: Bundle = Bundle()
        private var image: Int? = null
        private var onFirstButtonClick: (() -> Unit?)? = null
        private var onSecondButtonClick: (() -> Unit?)? = null

        fun setTitle(title: String): Builder {
            arguments.putString(ARG_TITLE, title)
            return this
        }

        fun setMessage(message: String): Builder {
            arguments.putString(ARG_MESSAGE, message)
            return this
        }

        fun setImage(imageResource: Int): Builder {
            image = imageResource
            return this
        }

        fun setFirstButtonText(firstButtonText: String): Builder {
            arguments.putString(ARG_FIRST_BUTTON_TEXT, firstButtonText)
            return this
        }

        fun setSecondButtonText(secondButtonText: String): Builder {
            arguments.putString(ARG_SECOND_BUTTON_TEXT, secondButtonText)
            return this
        }

        fun setOnFirstButtonClick(onFirstButtonClick: () -> Unit): Builder {

            this.onFirstButtonClick = onFirstButtonClick
            return this
        }

        fun setOnSecondButtonClick(onSecondButtonClick: () -> Unit): Builder {

            this.onSecondButtonClick = onSecondButtonClick
            return this
        }

        fun build(): BottomDialogFragment {
            val fragment = BottomDialogFragment()
            fragment.arguments = arguments
            fragment.image = image
            fragment.onFirstButtonClick = onFirstButtonClick
            fragment.onSecondButtonClick = onSecondButtonClick
            return fragment
        }

    }

    fun BottomDialogFragment() {
        // Required empty public constructor
    }
}
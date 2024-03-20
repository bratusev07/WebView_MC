package ru.bratusev.webview_mc

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class WebViewScreen : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var adapter: ImageAdapter

    private val firstUserData = listOf(
        WebModel(R.drawable.git_icon, "https://github.com/"),
        WebModel(R.drawable.youtube_icon, "https://www.youtube.com/"),
        WebModel(R.drawable.twitch_icon, "https://www.twitch.tv/"),
        WebModel(R.drawable.twiter_icon, "http://twitter.com/"),
        WebModel(R.drawable.vk_icon, "https://vk.com")
    )

    private val secondUserData = listOf(
        WebModel(R.drawable.youtube_icon, "https://www.youtube.com/"),
        WebModel(R.drawable.twitch_icon, "https://www.twitch.tv/"),
        WebModel(R.drawable.twiter_icon, "http://twitter.com/"),
        WebModel(R.drawable.vk_icon, "https://vk.com")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_screen)
        configureWebView()
        configureRecycler(intent.getIntExtra("ACCOUNT_ID", 1))
    }

    private fun configureRecycler(userID: Int) {
        val recyclerView = findViewById<RecyclerView>(R.id.collection_list)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = ImageAdapter(
            webView,
            if (userID == 1) firstUserData else secondUserData
        )
        recyclerView.adapter = adapter
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configureWebView() {
        webView = findViewById(R.id.webView)
        webView.getSettings().javaScriptEnabled = true
        val webViewClient: WebViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
        webView.setWebViewClient(webViewClient)
    }
}

class ImageAdapter(private val webView: WebView, private val imageUrls: List<WebModel>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = imageUrls[position]
        holder.bind(imageUrl)
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(imageUrl: WebModel) {
            imageView.setImageResource(imageUrl.resource)
            itemView.setOnClickListener {
                webView.loadUrl(imageUrl.link)
            }
        }
    }
}

data class WebModel(val resource: Int, val link: String)
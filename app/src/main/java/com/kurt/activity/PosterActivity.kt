package com.kurt.activity

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.kurt.R
import com.kurt.Utils
import com.kurt.control.ScrollLayoutManager
import com.kurt.entity.SearchResult
import com.kurt.entity.VodMedia
import com.kurt.util.ContextFun.logD
import com.kurt.util.ContextFun.logE
import com.kurt.util.ContextFun.logI
import com.kurt.util.ContextFun.readAsset
import com.kurt.util.ContextFun.toast
import com.kurt.util.JsonFun
import com.kurt.util.ViewFun.inflate
import com.kurt.util.ViewFun.load
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.view_poster_layout.view.*

class PosterActivity : BaseKotlinActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
    }

    class PosterRecyclerView : RecyclerView {
        private var list: MutableList<SearchResult> = ArrayList()

        constructor(context: Context) : super(context)
        constructor(context: Context, aSet: AttributeSet?) : super(context, aSet)

        init {
            layoutManager = ScrollLayoutManager(context, 3)
            adapter = PosterAdapter()
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                    super.getItemOffsets(outRect, view, parent, state)
                    outRect.set(20, 50, 20, 50)
                }
            })
        }

        override fun onAttachedToWindow() {
            super.onAttachedToWindow()
            Flowable.create({ emmiter: FlowableEmitter<List<SearchResult>> ->
                try {
                    val json = context.readAsset("example.json")
                    val res = JsonFun.fromJson<VodMedia>(json).searchResultList
                    emmiter.onNext(res)
                    emmiter.onComplete()
                } catch (e: Exception) {
                    emmiter.onError(e)
                }
            }, BackpressureStrategy.LATEST
            ).subscribeOn(Schedulers.io()
            ).observeOn(AndroidSchedulers.mainThread()
            ).subscribe({
                logI("poster view on next")
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }, {
                logE("poster view on error:", it)
            }, {
                logD("poster view on success")
            }, {
                context.toast("加载中")
                it.request(5)
            })
        }

        inner class PosterAdapter : RecyclerView.Adapter<PosterHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterHolder {
                val view = parent.inflate(R.layout.view_poster_layout) as ViewGroup
                view.clipToPadding = false
                view.clipChildren = false
                return PosterHolder(view)
            }

            override fun onBindViewHolder(holder: PosterHolder, position: Int) {
                holder.update(position)
            }

            override fun getItemCount(): Int = list.size
        }

        inner class PosterHolder(var parent: ViewGroup) : RecyclerView.ViewHolder(parent) {
            init {
                parent.isFocusable = true
                parent.isFocusableInTouchMode = true
                parent.descendantFocusability = ViewGroup.FOCUS_BEFORE_DESCENDANTS
                parent.setOnFocusChangeListener({ _, b -> parent.tv_poster_title.setFocus(b)})
            }

            fun update(pos: Int) {
                val bean = list[pos]
                parent.tv_poster_title.text = Utils.highlightTitle(bean.mediaTitle)
                parent.iv_poster.load(bean.poster)
            }
        }
    }
}
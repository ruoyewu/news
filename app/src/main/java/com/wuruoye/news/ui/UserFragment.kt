package com.wuruoye.news.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.wuruoye.library.ui.WBaseFragment
import com.wuruoye.library.util.BitmapUtil
import com.wuruoye.news.R
import com.wuruoye.news.contract.UserContract
import com.wuruoye.news.model.Config.USER_LOGIN
import com.wuruoye.news.model.bean.LoginUser
import kotlinx.android.synthetic.main.fragment_user.*

/**
 * @Created : wuruoye
 * @Date : 2018/7/9 16:55.
 * @Description : 用户页面
 */
class UserFragment : WBaseFragment<UserContract.Presenter>(), UserContract.View,
        View.OnClickListener {
    private var mIsLogin = false

    override fun getContentView(): Int {
        return R.layout.fragment_user
    }

    override fun initData(p0: Bundle?) {

    }

    override fun initView(p0: View?) {
        p0!!.post {
            abl_user.post {
                cl_user.dispatchDependentViewsChanged(abl_user)
            }

            civ_user_avatar.setOnClickListener(this)
        }
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.civ_user_avatar -> {
                val intent = Intent(context, LoginActivity::class.java)
                startActivityForResult(intent, USER_LOGIN)
            }
        }
    }

    private fun onLoginResult(loginUser: LoginUser) {
        mIsLogin = true
        Glide.with(civ_user_avatar)
                .asBitmap()
                .load(loginUser.avatar)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(e: GlideException?, model: Any?,
                                              target: Target<Bitmap>?,
                                              isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Bitmap?, model: Any?,
                                                 target: Target<Bitmap>?, dataSource: DataSource?,
                                                 isFirstResource: Boolean): Boolean {
                        civ_user_avatar.setImageBitmap(resource)
                        iv_user_bg.setImageBitmap(BitmapUtil.blur(context, resource))
                        return true
                    }
                })
                .submit()
        tv_user_user.text = loginUser.name
        tv_user_sign.text = loginUser.sign
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == USER_LOGIN && resultCode == RESULT_OK) {
            val loginUser = data!!.getParcelableExtra<LoginUser>("loginUser")
            onLoginResult(loginUser)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
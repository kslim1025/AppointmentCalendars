package com.kslim1025.daily10minute

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.kslim1025.daily10minute.utils.ContextUtil

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        val myHandler = Handler()

        myHandler.postDelayed({

//            로그인을 했느냐 안했느냐는
//            토큰에 저장된값이 있느냐, 빈칸이냐로 구별하자.

            if (ContextUtil.getLoginUserToken(mContext) == "") {
//                로그인을 아직 안한 경우
                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)
            }
            else {
//                로그인을 해둔 경우

                val myIntent = Intent(mContext, MainActivity::class.java)
                startActivity(myIntent)

            }

//            스플래쉬화면은 종료
            finish()

        }, 2500)


    }

}
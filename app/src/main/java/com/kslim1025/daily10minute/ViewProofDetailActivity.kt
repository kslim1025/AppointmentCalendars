package com.kslim1025.daily10minute

import android.os.Bundle
import com.kslim1025.daily10minute.datas.Proof
import com.kslim1025.daily10minute.utils.ServerUtil
import com.kslim1025.daily10minute.utils.TimeUtil
import kotlinx.android.synthetic.main.activity_view_proof_detail.*
import org.json.JSONObject

class ViewProofDetailActivity : BaseActivity() {

    //    목록에서 넘겨주는 인증글의 id값
    var mProofId = 0

    //    이 화면에서 표시해야할 데이터들이 담긴 인증 글
    lateinit var mProof : Proof

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_proof_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mProofId = intent.getIntExtra("proof_id", 0)

        getProofDataFromServer()
    }

    fun getProofDataFromServer() {
//        GET - /project_proof/id값   API 호출

        ServerUtil.getRequestProofDetail(mContext, mProofId, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")
                val proof = data.getJSONObject("project")

                mProof = Proof.getProofFromJson(proof)

                runOnUiThread {
                    writerNickNameTxt.text = mProof.user.nickName
                    createAtTxt.text = TimeUtil.getTimeAgoStringFromCalendar(mProof.proofTime)
                    contentTxt.text = mProof.content
                }

            }

        })

    }

}
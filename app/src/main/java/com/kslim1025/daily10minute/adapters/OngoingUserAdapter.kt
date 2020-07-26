package com.kslim1025.daily10minute.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kslim1025.daily10minute.R
import com.kslim1025.daily10minute.datas.User

class OngoingUserAdapter(
    val mContext:Context,
    resId:Int,
    val mList:List<User>) : ArrayAdapter<User>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.ongoing_user_list_item, null)
        }

        val row = tempRow!!

        val profileImg = row.findViewById<ImageView>(R.id.profileImg)
        val nickNameTxt = row.findViewById<TextView>(R.id.nickNameTxt)
        val emailTxt = row.findViewById<TextView>(R.id.emailTxt)
        val daysTxt = row.findViewById<TextView>(R.id.daysTxt)


        val data = mList[position]

        nickNameTxt.text = data.nickName
        emailTxt.text = data.email

//        도전 ~일차 가공
        daysTxt.text = "도전 ${data.projectDays}일차"

//        프사를 띄워주자. => 0번쨰 프사가 제일 최근 업로드 프사. => 0번째 사진을 무조건 보여주자.
        Glide.with(mContext).load(data.profileImageList[0].imageUrl).into(profileImg)


        return row
    }


}
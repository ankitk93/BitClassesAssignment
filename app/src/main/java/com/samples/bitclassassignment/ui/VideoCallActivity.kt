package com.samples.bitclassassignment.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.samples.bitclassassignment.R
import com.samples.bitclassassignment.databinding.ActivityVideoCallBinding
import com.samples.bitclassassignment.viewModel.videoCallActivity.VideoCallViewModel
import com.samples.bitclassassignment.viewModel.videoCallActivity.VideoCallViewModelFactory

/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */
class VideoCallActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityVideoCallBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_video_call)

        val viewModelFactory =
            VideoCallViewModelFactory(
                application
            )

        val viewModel: VideoCallViewModel by lazy {
            ViewModelProviders.of(this, viewModelFactory).get(VideoCallViewModel::class.java)
        }

        binding.vm = viewModel
        binding.lifecycleOwner = this

        // If all the permissions are granted, initialize the RtcEngine object and join a channel.
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO, PERMISSION_REQ_ID_RECORD_AUDIO) &&
            checkSelfPermission(Manifest.permission.CAMERA, PERMISSION_REQ_ID_CAMERA)) {
            viewModel.joinChannel()
            viewModel.setupVideoProfile()
        }

        viewModel.shouldCloseLiveData.observe(this, Observer {
            finish()
        })
    }

    private fun initEngineAndJoinChannel() {
        //setupLocalVideo()
    }

    private fun checkSelfPermission(permission: String, requestCode: Int): Boolean{
        Log.e("LOG_TAG", "checkSelfPermission $permission $requestCode")
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQ_ID_RECORD_AUDIO -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkSelfPermission(Manifest.permission.CAMERA, PERMISSION_REQ_ID_CAMERA)
                } else {
                    showLongToast("No permission for " + Manifest.permission.RECORD_AUDIO)
                    finish()
                }
            }
            PERMISSION_REQ_ID_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initEngineAndJoinChannel()
                } else {
                    showLongToast("No permission for " + Manifest.permission.CAMERA)
                    finish()
                }
            }
        }
    }

    private fun showLongToast(msg: String) {
        this.runOnUiThread { Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show() }
    }

    companion object {
        private const val PERMISSION_REQ_ID_RECORD_AUDIO = 10
        private const val PERMISSION_REQ_ID_CAMERA = 11
    }
}
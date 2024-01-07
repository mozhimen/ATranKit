package com.mozhimen.transk.test

import android.os.Bundle
import com.mozhimen.basick.elemk.androidx.appcompat.bases.databinding.BaseActivityVB
import com.mozhimen.basick.lintk.optin.OptInApiInit_ByLazy
import com.mozhimen.basick.lintk.optin.OptInApiCall_BindLifecycle
import com.mozhimen.basick.manifestk.cons.CPermission
import com.mozhimen.basick.manifestk.permission.ManifestKPermission
import com.mozhimen.basick.manifestk.permission.annors.APermissionCheck
import com.mozhimen.basick.manifestk.annors.AManifestKRequire
import com.mozhimen.basick.utilk.android.os.UtilKBuildVersion
import com.mozhimen.transk.test.databinding.ActivityTranskTtsBinding
import com.mozhimen.transk.tts.TransKTTSProxy

@AManifestKRequire(CPermission.FOREGROUND_SERVICE)
@APermissionCheck(CPermission.FOREGROUND_SERVICE)
class TransKTTSActivity : BaseActivityVB<ActivityTranskTtsBinding>() {
    @OptIn(OptInApiInit_ByLazy::class, OptInApiCall_BindLifecycle::class)
    private val _transKTTSProxy by lazy {
        TransKTTSProxy(this)
    }

    override fun initData(savedInstanceState: Bundle?) {
        if (UtilKBuildVersion.isAfterV_28_9_P()) {
            ManifestKPermission.requestPermissions(this, arrayOf(CPermission.FOREGROUND_SERVICE)) {
                super.initData(savedInstanceState)
            }
        } else {
            super.initData(savedInstanceState)
        }
    }

    @OptIn(OptInApiInit_ByLazy::class, OptInApiCall_BindLifecycle::class)
    override fun initView(savedInstanceState: Bundle?) {
        _transKTTSProxy.bindLifecycle(this)
        vb.transkT2sBtn.setOnClickListener {
            _transKTTSProxy.play(vb.transkT2sEdt.text.toString())
        }
    }
}
/*
 * Copyright 2017 Yan Zhenjie.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.widget.album.api.choice;

import android.content.Context;

import com.example.widget.album.api.VideoMultipleWrapper;
import com.example.widget.album.api.VideoSingleWrapper;


/**
 * Created by YanZhenjie on 2017/8/16.
 */
public final class VideoChoice implements Choice<VideoMultipleWrapper, VideoSingleWrapper> {

    private Context mContext;

    public VideoChoice(Context context) {
        mContext = context;
    }

    @Override
    public VideoMultipleWrapper multipleChoice() {
        return new VideoMultipleWrapper(mContext);
    }

    @Override
    public VideoSingleWrapper singleChoice() {
        return new VideoSingleWrapper(mContext);
    }

}
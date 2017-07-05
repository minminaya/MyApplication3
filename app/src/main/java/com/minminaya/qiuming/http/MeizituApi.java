package com.minminaya.qiuming.http;

import com.minminaya.qiuming.model.MeizituModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Niwa on 2017/7/5.
 */

public interface MeizituApi {
    @GET("meizitu/api/hello/pic")
    Observable<List<MeizituModel>> loadMeiziPic(
            @Query("size") Integer size,
            @Query("offset") Integer offset);
}

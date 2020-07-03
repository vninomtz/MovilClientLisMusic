package com.uv.lismusicjava.Albums;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AlbumsApi {
    @GET("account/{idAccount}/albumsLike")
    Call<List<Album>> getAlbumsLikes(@Path("idAccount") String idAccount, @Header("Authorization") String token);
}

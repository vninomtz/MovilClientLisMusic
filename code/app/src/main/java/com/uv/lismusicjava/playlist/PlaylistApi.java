package com.uv.lismusicjava.playlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlaylistApi {
    @GET("account/{idAccount}/playlist")
    Call<List<Playlist>> getPlaylistsAccount(@Path("idAccount") String idAccount);
}

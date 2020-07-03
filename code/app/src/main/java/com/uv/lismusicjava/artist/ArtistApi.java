package com.uv.lismusicjava.artist;

import com.uv.lismusicjava.playlist.Playlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArtistApi {
    @GET("/account/{idAccount}/artistsLike")
    Call<List<Artist>> getArtistsLikeOfAccount(@Path("idAccount") String idAccount);
}

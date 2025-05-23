package pt.ua.deti.ies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * IPMA API service mapping
 */
public interface IpmaService {

    @GET("forecast/meteorology/cities/daily/{city_id}.json")
    Call<IpmaCityForecast> getForecastForACity(@Path("city_id") int cityId);

    @GET("distrits-islands.json")
    Call<IpmaCityForecast> getCities();

}

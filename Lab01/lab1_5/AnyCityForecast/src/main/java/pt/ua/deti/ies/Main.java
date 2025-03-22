package pt.ua.deti.ies;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);
    private Timer timer;
    private static final String BASE_URL = "http://api.ipma.pt/open-data/";
    private IpmaService service;

    public Main() {
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        
        service = retrofit.create(IpmaService.class);

        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 0, 20 * 1000); // schedule every 20 seconds
    }

    // Classe que será executada periodicamente
    class RemindTask extends TimerTask {
        @Override
        public void run() {
            try {
                logger.info("Fetching cities...");

                // Fetch de cidades da API
                Call<IpmaCityForecast> callSync = service.getCities();
                Response<IpmaCityForecast> apiResponse = callSync.execute();

                if (apiResponse.isSuccessful()) {
                    IpmaCityForecast forecast = apiResponse.body();
                    Map<String, String> cityNamesById = new HashMap<>();

                    if (forecast != null && forecast.getData() != null) {
                        // Armazene os dados da cidade (ID + Nome) em um mapa
                        for (CityForecast city : forecast.getData()) {
                            cityNamesById.put(city.getGlobalIdLocal().toString(), city.getLocal());
                        }

                        // Selecionar uma cidade aleatória
                        List<String> cityIds = new ArrayList<>(cityNamesById.keySet());
                        int randomIndex = ThreadLocalRandom.current().nextInt(cityIds.size());
                        String randomCityId = cityIds.get(randomIndex);
                        String cityName = cityNamesById.get(randomCityId);

                        logger.info("Random city selected: " + randomCityId + " (" + cityName + ")");
                        fetchForecastForCity(randomCityId, cityName);  // Passe o nome da cidade aqui
                    } else {
                        logger.warn("No cities data available!");
                    }
                } else {
                    logger.error("Error fetching cities: " + apiResponse.errorBody().string());
                }

            } catch (Exception e) {
                logger.error("Error during city fetch: ", e);
            }
        }

    
        private void fetchForecastForCity(String cityId, String cityName) {
            try {
                logger.info("Fetching forecast for city: " + cityName + " (ID: " + cityId + ")");

                
                Call<IpmaCityForecast> callForecast = service.getForecastForACity(Integer.parseInt(cityId));
                Response<IpmaCityForecast> forecastResponse = callForecast.execute();

                if (forecastResponse.isSuccessful()) {
                    IpmaCityForecast forecast = forecastResponse.body();
                    if (forecast != null) {
                        logger.info("Forecast for city " + cityName + ":");
                        for (CityForecast cityForecast : forecast.getData()) {
                            logger.info("Date: " + cityForecast.getForecastDate());
                            logger.info("Min Temp: " + cityForecast.getTMin());
                            logger.info("Max Temp: " + cityForecast.getTMax());
                            logger.info("Precipitation Probability: " + cityForecast.getPrecipitaProb());
                        }
                    } else {
                        logger.warn("No forecast data available for city " + cityName);
                    }
                } else {
                    logger.error("Error fetching forecast: " + forecastResponse.errorBody().string());
                }

            } catch (Exception e) {
                logger.error("Error fetching forecast for city " + cityName + " (ID: " + cityId + ")", e);
            }
        }
    }


    public static void main(String[] args) {
        logger.info("Starting the AnyCityForecast application...");
        new Main();  // Instancia a classe Main, que inicializa Retrofit e o serviço IPMA
    }
}

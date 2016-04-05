package com.chan.chanweather.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chan on 16/3/26.
 */
public class CityResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("city_info")
    private List<CityInfoEntity> cityInfo;

    public static CityResponse objectFromData(String str) {

        return new Gson().fromJson(str, CityResponse.class);
    }

    public static List<CityResponse> arrayCityResponseFromData(String str) {

        Type listType = new TypeToken<ArrayList<CityResponse>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CityInfoEntity> getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(List<CityInfoEntity> cityInfo) {
        this.cityInfo = cityInfo;
    }

    public static class CityInfoEntity {
        @SerializedName("city")
        private String city;
        @SerializedName("cnty")
        private String cnty;
        @SerializedName("id")
        private String id;
        @SerializedName("lat")
        private String lat;
        @SerializedName("lon")
        private String lon;
        @SerializedName("prov")
        private String prov;

        public static CityInfoEntity objectFromData(String str) {

            return new Gson().fromJson(str, CityInfoEntity.class);
        }

        public static List<CityInfoEntity> arrayCityInfoEntityFromData(String str) {

            Type listType = new TypeToken<ArrayList<CityInfoEntity>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCnty() {
            return cnty;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getProv() {
            return prov;
        }

        public void setProv(String prov) {
            this.prov = prov;
        }
    }
}

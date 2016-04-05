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
public class WeatherTypeResponse {

    /**
     * status : ok
     * cond_info : [{"code":"100","txt":"晴","txt_en":"Sunny/Clear","icon":"http://files.heweather.com/cond_icon/100.png"},{"code":"101","txt":"多云","txt_en":"Cloudy","icon":"http://files.heweather.com/cond_icon/101.png"},{"code":"102","txt":"少云","txt_en":"Few Clouds","icon":"http://files.heweather.com/cond_icon/102.png"},{"code":"103","txt":"晴间多云","txt_en":"Partly Cloudy","icon":"http://files.heweather.com/cond_icon/103.png"},{"code":"104","txt":"阴","txt_en":"Overcast","icon":"http://files.heweather.com/cond_icon/104.png"},{"code":"200","txt":"有风","txt_en":"Windy","icon":"http://files.heweather.com/cond_icon/200.png"},{"code":"201","txt":"平静","txt_en":"Calm","icon":"http://files.heweather.com/cond_icon/201.png"},{"code":"202","txt":"微风","txt_en":"Light Breeze","icon":"http://files.heweather.com/cond_icon/202.png"},{"code":"203","txt":"和风","txt_en":"Moderate/Gentle Breeze","icon":"http://files.heweather.com/cond_icon/203.png"},{"code":"204","txt":"清风","txt_en":"Fresh Breeze","icon":"http://files.heweather.com/cond_icon/204.png"},{"code":"205","txt":"强风/劲风","txt_en":"Strong Breeze","icon":"http://files.heweather.com/cond_icon/205.png"},{"code":"206","txt":"疾风","txt_en":"High Wind, Near Gale","icon":"http://files.heweather.com/cond_icon/206.png"},{"code":"207","txt":"大风","txt_en":"Gale","icon":"http://files.heweather.com/cond_icon/207.png"},{"code":"208","txt":"烈风","txt_en":"Strong Gale","icon":"http://files.heweather.com/cond_icon/208.png"},{"code":"209","txt":"风暴","txt_en":"Storm","icon":"http://files.heweather.com/cond_icon/209.png"},{"code":"210","txt":"狂爆风","txt_en":"Violent Storm","icon":"http://files.heweather.com/cond_icon/210.png"},{"code":"211","txt":"飓风","txt_en":"Hurricane","icon":"http://files.heweather.com/cond_icon/211.png"},{"code":"212","txt":"龙卷风","txt_en":"Tornado","icon":"http://files.heweather.com/cond_icon/212.png"},{"code":"213","txt":"热带风暴","txt_en":"Tropical Storm","icon":"http://files.heweather.com/cond_icon/213.png"},{"code":"300","txt":"阵雨","txt_en":"Shower Rain","icon":"http://files.heweather.com/cond_icon/300.png"},{"code":"301","txt":"强阵雨","txt_en":"Heavy Shower Rain","icon":"http://files.heweather.com/cond_icon/301.png"},{"code":"302","txt":"雷阵雨","txt_en":"Thundershower","icon":"http://files.heweather.com/cond_icon/302.png"},{"code":"303","txt":"强雷阵雨","txt_en":"Heavy Thunderstorm","icon":"http://files.heweather.com/cond_icon/303.png"},{"code":"304","txt":"雷阵雨伴有冰雹","txt_en":"Hail","icon":"http://files.heweather.com/cond_icon/304.png"},{"code":"305","txt":"小雨","txt_en":"Light Rain","icon":"http://files.heweather.com/cond_icon/305.png"},{"code":"306","txt":"中雨","txt_en":"Moderate Rain","icon":"http://files.heweather.com/cond_icon/306.png"},{"code":"307","txt":"大雨","txt_en":"Heavy Rain","icon":"http://files.heweather.com/cond_icon/307.png"},{"code":"308","txt":"极端降雨","txt_en":"Extreme Rain","icon":"http://files.heweather.com/cond_icon/308.png"},{"code":"309","txt":"毛毛雨/细雨","txt_en":"Drizzle Rain","icon":"http://files.heweather.com/cond_icon/309.png"},{"code":"310","txt":"暴雨","txt_en":"Storm","icon":"http://files.heweather.com/cond_icon/310.png"},{"code":"311","txt":"大暴雨","txt_en":"Heavy Storm","icon":"http://files.heweather.com/cond_icon/311.png"},{"code":"312","txt":"特大暴雨","txt_en":"Severe Storm","icon":"http://files.heweather.com/cond_icon/312.png"},{"code":"313","txt":"冻雨","txt_en":"Freezing Rain","icon":"http://files.heweather.com/cond_icon/313.png"},{"code":"400","txt":"小雪","txt_en":"Light Snow","icon":"http://files.heweather.com/cond_icon/400.png"},{"code":"401","txt":"中雪","txt_en":"Moderate Snow","icon":"http://files.heweather.com/cond_icon/401.png"},{"code":"402","txt":"大雪","txt_en":"Heavy Snow","icon":"http://files.heweather.com/cond_icon/402.png"},{"code":"403","txt":"暴雪","txt_en":"Snowstorm","icon":"http://files.heweather.com/cond_icon/403.png"},{"code":"404","txt":"雨夹雪","txt_en":"Sleet","icon":"http://files.heweather.com/cond_icon/404.png"},{"code":"405","txt":"雨雪天气","txt_en":"Rain And Snow","icon":"http://files.heweather.com/cond_icon/405.png"},{"code":"406","txt":"阵雨夹雪","txt_en":"Shower Snow","icon":"http://files.heweather.com/cond_icon/406.png"},{"code":"407","txt":"阵雪","txt_en":"Snow Flurry","icon":"http://files.heweather.com/cond_icon/407.png"},{"code":"500","txt":"薄雾","txt_en":"Mist","icon":"http://files.heweather.com/cond_icon/500.png"},{"code":"501","txt":"雾","txt_en":"Foggy","icon":"http://files.heweather.com/cond_icon/501.png"},{"code":"502","txt":"霾","txt_en":"Haze","icon":"http://files.heweather.com/cond_icon/502.png"},{"code":"503","txt":"扬沙","txt_en":"Sand","icon":"http://files.heweather.com/cond_icon/503.png"},{"code":"504","txt":"浮尘","txt_en":"Dust","icon":"http://files.heweather.com/cond_icon/504.png"},{"code":"506","txt":"火山灰","txt_en":"Volcanic Ash","icon":"http://files.heweather.com/cond_icon/506.png"},{"code":"507","txt":"沙尘暴","txt_en":"Duststorm","icon":"http://files.heweather.com/cond_icon/507.png"},{"code":"508","txt":"强沙尘暴","txt_en":"Sandstorm","icon":"http://files.heweather.com/cond_icon/508.png"},{"code":"900","txt":"热","txt_en":"Hot","icon":"http://files.heweather.com/cond_icon/900.png"},{"code":"901","txt":"冷","txt_en":"Cold","icon":"http://files.heweather.com/cond_icon/901.png"},{"code":"999","txt":"未知","txt_en":"Unknown","icon":"http://files.heweather.com/cond_icon/999.png"}]
     */

    @SerializedName("status")
    private String status;
    /**
     * code : 100
     * txt : 晴
     * txt_en : Sunny/Clear
     * icon : http://files.heweather.com/cond_icon/100.png
     */

    @SerializedName("cond_info")
    private List<CondInfoEntity> condInfo;

    public static WeatherTypeResponse objectFromData(String str) {

        return new Gson().fromJson(str, WeatherTypeResponse.class);
    }

    public static List<WeatherTypeResponse> arrayWeatherTypeResponseFromData(String str) {

        Type listType = new TypeToken<ArrayList<WeatherTypeResponse>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CondInfoEntity> getCondInfo() {
        return condInfo;
    }

    public void setCondInfo(List<CondInfoEntity> condInfo) {
        this.condInfo = condInfo;
    }

    public static class CondInfoEntity {
        @SerializedName("code")
        private String code;
        @SerializedName("txt")
        private String txt;
        @SerializedName("txt_en")
        private String txtEn;
        @SerializedName("icon")
        private String icon;

        public static CondInfoEntity objectFromData(String str) {

            return new Gson().fromJson(str, CondInfoEntity.class);
        }

        public static List<CondInfoEntity> arrayCondInfoEntityFromData(String str) {

            Type listType = new TypeToken<ArrayList<CondInfoEntity>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public String getTxtEn() {
            return txtEn;
        }

        public void setTxtEn(String txtEn) {
            this.txtEn = txtEn;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}

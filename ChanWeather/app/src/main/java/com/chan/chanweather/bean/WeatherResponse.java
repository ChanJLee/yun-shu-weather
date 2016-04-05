package com.chan.chanweather.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chan on 16/3/25.
 */
public class WeatherResponse {

    /**
     * aqi : {"city":{"aqi":"30","co":"0","no2":"12","o3":"93","pm10":"17","pm25":"12","qlty":"优","so2":"3"}}
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","update":{"loc":"2016-03-25 13:52","utc":"2016-03-25 05:52"}}
     * daily_forecast : [{"astro":{"sr":"06:09","ss":"18:31"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-03-25","hum":"14","pcpn":"0.0","pop":"0","pres":"1028","tmp":{"max":"15","min":"3"},"vis":"10","wind":{"deg":"10","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"06:07","ss":"18:32"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-03-26","hum":"11","pcpn":"0.0","pop":"0","pres":"1025","tmp":{"max":"18","min":"4"},"vis":"10","wind":{"deg":"336","dir":"无持续风向","sc":"微风","spd":"9"}},{"astro":{"sr":"06:05","ss":"18:33"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-03-27","hum":"9","pcpn":"0.0","pop":"0","pres":"1018","tmp":{"max":"20","min":"6"},"vis":"10","wind":{"deg":"217","dir":"无持续风向","sc":"微风","spd":"9"}},{"astro":{"sr":"06:04","ss":"18:34"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2016-03-28","hum":"11","pcpn":"0.0","pop":"0","pres":"1014","tmp":{"max":"20","min":"8"},"vis":"10","wind":{"deg":"346","dir":"无持续风向","sc":"微风","spd":"2"}},{"astro":{"sr":"06:02","ss":"18:35"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-03-29","hum":"12","pcpn":"0.0","pop":"0","pres":"1019","tmp":{"max":"21","min":"7"},"vis":"10","wind":{"deg":"311","dir":"无持续风向","sc":"微风","spd":"1"}},{"astro":{"sr":"06:00","ss":"18:36"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-03-30","hum":"13","pcpn":"0.0","pop":"0","pres":"1014","tmp":{"max":"20","min":"6"},"vis":"10","wind":{"deg":"201","dir":"无持续风向","sc":"微风","spd":"4"}},{"astro":{"sr":"05:59","ss":"18:37"},"cond":{"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"},"date":"2016-03-31","hum":"30","pcpn":"0.0","pop":"41","pres":"1004","tmp":{"max":"21","min":"10"},"vis":"10","wind":{"deg":"205","dir":"无持续风向","sc":"微风","spd":"6"}}]
     * hourly_forecast : [{"date":"2016-03-25 13:00","hum":"15","pop":"0","pres":"1029","tmp":"15","wind":{"deg":"10","dir":"北风","sc":"微风","spd":"13"}},{"date":"2016-03-25 16:00","hum":"13","pop":"0","pres":"1028","tmp":"16","wind":{"deg":"7","dir":"北风","sc":"微风","spd":"9"}},{"date":"2016-03-25 19:00","hum":"18","pop":"0","pres":"1028","tmp":"13","wind":{"deg":"54","dir":"东北风","sc":"微风","spd":"4"}},{"date":"2016-03-25 22:00","hum":"22","pop":"0","pres":"1029","tmp":"10","wind":{"deg":"234","dir":"西南风","sc":"微风","spd":"10"}}]
     * now : {"cond":{"code":"100","txt":"晴"},"fl":"14","hum":"14","pcpn":"0","pres":"1029","tmp":"13","vis":"10","wind":{"deg":"11","dir":"西北风","sc":"4-5","spd":"22"}}
     * status : ok
     * suggestion : {"comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"flu":{"brf":"较易发","txt":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"},"sport":{"brf":"较不宜","txt":"天气较好，但考虑天气寒冷，推荐您进行各种室内运动，若在户外运动请注意保暖并做好准备活动。"},"trav":{"brf":"适宜","txt":"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。"},"uv":{"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}}
     */

    @SerializedName("decor")
    private List<DecorEntity> decor;

    public static WeatherResponse objectFromData(String str) {

        return new Gson().fromJson(str, WeatherResponse.class);
    }

    public static List<WeatherResponse> arrayWeatherResponseFromData(String str) {

        Type listType = new TypeToken<ArrayList<WeatherResponse>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public List<DecorEntity> getDecor() {
        return decor;
    }

    public void setDecor(List<DecorEntity> decor) {
        this.decor = decor;
    }

    public static class DecorEntity {
        /**
         * city : {"aqi":"30","co":"0","no2":"12","o3":"93","pm10":"17","pm25":"12","qlty":"优","so2":"3"}
         */

        @SerializedName("aqi")
        private AqiEntity aqi;
        /**
         * city : 北京
         * cnty : 中国
         * id : CN101010100
         * lat : 39.904000
         * lon : 116.391000
         * update : {"loc":"2016-03-25 13:52","utc":"2016-03-25 05:52"}
         */

        @SerializedName("basic")
        private BasicEntity basic;
        /**
         * cond : {"code":"100","txt":"晴"}
         * fl : 14
         * hum : 14
         * pcpn : 0
         * pres : 1029
         * tmp : 13
         * vis : 10
         * wind : {"deg":"11","dir":"西北风","sc":"4-5","spd":"22"}
         */

        @SerializedName("now")
        private NowEntity now;
        @SerializedName("status")
        private String status;
        /**
         * comf : {"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
         * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
         * drsg : {"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}
         * flu : {"brf":"较易发","txt":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"}
         * sport : {"brf":"较不宜","txt":"天气较好，但考虑天气寒冷，推荐您进行各种室内运动，若在户外运动请注意保暖并做好准备活动。"}
         * trav : {"brf":"适宜","txt":"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。"}
         * uv : {"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}
         */

        @SerializedName("suggestion")
        private SuggestionEntity suggestion;
        /**
         * astro : {"sr":"06:09","ss":"18:31"}
         * cond : {"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"}
         * date : 2016-03-25
         * hum : 14
         * pcpn : 0.0
         * pop : 0
         * pres : 1028
         * tmp : {"max":"15","min":"3"}
         * vis : 10
         * wind : {"deg":"10","dir":"无持续风向","sc":"微风","spd":"7"}
         */

        @SerializedName("daily_forecast")
        private List<DailyForecastEntity> dailyForecast;
        /**
         * date : 2016-03-25 13:00
         * hum : 15
         * pop : 0
         * pres : 1029
         * tmp : 15
         * wind : {"deg":"10","dir":"北风","sc":"微风","spd":"13"}
         */

        @SerializedName("hourly_forecast")
        private List<HourlyForecastEntity> hourlyForecast;

        public static DecorEntity objectFromData(String str) {

            return new Gson().fromJson(str, DecorEntity.class);
        }

        public static List<DecorEntity> arrayDecorEntityFromData(String str) {

            Type listType = new TypeToken<ArrayList<DecorEntity>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public AqiEntity getAqi() {
            return aqi;
        }

        public void setAqi(AqiEntity aqi) {
            this.aqi = aqi;
        }

        public BasicEntity getBasic() {
            return basic;
        }

        public void setBasic(BasicEntity basic) {
            this.basic = basic;
        }

        public NowEntity getNow() {
            return now;
        }

        public void setNow(NowEntity now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public SuggestionEntity getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionEntity suggestion) {
            this.suggestion = suggestion;
        }

        public List<DailyForecastEntity> getDailyForecast() {
            return dailyForecast;
        }

        public void setDailyForecast(List<DailyForecastEntity> dailyForecast) {
            this.dailyForecast = dailyForecast;
        }

        public List<HourlyForecastEntity> getHourlyForecast() {
            return hourlyForecast;
        }

        public void setHourlyForecast(List<HourlyForecastEntity> hourlyForecast) {
            this.hourlyForecast = hourlyForecast;
        }

        public static class AqiEntity {
            /**
             * aqi : 30
             * co : 0
             * no2 : 12
             * o3 : 93
             * pm10 : 17
             * pm25 : 12
             * qlty : 优
             * so2 : 3
             */

            @SerializedName("city")
            private CityEntity city;

            public static AqiEntity objectFromData(String str) {

                return new Gson().fromJson(str, AqiEntity.class);
            }

            public static List<AqiEntity> arrayAqiEntityFromData(String str) {

                Type listType = new TypeToken<ArrayList<AqiEntity>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public CityEntity getCity() {
                return city;
            }

            public void setCity(CityEntity city) {
                this.city = city;
            }

            public static class CityEntity {
                @SerializedName("aqi")
                private String aqi;
                @SerializedName("co")
                private String co;
                @SerializedName("no2")
                private String no2;
                @SerializedName("o3")
                private String o3;
                @SerializedName("pm10")
                private String pm10;
                @SerializedName("pm25")
                private String pm25;
                @SerializedName("qlty")
                private String qlty;
                @SerializedName("so2")
                private String so2;

                public static CityEntity objectFromData(String str) {

                    return new Gson().fromJson(str, CityEntity.class);
                }

                public static List<CityEntity> arrayCityEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<CityEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }
            }
        }

        public static class BasicEntity {
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
            /**
             * loc : 2016-03-25 13:52
             * utc : 2016-03-25 05:52
             */

            @SerializedName("update")
            private UpdateEntity update;

            public static BasicEntity objectFromData(String str) {

                return new Gson().fromJson(str, BasicEntity.class);
            }

            public static List<BasicEntity> arrayBasicEntityFromData(String str) {

                Type listType = new TypeToken<ArrayList<BasicEntity>>() {
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

            public UpdateEntity getUpdate() {
                return update;
            }

            public void setUpdate(UpdateEntity update) {
                this.update = update;
            }

            public static class UpdateEntity {
                @SerializedName("loc")
                private String loc;
                @SerializedName("utc")
                private String utc;

                public static UpdateEntity objectFromData(String str) {

                    return new Gson().fromJson(str, UpdateEntity.class);
                }

                public static List<UpdateEntity> arrayUpdateEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<UpdateEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class NowEntity {
            /**
             * code : 100
             * txt : 晴
             */

            @SerializedName("cond")
            private CondEntity cond;
            @SerializedName("fl")
            private String fl;
            @SerializedName("hum")
            private String hum;
            @SerializedName("pcpn")
            private String pcpn;
            @SerializedName("pres")
            private String pres;
            @SerializedName("tmp")
            private String tmp;
            @SerializedName("vis")
            private String vis;
            /**
             * deg : 11
             * dir : 西北风
             * sc : 4-5
             * spd : 22
             */

            @SerializedName("wind")
            private WindEntity wind;

            public static NowEntity objectFromData(String str) {

                return new Gson().fromJson(str, NowEntity.class);
            }

            public static List<NowEntity> arrayNowEntityFromData(String str) {

                Type listType = new TypeToken<ArrayList<NowEntity>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public CondEntity getCond() {
                return cond;
            }

            public void setCond(CondEntity cond) {
                this.cond = cond;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindEntity getWind() {
                return wind;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public static class CondEntity {
                @SerializedName("code")
                private String code;
                @SerializedName("txt")
                private String txt;

                public static CondEntity objectFromData(String str) {

                    return new Gson().fromJson(str, CondEntity.class);
                }

                public static List<CondEntity> arrayCondEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<CondEntity>>() {
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
            }

            public static class WindEntity {
                @SerializedName("deg")
                private String deg;
                @SerializedName("dir")
                private String dir;
                @SerializedName("sc")
                private String sc;
                @SerializedName("spd")
                private String spd;

                public static WindEntity objectFromData(String str) {

                    return new Gson().fromJson(str, WindEntity.class);
                }

                public static List<WindEntity> arrayWindEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<WindEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class SuggestionEntity {
            /**
             * brf : 舒适
             * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
             */

            @SerializedName("comf")
            private ComfEntity comf;
            /**
             * brf : 较适宜
             * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
             */

            @SerializedName("cw")
            private CwEntity cw;
            /**
             * brf : 较冷
             * txt : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
             */

            @SerializedName("drsg")
            private DrsgEntity drsg;
            /**
             * brf : 较易发
             * txt : 昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。
             */

            @SerializedName("flu")
            private FluEntity flu;
            /**
             * brf : 较不宜
             * txt : 天气较好，但考虑天气寒冷，推荐您进行各种室内运动，若在户外运动请注意保暖并做好准备活动。
             */

            @SerializedName("sport")
            private SportEntity sport;
            /**
             * brf : 适宜
             * txt : 天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。
             */

            @SerializedName("trav")
            private TravEntity trav;
            /**
             * brf : 中等
             * txt : 属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。
             */

            @SerializedName("uv")
            private UvEntity uv;

            public static SuggestionEntity objectFromData(String str) {

                return new Gson().fromJson(str, SuggestionEntity.class);
            }

            public static List<SuggestionEntity> arraySuggestionEntityFromData(String str) {

                Type listType = new TypeToken<ArrayList<SuggestionEntity>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public ComfEntity getComf() {
                return comf;
            }

            public void setComf(ComfEntity comf) {
                this.comf = comf;
            }

            public CwEntity getCw() {
                return cw;
            }

            public void setCw(CwEntity cw) {
                this.cw = cw;
            }

            public DrsgEntity getDrsg() {
                return drsg;
            }

            public void setDrsg(DrsgEntity drsg) {
                this.drsg = drsg;
            }

            public FluEntity getFlu() {
                return flu;
            }

            public void setFlu(FluEntity flu) {
                this.flu = flu;
            }

            public SportEntity getSport() {
                return sport;
            }

            public void setSport(SportEntity sport) {
                this.sport = sport;
            }

            public TravEntity getTrav() {
                return trav;
            }

            public void setTrav(TravEntity trav) {
                this.trav = trav;
            }

            public UvEntity getUv() {
                return uv;
            }

            public void setUv(UvEntity uv) {
                this.uv = uv;
            }

            public static class ComfEntity {
                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public static ComfEntity objectFromData(String str) {

                    return new Gson().fromJson(str, ComfEntity.class);
                }

                public static List<ComfEntity> arrayComfEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<ComfEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class CwEntity {
                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public static CwEntity objectFromData(String str) {

                    return new Gson().fromJson(str, CwEntity.class);
                }

                public static List<CwEntity> arrayCwEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<CwEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class DrsgEntity {
                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public static DrsgEntity objectFromData(String str) {

                    return new Gson().fromJson(str, DrsgEntity.class);
                }

                public static List<DrsgEntity> arrayDrsgEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<DrsgEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class FluEntity {
                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public static FluEntity objectFromData(String str) {

                    return new Gson().fromJson(str, FluEntity.class);
                }

                public static List<FluEntity> arrayFluEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<FluEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class SportEntity {
                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public static SportEntity objectFromData(String str) {

                    return new Gson().fromJson(str, SportEntity.class);
                }

                public static List<SportEntity> arraySportEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<SportEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class TravEntity {
                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public static TravEntity objectFromData(String str) {

                    return new Gson().fromJson(str, TravEntity.class);
                }

                public static List<TravEntity> arrayTravEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<TravEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class UvEntity {
                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public static UvEntity objectFromData(String str) {

                    return new Gson().fromJson(str, UvEntity.class);
                }

                public static List<UvEntity> arrayUvEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<UvEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }

        public static class DailyForecastEntity {
            /**
             * sr : 06:09
             * ss : 18:31
             */

            @SerializedName("astro")
            private AstroEntity astro;
            /**
             * code_d : 100
             * code_n : 100
             * txt_d : 晴
             * txt_n : 晴
             */

            @SerializedName("cond")
            private CondEntity cond;
            @SerializedName("date")
            private String date;
            @SerializedName("hum")
            private String hum;
            @SerializedName("pcpn")
            private String pcpn;
            @SerializedName("pop")
            private String pop;
            @SerializedName("pres")
            private String pres;
            /**
             * max : 15
             * min : 3
             */

            @SerializedName("tmp")
            private TmpEntity tmp;
            @SerializedName("vis")
            private String vis;
            /**
             * deg : 10
             * dir : 无持续风向
             * sc : 微风
             * spd : 7
             */

            @SerializedName("wind")
            private WindEntity wind;

            public static DailyForecastEntity objectFromData(String str) {

                return new Gson().fromJson(str, DailyForecastEntity.class);
            }

            public static List<DailyForecastEntity> arrayDailyForecastEntityFromData(String str) {

                Type listType = new TypeToken<ArrayList<DailyForecastEntity>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public AstroEntity getAstro() {
                return astro;
            }

            public void setAstro(AstroEntity astro) {
                this.astro = astro;
            }

            public CondEntity getCond() {
                return cond;
            }

            public void setCond(CondEntity cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpEntity getTmp() {
                return tmp;
            }

            public void setTmp(TmpEntity tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindEntity getWind() {
                return wind;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public static class AstroEntity {
                @SerializedName("sr")
                private String sr;
                @SerializedName("ss")
                private String ss;

                public static AstroEntity objectFromData(String str) {

                    return new Gson().fromJson(str, AstroEntity.class);
                }

                public static List<AstroEntity> arrayAstroEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<AstroEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondEntity {
                @SerializedName("code_d")
                private String codeD;
                @SerializedName("code_n")
                private String codeN;
                @SerializedName("txt_d")
                private String txtD;
                @SerializedName("txt_n")
                private String txtN;

                public static CondEntity objectFromData(String str) {

                    return new Gson().fromJson(str, CondEntity.class);
                }

                public static List<CondEntity> arrayCondEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<CondEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getCodeD() {
                    return codeD;
                }

                public void setCodeD(String codeD) {
                    this.codeD = codeD;
                }

                public String getCodeN() {
                    return codeN;
                }

                public void setCodeN(String codeN) {
                    this.codeN = codeN;
                }

                public String getTxtD() {
                    return txtD;
                }

                public void setTxtD(String txtD) {
                    this.txtD = txtD;
                }

                public String getTxtN() {
                    return txtN;
                }

                public void setTxtN(String txtN) {
                    this.txtN = txtN;
                }
            }

            public static class TmpEntity {
                @SerializedName("max")
                private String max;
                @SerializedName("min")
                private String min;

                public static TmpEntity objectFromData(String str) {

                    return new Gson().fromJson(str, TmpEntity.class);
                }

                public static List<TmpEntity> arrayTmpEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<TmpEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindEntity {
                @SerializedName("deg")
                private String deg;
                @SerializedName("dir")
                private String dir;
                @SerializedName("sc")
                private String sc;
                @SerializedName("spd")
                private String spd;

                public static WindEntity objectFromData(String str) {

                    return new Gson().fromJson(str, WindEntity.class);
                }

                public static List<WindEntity> arrayWindEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<WindEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class HourlyForecastEntity {
            @SerializedName("date")
            private String date;
            @SerializedName("hum")
            private String hum;
            @SerializedName("pop")
            private String pop;
            @SerializedName("pres")
            private String pres;
            @SerializedName("tmp")
            private String tmp;
            /**
             * deg : 10
             * dir : 北风
             * sc : 微风
             * spd : 13
             */

            @SerializedName("wind")
            private WindEntity wind;

            public static HourlyForecastEntity objectFromData(String str) {

                return new Gson().fromJson(str, HourlyForecastEntity.class);
            }

            public static List<HourlyForecastEntity> arrayHourlyForecastEntityFromData(String str) {

                Type listType = new TypeToken<ArrayList<HourlyForecastEntity>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public WindEntity getWind() {
                return wind;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public static class WindEntity {
                @SerializedName("deg")
                private String deg;
                @SerializedName("dir")
                private String dir;
                @SerializedName("sc")
                private String sc;
                @SerializedName("spd")
                private String spd;

                public static WindEntity objectFromData(String str) {

                    return new Gson().fromJson(str, WindEntity.class);
                }

                public static List<WindEntity> arrayWindEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<WindEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}

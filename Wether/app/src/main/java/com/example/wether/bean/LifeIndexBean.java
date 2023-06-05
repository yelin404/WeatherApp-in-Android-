package com.example.wether.bean;

public class LifeIndexBean {


    /**
     * reason : 查询成功!
     * result : {"city":"深圳","life":{"kongtiao":{"v":"较少开启","des":"您将感到很舒适，一般不需要开启空调。"},"guomin":{"v":"易发","des":"天气条件易诱发过敏，有降水，易过敏人群应减少外出，如需外出最好穿长衣长裤，预防感冒可能引发的过敏。"},"shushidu":{"v":"舒适","des":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"chuanyi":{"v":"较舒适","des":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"},"diaoyu":{"v":"不宜","des":"天气不好，不适合垂钓。"},"ganmao":{"v":"较易发","des":"天气转凉，空气湿度较大，较易发生感冒，体质较弱的朋友请注意适当防护。"},"ziwaixian":{"v":"弱","des":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"},"xiche":{"v":"不宜","des":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"yundong":{"v":"较不宜","des":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"},"daisan":{"v":"带伞","des":"有降水，如果您要短时间外出的话可不必带雨伞。"}}}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * city : 深圳
         * life : {"kongtiao":{"v":"较少开启","des":"您将感到很舒适，一般不需要开启空调。"},"guomin":{"v":"易发","des":"天气条件易诱发过敏，有降水，易过敏人群应减少外出，如需外出最好穿长衣长裤，预防感冒可能引发的过敏。"},"shushidu":{"v":"舒适","des":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"chuanyi":{"v":"较舒适","des":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"},"diaoyu":{"v":"不宜","des":"天气不好，不适合垂钓。"},"ganmao":{"v":"较易发","des":"天气转凉，空气湿度较大，较易发生感冒，体质较弱的朋友请注意适当防护。"},"ziwaixian":{"v":"弱","des":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"},"xiche":{"v":"不宜","des":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"yundong":{"v":"较不宜","des":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"},"daisan":{"v":"带伞","des":"有降水，如果您要短时间外出的话可不必带雨伞。"}}
         */

        private String city;
        private LifeBean life;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public LifeBean getLife() {
            return life;
        }

        public void setLife(LifeBean life) {
            this.life = life;
        }

        public static class LifeBean {
            /**
             * kongtiao : {"v":"较少开启","des":"您将感到很舒适，一般不需要开启空调。"}
             * guomin : {"v":"易发","des":"天气条件易诱发过敏，有降水，易过敏人群应减少外出，如需外出最好穿长衣长裤，预防感冒可能引发的过敏。"}
             * shushidu : {"v":"舒适","des":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
             * chuanyi : {"v":"较舒适","des":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"}
             * diaoyu : {"v":"不宜","des":"天气不好，不适合垂钓。"}
             * ganmao : {"v":"较易发","des":"天气转凉，空气湿度较大，较易发生感冒，体质较弱的朋友请注意适当防护。"}
             * ziwaixian : {"v":"弱","des":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
             * xiche : {"v":"不宜","des":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}
             * yundong : {"v":"较不宜","des":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"}
             * daisan : {"v":"带伞","des":"有降水，如果您要短时间外出的话可不必带雨伞。"}
             */

            private KongtiaoBean kongtiao;
            private GuominBean guomin;
            private ShushiduBean shushidu;
            private ChuanyiBean chuanyi;
            private DiaoyuBean diaoyu;
            private GanmaoBean ganmao;
            private ZiwaixianBean ziwaixian;
            private XicheBean xiche;
            private YundongBean yundong;
            private DaisanBean daisan;

            public KongtiaoBean getKongtiao() {
                return kongtiao;
            }

            public void setKongtiao(KongtiaoBean kongtiao) {
                this.kongtiao = kongtiao;
            }

            public GuominBean getGuomin() {
                return guomin;
            }

            public void setGuomin(GuominBean guomin) {
                this.guomin = guomin;
            }

            public ShushiduBean getShushidu() {
                return shushidu;
            }

            public void setShushidu(ShushiduBean shushidu) {
                this.shushidu = shushidu;
            }

            public ChuanyiBean getChuanyi() {
                return chuanyi;
            }

            public void setChuanyi(ChuanyiBean chuanyi) {
                this.chuanyi = chuanyi;
            }

            public DiaoyuBean getDiaoyu() {
                return diaoyu;
            }

            public void setDiaoyu(DiaoyuBean diaoyu) {
                this.diaoyu = diaoyu;
            }

            public GanmaoBean getGanmao() {
                return ganmao;
            }

            public void setGanmao(GanmaoBean ganmao) {
                this.ganmao = ganmao;
            }

            public ZiwaixianBean getZiwaixian() {
                return ziwaixian;
            }

            public void setZiwaixian(ZiwaixianBean ziwaixian) {
                this.ziwaixian = ziwaixian;
            }

            public XicheBean getXiche() {
                return xiche;
            }

            public void setXiche(XicheBean xiche) {
                this.xiche = xiche;
            }

            public YundongBean getYundong() {
                return yundong;
            }

            public void setYundong(YundongBean yundong) {
                this.yundong = yundong;
            }

            public DaisanBean getDaisan() {
                return daisan;
            }

            public void setDaisan(DaisanBean daisan) {
                this.daisan = daisan;
            }

            public static class KongtiaoBean {
                /**
                 * v : 较少开启
                 * des : 您将感到很舒适，一般不需要开启空调。
                 */

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class GuominBean {
                /**
                 * v : 易发
                 * des : 天气条件易诱发过敏，有降水，易过敏人群应减少外出，如需外出最好穿长衣长裤，预防感冒可能引发的过敏。
                 */

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class ShushiduBean {
                /**
                 * v : 舒适
                 * des : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
                 */

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class ChuanyiBean {
                /**
                 * v : 较舒适
                 * des : 建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。
                 */

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class DiaoyuBean {
                /**
                 * v : 不宜
                 * des : 天气不好，不适合垂钓。
                 */

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class GanmaoBean {
                /**
                 * v : 较易发
                 * des : 天气转凉，空气湿度较大，较易发生感冒，体质较弱的朋友请注意适当防护。
                 */

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class ZiwaixianBean {
                /**
                 * v : 弱
                 * des : 紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。
                 */

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class XicheBean {
                /**
                 * v : 不宜
                 * des : 不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。
                 */

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class YundongBean {
                /**
                 * v : 较不宜
                 * des : 有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。
                 */

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class DaisanBean {
                /**
                 * v : 带伞
                 * des : 有降水，如果您要短时间外出的话可不必带雨伞。
                 */

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }
        }
    }
}

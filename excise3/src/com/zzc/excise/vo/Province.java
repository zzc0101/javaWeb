package com.zzc.excise.vo;

/**
 * @ClassName: Province
 * @Author: zzc
 * @CreateTime: 2020/10/17 14:53
 * @Description: 将省份封装成一个对象
 */

public class Province {
    private int p_id;
    private String province;

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Province{" +
                "p_id=" + p_id +
                ", province='" + province + '\'' +
                '}';
    }
}

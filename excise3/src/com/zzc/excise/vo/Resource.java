package com.zzc.excise.vo;

/**
 * @ClassName: Resource
 * @Author: zzc
 * @CreateTime: 2020/10/14 13:42
 * @Description: 将权限资源封装成一个对象，主要包含资源名、资源序列和资源路径
 */

public class Resource {

    private int resourceId;
    private String resourceName;
    private String url;

    public Resource() {

    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", resourceName='" + resourceName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

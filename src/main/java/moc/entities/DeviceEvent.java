package moc.entities;

import lombok.Data;

/**
 * Created by Administrator on 2016/12/10.
 */
@Data
public class DeviceEvent {
    private String createTime="";
    private String deviceUuid="";
    private String propertyName="";
    private String propertyValue="";
}

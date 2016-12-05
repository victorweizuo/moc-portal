package moc.entities;

import lombok.Data;

/**
 * Created by Administrator on 2016/12/5.
 */
@Data
public class DeviceStatus {
    private String parameter;
    private String status;
    private String lastUpdateTime;
}

package moc.entities;

import lombok.Data;

/**
 * Created by Administrator on 2016/12/11.
 */
@Data
public class DeviceStatistic {
    private String parameter;
    private String total="0";
    private String perday;
}

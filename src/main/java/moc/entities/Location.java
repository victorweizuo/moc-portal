package moc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Administrator on 2016/11/8.
 */
@Data
@AllArgsConstructor
public class Location {
    public double lat;
    public double lon;
    public String devuuid;
}

package moc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Administrator on 2016/10/22.
 */
@Data
@AllArgsConstructor
public class Device {
    private String devicebtblemac="";
    private String devicebtsppmac="";
    private String devicesmsno="";
    private String deviceserialno="";
    private String devuuid="";
    private String inuse="";
    private String plate_vin="";
}

package moc.services;

import moc.entities.VehicleInformation;

import java.util.List;

/**
 * Created by Administrator on 2016/12/18.
 */
public interface VehicleService {
    List<VehicleInformation> getVehicleInformations(String devuuid,String token);

}

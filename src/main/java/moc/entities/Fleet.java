package moc.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/22.
 */
@Data
public class Fleet {
    private List<Device> fleet=new ArrayList<>();
}

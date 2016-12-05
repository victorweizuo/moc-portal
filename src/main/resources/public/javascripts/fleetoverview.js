Ext.Loader.setPath('Ext.ux', 'javascripts/');
Ext.require(['Ext.window.*', 'Ext.ux.GMapPanel']);

Ext.define('FleetOverview', {
    extend: 'Ext.panel.Panel',
    width: '100%',
    height: '100%',
    layout: 'border',
    id: 'fleetoverview_panel',
    items: [{
        xtype: 'panel',
        region: 'west',
        id: 'mappanel1',
        width: '70%',
        layout: 'fit',
        items: {
            xtype: 'gmappanel',
            id: 'fleetoverview_gmap',
            zoomLevel: 14,
            gmapType: 'map',
            mapConfOpts: ['enableScrollWheelZoom', 'enableDoubleClickZoom', 'enableDragging'],
            mapControls: ['GSmallMapControl', 'GMapTypeControl'],
            setCenter: {
                lat: 34.19051,
                lng: 108.9583
            },
            markers: [
            ]
        }
    }, {
        xtype: 'panel',
        region: 'east',
        width: '30%',
        items: [{
            xtype: 'combobox',
            id: 'fleetoverview_combo',
            fieldLabel: 'Device List',
            editable: false,
            forceSelection: true,
            displayField: 'devuuid',
            queryMode: 'local',
            valueField: 'devuuid',
            store: Ext.create('Ext.data.JsonStore', {
                autoLoad: false,
                fields: ['devuuid'],
                data: []
            }),
            listeners: {
                select: function (combo, records, e) {
                    var devuuid = (records[0].get('devuuid'));
                    Ext.Ajax.request({
                        url: '/device/getcurlocation',
                        params: {
                            devuuid: devuuid
                        },
                        method: 'POST',
                        success: function (res) {
                            var data = JSON.parse(res.responseText);
                            var lat = data.lat;
                            var lon = data.lon;
                            var devuuid=data.devuuid;
                            console.info(data);
                            var point = new google.maps.LatLng(lat,lon);
                            var marker = {
                                title: devuuid,
                                listeners:{
                                    click: function(e){
                                        alert(devuuid);
                                    }
                                }
                            };
                            Ext.getCmp('fleetoverview_gmap').addMarker(point,marker,false,true,marker.listeners);
                        }
                    });
                }
            }

        }]

    }]

});
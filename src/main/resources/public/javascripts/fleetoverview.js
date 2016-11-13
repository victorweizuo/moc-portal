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
            id:'fleetoverview_gmap',
            center: {
                geoCodeAddr: '陕西省西安市雁塔区',
                marker: {
                    lat: 34.19051,
                    lng: 108.9583,
                    title: 'Xi an',
                    listeners: {
                        click: function (e) {
                            Ext.Msg.alert('It\'s fine',
                                'and it\'s art.');
                        }
                    }
                }
            },
            markers: [{
                lat: 34.19051,
                lng: 108.9583,
                title: 'Xi an',
                listeners: {
                    click: function (e) {
                        Ext.Msg.alert('It\'s fine',
                            'and it\'s art.');
                    }
                }
            }]
        }
    }, {
        xtype: 'panel',
        region: 'east',
        width: '30%',
        items: [{
            xtype: 'combobox',
            id:'fleetoverview_combo',
            fieldLabel: 'Device List',
            editable:false,
            forceSelection:true,
            displayField: 'devuuid',
            queryMode: 'local',
            valueField:'devuuid',
            store: Ext.create('Ext.data.JsonStore', {
                autoLoad:false,
                fields: ['devuuid'],
                data: []
            })

        }]

    }]

});
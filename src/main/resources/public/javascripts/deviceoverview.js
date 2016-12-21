Ext.define('DeviceOverview', {
    extend: 'Ext.panel.Panel',
    layout: {
        type: 'vbox',
        align: 'center'
    },
    autoScroll: true,
    items: [{
        xtype: 'panel',
        layout: {
            type: 'hbox', pack: 'center', align: 'middle'
        },
        width: "75%", margin: 30, height: 50,
        items: [{
            xtype: 'combobox', id: 'deviceoverview_combo', fieldLabel: 'Device List', displayField: 'device',
            queryMode: 'local', valueField: 'devuuid', editable: false, forceSelection: true, displayField: 'devuuid',
            store: Ext.create('Ext.data.Store', {
                autoLoad: false,
                fields: ['devuuid'],
                data: []
            }),
            listeners: {
                select: function (combo, records, e) {
                    var devuuid = (records[0].get('devuuid'));
                    Ext.Ajax.request({
                        url: '/device/retrieveone',
                        params: {
                            devuuid: devuuid
                        },
                        method: 'POST',
                        success: function (res) {
                            var data = JSON.parse(res.responseText);
                            var devicebtblemac = data.devicebtblemac
                            var devicebtsppmac = data.devicebtsppmac;
                            var devicesmsno = data.devicesmsno;
                            var deviceserialno = data.deviceserialno;
                            var inuse = data.inuse;
                            Ext.getCmp('deviceoverview_devicebtblemac').setValue(devicebtblemac);
                            Ext.getCmp('deviceoverview_devicebtsppmac').setValue(devicebtsppmac);
                            Ext.getCmp('deviceoverview_devicesmsno').setValue(devicesmsno);
                            Ext.getCmp('deviceoverview_devuuid').setValue(devuuid);
                            console.info(data);
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
                                    var devuuid = data.devuuid;
                                    console.info(data);
                                    var point = new google.maps.LatLng(lat, lon);
                                    var marker = {
                                        title: devuuid,
                                        listeners: {
                                            click: function (e) {
                                                alert(devuuid);
                                            }
                                        }
                                    };
                                    Ext.getCmp('deviceoverview_gmap').addMarker(point, marker, false, true, marker.listeners);
                                }
                            });


                            Ext.Ajax.request({
                                url: '/device/getdevicestatus',
                                params: {
                                    devuuid: devuuid
                                },
                                method: 'POST',
                                success: function (res) {
                                    var data = JSON.parse(res.responseText);
                                    Ext.getCmp('devicestatusgrid').getStore().loadData(data);
                                }
                            });

                            Ext.Ajax.request({
                                url: '/device/getdevicestatisticsgrid',
                                params: {
                                    devuuid: devuuid
                                },
                                method: 'POST',
                                success: function (res) {
                                    var data = JSON.parse(res.responseText);
                                    Ext.getCmp('devicestatisticsgrid').getStore().loadData(data);
                                }
                            });

                            Ext.Ajax.request({
                                url: '/vehicle/getvehicleinformations',
                                params: {
                                    devuuid: devuuid
                                },
                                method: 'POST',
                                success: function (res) {
                                    var data = JSON.parse(res.responseText);
                                    Ext.getCmp('vehicleinformationgrid').getStore().loadData(data);
                                }
                            });
                        }
                    });
                }
            }

        }, {
            xtype: 'button', margin: "0 0 0 20", text: 'Control and Configuration'
        }, {
            xtype: 'button', margin: "0 0 0 20", text: 'Add New Device'
        }]
    }, {
        xtype: 'panel', border: false,
        layout: {
            type: 'hbox', align: 'middle'
        },
        width: '75%', height: 180,
        items: [{
            xtype: 'form', width: 350, bodyPadding: 10, height: 180,
            items: [{
                xtype: 'textfield', anchor: '100%', value: 'device2 uuid', itemId: 'uuid', id: 'deviceoverview_devuuid',
                fieldLabel: 'UUID', labelAlign: 'right', labelWidth: 70, editable: true
            }, {
                xtype: 'textfield', anchor: '100%', value: 'device2 sms', itemId: 'sms',
                id: 'deviceoverview_devicesmsno', fieldLabel: 'SMS NO.', labelAlign: 'right',
                labelWidth: 70, editable: true
            }, {
                xtype: 'textfield', anchor: '100%', value: 'device2 ble', itemId: 'mac',
                id: 'deviceoverview_devicebtblemac', fieldLabel: 'BLE mac.', labelAlign: 'right',
                labelWidth: 70, editable: true
            }, {
                xtype: 'textfield', anchor: '100%', value: 'device2 spp', itemId: 'sppmac',
                id: 'deviceoverview_devicebtsppmac', fieldLabel: 'SPP mac.', labelAlign: 'right',
                labelWidth: 70, editable: true
            }, {
                xtype: 'textfield', anchor: '100%', value: '1.2.3', itemId: 'version',
                fieldLabel: 'Version.', labelAlign: 'right', labelWidth: 70, readOnly: true,
                disabled: true
            }, {
                xtype: 'textfield', anchor: '100%', value: '4th July 2015', itemId: 'update',
                fieldLabel: 'Updated on', labelAlign: 'right', labelWidth: 70, readOnly: true,
                disabled: true
            }]

        }, {
            xtype: 'panel', border: false, width: 350, height: 180,
            items: [{
                xtype: 'label', padding: '0 0 0 20', style: ' font-size: 32px ', text: 'Device', id: 'deviceName'
            }]
        }]
    }, {
        xtype: 'panel', border: true,
        layout: {
            type: 'hbox'
        },
        margin: '30 0 0 0', width: '75%', height: 1500,
        items: [{
            xtype: 'panel',
            layout: {
                type: 'vbox', align: 'center'
            },
            width: '50%',
            border: true,
            items: [{
                xtype: 'panel',
                layout: {
                    type: 'vbox', align: 'center'
                },
                width: 550,
                height: 220,
                items: [{
                    xtype: 'label', padding: '0 0 0 20', style: ' font-size: 22px ', text: 'Device'
                }, {
                    xtype: 'panel', width: 520, height: 180, layout: 'fit',
                    items: {
                        xtype: 'gmappanel',
                        id: 'deviceoverview_gmap',
                        zoomLevel: 14,
                        gmapType: 'map',
                        mapConfOpts: ['enableScrollWheelZoom', 'enableDoubleClickZoom', 'enableDragging'],
                        mapControls: ['GSmallMapControl', 'GMapTypeControl'],
                        setCenter: {
                            lat: 34.19051, lng: 108.9583
                        },
                        markers: []

                    }
                }]
            }, {
                xtype: 'panel',
                layout: {
                    type: 'vbox', align: 'center'
                },
                margin: '20 0 0 0',
                width: 550,
                height: 500,
                border: true,
                items: [{
                    xtype: 'label', padding: '0 0 0 20', style: ' font-size: 22px ', text: 'Event Stastics'
                }, {
                    xtype: 'chart', width: 550, id: 'eventstasticschart', height: 200,
                    style: 'background:#fff', animate: true, shadow: true,
                    store: Ext.create('Ext.data.Store', {
                        fields: ['devuuid', 'eventcount'], data: []
                    }),
                    axes: [{
                        type: 'Numeric', position: 'left', fields: ['eventcount'], grid: true, minimum: 0
                    }, {
                        type: 'Category', position: 'bottom', fields: ['devuuid']
                    }],
                    series: [{
                        type: 'column', axis: 'left', highlight: true, minimum: 0,
                        label: {
                            renderer: Ext.util.Format.numberRenderer('0,0')
                        },
                        xField: 'devuuid', yField: ['eventcount']
                    }]
                }, {
                    xtype: 'gridpanel',
                    id: 'devicestatisticsgrid',
                    store: Ext.create('Ext.data.Store', {
                        fields: ['parameter', 'total',
                            'perday'],
                        data: [],
                        proxy: {
                            type: 'memory',
                            reader: {
                                type: 'json', root: 'items'
                            }
                        }
                    }),
                    height: 200, title: 'Stastic Table', width: 550, margin: '20 0 0 0',
                    columns: [{text: 'Parameter', dataIndex: 'parameter'},
                        {text: 'Total', dataIndex: 'total'},
                        {text: 'Per Day', dataIndex: 'perday'}]
                }]
            }]
        }, {
            xtype: 'panel',
            layout: {
                type: 'vbox', align: 'center'
            },
            width: '50%',
            border: true,
            items: [{
                xtype: 'gridpanel',
                id: 'devicestatusgrid',
                store: Ext.create('Ext.data.Store', {
                    fields: ['parameter', 'status', 'lastupdatetime'],
                    data: [],
                    proxy: {
                        type: 'memory',
                        reader: {
                            type: 'json', root: 'items'
                        }
                    }
                }),
                height: 300, title: 'Device Status', width: 400, margin: '20 0 0 0',
                columns: [{
                    text: 'Parameter', dataIndex: 'parameter', flex: 2
                }, {
                    text: 'Status', dataIndex: 'status', flex: 2
                }, {
                    text: 'Last Update Time', dataIndex: 'lastupdatetime', flex: 1.5
                }]
            }, {
                xtype: 'gridpanel',
                id: 'vehicleinformationgrid',
                store: Ext.create('Ext.data.Store', {
                    fields: ['parameter', 'status',
                        'lastupdatetime'],
                    data: [],
                    proxy: {
                        type: 'memory',
                        reader: {
                            type: 'json', root: 'items'
                        }
                    }
                }),
                height: 200, title: 'Vehicle Information', width: 400, margin: '20 0 0 0',
                columns: [{
                    text: 'Parameter', dataIndex: 'parameter', flex: 2
                }, {
                    text: 'Status', dataIndex: 'status', flex: 2
                }, {
                    text: 'Last Update Time', dataIndex: 'lastupdatetime', flex: 1.5
                }]
            }, {
                xtype: 'gridpanel',
                store: Ext.create('Ext.data.Store', {
                    fields: ['parameter', 'status', 'lastchange',
                        'time'],
                    data: {},
                    proxy: {
                        type: 'memory',
                        reader: {
                            type: 'json', root: 'items'
                        }
                    }
                }),
                height: 300, title: 'Hardware Status and Setting', width: 400, margin: '20 0 0 0',
                columns: [{text: 'Parameter', dataIndex: 'parameter', flex: 2},
                    {text: 'Status', dataIndex: 'status', flex: 2},
                    {text: 'Last Change', dataIndex: 'lastchange', flex: 1.5},
                    {text: 'Time', dataIndex: 'time', flex: 1.5}]
            }]
        }]
    }]
});
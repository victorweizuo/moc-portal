Ext.define('ControlAndConfiguration', {
    extend: 'Ext.panel.Panel',
    layout: {
        type: 'vbox', align: 'center'
    },
    autoScroll: true,
    items: [{
        xtype: 'panel',
        layout: {
            type: 'hbox', pack: 'center', align: 'middle'
        },
        width: "90%", margin: 30, height: 50,
        items: [{
            xtype: 'combobox', id: 'controlandconfigurationcombo', fieldLabel: 'Device List',
            queryMode: 'local', valueField: 'devuuid', editable: false, forceSelection: true, displayField: 'devuuid',
            store: Ext.create('Ext.data.Store', {
                fields: ['devuuid'], autoLoad: false, data: []
            }),
            listeners:{
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
                            Ext.getCmp('controlandconfiguration_blemac').setValue(devicebtblemac);
                            Ext.getCmp('controlandconfiguration_sppmac').setValue(devicebtsppmac);
                            Ext.getCmp('controlandconfiguration_smsno').setValue(devicesmsno);
                            Ext.getCmp('controlandconfiguration_uuid').setValue(devuuid);
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
                            Ext.getCmp('devicecontrol_grid').getStore().loadData(data);
                        }
                    });

                }
            }
        }, {
            xtype: 'button', margin: "0 0 0 20", text: 'Add New Device'
        }]
    }, {
        xtype: 'panel', border: false, layout: {
            type: 'hbox', align: 'middle'
        },
        width: '90%', height: 180,
        items: [{
            xtype: 'form', width: 450, bodyPadding: 10, height: 180,
            items: [{
                xtype: 'textfield', anchor: '100%', value: 'device2 uuid', itemId: 'uuid',
                id:'controlandconfiguration_uuid',disabled:true,
                fieldLabel: 'UUID', labelAlign: 'right', labelWidth: 70, editable: false
            }, {
                xtype: 'textfield', anchor: '100%', value: 'device2 sms', itemId: 'sms',
                id:'controlandconfiguration_smsno',
                fieldLabel: 'SMS NO.', labelAlign: 'right', labelWidth: 70, editable: true
            }, {
                xtype: 'textfield', anchor: '100%', value: 'device2 ble', itemId: 'mac',
                id:'controlandconfiguration_blemac',
                fieldLabel: 'BLE mac.', labelAlign: 'right', labelWidth: 70, editable: true
            }, {
                xtype: 'textfield', anchor: '100%', value: 'device2 spp', itemId: 'sppmac',
                id:'controlandconfiguration_sppmac',
                fieldLabel: 'SPP mac.', labelAlign: 'right', labelWidth: 70, editable: true
            }, {
                xtype: 'button', anchor: '100%', text: 'Save',
                handler:function(){
                    Ext.Ajax.request({
                        url: '/device/savedevice',
                        params: {
                            devuuid: Ext.getCmp('controlandconfiguration_uuid').getValue(),
                            smsno:Ext.getCmp('controlandconfiguration_smsno').getValue(),
                            sppmac:Ext.getCmp('controlandconfiguration_sppmac').getValue(),
                            blemac:Ext.getCmp('controlandconfiguration_blemac').getValue()
                        },
                        method: 'POST',
                        success: function (res) {
                            alert(res.responseText);
                        }
                    });
                }
            }]

        }, {
            xtype: 'panel', border: false, width: 450, height: 180,
            items: [{
                xtype: 'label', padding: '0 0 0 20', style: ' font-size: 32px ', text: 'Device', id: 'deviceName'
            }]
        }]
    }, {
        xtype: 'panel', border: true,
        layout: {
            type: 'hbox'
        },
        margin: '30 0 0 0', width: '90%', height: 1500,
        items: [{
            xtype: 'gridpanel', height: 400, title: 'Device Control', width: 450, margin: '20 0 0 0',
            id:'devicecontrol_grid',
            columns: [{
                text: 'Parameter', dataIndex: 'parameter', flex: 1
            }, {
                text: 'Status', dataIndex: 'status', flex: 1
            }, {
                text: 'Update Time', dataIndex: 'updatetime', flex: 1
            }, {
                header: 'Action', flex: 2, sortable: false,
                renderer: function (val) {
                    var actions = val.split(',');
                    var result = '';
                    for (var i = 0; i < actions.length; i++) {
                        result = result + '<input type="button" value="' + actions[i] + '" id="'
                            + actions[i] + '"/>' + '<br/>';
                    }
                    return result;
                },
                value:'Action'
            }],
            store: Ext.create('Ext.data.Store', {
                fields: ['parameter', 'status', 'updatetime','propertyName'],
                storeId: 'simpsonsStore',
                data: []

            })
        },
            {
                xtype: 'gridpanel', height: 500, title: 'Device Configuration', width: 450,
                margin: '20 0 0 20',
                columns: [{
                    text: 'Parameter', dataIndex: 'parameter', flex: 1
                }, {
                    text: 'Status', dataIndex: 'status', flex: 1, sortable: false,
                    renderer: function (val) {
                        return val;
                    }
                }, {
                    header: 'Action', flex: 1, sortable: false,
                    renderer: function (val) {
                        var actions = val.split(',');
                        var result = '';
                        for (var i = 0; i < actions.length; i++) {
                            result = result + '<input type="button" value="' + actions[i] + '" id="'
                                + actions[i] + '"/>' + '<br/>';
                        }
                        return result;
                    },
                    dataIndex: 'action'
                }],
                store: Ext.create('Ext.data.Store', {
                    fields: ['parameter', 'status', 'action'],
                    storeId: 'simpsonsStore',
                    data: [],
                    proxy: {
                        type: 'memory',
                        reader: {
                            type: 'json', root: 'items'
                        }
                    }
                })
            }]

    }]
});
Ext.Loader
    .setPath(
        "Ext.ux.DateTimePicker",
        "https://rawgit.com/gportela85/DateTimeField/datetimefield-4.2.x/src/DateTimePicker.js");
Ext.Loader
    .setPath(
        "Ext.ux.DateTimeField",
        "https://rawgit.com/gportela85/DateTimeField/datetimefield-4.2.x/src/DateTimeField.js");

Ext.define('FleetEvent', {
    extend: 'Ext.panel.Panel',
    requires: ['Ext.ux.DateTimeField'],
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
            xtype: 'combobox', queryMode: 'local', valueField: 'devuuid', editable: false,
            id: 'fleetevent_combo', forceSelection: true, displayField: 'devuuid', fieldLabel: 'Device List',
            store: Ext.create('Ext.data.Store', {
                fields: ['devuuid'], autoLoad: false, data: []
            })

        }, {
            xtype: 'button', margin: "0 0 0 20", text: 'API'
        }, {
            xtype: 'button', margin: "0 0 0 20", text: 'Device Event'
        }]
    }, {
        xtype: 'panel', border: false,
        layout: {
            type: 'hbox', align: 'middle'
        },
        width: '90%', height: 180,
        items: [{
            xtype: 'form', width: 450, bodyPadding: 10, height: 180, id: 'fleetevent_form',
            items: [{
                xtype: 'datetimefield', anchor: '100%', itemId: 'from', fieldLabel: 'From',
                labelAlign: 'right', labelWidth: 70, editable: true
            }, {
                xtype: 'datetimefield', anchor: '100%', itemId: 'to', fieldLabel: 'To',
                labelAlign: 'right', labelWidth: 70, editable: true
            }, {
                xtype: 'button', anchor: '30%', text: 'Search',
                handler: function () {
                    var startDate = (Ext.getCmp('fleetevent_form').getComponent('from').getValue());
                    var startTimestamp = Ext.Date.format(startDate, "time");
                    var endDate = (Ext.getCmp('fleetevent_form').getComponent('to').getValue());
                    var endTimestamp = Ext.Date.format(endDate, "time");
                    Ext.Ajax.request({
                        url: '/device/getdeviceeventsbytime',
                        params: {
                            startDate: startTimestamp,
                            endDate: endTimestamp
                        },
                        method: 'POST',
                        success: function (res) {
                            var data = JSON.parse(res.responseText);
                            Ext.getCmp("fleetevent_chart").getStore().loadData(data);
                        }
                    });

                    Ext.Ajax.request({
                        url: '/device/getdevicestatisticsgridbytime',
                        params: {
                            startDate: startTimestamp,
                            endDate: endTimestamp
                        },
                        method: 'POST',
                        success: function (res) {
                            var data = JSON.parse(res.responseText);
                            Ext.getCmp("fleetevent_statisticgrid").getStore().loadData(data);
                        }
                    });
                }
            }]
        }]
    }, {
        xtype: 'panel', border: true,
        layout: {
            type: 'hbox'
        },
        margin: '30 0 0 0', width: '90%', height: 800,
        items: [{
            xtype: 'panel',
            layout: {
                type: 'vbox', align: 'center'
            },
            width: '50%', border: true,
            items: [{
                xtype: 'panel',
                layout: {
                    type: 'vbox', align: 'center'
                },
                margin: '20 0 0 0', width: 350, height: 500, border: true,
                items: [{
                    xtype: 'label', padding: '0 0 0 20', style: ' font-size: 22px ', text: 'Event Stastics'
                }, {
                    xtype: 'chart', width: 350, height: 200, style: 'background:#fff',
                    animate: true, shadow: true,id:'fleetevent_chart',
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
                            renderer: Ext.util.Format
                                .numberRenderer('0,0')
                        },
                        xField: 'devuuid', yField: ['eventcount']
                    }]
                }, {
                    xtype: 'gridpanel',id:'fleetevent_statisticgrid',
                    store: Ext.create('Ext.data.Store', {
                        fields: ['parameter', 'total',
                            'perday'],
                        storeId: 'simpsonsStore',
                        data: {
                            'items': [{
                                'parameter': "API Calls", 'total': '6000', 'perday': '339'
                            }, {
                                'parameter': "Events", 'total': '4000', 'perday': '303'
                            }, {
                                'parameter': "Heart beats", 'total': '4999', 'perday': '300'
                            }]
                        },
                        proxy: {
                            type: 'memory',
                            reader: {
                                type: 'json', root: 'items'
                            }
                        }
                    }),
                    height: 200, title: 'Stastic Table', width: 350, margin: '20 0 0 0',
                    columns: [{
                        text: 'Parameter', dataIndex: 'parameter'
                    }, {
                        text: 'Total', dataIndex: 'total'
                    }, {
                        text: 'Per Day', dataIndex: 'perday'
                    }]
                }]

            }]
        }, {
            xtype: 'panel',
            layout: {
                type: 'vbox', align: 'center'
            },
            margin: '20 0 0 0', width: 450, height: 500, border: true,
            items: [{
                xtype: 'gridpanel',
                store: Ext.create('Ext.data.Store', {
                    fields: ['type', 'updatetime', 'functionarea',
                        'action'],
                    data: {
                        'items': [{
                            'type': "API call", 'updatetime': '23.07 11:30',
                            'functionarea': 'Central lock', 'action': 'lock'
                        }, {
                            'type': "Device event", 'updatetime': '23.07 11:30', 'functionarea': 'Immobiliser',
                            'action': 'lock'
                        }, {
                            'type': "API call", 'updatetime': '23.07 11:30',
                            'functionarea': 'BT use', 'action': 'lock'
                        }, {
                            'type': "API call", 'updatetime': '23.07 11:30',
                            'functionarea': 'Main board', 'action': 'lock'
                        }, {
                            'type': "API call", 'updatetime': '23.07 11:30',
                            'functionarea': 'BT module', 'action': 'lock'
                        }, {
                            'type': "API call", 'updatetime': '23.07 11:30',
                            'functionarea': 'Current BT <br/> connected device', 'action': 'lock'
                        }, {
                            'type': "Device event", 'updatetime': '23.07 11:30',
                            'functionarea': 'OBD module', 'action': 'lock'
                        }, {
                            'type': "Device event", 'updatetime': '23.07 11:30',
                            'functionarea': 'GPRS module', 'action': 'lock'
                        }, {
                            'type': "Device event", 'updatetime': '23.07 11:30',
                            'functionarea': 'GPRS connection', 'action': 'lock'
                        }]
                    },
                    proxy: {
                        type: 'memory',
                        reader: {
                            type: 'json', root: 'items'
                        }
                    }
                }),
                height: 300, title: 'Event overview', width: 450, margin: '20 0 0 0',
                columns: [{
                    text: 'Type', dataIndex: 'type', flex: 1.5
                }, {
                    text: 'Update Time', dataIndex: 'updatetime', flex: 2
                }, {
                    text: 'Function Area', dataIndex: 'functionarea', flex: 2
                }, {
                    text: 'Action', dataIndex: 'action', flex: 1
                }]
            }]
        }]
    }]
});
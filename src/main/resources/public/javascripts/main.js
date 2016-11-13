Ext.define('MainWindow', {
    extend: 'Ext.window.Window',
    closable: false,
    autoShow: true,
    height: 369,
    width: 610,
    title: 'MoC Portal Main',
    layout: 'border',
    initComponent: function () {
        var me = this;
        me.callParent(arguments);
    },
    items: [{
        xtype: 'treepanel',
        width: '20%',
        region: 'west',
        title: 'Menu',
        listeners: {
            celldblclick: function (tree, td, cellIndex, record, tr,
                                    rowIndex, e, eOpts) {
                var tabpanel = Ext.getCmp('maintab');
                var tab = Ext.getCmp(record.raw.id + '_tab');
                if (tab) {
                    tabpanel.setActiveTab(tab);
                }
                else {
                    var tabname = '';
                    if (record.raw.id == 'fleetoverview') {
                        tabname = 'Fleet Overview';
                        tab = tabpanel.add({
                            title: tabname,
                            closable: false,
                            id: record.raw.id + "_tab",
                            layout: 'fit',
                            items: [Ext.create('FleetOverview')]
                        });
                        Ext.Ajax.request({
                            url: '/device/retrieveall',
                            method: 'POST',
                            success: function (res) {
                                var data = JSON.parse(res.responseText);
                                Ext.getCmp("fleetoverview_combo").getStore().loadData(data);
                            }
                        });
                    }
                    else if (record.raw.id == 'deviceoverview') {
                        tabname = 'Device Overview';
                        tab = tabpanel.add({
                            title: tabname,
                            closable: false,
                            id: record.raw.id + "_tab",
                            layout: 'fit',
                            items: [Ext.create('DeviceOverview')]
                        });
                    }
                    else if (record.raw.id == 'controlandconfiguration') {
                        tabname = 'Control and Configuration';
                        tab = tabpanel.add({
                            title: tabname,
                            closable: false,
                            id: record.raw.id + "_tab",
                            layout: 'fit',
                            items: [Ext.create('ControlAndConfiguration')]
                        });
                    }
                    else if (record.raw.id == 'addnewdevice') {
                        tabname = 'Add New Device';
                        tab = tabpanel.add({
                            title: tabname,
                            closable: false,
                            id: record.raw.id + "_tab",
                            layout: 'fit',
                            items: [Ext.create('AddNewDevice')]
                        });
                    }
                    else if (record.raw.id == 'fleetevent') {
                        tabname = 'Fleet Event';
                        tab = tabpanel.add({
                            title: tabname,
                            closable: false,
                            id: record.raw.id + "_tab",
                            layout: 'fit',
                            items: [Ext.create('FleetEvent')]
                        });
                    }
                    else if (record.raw.id == 'usersetting') {
                        tabname = 'User Setting';
                        tab = tabpanel.add({
                            title: tabname,
                            closable: false,
                            id: record.raw.id + "_tab",
                            layout: 'fit',
                            items: [Ext.create('UserSetting')]
                        });
                    }
                    else tabname = '';
                    tabpanel.setActiveTab(tab);

                    if (record.raw.id == 'fleetoverview') {


                    }
                }

            }
        },
        root: {
            text: 'Menu',
            leaf: false,
            children: [{
                text: 'Fleet Overview',
                id: 'fleetoverview',
                leaf: true
            }, {
                text: 'Device Overview',
                id: 'deviceoverview',
                leaf: true
            }, {
                text: 'Control and Configuration',
                id: 'controlandconfiguration',
                leaf: false,
                children: [
                    {
                        text: 'Add New Device',
                        id: 'addnewdevice',
                        leaf: true
                    }
                ]
            }, {
                text: 'Fleet Event',
                id: 'fleetevent',
                leaf: true
            }, {
                text: 'User Setting',
                id: 'usersetting',
                leaf: true
            }]
        }
    },
        {
            xtype: 'tabpanel',
            region: 'east',
            id: 'maintab',
            width: '80%'
        }
    ]

})
;
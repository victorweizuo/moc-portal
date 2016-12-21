Ext.define('AddNewDevice', {
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
        items: []
    }, {
        xtype: 'panel', border: false,
        layout: {
            type: 'hbox', align: 'middle'
        },
        width: '90%', height: 180,
        items: [{
            xtype: 'form', width: 450, bodyPadding: 10, height: 180,
            items: [
                {
                    xtype: 'textfield', anchor: '100%', itemId: 'serial', fieldLabel: 'Serial No.'
                    , labelAlign: 'right', labelWidth: 70, editable: true, id: 'addnewdevice_serialno'
                }, {
                    xtype: 'textfield', anchor: '100%', itemId: 'serial', fieldLabel: 'Serial No.',
                    labelAlign: 'right', labelWidth: 70, editable: true, id: 'addnewdevice_platvin'
                },
                {
                    xtype: 'textfield', anchor: '100%', itemId: 'sms', fieldLabel: 'SMS NO.',
                    labelAlign: 'right', labelWidth: 70, editable: true, id: 'addnewdevice_smsno'
                }, {
                    xtype: 'textfield', anchor: '100%', itemId: 'mac', fieldLabel: 'BLE mac.',
                    labelAlign: 'right', labelWidth: 70, editable: true, id: 'addnewdevice_blemac'
                }, {
                    xtype: 'textfield', anchor: '100%', itemId: 'sppmac', fieldLabel: 'SPP mac.',
                    labelAlign: 'right', labelWidth: 70, editable: true, id: 'addnewdevice_sppmac'
                }, {
                    xtype: 'button', anchor: '20%', text: 'Add',
                    handler: function () {
                        Ext.Ajax.request({
                            url: '/device/adddevice',
                            params: {
                                serialno: Ext.getCmp('addnewdevice_serialno').getValue(),
                                platvin: Ext.getCmp('addnewdevice_platvin').getValue(),
                                smsno: Ext.getCmp('addnewdevice_smsno').getValue(),
                                sppmac: Ext.getCmp('addnewdevice_sppmac').getValue(),
                                blemac: Ext.getCmp('addnewdevice_blemac').getValue()
                            },
                            method: 'POST',
                            success: function (res) {
                                alert(res.responseText);
                            }
                        });
                    }
                }]

        }]
    }]
});
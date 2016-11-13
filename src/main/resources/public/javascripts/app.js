Ext.application({
    name: 'MoC Portal',
    launch: function () {
        Ext.create('Ext.container.Viewport', {
            layout: {
                type: 'vbox',
                align: 'center'
            },
            items: [{
                xtype: 'panel',
                width: 136,
                height: 37,
                margin: "0 0 10 0",
                layout: 'fit',
                border: false,
                items: [{
                    xtype: 'image',
                    src: 'images/u0.png'
                }]
            }, {
                xtype: 'panel',

                width: 1095,
                height: 305,
                margin: 10,
                layout: 'fit',
                items: [{
                    xtype: 'image',
                    src: 'images/u11.jpg'
                }]

            }, {
                xtype: 'form',
                itemId: 'form',
                width: 350,
                bodyPadding: 10,
                title: 'Login',
                items: [{
                    xtype: 'textfield',
                    anchor: '100%',
                    id:'username',
                    value: 'mobilityoncloudvip',
                    itemId: 'username',
                    fieldLabel: 'Username',
                    labelAlign: 'right',
                    labelWidth: 70,
                    editable: false,
                    displayField: 'value',
                    valueField: 'id'
                }, {
                    xtype: 'textfield',
                    anchor: '100%',
                    value: 'skdjfRRR34dfe&',
                    itemId: 'password',
                    id:'password',
                    fieldLabel: 'Password',
                    labelAlign: 'right',
                    labelWidth: 70,
                    editable: false,
                    inputType: 'password',
                    displayField: 'value',
                    valueField: 'id'
                }, {
                    xype: 'container',
                    anchor: '100%',
                    border: false,
                    layout: {
                        type: 'hbox',
                        align: 'middle',
                        pack: 'center'
                    },
                    items: [{
                        xtype: 'button',
                        itemId: 'login',
                        text: 'Login',
                        listeners: {
                            click: function (e) {

                                Ext.Ajax.request({
                                    url: '/user/validate',
                                    params: {
                                        username: Ext.getCmp("username").getValue(),
                                        password: Ext.getCmp("password").getValue()
                                    },
                                    method: 'POST',
                                    success: function (res) {
                                        if (res.responseText.indexOf("Success") >= 0) {
                                            var win = new MainWindow();
                                            win.show();
                                            win.maximize(true);
                                        }
                                        else {
                                            alert(res.responseText);
                                        }

                                    }
                                });
                            }
                        }
                    }]
                }]
            }]
        })
    }
});
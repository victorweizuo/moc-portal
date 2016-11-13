Ext.define('AddNewDevice', {
	extend : 'Ext.panel.Panel',
	layout : {
		type : 'vbox',
		align : 'center'
	},
	autoScroll : true,
	items : [{
				xtype : 'panel',
				layout : {
					type : 'hbox',
					pack : 'center',
					align : 'middle'
				},
				width : "90%",
				margin : 30,
				height : 50,
				items : []
			}, {
				xtype : 'panel',
				border : false,
				layout : {
					type : 'hbox',
					align : 'middle'
				},
				width : '90%',
				height : 180,
				items : [{
							xtype : 'form',
							width : 450,
							bodyPadding : 10,
							height : 180,
							items : [{
										xtype : 'textfield',
										anchor : '100%',
										value : 'device2 uuid',
										itemId : 'uuid',
										fieldLabel : 'UUID',
										labelAlign : 'right',
										labelWidth : 70,
										editable : true
									}, {
										xtype : 'textfield',
										anchor : '100%',
										value : 'device2 sms',
										itemId : 'sms',
										fieldLabel : 'SMS NO.',
										labelAlign : 'right',
										labelWidth : 70,
										editable : true
									}, {
										xtype : 'textfield',
										anchor : '100%',
										value : 'device2 ble',
										itemId : 'mac',
										fieldLabel : 'BLE mac.',
										labelAlign : 'right',
										labelWidth : 70,
										editable : true
									}, {
										xtype : 'textfield',
										anchor : '100%',
										value : 'device2 spp',
										itemId : 'sppmac',
										fieldLabel : 'SPP mac.',
										labelAlign : 'right',
										labelWidth : 70,
										editable : true
									}, {
										xtype : 'button',
										anchor : '20%',
										text : 'Add'
									}]

						}]
			}]
});
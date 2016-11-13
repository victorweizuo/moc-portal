Ext.define('ControlAndConfiguration', {
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
				items : [{

							xtype : 'combobox',
							fieldLabel : 'Device List',
							displayField : 'device',
							store : Ext.create('Ext.data.Store', {
										fields : ['device'],
										data : [{
													'device' : "Device1"
												}, {
													'device' : "Device2"
												}, {
													'device' : "Device3"
												}, {
													'device' : "Device4"
												}, {
													'device' : "Device5"
												}, {
													'device' : "Device6"
												}]
									})

						}, {
							xtype : 'button',
							margin : "0 0 0 20",
							text : 'Add New Device'
						}]
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
										anchor : '100%',
										text : 'Save'
									}]

						}, {
							xtype : 'panel',
							border : false,
							width : 450,
							height : 180,
							items : [{
										xtype : 'label',
										padding : '0 0 0 20',
										style : ' font-size: 32px ',
										text : 'Device',
										id : 'deviceName'
									}]
						}]
			}, {
				xtype : 'panel',
				border : true,
				layout : {
					type : 'hbox'
				},
				margin : '30 0 0 0',
				width : '90%',
				height : 1500,
				items : [{
					xtype : 'gridpanel',
					height : 400,
					title : 'Device Control',
					width : 450,
					margin : '20 0 0 0',
					columns : [{
								text : 'Parameter',
								dataIndex : 'parameter',
								flex:1
							}, {
								text : 'Status',
								dataIndex : 'status',
								flex:1
							}, {
								text : 'Update Time',
								dataIndex : 'updatetime',
								flex:1
							}, {
								header : 'Action',
								flex:2,
								sortable : false,
								renderer : function(val) {
									var actions=val.split(',');
									var result='';
									for(var i=0;i<actions.length;i++)
									{
										result=result+'<input type="button" value="'+actions[i]+'" id="'
											+ actions[i] + '"/>'+'<br/>';
									}
									return result;
								},
								dataIndex : 'action'
							}],
					store : Ext.create('Ext.data.Store', {
						fields : ['parameter', 'status', 'updatetime', 'action'],
						storeId : 'simpsonsStore',
						data : {
							'items' : [{
										'parameter' : "Centrol Lock",
										'status' : 'lock',
										'updatetime' : '23.07 11:30',
										'action' : 'Lock,Unlock'
									}, {
										'parameter' : "Immobiliser",
										'status' : 'lock',
										'updatetime' : '23.07 11:30',
										'action' : 'Lock,Unlock'
									}, {
										'parameter' : "BT use",
										'status' : 'Yes',
										'updatetime' : '23.07 11:30',
										'action' : 'Return key'
									}, {
										'parameter' : "Main board",
										'status' : 'OK',
										'updatetime' : '23.07 11:30',
										'action' : 'Reset'
									}, {
										'parameter' : "BT module",
										'status' : 'OK',
										'updatetime' : '23.07 11:30',
										'action' : 'Reset'
									}, {
										'parameter' : "Current BT connected device",
										'status' : 's3:d3:3e:33:33',
										'updatetime' : '23.07 11:30',
										'action' : 'Disconnect'
									}, {
										'parameter' : "OBD module",
										'status' : 'OK',
										'updatetime' : '23.07 11:30',
										'action' : 'Reset'
									}, {
										'parameter' : "GPRS module",
										'status' : 'OK',
										'updatetime' : '23.07 11:30',
										'action' : 'Reset'
									}, {
										'parameter' : "GPRS connection",
										'status' : 'OK',
										'updatetime' : '23.07 11:30',
										'action' : 'Reset'
									}]
						},
						proxy : {
							type : 'memory',
							reader : {
								type : 'json',
								root : 'items'
							}
						}

					})
				},
				{
					xtype : 'gridpanel',
					height : 500,
					title : 'Device Configuration',
					width : 450,
					margin : '20 0 0 20',
					columns : [{
								text : 'Parameter',
								dataIndex : 'parameter',
								flex:1
							}, {
								text : 'Status',
								dataIndex : 'status',
								flex:1,
								sortable:false,
								renderer: function(val)
								{
									return val;
								}
							}, {
								header : 'Action',
								flex:1,
								sortable : false,
								renderer : function(val) {
									var actions=val.split(',');
									var result='';
									for(var i=0;i<actions.length;i++)
									{
										result=result+'<input type="button" value="'+actions[i]+'" id="'
											+ actions[i] + '"/>'+'<br/>';
									}
									return result;
								},
								dataIndex : 'action'
							}],
					store : Ext.create('Ext.data.Store', {
						fields : ['parameter', 'status', 'action'],
						storeId : 'simpsonsStore',
						data : {
							'items' : [{
										'parameter' : "Mileague unit",
										'status' : 'KM',
										'action' : 'KM,Miles'
									}, {
										'parameter' : "Allowed RFID",
										'status' : '<input type="number"/><br/><input type="date"/><br/><input type="date"/>',
										'action' : 'Reset'
									}, {
										'parameter' : "Key RFID",
										'status' : 'r435324532534',
										'action' : 'Reset'
									}, {
										'parameter' : "Fuel RFID",
										'status' : '554325325435',
										'action' : 'Reset'
									}, {
										'parameter' : "Parking RFID",
										'status' : '54353254325',
										'action' : 'Reset'
									}, {
										'parameter' : "Anti theft",
										'status' : 'Active',
										'action' : 'On,Off'
									}, {
										'parameter' : "OBD module",
										'status' : 'OK',
										'action' : 'Reset'
									}, {
										'parameter' : "GPRS offline alarm time",
										'status' : '<input type="date"/>',
										'action' : 'Reset'
									}, {
										'parameter' : "SMS time limit",
										'status' : 'Yes',
										'action' : 'On,Off'
									}, {
										'parameter' : "SMS max time",
										'status' : '<input type="date"/>',
										'action' : 'Reset'
									}, {
										'parameter' : "BT key time limit",
										'status' : 'Yes',
										'action' : 'On,Off'
									}]
						},
						proxy : {
							type : 'memory',
							reader : {
								type : 'json',
								root : 'items'
							}
						}

					})
				}]

			}]
});
Ext.define('DeviceOverview', {
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
				width : "75%",
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
							text : 'Control and Configuration'
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
				width : '75%',
				height : 180,
				items : [{
							xtype : 'form',
							width : 350,
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
										xtype : 'textfield',
										anchor : '100%',
										value : '1.2.3',
										itemId : 'version',
										fieldLabel : 'Version.',
										labelAlign : 'right',
										labelWidth : 70,
										readOnly : true,
										disabled : true
									}, {
										xtype : 'textfield',
										anchor : '100%',
										value : '4th July 2015',
										itemId : 'update',
										fieldLabel : 'Updated on',
										labelAlign : 'right',
										labelWidth : 70,
										readOnly : true,
										disabled : true
									}]

						}, {
							xtype : 'panel',
							border : false,
							width : 350,
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
				width : '75%',
				height : 1500,
				items : [{
					xtype : 'panel',
					layout : {
						type : 'vbox',
						align : 'center'
					},
					width : '50%',
					border : true,
					items : [{
						xtype : 'panel',
						layout : {
							type : 'vbox',
							align : 'center'
						},
						width : 350,
						height : 220,
						items : [{
									xtype : 'label',
									padding : '0 0 0 20',
									style : ' font-size: 22px ',
									text : 'Device'
								}, {
									xtype : 'panel',
									width : 320,
									height : 180,
									layout : 'fit',
									items : {
										xtype : 'gmappanel',
										center : {
											geoCodeAddr : '4 Yawkey Way, Boston, MA, 02215-3409, USA',
											marker : {
												title : 'Fenway Park'
											}
										},
										markers : [{
											lat : 42.339641,
											lng : -71.094224,
											title : 'Boston Museum of Fine Arts',
											listeners : {
												click : function(e) {
													Ext.Msg.alert('It\'s fine',
															'and it\'s art.');
												}
											}
										}, {
											lat : 42.339419,
											lng : -71.09077,
											title : 'Northeastern University'
										}]
									}
								}]
					}, {
						xtype : 'panel',
						layout : {
							type : 'vbox',
							align : 'center'
						},
						margin : '20 0 0 0',
						width : 350,
						height : 500,
						border : true,
						items : [{
									xtype : 'label',
									padding : '0 0 0 20',
									style : ' font-size: 22px ',
									text : 'Event Stastics'
								}, {
									xtype : 'chart',
									width : 350,
									height : 200,
									style : 'background:#fff',
									animate : true,
									shadow : true,
									store : Ext.create('Ext.data.Store', {
												fields : ['name', 'data1',
														'data2', 'data3'],
												data : [{
															'name' : "Device1",
															'data1' : 4,
															'data2' : 3,
															'data3' : 7
														}, {
															'name' : "Device1",
															'data1' : 4,
															'data2' : 3,
															'data3' : 7
														}, {
															'name' : "Device1",
															'data1' : 4,
															'data2' : 3,
															'data3' : 7
														}, {
															'name' : "Device1",
															'data1' : 4,
															'data2' : 3,
															'data3' : 7
														}, {
															'name' : "Device1",
															'data1' : 4,
															'data2' : 3,
															'data3' : 7
														}]
											}),
									axes : [{
										type : 'Numeric',
										position : 'left',
										fields : ['data1', 'data2', 'data3'],
										label : {
											renderer : Ext.util.Format
													.numberRenderer('0,0')
										},
										grid : true,
										minimum : 0
									}, {
										type : 'Category',
										position : 'bottom',
										fields : ['name']
									}],
									series : [{
										type : 'column',
										axis : 'left',
										highlight : true,
										minimum : 0,
										label : {
											renderer : Ext.util.Format
													.numberRenderer('0,0')
										},
										xField : 'name',
										yField : ['data1', 'data2', 'data3']
									}]
								}, {
									xtype : 'gridpanel',
									store : Ext.create('Ext.data.Store', {
										fields : ['parameter', 'total',
												'perday'],
										storeId : 'simpsonsStore',
										data : {
											'items' : [{
														'parameter' : "API Calls",
														'total' : '6000',
														'perday' : '339'
													}, {
														'parameter' : "Events",
														'total' : '4000',
														'perday' : '303'
													}, {
														'parameter' : "Heart beats",
														'total' : '4999',
														'perday' : '300'
													}]
										},
										proxy : {
											type : 'memory',
											reader : {
												type : 'json',
												root : 'items'
											}
										}
									}),
									height : 200,
									title : 'Stastic Table',
									width : 350,
									margin : '20 0 0 0',
									columns : [{
												text : 'Parameter',
												dataIndex : 'parameter'
											}, {
												text : 'Total',
												dataIndex : 'total'
											}, {
												text : 'Per Day',
												dataIndex : 'perday'
											}]
								}]
					}]
				}, {
					xtype : 'panel',
					layout : {
						type : 'vbox',
						align : 'center'
					},
					width : '50%',

					border : true,
					items : [{
						xtype : 'gridpanel',
						store : Ext.create('Ext.data.Store', {
							fields : ['parameter', 'status', 'lastupdatetime'],

							data : {
								'items' : [{
											'parameter' : "Central Lock",
											'status' : 'Locked',
											'lastupdatetime' : '2 July 11:20'
										}, {
											'parameter' : "Immobiliser",
											'status' : 'Locked',
											'lastupdatetime' : ''
										}, {
											'parameter' : "BT in USE",
											'status' : 'Yes',
											'lastupdatetime' : ''
										}, {
											'parameter' : "Current Connected BT",
											'status' : '332:E3.341:3e:',
											'lastupdatetime' : ''
										}, {
											'parameter' : "Allowed RFID",
											'status' : '1489653165151',
											'lastupdatetime' : ''
										}, {
											'parameter' : "Key RFID",
											'status' : 'Not Set',
											'lastupdatetime' : ''
										}, {
											'parameter' : "Fuel Card",
											'status' : 'In',
											'lastupdatetime' : ''
										}, {
											'parameter' : "Parking Card",
											'status' : 'Out',
											'lastupdatetime' : ''
										}, {
											'parameter' : "Anti Theft Status",
											'status' : 'Unplegged',
											'lastupdatetime' : ''
										}, {
											'parameter' : "Device Time",
											'status' : '',
											'lastupdatetime' : ''
										}]
							},
							proxy : {
								type : 'memory',
								reader : {
									type : 'json',
									root : 'items'
								}
							}
						}),
						height : 300,
						title : 'Device Status',
						width : 400,
						margin : '20 0 0 0',
						columns : [{
									text : 'Parameter',
									dataIndex : 'parameter',
									flex : 2
								}, {
									text : 'Status',
									dataIndex : 'status',
									flex : 2
								}, {
									text : 'Last Update Time',
									dataIndex : 'lastupdatetime',
									flex : 1.5
								}]
					}, {
						xtype : 'gridpanel',
						store : Ext.create('Ext.data.Store', {
									fields : ['parameter', 'status',
											'lastupdatetime'],

									data : {
										'items' : [{
													'parameter' : "Vehicle Battery",
													'status' : '133V',
													'lastupdatetime' : '2 July 11:20'
												}, {
													'parameter' : "Mileage",
													'status' : '',
													'lastupdatetime' : ''
												}, {
													'parameter' : "Mileage Unit",
													'status' : 'Km',
													'lastupdatetime' : ''
												}, {
													'parameter' : "Speed",
													'status' : '',
													'lastupdatetime' : ''
												}, {
													'parameter' : "Ignition",
													'status' : '',
													'lastupdatetime' : ''
												}, {
													'parameter' : "Fuel Level",
													'status' : '',
													'lastupdatetime' : ''
												}]
									},
									proxy : {
										type : 'memory',
										reader : {
											type : 'json',
											root : 'items'
										}
									}
								}),
						height : 200,
						title : 'Vehicle Information',
						width : 400,
						margin : '20 0 0 0',
						columns : [{
									text : 'Parameter',
									dataIndex : 'parameter',
									flex : 2
								}, {
									text : 'Status',
									dataIndex : 'status',
									flex : 2
								}, {
									text : 'Last Update Time',
									dataIndex : 'lastupdatetime',
									flex : 1.5
								}]
					}, {
						xtype : 'gridpanel',
						store : Ext.create('Ext.data.Store', {
							fields : ['parameter', 'status', 'lastchange',
									'time'],

							data : {
								'items' : [{
											'parameter' : "Main Board",
											'status' : 'OK',
											'lastchange' : 'Self Reboot',
											'time' : '2 July 11:20'
										}, {
											'parameter' : "BT Module",
											'status' : 'OK',
											'lastchange' : 'Self Reboot',
											'time' : ''
										}, {
											'parameter' : "GPRS Module",
											'status' : 'OK',
											'lastchange' : 'Remote Reboot',
											'time' : ''
										}, {
											'parameter' : "GPRS Connection",
											'status' : 'Offline',
											'lastchange' : '',
											'time' : ''
										}, {
											'parameter' : "OBD Module",
											'status' : 'OK',
											'lastchange' : 'Self Reboot',
											'time' : ''
										}, {
											'parameter' : "Device Battery",
											'status' : '13.3V',
											'lastchange' : '',
											'time' : ''
										}, {
											'parameter' : "GPRS Offline Alarm Time",
											'status' : 'NO',
											'lastchange' : '',
											'time' : ''
										}, {
											'parameter' : "SMS Time Limit Time",
											'status' : 'No',
											'lastchange' : '',
											'time' : ''
										}, {
											'parameter' : "BT Key Time Limit",
											'status' : 'Yes',
											'lastchange' : '',
											'time' : ''
										}, {
											'parameter' : "Anti Theft",
											'status' : 'Active',
											'lastchange' : '',
											'time' : ''
										}]
							},
							proxy : {
								type : 'memory',
								reader : {
									type : 'json',
									root : 'items'
								}
							}
						}),
						height : 300,
						title : 'Hardware Status and Setting',
						width : 400,
						margin : '20 0 0 0',
						columns : [{
									text : 'Parameter',
									dataIndex : 'parameter',
									flex : 2
								}, {
									text : 'Status',
									dataIndex : 'status',
									flex : 2
								}, {
									text : 'Last Change',
									dataIndex : 'lastchange',
									flex : 1.5
								}, {
									text : 'Time',
									dataIndex : 'time',
									flex : 1.5
								}]
					}]
				}]
			}]
});
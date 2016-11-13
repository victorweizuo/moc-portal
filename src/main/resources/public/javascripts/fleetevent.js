Ext.Loader
		.setPath(
				"Ext.ux.DateTimePicker",
				"https://rawgit.com/gportela85/DateTimeField/datetimefield-4.2.x/src/DateTimePicker.js");
Ext.Loader
		.setPath(
				"Ext.ux.DateTimeField",
				"https://rawgit.com/gportela85/DateTimeField/datetimefield-4.2.x/src/DateTimeField.js");

Ext.define('FleetEvent', {
	extend : 'Ext.panel.Panel',
	requires : ['Ext.ux.DateTimeField'],
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
							text : 'API'
						}, {
							xtype : 'button',
							margin : "0 0 0 20",
							text : 'Device Event'
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
										xtype : 'datetimefield',
										anchor : '100%',
										itemId : 'from',
										fieldLabel : 'From',
										labelAlign : 'right',
										labelWidth : 70,
										editable : true
									}, {
										xtype : 'datetimefield',
										anchor : '100%',
										itemId : 'to',
										fieldLabel : 'To',
										labelAlign : 'right',
										labelWidth : 70,
										editable : true
									}, {
										xtype : 'button',
										anchor : '30%',
										text : 'Search'
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
				height : 800,
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
						margin : '20 0 0 0',
						width : 450,
						height : 500,
						border : true,
						items : [{
							xtype : 'gridpanel',
							store : Ext.create('Ext.data.Store', {
								fields : ['type', 'updatetime','functionarea',
										'action'],

								data : {
									'items' : [{
												'type' : "API call",
												'updatetime' : '23.07 11:30',
												'functionarea' : 'Central lock',
												'action':'lock'
											},{
												'type' : "Device event",
												'updatetime' : '23.07 11:30',
												'functionarea' : 'Immobiliser',
												'action':'lock'
											},{
												'type' : "API call",
												'updatetime' : '23.07 11:30',
												'functionarea' : 'BT use',
												'action':'lock'
											},{
												'type' : "API call",
												'updatetime' : '23.07 11:30',
												'functionarea' : 'Main board',
												'action':'lock'
											},{
												'type' : "API call",
												'updatetime' : '23.07 11:30',
												'functionarea' : 'BT module',
												'action':'lock'
											},{
												'type' : "API call",
												'updatetime' : '23.07 11:30',
												'functionarea' : 'Current BT <br/> connected device',
												'action':'lock'
											},{
												'type' : "Device event",
												'updatetime' : '23.07 11:30',
												'functionarea' : 'OBD module',
												'action':'lock'
											},{
												'type' : "Device event",
												'updatetime' : '23.07 11:30',
												'functionarea' : 'GPRS module',
												'action':'lock'
											},{
												'type' : "Device event",
												'updatetime' : '23.07 11:30',
												'functionarea' : 'GPRS connection',
												'action':'lock'
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
							title : 'Event overview',
							width : 450,
							margin : '20 0 0 0',
							columns : [{
										text : 'Type',
										dataIndex : 'type',
										flex : 1.5
									}, {
										text : 'Update Time',
										dataIndex : 'updatetime',
										flex : 2
									}, {
										text : 'Function Area',
										dataIndex : 'functionarea',
										flex : 2
									}, {
										text : 'Action',
										dataIndex : 'action',
										flex : 1
									}]
						}]
					}]
			}]
});
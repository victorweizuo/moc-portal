Ext.define('UserSetting', {

			extend : 'Ext.panel.Panel',
			layout : {
				type : 'vbox',
				align : 'center'
			},
			autoScroll : true,
			items : [{
						xtype : 'panel',
						height : 50,
						width : '80%',
						margin : '30 0 0 0',
						bodyStyle : {
							"background-color" : "grey"
						},
						layout : {
							type : 'vbox',
							align : 'center',
							pack : 'center'
						},
						items : [{
									xtype : 'label',
									text : 'zuowei'
								}]
					}, {
						xtype : 'form',
						bodyPadding : 10,
						width : '100%',
						title : 'User Profile',
						margin : '40 0 0 0',
						height : 120,
						items : [{
									xtype : 'textfield',
									anchor : '30%',
									itemId : 'password',
									fieldLabel : 'Password',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'textfield',
									anchor : '30%',
									itemId : 'passwordreset',
									fieldLabel : 'Password Reset',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'button',
									anchor : '20%',
									text : 'Reset'
								}]
					}, {

						xtype : 'form',
						bodyPadding : 10,
						width : '100%',
						height : 380,
						title : 'Contact',
						margin : '40 0 0 0',
						width : '100%',
						items : [{
									xtype : 'textfield',
									anchor : '50%',
									itemId : 'companyname',
									fieldLabel : 'Company Name',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'textfield',
									anchor : '50%',
									itemId : 'status',
									fieldLabel : 'Status',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'textfield',
									anchor : '50%',
									itemId : 'street',
									fieldLabel : 'Street',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'textfield',
									anchor : '50%',
									itemId : 'postcode',
									fieldLabel : 'Postcode',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'textfield',
									anchor : '50%',
									itemId : 'city',
									fieldLabel : 'City',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'textfield',
									anchor : '50%',
									itemId : 'country',
									fieldLabel : 'Country',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'textfield',
									anchor : '50%',
									itemId : 'vatno',
									fieldLabel : 'VAT No.',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'label',
									anchor : '50%',
									text : 'Contact Person',
									style : {
										color : 'red'
									}

								}, {

									xtype : 'textfield',
									anchor : '50%',
									itemId : 'firstname',
									fieldLabel : 'First Name',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'textfield',
									anchor : '50%',
									itemId : 'lastname',
									fieldLabel : 'Last Name',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'textfield',
									anchor : '50%',
									itemId : 'phonenumber',
									fieldLabel : 'Phone Number',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'textfield',
									anchor : '50%',
									itemId : 'email',
									fieldLabel : 'Email',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'button',
									text : 'Reset',
									anchor : '20%'
								}]

					}, {
						xtype : 'form',
						title : 'Auth Domain',
						width : '100%',
						height : 160,
						margin : '40 0 0 0',
						bodyPadding : 10,
						items : [{
									xtype : 'textfield',
									anchor : '50%',
									itemId : '1',
									fieldLabel : '1:',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'textfield',
									anchor : '50%',
									itemId : '2',
									fieldLabel : '2:',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'textfield',
									anchor : '50%',
									itemId : '3',
									fieldLabel : '3:',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'button',
									text : 'Reset',
									anchor : '20%'
								}]

					}, {

						xtype : 'form',
						bodyPadding : 10,
						title : 'System Setting',
						width : '100%',
						height : 130,
						margin : '40 0 0 0',
						items : [{
									xtype : 'textfield',
									anchor : '50%',
									itemId : 'callback',
									fieldLabel : 'Callback',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'textfield',
									anchor : '50%',
									itemId : 'allowmultikey',
									fieldLabel : 'Allow Multi Key',
									labelAlign : 'left',
									labelWidth : 140,
									editable : true
								}, {
									xtype : 'button',
									anchor : '20%',
									text : 'Reset'
								}]

					}]
		});
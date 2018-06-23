// 树木识别信息查询
Ext.define('App.GoodsQueryWindow', {
	extend : 'Ext.window.Window',
	constructor : function(config) {
		config = config || {};
		Ext.apply(config, {
			title : '商品信息',
			width : 350,
			height : 250,
			bodyPadding : '10 5',
			layout : 'fit',
			modal : true,
			items : [ {
				xtype : 'form',
				fieldDefaults : {
					labelAlign : 'left',
					labelWidth : 90,
					anchor : '100%'
				},
				items : [ {
					xtype : 'textfield',
					name : 'goods_sn',
					fieldLabel : '商品编码'
				}, {
					xtype : 'textfield',
					name : 'name',
					fieldLabel : '名称'
				}, {
					xtype : 'datefield',
					name : 'add_time',
					fieldLabel : '录入时间'
				}, {
					xtype : 'textfield',
					name : 'attribute_author_value',
					fieldLabel : '作者'
				}, {
					xtype : 'textfield',
					fieldLabel : 'ISBN',
					name : 'attribute_ISBN_value'
				} ],
				buttons : [ '->', {
					text : '确定',
					iconCls : 'icon-accept',
					width : 80,
					handler : function() {
						this.up('window').close();
					}
				}, '->' ]
			} ]
		});
		App.GoodsQueryWindow.superclass.constructor.call(this, config);
	}
});

Ext.define('Forestry.app.goodsManage.GoodsQuery', {
	extend : 'Ext.grid.Panel',
	region : 'center',
	initComponent : function() {
		var me = this;
		Ext.define('ModelList', {
			extend : 'Ext.data.Model',
			idProperty : 'id',
			fields : [ {
				name : 'id',
				type : 'int'
			}, 'goods_sn','attribute_ISBN_value', 'name',  'attribute_author_value','attribute_authorbrief_value','goods_brief','keywords','attribute_pagenum_value','attribute_publishHouse_value',
			{
				name : 'attribute_publishtime_value',
				type : 'datetime',
				dateFormat : 'Y-m-d'
			},
			{
				name : 'add_time',
				type : 'datetime',
				dateFormat : 'Y-m-d'
			}]
		});


		var goodsquerystore = Ext.create('Ext.data.Store', {
			model : 'ModelList',
			// autoDestroy: true,
			autoLoad : true,
			remoteSort : true,
			pageSize : globalPageSize,
			proxy : {
				type : 'ajax',
				url : appBaseUri + '/sys/goods/getGoods',
				extraParams : me.extraParams || null,
				reader : {
					type : 'json',
					root : 'data',
					totalProperty : 'totalRecord',
					successProperty : "success"
				}
			},
			sorters : [ {
				property : 'id',
				direction : 'DESC'
			} ]
		});

		var columns = [ {
			text : "ID",
			dataIndex : 'id',
			width : '5%'
		}, {
			text : "商品编码",
			dataIndex : 'goods_sn',
			width : '10%'
		}, {
			text : "ISBN",
			dataIndex : 'attribute_ISBN_value',
			width : '10%',
			sortable : false
		},{
			text : "名称",
			dataIndex : 'name',
			width : '15%'
		}, {
			text : "作者",
			dataIndex : 'attribute_author_value',
			width : '5%'
		},{
			text : "作者简介",
			dataIndex : 'attribute_authorbrief_value',
			width : '5%'
		},{
			text : "商品简介",
			dataIndex : 'goods_brief',
			width : '5%'
		},{
			text : "关键字",
			dataIndex : 'keywords',
			width : '5%'
		},{
			text : "页码",
			dataIndex : 'attribute_pagenum_value',
			width : '5%'
		},{
			text : "出版社",
			dataIndex : 'attribute_publishHouse_value',
			width : '10%'
		},{
			text : "出版时间",
			dataIndex : 'attribute_publishtime_value',
			width : '10%'
		},{
			text : "导入时间",
			dataIndex : 'add_time',
			width : '15%'
		}];

		var ttoolbar = Ext.create('Ext.toolbar.Toolbar', {
			items : [ {
				xtype : 'textfield',
				id : 'goodsquery-goods_sn',
				name : 'goods_sn',
				fieldLabel : '商品编码',
				labelWidth : 60,
				width : '25%'
			}, {
				xtype : 'textfield',
				id : 'goodsquery-name',
				name : 'name',
				fieldLabel : '名称',
				labelWidth : 30,
				width : '20%'
			}, {
				xtype : 'button',
				text : '查询',
				iconCls : 'icon-search',
				disabled : !globalObject.haveActionMenu(me.cButtons, 'Query'),
				width : '10%',
				maxWidth : 60,
				handler : function(btn, eventObj) {
					var searchParams = {
						goods_sn : Ext.getCmp('goodsquery-goods_sn').getValue(),
						name : Ext.getCmp('goodsquery-name').getValue()
						};
					Ext.apply(goodsquerystore.proxy.extraParams, searchParams);
					goodsquerystore.reload();
				}
			}, {
				xtype : 'button',
				text : '重置',
				iconCls : 'icon-reset',
				width : '10%',
				maxWidth : 60,
				handler : function(btn, eventObj) {
					Ext.getCmp('goodsquery-goods_sn').setValue(null);
					Ext.getCmp('goodsquery-name').setValue(null);
				}
			} ]
		});

		Ext.apply(this, {
			store : goodsquerystore,
			columns : columns,
			tbar : ttoolbar,
			bbar : Ext.create('Ext.PagingToolbar', {
				store : goodsquerystore,
				displayInfo : true
			}),
			listeners : {
				itemdblclick : function(dataview, record, item, index, e) {
					var grid = this;
					var id = grid.getSelectionModel().getSelection()[0].get('id');
					var gridRecord = grid.getStore().findRecord('id', id);
					var win = new App.GoodsQueryWindow({
						hidden : true
					});
					var form = win.down('form').getForm();
					form.loadRecord(gridRecord);
					form.findField('goods_sn').setReadOnly(true);
					form.findField('name').setReadOnly(true);
					form.findField('add_time').setReadOnly(true);
					form.findField('attribute_author_value').setReadOnly(true);
					form.findField('attribute_ISBN_value').setReadOnly(true);
					win.show();
				}
			}
		});

		this.callParent(arguments);
	}
});